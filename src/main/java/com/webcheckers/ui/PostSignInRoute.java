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

    //Error messages, for no input, redundant player, and for AI name.
    private static final Message EMPTY_USERNAME = Message.info("Error! You must type in a username with at least one character!");
    private static final Message NAME_TAKEN = Message.info("Error! The username is already taken, try a different username!");
    private static final Message NAME_CPU = Message.info("Error! The username CPU is used for the AI mode, try a different username!");

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



        //Get current session.
        Session session = request.session();

        //Query for the playerName.
        String name = request.queryParams("playerName").trim();

        //If the playername is empty, print error message and have them try again.
        if (name.equals("")){
            vm.put("message", EMPTY_USERNAME);
            return templateEngine.render(new ModelAndView(vm , "signin.ftl"));
        }

        //Create the new player object.
        Player player = new Player(name);

        //Attributing the current session's player to the newly created player object.
        session.attribute("currentPlayer", player);

        //Get if the player add oepration is successful.
        boolean success = WebServer.PLAYER_LOBBY.addUser(player);
        if (!success){
            vm.put("message", NAME_TAKEN);
            return templateEngine.render(new ModelAndView(vm , "signin.ftl"));
        }

        //If the user enters in the username of the AI.
        if(name.equals("CPU")){
            vm.put("message", NAME_CPU);
            return templateEngine.render(new ModelAndView(vm , "signin.ftl"));
        }

        //Redirect to the home page.
        response.redirect("/");
        return "";
    }


}
