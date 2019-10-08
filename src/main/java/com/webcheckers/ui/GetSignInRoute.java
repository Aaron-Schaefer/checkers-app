package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import com.webcheckers.util.Message;
public class GetSignInRoute {
    private final TemplateEngine templateEngine;
    private boolean IsSignedIn;
    public Object handle(Request request, Response response){
        final Map<String,Object> vm = new HashMap<>();
        vm.put("pageTitle", "Home");
        return templateEngine.render(new ModelAndView(vm,"home.ftl"));

    }

}
