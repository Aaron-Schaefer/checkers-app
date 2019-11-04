package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import spark.*;

import java.util.Objects;
import java.util.logging.Logger;

import static spark.route.HttpMethod.get;
import static spark.route.HttpMethod.post;

public class PostSubmitTurnRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private final TemplateEngine templateEngine;

    private Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public PostSubmitTurnRoute(final TemplateEngine templateEngine, Gson gson) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gson = gson;
        //
        LOG.config("PostSubmitTurnRoute is initialized.");
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
        WebServer.TURN_MADE = true;
//        request.session().attribute("test", true);
        Message message = Message.info("true");
        String jsonMsg = gson.toJson(message, Message.class);
        Spark.get(WebServer.GAME_URL, new GetGameRoute(templateEngine, gson));
        Move move = WebServer.RECENT_MOVE;
        if(move.getValidState() == MoveValidator.MoveValidation.JUMPNEEDED
                || move.getValidState() == MoveValidator.MoveValidation.OCCUPIED
                || move.getValidState() == MoveValidator.MoveValidation.TOOFAR){

        }
        else {
            Piece piece = WebServer.BOARD.getSpace(move.getStart().getRow(), move.getStart().getCell()).getPiece();
            WebServer.BOARD.removePiece(move.getStart().getRow(), move.getStart().getCell());
            WebServer.BOARD.addPiece(move.getEnd().getRow(), move.getEnd().getCell(), piece);
            if (move.getValidState() == MoveValidator.MoveValidation.VALIDJUMP) {
                System.out.println("reached jump");
                Spark.post(WebServer.VALIDATE_MOVE_URL, new PostValidateMoveRoute(templateEngine, gson));
            }
            else if (move.getValidState() == MoveValidator.MoveValidation.VALID) {
                System.out.println("changed");
                WebServer.BOARD.changeActiveColor();
            }
            for(Position position : WebServer.BOARD.getPositionsTaken()){
                WebServer.BOARD.removePiece(position.getRow(), position.getCell());
            }
            WebServer.BOARD.clearPositionsTaken();
        }
        return jsonMsg;
    }
}
