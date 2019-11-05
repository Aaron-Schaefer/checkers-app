package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
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
        GameCenter gameCenter = WebServer.GAME_CENTER;
        Player currentPlayer = request.session().attribute("currentPlayer");
        Game game = gameCenter.getGame(currentPlayer);
        if(WebServer.RESIGN_CHECK){
            Spark.get(WebServer.GAME_URL, new GetGameRoute(templateEngine, gson));
        }
        Message message = Message.info("false");
        if (game.isTurnMade()) {
            message = Message.info("true");
            Spark.get(WebServer.GAME_URL, new GetGameRoute(templateEngine, gson));
        }
        game.setTurnMade(false);
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
