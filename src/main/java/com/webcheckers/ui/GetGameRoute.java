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
        System.out.println(player.getName());

        String name = request.queryParams("playerName");
        System.out.println(name);
        Player whitePlayer = WebServer.PLAYER_LOBBY.getPlayer(name);
       // System.out.println(whitePlayer);


        vm.put("title", "Time to play!");
        vm.put("viewMode", "PLAY");
        vm.put("currentUser", player);
        vm.put("redPlayer", player);
        vm.put("whitePlayer", whitePlayer);
        vm.put("activeColor", "activeColTest");



        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }
}
