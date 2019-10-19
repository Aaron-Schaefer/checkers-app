package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.webcheckers.model.Board;
import com.webcheckers.model.Move;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Position;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.net.URLDecoder;
import java.util.Objects;
import java.util.logging.Logger;

public class PostValidateMoveRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private final TemplateEngine templateEngine;

    private final Gson gson;

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
        final String moveJSON = URLDecoder.decode(request.body()).replace("{", "").replace("}", "");
        //Move move = gson.fromJson("{"+moveJSON+"}", Move.class);
        String[] moveString = moveJSON.split("[,:]");
        Position start = new Position(Integer.parseInt(moveString[2]), Integer.parseInt(moveString[4]));
        Position end = new Position(Integer.parseInt(moveString[7]), Integer.parseInt(moveString[9]));
        Move move = new Move(start, end);
        Board model = request.session().attribute("board");
        Piece piece = model.getSpace(start.getRow(), start.getCell()).getPiece();
        model.removePiece(start.getRow(), start.getCell());
        model.addPiece(end.getRow(), end.getCell(), piece);
        request.session().attribute("board", model);
        return moveJSON;
    }
}
