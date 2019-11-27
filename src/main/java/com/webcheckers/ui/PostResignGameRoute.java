package com.webcheckers.ui;

import com.google.gson.Gson;
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
 * Author: Ries Scerbin
 * A class to get resign route
 */
public class PostResignGameRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostResignGameRoute.class.getName());

    private final TemplateEngine templateEngine;

    private Gson gson;


    public PostResignGameRoute(final TemplateEngine templateEngine, Gson gson){

        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        LOG.config("PostResignGameRoute is initialized.");
        this.gson = gson;
    }


    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("PostSignInRoute is invoked.");

        Session session = request.session();
        PlayerLobby playerLobby = WebServer.PLAYER_LOBBY;
        Player currentPlayer = session.attribute("currentPlayer");
        Game game = WebServer.GAME_CENTER.getGame(currentPlayer);
        Board board = game.getBoard();
        Piece.Color color = (currentPlayer == game.getRedPlayer()) ? Piece.Color.RED : Piece.Color.WHITE;

        Message message;
        if(color != board.getActiveColor()){
            message = Message.error("You can't resign. It's not your turn");
        }
        else{
//            game.makeResigned();
//            session.attribute("resignPlayer", currentPlayer);
//            Player resign = session.attribute("resignPlayer");
            game.setResignPlayer(currentPlayer);
            playerLobby.removePlayer(currentPlayer);
            playerLobby.removeGamePlayer(currentPlayer);
            message = Message.info("You resigned");
        }
        String jsonMsg = gson.toJson(message, Message.class);
        return jsonMsg;
    }
}
