package com.webcheckers.ui;

/**
 * Language: Java
 * @Author: An Chang (Mark), Gavin Burris.
 * Purpose: A class to post a sign in route..
 */
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class PostSignInRoute implements Route {

    //The log for PostSingInRoute.
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

    //Template engine for spark.
    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) to handle all {@code POST /signin} HTTP requests.
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
     * Post things to the WebCheckers SignIn page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the SignIn page
     */
    public Object handle(Request request, Response response) {
        LOG.finer("PostSignInRoute is invoked.");

        //Hash map for the view model.
        Map<String, Object> vm = new HashMap<>();

        //Error messages, one for no input, other for redundant player.
        Message emptyUsername = Message.info("Error! You must type in a username with at least one character!");
        Message nameTaken = Message.info("Error! The username is already taken, try a different username!");

        //Get current session.
        Session session = request.session();

        //Query for the playerName.
        System.out.println(request.queryParams().contains("playerName"));
        String name = request.queryParams("playerName").trim();

        //If the playername is empty, print error message and have them try again.
        if (name.equals("")){
            vm.put("message", emptyUsername);
            return templateEngine.render(new ModelAndView(vm , "signin.ftl"));
        }

        //Create the new player object.
        Player player = new Player(name);

        //Attributing the current session's player to the newly created player object.
        session.attribute("currentPlayer", player);

        //Get if the player add oepration is successful.
        boolean success = WebServer.PLAYER_LOBBY.addPlayer(player);
        if (!success){
            vm.put("message", nameTaken);
            return templateEngine.render(new ModelAndView(vm , "signin.ftl"));
        }

        //Redirect to the home page.
        response.redirect("/");
        return "";
    }


}
