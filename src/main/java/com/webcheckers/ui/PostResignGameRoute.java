package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import freemarker.template.Template;
import spark.*;
import spark.Request;
import spark.Response;
import spark.Route;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Language: Java
 * Author: Ries Scerbin and Gavin Burris
 * A class to get resign route
 */
public class PostResignGameRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostResignGameRoute.class.getName());

    private final TemplateEngine templateEngine;

    private Gson gson;

    /**
     *  Creates the Spark Route to handle the Post/resign requests
     * @param templateEngine the HTML template rendering engine
     * @param gson
     */
    public PostResignGameRoute(final TemplateEngine templateEngine, Gson gson){

        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        LOG.config("PostResignGameRoute is initialized.");
        this.gson = gson;
    }

    /**
     * Returns a player back to the homepage and the other player gets prompted an exit
     * @param request an HTTP request
     * @param response an HTTP response
     * @return the rendered home page for the user who clicks resign and an exit button on the game page
     *          for the other player in the game
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("PostSignInRoute is invoked.");

        //The request's session and the current Player retrieved from the session.
        Session session = request.session();
        Player currentPlayer = session.attribute("currentPlayer");

        //The PlayerLobby and GameCenter from the WebServer, and the current Game
        //from the GameCenter.
        PlayerLobby playerLobby = WebServer.PLAYER_LOBBY;
        GameCenter gameCenter = WebServer.GAME_CENTER;
        Game game = gameCenter.getGame(currentPlayer);

        //The Game Board.
        Board board = game.getBoard();

        //The color of the active Player.
        Piece.Color color = (currentPlayer == game.getRedPlayer()) ? Piece.Color.RED : Piece.Color.WHITE;

        Message message;
        //The current Player isn't the active color then they cant resign.
        if(color != board.getActiveColor()){
            message = Message.error("You can't resign. It's not your turn");
        }
        //The current Player is the active color then they are set to be to be
        //the resigned Player, and the Player is removed from the PlayerLobby's Player and Game
        //Player lists.
        else{
            game.setResignPlayer(currentPlayer);
            playerLobby.removePlayer(currentPlayer);
            playerLobby.removeGamePlayer(currentPlayer);
            message = Message.info("You resigned");
        }

        //Turns the resign message into a JSON.
        String jsonMsg = gson.toJson(message, Message.class);
        return jsonMsg;
    }
}
