package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.Board;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Move;
import com.webcheckers.model.Piece;
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

//    private static JsonObject object = null;
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
        Message message = Message.info("true");
        String jsonMsg = gson.toJson(message, Message.class);
        Piece piece = WebServer.BOARD.getSpace(move.getStart().getRow(), move.getStart().getCell()).getPiece();
        WebServer.BOARD.removePiece(move.getStart().getRow(), move.getStart().getCell());
        WebServer.BOARD.addPiece(move.getEnd().getRow(), move.getEnd().getCell(), piece);
        if(piece.getColor() == Piece.Color.RED){
//            request.session().attribute("currentColor", Piece.Color.WHITE);
        }
        else {
//            request.session().attribute("currentColor", Piece.Color.RED);
        }
        return jsonMsg;
    }
}
