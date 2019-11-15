package com.webcheckers.ui;

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

public class GetReplayGameRoute implements Route {
    //The LOG for GetSignInRoute.
    private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());

    //The template engine for spark.
    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /signin} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetReplayGameRoute(final TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
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

        //Hash map for the view model.
        Map<String, Object> vm = new HashMap<>();
        String gameID = request.queryParams("game");
        System.out.println(gameID);

        GameCenter gameCenter = WebServer.GAME_CENTER;
        Game game = gameCenter.getGame(Integer.parseInt(gameID));

        Player currentPlayer = request.session().attribute("currentPlayer");
        Player redPlayer = game.getRedPlayer();
        Player whitePlayer = game.getWhitePlayer();

        Board board = game.getBoard();
        BoardView boardView = new BoardView(board, redPlayer);


        vm.put("title", "Replay the game!");
        vm.put("gameID", game.getGameID());
        vm.put("viewMode", "REPLAY");
        vm.put("currentUser", currentPlayer);
        vm.put("redPlayer", redPlayer);
        vm.put("whitePlayer", whitePlayer);
        vm.put("activeColor", board.getActiveColor());
        vm.put("board", boardView);
        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }
}
