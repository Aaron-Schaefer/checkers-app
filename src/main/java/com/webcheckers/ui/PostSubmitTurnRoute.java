package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
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

        //Gets the current Player from the session.
        Player currentPlayer = request.session().attribute("currentPlayer");

        //Gets the GameCenter from the WebServer, the Game from the GameCenter, and
        //the Board from the Game.
        GameCenter gameCenter = WebServer.GAME_CENTER;
        Game game = gameCenter.getGame(currentPlayer);
        Board board = game.getBoard();

        //Initializes the message and converts it into a JSON.
        Message message = Message.info("true");
        String jsonMsg = gson.toJson(message, Message.class);

        //Refreshes the Game page.
        Spark.get(WebServer.GAME_URL, new GetGameRoute(templateEngine, gson));

        //Sets the Move to the Games most recent Move.
        Move move = game.getRecentMove();

        //Does the turn with the given Move and sets the Player to validate the next Move, if
        //the Move's state is VALIDJUMP.
        if (move.getValidState() == MoveValidator.MoveValidation.VALIDJUMP) {
            game.doTurn(move);
            Spark.post(WebServer.VALIDATE_MOVE_URL, new PostValidateMoveRoute(templateEngine, gson));
        }
        //Does the turn with the given Move and then changes the Board's active color.
        else if(move.getValidState() == MoveValidator.MoveValidation.VALID) {
            game.doTurn(move);
            board.changeActiveColor();
            //If opponent is the AI player
            if (game.getWhitePlayer().getName().equals("CPU") && board.getActiveColor() == Piece.Color.WHITE && game.getWinner() == null) {
                boolean aiTurn = true;
                while (aiTurn) {
                    aiTurn = false;
                    Move aiMove = AI.decideMove(game);
                    //If no move is available then the AI loses
                    if (aiMove != null) {
                        game.setRecentMove(aiMove);
                        game.doTurn(aiMove);
                        //if a multi jump is available redirect to the AIs turn
                        if (((aiMove.getStart().getRow() + aiMove.getEnd().getRow()) % 2) == 0 && AI.jumpAvailable(game, aiMove.getEnd())) {
                            aiTurn = true;
                        }
                    }
                    else {
                        game.setWinner(game.getRedPlayer());
                    }
                }
                board.changeActiveColor(); //switch back to the players turn
            }
        }
        return jsonMsg;
    }
}
