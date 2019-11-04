package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.Move;
import com.webcheckers.model.Piece;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.Objects;
import java.util.logging.Logger;

public class PostBackupMoveRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private final TemplateEngine templateEngine;

    private Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
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
        Message message = Message.info("true");
        Move move = WebServer.RECENT_MOVE;
        Move newMove = new Move(move.getEnd(), move.getStart());
        Piece piece = WebServer.BOARD.getSpace(newMove.getStart().getRow(), newMove.getStart().getCell()).getPiece();
        WebServer.BOARD.removePiece(newMove.getStart().getRow(), newMove.getStart().getCell());
        WebServer.BOARD.addPiece(newMove.getEnd().getRow(), newMove.getEnd().getCell(), piece);
        String jsonMsg = gson.toJson(message, Message.class);
        return jsonMsg;
    }
}
