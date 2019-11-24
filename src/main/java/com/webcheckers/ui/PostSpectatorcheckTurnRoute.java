package com.webcheckers.ui;

import java.util.logging.Logger;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The route that will update the game page for a spectator to get the most recent moves
 */
public class PostSpectatorcheckTurnRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSpectatorcheckTurnRoute.class.getName());
    private final TemplateEngine templateEngine;
    private Gson gson;

    /**
     * Creates the Spark Route to handle the POST/Spectator/checkTurn requests
     * @param templateEngine
     * @param gson
     */
    PostSpectatorcheckTurnRoute(final TemplateEngine templateEngine, Gson gson){
        Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.templateEngine = templateEngine;
        this.gson = gson;
    }

    /**
     * Renders the game page for the spectator after a turn has been made (refresh)
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.info("Handling PostSpectatorcheckTurnRoute");

        Map<String, Object> vm = new HashMap<>();

        Session session = request.session();
        Player currentPlayer = session.attribute("currentPlayer");

        PlayerLobby playerLobby = WebServer.PLAYER_LOBBY;
        GameCenter gameCenter = WebServer.GAME_CENTER;

        String spectatedName = session.attribute("spectatorName");

        System.out.println("Spectating Player: " + spectatedName);
        Player player = playerLobby.getUser(spectatedName);
        Game game = gameCenter.getGame(player);
        Message message;
        message = Message.info("false");

        //Check if turn was made
        if (game.isGameOver() || game.getResignPlayer() != null || game.isTurnMade()){
            message = Message.info("true");
        }
        String jsonMsg = gson.toJson(message, Message.class);
        return jsonMsg;
    }
}
