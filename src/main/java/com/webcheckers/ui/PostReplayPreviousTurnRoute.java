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

public class PostReplayPreviousTurnRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostReplayPreviousTurnRoute.class.getName());

    private final TemplateEngine templateEngine;

    private Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
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
     * Render the WebCheckers SignIn page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Home page
     */
    public Object handle(Request request, Response response) {

        Session session = request.session();

        Game game = session.attribute("replayGame");

        Board board = session.attribute("replayBoard");

        int numMove = session.attribute("numMove");

        Move move = game.getMove(numMove -1);

        if(move.getTakenPosition() != null){
            Position position = move.getTakenPosition();
            board.addPiece(position.getRow(), position.getCell(), move.getTakenPiece());
        }

        board.undoMove(move);

        Message message = Message.info("false");

        if(numMove > 0) {
            session.attribute("numMove", numMove - 1);
            message = Message.info("true");
        }

        return gson.toJson(message, Message.class);
    }
}
