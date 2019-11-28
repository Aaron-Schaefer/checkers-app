package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Position;
import com.webcheckers.util.Message;
import spark.*;

import java.util.Objects;
import java.util.logging.Logger;

public class PostReplayNextTurnRoute implements Route {

    //The log for PostSingInRoute.
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

    //Template engine for spark.
    private final TemplateEngine templateEngine;

    private Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle all {@code POST /signin} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public PostReplayNextTurnRoute(final TemplateEngine templateEngine, Gson gson) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gson = gson;
        //
        LOG.config("PostReplayNextTurnRoute is initialized.");
    }


    /**
     * Post things to the WebCheckers ReplayNextTurnRoute page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the SignIn page
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

        //The current move retrieved from the Move number.
        Move move = game.getMove(numMove);

        //If a piece was taken during the current Move then remove the taken
        //Piece from the Board.
        if(move.getTakenPosition() != null){
            Position position = move.getTakenPosition();
            board.removePiece(position.getRow(), position.getCell());
        }

        //Update the replay Board.
        board.updateBoard(move);

        //Initializes the message to false.
        Message message = Message.info("false");

        //If there is a next Move then the numMove is incremented, and the
        // message is set to true.
        if(numMove < game.getNumMoves()) {
            session.attribute("numMove", numMove + 1);
            message = Message.info("true");
        }

        return gson.toJson(message, Message.class);
    }
}
