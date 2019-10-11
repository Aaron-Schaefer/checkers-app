package com.webcheckers.ui;

import com.webcheckers.model.Board;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GetGameRoute implements Route {

    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /game} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    GetGameRoute(final TemplateEngine templateEngine) {
        Objects.requireNonNull(templateEngine, "templateEngine is required");
        //
        this.templateEngine = templateEngine;
    }

    /**
     * Render the WebCheckers Game page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Game page
     */
    @Override
    public Object handle(Request request, Response response){
        //Hash map for the view model.
        Map<String, Object> vm = new HashMap<>();

        //Get current session, and get the current Player from the session.
        Session session = request.session();
        Player currentPlayer = session.attribute("currentPlayer");
        Player redPlayer = WebServer.PLAYER_LOBBY.getRedPlayer();
        Player whitePlayer = WebServer.PLAYER_LOBBY.getWhitePlayer();

        if(WebServer.PLAYER_LOBBY.getRedPlayer() == null){
            redPlayer = currentPlayer;
            WebServer.PLAYER_LOBBY.addToGame(redPlayer);
            String name = request.queryParams("playerName");
            whitePlayer = WebServer.PLAYER_LOBBY.getPlayer(name);
            WebServer.PLAYER_LOBBY.addToGame(whitePlayer);
        }
        else if(currentPlayer != redPlayer && currentPlayer != whitePlayer){
            WebServer.PLAYER_LOBBY.playerChoseInGame();
            response.redirect("/");
        }
        else{
            WebServer.PLAYER_LOBBY.notChoseInGame();
        }

        vm.put("title", "Time to play!");
        vm.put("viewMode", "PLAY");
        vm.put("currentUser", currentPlayer);
        vm.put("redPlayer", redPlayer);
        vm.put("whitePlayer", whitePlayer);
        vm.put("activeColor", Piece.Color.RED);

        Board model = new Board(WebServer.PLAYER_LOBBY.getWhitePlayer(), WebServer.PLAYER_LOBBY.getRedPlayer());
        BoardView boardView = new BoardView(model, currentPlayer);

        vm.put("board", boardView);

        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }
}
