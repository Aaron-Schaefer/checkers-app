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
 *The route that will take a spectator to a game based on its ID
 */
public class GetSpectatorGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetSpectatorGameRoute.class.getName());
    private final TemplateEngine templateEngine;
    private Gson gson;

    /**
     * Creates the Spark Route to handle the GET/Spectator/game requests
     * @param templateEngine
     * @param gson
     */
    GetSpectatorGameRoute(final TemplateEngine templateEngine, Gson gson){
        Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.templateEngine = templateEngine;
        this.gson = gson;

    }

    /**
     * Renders a spectator mode of a game being played by others (current user
     * will not be able to interact with pieces.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.info("Handling GetSpectatorGameRoute");
        Map<String, Object> vm = new HashMap<>();

        Session session = request.session();
        Player currentPlayer = session.attribute("currentPlayer");

        PlayerLobby playerLobby = WebServer.PLAYER_LOBBY;
        GameCenter gameCenter = WebServer.GAME_CENTER;

        //Will need to get game by the player the current user decides to spectate

        String spectatedName = session.attribute("spectatorName");
        if (spectatedName == null){
            spectatedName = request.queryParams("playerName");
            session.attribute("spectatorName", spectatedName);
        }
        Player player = playerLobby.getUser(spectatedName);
        Game game = gameCenter.getGame(player);
        Player redPlayer = game.getRedPlayer();
        Player whitePlayer = game.getWhitePlayer();
        Board board = game.getBoard();
        BoardView boardView = new BoardView(board, player);


        //End of game cases win/loss message
        if (game.isGameOver()){
            if (game.getWinner() == redPlayer){
               Message.info(redPlayer + " won by taking all of " + whitePlayer + "'s pieces!");
            }
            if (game.getWinner() == whitePlayer){
                Message.info(whitePlayer + " won by taking all of " + redPlayer + "'s pieces!");
            }
            response.redirect("/");
        }
        else if (game.getResignPlayer() != null){
            if (game.getWinner() == redPlayer){
                Message.info(redPlayer + " won because " + whitePlayer + " resigned.");
            }
            if (game.getWinner() == whitePlayer) {
                Message.info(whitePlayer + " won because " + redPlayer + " resigned.");
            }
            response.redirect("/");
        }

        vm.put("title", "How do you like my box tickets to the checkers match of the century!");
        vm.put("viewMode", "SPECTATOR");
        vm.put("currentUser", currentPlayer);
        vm.put("redPlayer", redPlayer);
        vm.put("whitePlayer", whitePlayer);
        vm.put("activeColor", board.getActiveColor());
        vm.put("board", boardView);


        return templateEngine.render(new ModelAndView(vm,"game.ftl"));
    }
}
