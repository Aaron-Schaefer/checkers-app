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
        GameCenter gameCenter = WebServer.GAME_CENTER;
        Player currentPlayer = request.session().attribute("currentPlayer");
        Game game = gameCenter.getGame(currentPlayer);
        game.setTurnMade(true);
        Message message = Message.info("true");
        String jsonMsg = gson.toJson(message, Message.class);
        Spark.get(WebServer.GAME_URL, new GetGameRoute(templateEngine, gson));
        Move move = game.getRecentMove();
        if(move.getValidState() == MoveValidator.MoveValidation.JUMPNEEDED
                || move.getValidState() == MoveValidator.MoveValidation.OCCUPIED
                || move.getValidState() == MoveValidator.MoveValidation.TOOFAR){

        }
        else {
            game.updateBoard(move);
            if (move.getValidState() == MoveValidator.MoveValidation.VALIDJUMP) {
                Spark.post(WebServer.VALIDATE_MOVE_URL, new PostValidateMoveRoute(templateEngine, gson));
            }
            else if (move.getValidState() == MoveValidator.MoveValidation.VALID) {
                game.endTurn();
            }
        }
        return jsonMsg;
    }
}
