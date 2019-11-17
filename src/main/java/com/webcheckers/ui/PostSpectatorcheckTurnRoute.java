package com.webcheckers.ui;

import java.util.logging.Logger;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.*;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The route that will update the game page for a spectator to get the most recent moves
 */
public class PostSpectatorcheckTurnRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSpectatorcheckTurnRoute.class.getName());
    private final TemplateEngine templateEngine;
    private Gson gson;

    /**
     * Creates the Spark Route to handle the POST/Spectator/checkTurn requests
     * @param templateEngine
     * @param gson
     */
    PostSpectatorcheckTurnRoute(final TemplateEngine templateEngine, Gson gson){
        Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.templateEngine = templateEngine;
        this.gson = gson;
    }

    /**
     * Renders the game page for the spectator after a turn has been made (refresh)
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        return null;
    }
}
