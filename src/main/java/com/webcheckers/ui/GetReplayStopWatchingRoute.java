package com.webcheckers.ui;

import com.google.gson.Gson;
import spark.*;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * Language: Java
 * @Author: Gavin Burris.
 * Purpose: The UI controller to return the Player back to the Home Page
 * from the Replay Game Page.
 */
public class GetReplayStopWatchingRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetReplayStopWatchingRoute.class.getName());

    private final TemplateEngine templateEngine;

    private final Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /replay/stopWatching} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetReplayStopWatchingRoute(final TemplateEngine templateEngine, Gson gson) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gson = gson;
        //
        LOG.config("GetReplayStopWatchingRoute is initialized.");
    }


    /**
     * Render the WebCheckers ReplayStopWatchingRoute page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   null
     */
    public Object handle(Request request, Response response) {

        //Sets everything relating to the replay Game in the session to null.
        //Also redirects to the Player to the home page.
        Session session = request.session();
        session.attribute("replayGame", null);
        session.attribute("replayBoard", null);
        session.attribute("numMove", null);
        response.redirect("/");
        return null;
    }
}
