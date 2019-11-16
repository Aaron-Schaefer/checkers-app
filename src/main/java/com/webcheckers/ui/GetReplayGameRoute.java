package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Board;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class GetReplayGameRoute implements Route {
    //The LOG for GetSignInRoute.
    private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());

    //The template engine for spark.
    private final TemplateEngine templateEngine;

    private Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /signin} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetReplayGameRoute(final TemplateEngine templateEngine, Gson gson) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gson = gson;
        //
        LOG.config("GetSignInRoute is initialized.");
    }

    /**
     * Render the WebCheckers SignIn page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Home page
     */
    @Override
    public Object handle(Request request, Response response) {

        Session session = request.session();

        //Hash map for the view model.
        Map<String, Object> vm = new HashMap<>();

        GameCenter gameCenter = WebServer.GAME_CENTER;
        if(session.attribute("replayGame") == null){
            String gameID = request.queryParams("game");
            Game game = gameCenter.getGame(Integer.parseInt(gameID));
            session.attribute("replayGame", game);
        }

        Game game = session.attribute("replayGame");

        Player currentPlayer = session.attribute("currentPlayer");
        Player redPlayer = game.getRedPlayer();
        Player whitePlayer = game.getWhitePlayer();

        if(session.attribute("replayBoard") == null){
            Board board = new Board(game.getRedPlayer(), game.getWhitePlayer());
            session.attribute("replayBoard", board);
        }

        Board board = session.attribute("replayBoard");

        BoardView boardView = new BoardView(board, redPlayer);

        if(session.attribute("numMove") == null){
            int numMove = 0;
            session.attribute("numMove", numMove);
        }
        int numMove = session.attribute("numMove");

        boolean hasPrevious = false;
        boolean hasNext = false;
        if(numMove > 0){
            hasPrevious = true;
        }
        if(numMove < game.getNumMoves()) {
            hasNext = true;
            System.out.println("hasNext: " + true);
        }

        final Map<String, Object> modeOptions = new HashMap<>(2);
        modeOptions.put("hasPrevious", hasPrevious);
        modeOptions.put("hasNext", hasNext);

        vm.put("title", "Replay the game!");
        vm.put("gameID", game.getGameID());
        vm.put("viewMode", "REPLAY");
        vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
        vm.put("currentUser", currentPlayer);
        vm.put("redPlayer", redPlayer);
        vm.put("whitePlayer", whitePlayer);
        vm.put("activeColor", board.getActiveColor());
        vm.put("board", boardView);
        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }
}
