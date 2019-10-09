package com.webcheckers.ui;

import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class PostSignInRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

    private static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");

    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public PostSignInRoute(final TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        //
        LOG.config("PostSignInRoute is initialized.");
    }


    /**
     * Render the WebCheckers SignIn page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Home page
     */
    public Object handle(Request request, Response response) {
        LOG.finer("PostSignInRoute is invoked.");
        //
        Map<String, Object> vm = new HashMap<>();

        Message error = Message.info("Error!");
        Session session = request.session();
        String name = request.queryParams("playerName");
        if (name.equals("")){
            session.invalidate();
            vm.put("message", error);
            return templateEngine.render(new ModelAndView(vm , "signin.ftl"));
        }

        Player player = new Player(name);
        session.attribute("currentPlayer", player);
        boolean success = WebServer.PLAYER_LOBBY.addPlayer(player);
        System.out.println(success);
        if (!success){
            session.invalidate();
            vm.put("message", error);
            return templateEngine.render(new ModelAndView(vm , "signin.ftl"));
        }

        response.redirect("/");
        System.out.println(WebServer.PLAYER_LOBBY.listPlayers());
        return "";
    }


}
