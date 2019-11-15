package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
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

        Session session = request.session();

        String gameID = request.queryParams("game");
        System.out.println(gameID);

        GameCenter gameCenter = WebServer.GAME_CENTER;
        Game game = gameCenter.getGame(Integer.parseInt(gameID));

        Board board = session.attribute("replayBoard");

        if(session.attribute("numMove") == null){
            int numMove = 0;
            session.attribute("numMove", numMove);
        }

        int numMove = session.attribute("numMove");

        Move move = game.getMove(numMove);

        board.updateBoard(move);

        if(numMove < game.getNumMoves()) {
            session.attribute("numMove", numMove + 1);
        }

        Message message = Message.info("true");
        String jsonMsg = gson.toJson(message, Message.class);
        return jsonMsg;
    }
}
