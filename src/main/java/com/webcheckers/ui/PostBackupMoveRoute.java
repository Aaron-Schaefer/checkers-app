package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * Language: Java
 * @Author: Gavin Burris.
 * Purpose: The UI Controller to POST the information of the backup Move.
 */
public class PostBackupMoveRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private final TemplateEngine templateEngine;

    private Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /backupMove} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public PostBackupMoveRoute(final TemplateEngine templateEngine, Gson gson) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gson = gson;
        //
        LOG.config("PostBackupMoveRoute is initialized.");
    }

    /**
     * Post information from the Game page about the BackUp Move
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   The JSON for the information about the BackUp Move
     */
    @Override
    public Object handle(Request request, Response response) {

        //Returns the Player to the Validate Move Route, and returns a JSON message.
        Message message = Message.info("true");
        Spark.post(WebServer.VALIDATE_MOVE_URL, new PostValidateMoveRoute(templateEngine, gson));
        String jsonMsg = gson.toJson(message, Message.class);
        return jsonMsg;
    }
}
