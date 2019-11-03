package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import org.eclipse.jetty.client.HttpResponse;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.Objects;
import java.util.logging.Logger;

public class PostValidateMoveRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private final TemplateEngine templateEngine;

    private Gson gson;

    private static final String VALID_MOVE = "This is a valid move.";
    private static final String OCCUPIED_SPACE = "Invalid move! This space is already occupied.";
    private static final String MOVED_TOO_FAR = "Invalid move! You have moved too many spaces.";
    private static final String NOT_DIAGONAL = "Invalid move! Your move was not diagonal.";


    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     * @param gson
     */
    public PostValidateMoveRoute(final TemplateEngine templateEngine, Gson gson) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gson = gson;
        //
        LOG.config("PostValidateMoveRoute is initialized.");
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
        final String moveJSON = request.queryParams("actionData");
        Move move = gson.fromJson(moveJSON, Move.class);
        WebServer.RECENT_MOVE = move;
        if(move.getStart().getCell() == 0){
            move.setValidState(MoveValidator.MoveValidation.JUMPNEEDED);
        }
        Message message = Message.info("true");
        String jsonMsg = gson.toJson(message, Message.class);
        return jsonMsg;
    }
}
