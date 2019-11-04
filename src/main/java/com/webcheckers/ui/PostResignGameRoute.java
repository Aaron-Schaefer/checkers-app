package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import freemarker.template.Template;
import spark.*;
import spark.Request;
import spark.Response;
import spark.Route;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Language: Java
 * Author: Ries Scerbin
 * A class to get resign route
 */
public class PostResignGameRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostResignGameRoute.class.getName());

    private final TemplateEngine templateEngine;

    private Gson gson;


    public PostResignGameRoute(final TemplateEngine templateEngine, Gson gson){

        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        LOG.config("PostResignGameRoute is initialized.");
        this.gson = gson;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("PostSignInRoute is invoked.");

        Map<String, Object> vm = new HashMap<>();

        Message gameover = Message.error("Error! Game is already over you cannot Resign");
        Session session = request.session();


        WebServer.resign_check = true;
        Message message = Message.info("Resign");
        String jsonMsg = gson.toJson(message, Message.class);

        return jsonMsg;
    }



}
