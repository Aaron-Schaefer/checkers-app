package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Board;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Language: Java
 * @Author: Gavin Burris.
 * Purpose: The UI controller to get the Replay Game Page
 */
public class GetReplayGameRoute implements Route {
    //The LOG for GetSignInRoute.
    private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());

    //The template engine for spark.
    private final TemplateEngine templateEngine;

    private Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /replay/game} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetReplayGameRoute(final TemplateEngine templateEngine, Gson gson) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gson = gson;
        //
        LOG.config("GetReplayGameRoute is initialized.");
    }

    /**
     * Render the WebCheckers Replay Game page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the ReplayGame page
     */
    @Override
    public Object handle(Request request, Response response) {

        Session session = request.session();

        //Hash map for the view model.
        Map<String, Object> vm = new HashMap<>();

        //The GameCenter from the WebServer.
        GameCenter gameCenter = WebServer.GAME_CENTER;

        //Adds the replayed Game to the session if it isn't already in the session.
        //The Game is retrieved using its GameID.
        if(session.attribute("replayGame") == null){
            String gameID = request.queryParams("game");
            Game game = gameCenter.getGame(Integer.parseInt(gameID));
            session.attribute("replayGame", game);
        }

        //The Game retrieved from the session.
        Game game = session.attribute("replayGame");

        //The Players used to render the Game page.
        Player currentPlayer = session.attribute("currentPlayer");
        Player redPlayer = game.getRedPlayer();
        Player whitePlayer = game.getWhitePlayer();

        //Adds the replayed Board to the session if it isn't already in the session. The Board
        //is a new Board with the red and white Players. The Board is then validated.
        if(session.attribute("replayBoard") == null){
            Board board = new Board(game.getRedPlayer(), game.getWhitePlayer());
            board.allValidSpaces();
            session.attribute("replayBoard", board);
        }

        //The Board retrieved from the session.
        Board board = session.attribute("replayBoard");

        //Creates the BoardView.
        BoardView boardView = new BoardView(board, redPlayer);

        //Adds the Move number to the session if it isn't already in the session. It also
        //sets the Move number to zero.
        if(session.attribute("numMove") == null){
            int numMove = 0;
            session.attribute("numMove", numMove);
        }

        //The Move number retrieved from the session.
        int numMove = session.attribute("numMove");

        //Sets if there is a previous move and if there is a next move.
        boolean hasPrevious = false;
        boolean hasNext = false;
        if(numMove > 0){
            hasPrevious = true;
        }
        if(numMove < game.getNumMoves()) {
            hasNext = true;
        }

        //Adds the boolean values of previous and next move to a Map.
        final Map<String, Object> modeOptions = new HashMap<>(2);
        modeOptions.put("hasPrevious", hasPrevious);
        modeOptions.put("hasNext", hasNext);

        //Uses view model to put to the variables to the game.ftl file.
        vm.put("title", "Replay the game!");
        vm.put("gameID", game.getGameID());
        vm.put("viewMode", "REPLAY");
        vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
        vm.put("currentUser", currentPlayer);
        vm.put("redPlayer", redPlayer);
        vm.put("whitePlayer", whitePlayer);
        vm.put("activeColor", board.getActiveColor());
        vm.put("board", boardView);

        //Renders the view.
        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }
}
