package com.webcheckers.ui;

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

        vm.put("viewMode", "PLAY");
        //Will need to check the current player turn (Options: boolean field for a player -OR- increment turns and set
        //a players turn based on even or odd

        vm.put("activeColor", "RED");

        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }
}
