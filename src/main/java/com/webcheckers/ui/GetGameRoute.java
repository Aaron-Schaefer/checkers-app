package com.webcheckers.ui;

import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GetGameRoute implements Route {

    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;

    GetGameRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby) {
        Objects.requireNonNull(templateEngine, "templateEngine is required");
        Objects.requireNonNull(playerLobby, "Players are required to play");
        //
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
    }

    @Override
    public Object handle(Request request, Response response){
        Map<String, Object> vm = new HashMap<>();

        vm.put("title", "Time to play!");
        vm.put("viewMode", "PLAY");
        vm.put("currentUser", "test");
        vm.put("redPlayer", "redTest");
        vm.put("whitePlayer", "whiteTest");
        vm.put("activeColor", "activeColTest");



        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }
}
