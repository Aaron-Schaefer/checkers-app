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
        final Map<String, Object> vm = new HashMap<>();

        return templateEngine.render(new ModelAndView(vm , "home.ftl"));
    }
}
