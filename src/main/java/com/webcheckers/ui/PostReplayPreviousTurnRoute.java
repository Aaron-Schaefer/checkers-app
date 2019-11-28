package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Position;
import com.webcheckers.util.Message;
import spark.*;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * Language: Java
 * @Author: Gavin Burris.
 * Purpose: The UI Controller to POST the information of the previous Move
 * made in the replayed Game.
 */
public class PostReplayPreviousTurnRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostReplayPreviousTurnRoute.class.getName());

    private final TemplateEngine templateEngine;

    private Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /replay/previousTurn} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public PostReplayPreviousTurnRoute(final TemplateEngine templateEngine, Gson gson) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gson = gson;
        //
        LOG.config("PostReplayPreviousTurnRoute is initialized.");
    }


    /**
     * Posts information from the Replay Game page about the previous
     * turn of the Replay Game.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   The JSON for the information about the previous turn of the
     *   Replay Game
     */
    public Object handle(Request request, Response response) {

        //The request's session.
        Session session = request.session();

        //The replayed Game from the session.
        Game game = session.attribute("replayGame");

        //The replayed Board from the session.
        Board board = session.attribute("replayBoard");

        //The current number move from the session.
        int numMove = session.attribute("numMove");

        //The previous move retrieved from the Move number.
        Move move = game.getMove(numMove -1);

        //If a piece was taken during the previous Move then add the taken
        //Piece back to the Board.
        if(move.getTakenPosition() != null){
            Position position = move.getTakenPosition();
            board.addPiece(position.getRow(), position.getCell(), move.getTakenPiece());
        }

        //Undoes the Move to the Board.
        board.undoMove(move);

        //Initializes the message to false.
        Message message = Message.info("false");

        //If there is a previous Move then the numMove is decremented, and the
        //message is set to true.
        if(numMove > 0) {
            session.attribute("numMove", numMove - 1);
            message = Message.info("true");
        }

        return gson.toJson(message, Message.class);
    }
}
