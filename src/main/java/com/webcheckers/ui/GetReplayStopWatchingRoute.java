package com.webcheckers.ui;

import com.google.gson.Gson;
import spark.*;

import java.util.Objects;
import java.util.logging.Logger;

public class GetReplayStopWatchingRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetReplayStopWatchingRoute.class.getName());

    private final TemplateEngine templateEngine;

    private final Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
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
     *   the rendered HTML for the Home page
     */
    public Object handle(Request request, Response response) {
        Session session = request.session();
        session.attribute("replayGame", null);
        session.attribute("replayBoard", null);
        session.attribute("numMove", null);
        response.redirect("/");
        return null;
    }
}
