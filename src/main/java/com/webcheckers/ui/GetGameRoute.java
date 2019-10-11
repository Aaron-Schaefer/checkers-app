package com.webcheckers.ui;

import com.webcheckers.model.Board;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GetGameRoute implements Route {

    private final TemplateEngine templateEngine;
    //private final PlayerLobby playerLobby;

    GetGameRoute(final TemplateEngine templateEngine) {
        Objects.requireNonNull(templateEngine, "templateEngine is required");
        //Objects.requireNonNull(playerLobby, "Players are required to play");
        //
        this.templateEngine = templateEngine;
        //this.playerLobby = playerLobby;
    }

    @Override
    public Object handle(Request request, Response response){
        Map<String, Object> vm = new HashMap<>();
        Session session = request.session();
        Player player = session.attribute("currentPlayer");

        if(WebServer.PLAYER_LOBBY.getRedPlayer() == null){
            String name = request.queryParams("playerName");
            WebServer.PLAYER_LOBBY.addToGame(player);
            Player whitePlayer = WebServer.PLAYER_LOBBY.getPlayer(name);
            WebServer.PLAYER_LOBBY.addToGame(whitePlayer);
        }

        vm.put("title", "Time to play!");
        vm.put("viewMode", "PLAY");
        vm.put("currentUser", player);
        vm.put("redPlayer", WebServer.PLAYER_LOBBY.getRedPlayer());
        vm.put("whitePlayer", WebServer.PLAYER_LOBBY.getWhitePlayer());
        vm.put("activeColor", Piece.Color.RED);

        Board model = new Board(WebServer.PLAYER_LOBBY.getWhitePlayer(), WebServer.PLAYER_LOBBY.getRedPlayer());
        BoardView boardView = new BoardView(model, player);



        vm.put("board", boardView);


        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }
}
