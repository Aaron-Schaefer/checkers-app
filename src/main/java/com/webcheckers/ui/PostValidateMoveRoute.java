package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
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
    private static final String VALID_JUMP_MOVE = "This is a valid move. You must submit the move then jump again";
    private static final String JUMP_MOVE = "Invalid move! A jump move is required";
    private static final String OCCUPIED_SPACE = "Invalid move! This space is already occupied.";
    private static final String MOVED_TOO_FAR = "Invalid move! You have moved too many spaces.";

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

        //The current Player from the session.
        Player currentPlayer = request.session().attribute("currentPlayer");

        //Gets the GameCenter from the WebServer, the Game from the GameCenter, and
        //the Board from the Game.
        GameCenter gameCenter = WebServer.GAME_CENTER;
        Game game = gameCenter.getGame(currentPlayer);
        Board board = game.getBoard();

        //Gets the Move from the moveJSON, and converts it into a Move.
        final String moveJSON = request.queryParams("actionData");
        Move move = gson.fromJson(moveJSON, Move.class);

        //Initializes the Move message to false.
        Message message = Message.info("false");

        //If there isn't a Piece at the start position then set the start to the recent Move's start Position.
        if(board.getSpace(move.getStart().getRow(),move.getStart().getCell()).getPiece() == null){
            move.setStart(game.getRecentMove().getStart());
        }

        //Cases for the different validation states set by the MoveValidator.
        switch (MoveValidator.validateMove(game, move)){

            //The state of a valid Move.
            case VALID:
                move.setValidState(MoveValidator.MoveValidation.VALID);
                message = Message.info(VALID_MOVE);
                break;

            //If the Piece has another jump Move available, it has the state VALIDJUMP, else
            //it has the state of VALID.
            case VALIDJUMP:
                Piece piece = board.getPiece(move.getStart().getRow(), move.getStart().getCell());
                Piece.Type type;

                //Sets the Pieces type to King if it reaches the end of the Board.
                if(move.getEnd().getRow() == 0 || move.getEnd().getRow() == 7) {
                    type = Piece.Type.KING;
                }
                else {
                    type = piece.getType();
                }

                if(MoveValidator.pieceHasJump(move.getEnd(), game, piece.getColor(), type, false)){
                    move.setValidState(MoveValidator.MoveValidation.VALIDJUMP);
                    message = Message.info(VALID_JUMP_MOVE);
                }
                else{
                    move.setValidState(MoveValidator.MoveValidation.VALID);
                    message = Message.info(VALID_MOVE);
                }
                break;

            //The state of an invalid Move if a jump Move needs to be made.
            case JUMPNEEDED:
                move.setValidState(MoveValidator.MoveValidation.JUMPNEEDED);
                message = Message.info(JUMP_MOVE);
                Spark.post(WebServer.VALIDATE_MOVE_URL, new PostValidateMoveRoute(templateEngine, gson));
                break;

            //The state of an invalid Move if a Piece is moved to an occupied Space.
            case OCCUPIED:
                move.setValidState(MoveValidator.MoveValidation.OCCUPIED);
                message = Message.error(OCCUPIED_SPACE);
                Spark.post(WebServer.VALIDATE_MOVE_URL, new PostValidateMoveRoute(templateEngine, gson));
                break;

            //The state of an invalid Move if a Piece is moved too far.
            case TOOFAR:
                move.setValidState(MoveValidator.MoveValidation.TOOFAR);
                message = Message.info(MOVED_TOO_FAR);
                Spark.post(WebServer.VALIDATE_MOVE_URL, new PostValidateMoveRoute(templateEngine, gson));
                break;
        }

        //The Game's recent move is set to validated Move. The Move's message is converted
        //into a JSON.
        game.setRecentMove(move);
        String jsonMsg = gson.toJson(message, Message.class);
        return jsonMsg;
    }
}
