package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.util.Message;
import spark.*;

import java.sql.SQLOutput;
import java.util.Objects;
import java.util.logging.Logger;

public class PostCheckTurnRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private final TemplateEngine templateEngine;

    private Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public PostCheckTurnRoute(final TemplateEngine templateEngine, Gson gson) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gson = gson;
        //
        LOG.config("PostCheckTurnRoute is initialized.");
    }

    /**
     * Render the WebCheckers Home page.
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
        Message message = Message.info("false");
        if (WebServer.TURN_MADE) {
            message = Message.info("true");
            Spark.get(WebServer.GAME_URL, new GetGameRoute(templateEngine, gson));
        }
        WebServer.TURN_MADE = false;
        String jsonMsg = gson.toJson(message, Message.class);
        return jsonMsg;
    }
}

//    Message message = Message.info("false");
//        if(request.session().attribute("test") == null) {
//            request.session().attribute("test", false);
//        }
//        boolean test = request.session().attribute("test");
//        System.out.println(test);
//        if (test) {
//            message = Message.info("true");
//            Spark.get(WebServer.GAME_URL, new GetGameRoute(templateEngine));
//        }
////            WebServer.TEST = false;
//        request.session().attribute("test", false);
//        String jsonMsg = gson.toJson(message, Message.class);
//        return jsonMsg;
