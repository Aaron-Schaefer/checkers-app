package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import org.eclipse.jetty.client.HttpResponse;
import spark.*;

import java.util.Objects;
import java.util.logging.Logger;

public class PostValidateMoveRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private final TemplateEngine templateEngine;

    private Gson gson;

    private static final String VALID_MOVE = "This is a valid move.";
    private static final String JUMP_MOVE = "This is a valid move. Another Jump move is required";
    private static final String OCCUPIED_SPACE = "Invalid move! This space is already occupied.";
    private static final String MOVED_TOO_FAR = "Invalid move! You have moved too many spaces.";
    private static final String VERTICAL_MOVE = "Invalid move! Your move was vertical.";
    private static final String HORIZONTAL_MOVE = "Invalid move! Your move was horizontal.";


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
        Message message = Message.info("true");;
        switch (MoveValidator.validateMove(WebServer.BOARD, move)){
            case VALID:
                move.setValidState(MoveValidator.MoveValidation.VALID);
                message = Message.info(VALID_MOVE);
            case JUMPNEEDED:
                move.setValidState(MoveValidator.MoveValidation.JUMPNEEDED);
                message = Message.info(JUMP_MOVE);
            case OCCUPIED:
                move.setValidState(MoveValidator.MoveValidation.OCCUPIED);
                message = Message.info(OCCUPIED_SPACE);
                Spark.post(WebServer.VALIDATE_MOVE_URL, new PostValidateMoveRoute(templateEngine, gson));
            case TOOFAR:
                move.setValidState(MoveValidator.MoveValidation.TOOFAR);
                message = Message.info(MOVED_TOO_FAR);
                Spark.post(WebServer.VALIDATE_MOVE_URL, new PostValidateMoveRoute(templateEngine, gson));
        }
        WebServer.RECENT_MOVE = move;
        String jsonMsg = gson.toJson(message, Message.class);
        return jsonMsg;
    }
}
