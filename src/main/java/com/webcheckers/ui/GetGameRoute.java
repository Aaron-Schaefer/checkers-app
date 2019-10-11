package com.webcheckers.ui;

import com.webcheckers.model.Board;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GetGameRoute implements Route {

    private final TemplateEngine templateEngine;

    GetGameRoute(final TemplateEngine templateEngine) {
        Objects.requireNonNull(templateEngine, "templateEngine is required");
        //
        this.templateEngine = templateEngine;
    }

    @Override
    public Object handle(Request request, Response response){
        Map<String, Object> vm = new HashMap<>();
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
