package com.webcheckers.ui;

import java.util.logging.Logger;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.*;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());

    private final TemplateEngine templateEngine;

//    private final Map<String, Object> modeOptions = new HashMap<>(2);
    private Gson gson;
    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /game} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    GetGameRoute(final TemplateEngine templateEngine, Gson gson) {
        Objects.requireNonNull(templateEngine, "templateEngine is required");
        //
        this.templateEngine = templateEngine;
        this.gson = gson;
    }

    /**
     * Render the WebCheckers Game page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Game page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.info("Handling GetGameRoute");

        //Hash map for the view model.
        Map<String, Object> vm = new HashMap<>();

        //Get current session, and get the current Player from the session.
        Session session = request.session();
        Set<String> params = request.queryParams();
        LOG.info("all params for GetGameRoute:");
        for (String param : params) {
            LOG.info("\t'" + param + "' is: '" + request.queryParams(param) + "'");
        }


        Player currentPlayer = session.attribute("currentPlayer");

        LOG.info("Current player name is: " + currentPlayer.getName());

        PlayerLobby playerLobby = WebServer.PLAYER_LOBBY;
        GameCenter gameCenter = WebServer.GAME_CENTER;

        Game game = gameCenter.getGame(currentPlayer);

        if (game == null) {
            LOG.info ("game was null, craete a new one!");
            game = new Game();
        } else {
            LOG.info("game was not null!");
        }

        //Set the Red Player and the White player as the Red Player and White Player
        //given by the Player Lobby.
        Player redPlayer = game.getRedPlayer();
        Player whitePlayer = game.getWhitePlayer();

        //Sets the Red Player as the current Player who selected to start a game. Sets
        //the White Player as the player who was selected on the home page. Adds both to
        //PlayerLobby.
        String name = request.queryParams("playerName");
        LOG.info("queried playername from request, name is: " + name);

        if (playerLobby.isInGame(playerLobby.getUser(name))) {
            LOG.info("player is in game, redirecting to '/'");
            playerLobby.playerChoseInGame();
            response.redirect("/");
        }
        //Sets the boolean in PlayerLobby to true to display a message error if the current
        //User selects a player who is already in a game.
        else {
            LOG.info("red player is: " + redPlayer + ", white player is: " + whitePlayer);

            if (redPlayer == null) {
                redPlayer = currentPlayer;
                game.addToGame(redPlayer);
                playerLobby.addGamePlayer(redPlayer);
                whitePlayer = playerLobby.getUser(name);
                game.addToGame(whitePlayer);
                playerLobby.addGamePlayer(whitePlayer);
            } else {
                playerLobby.notChoseInGame();
            }

            if (!gameCenter.containsKey(currentPlayer) && whitePlayer != null) {
                game.initializeBoard();
                game.setGameID(gameCenter.getNumOfGames());
                vm.put("gameID",gameCenter.getNumOfGames());
                gameCenter.addGame(game);

            }

            Board board = game.getBoard();

            //Creates the BoardView.
            BoardView boardView = new BoardView(board, currentPlayer);

            //Sets the game to over by resignation from the opponent
            if (WebServer.RESIGN_CHECK) {
                final Map<String, Object> modeOptions = new HashMap<>(2);
                modeOptions.put("isGameOver", true);
                if (session.attribute("currentPlayer") == redPlayer) {
                    modeOptions.put("gameOverMessage", whitePlayer.getName() + " has resigned you win!");
                    vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
                } else if (session.attribute("currentPlayer") == whitePlayer) {
                    modeOptions.put("gameOverMessage", redPlayer.getName() + " has resigned you win!");
                    vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
                }
            }
            else if(game.isGameOver() && whitePlayer != null){
                gameCenter.addGameOver(game);
                final Map<String, Object> modeOptions = new HashMap<>(2);
                modeOptions.put("isGameOver", true);
                if (session.attribute("currentPlayer") == redPlayer) {
                    modeOptions.put("gameOverMessage", redPlayer.getName() + " has captured all of the pieces!");
                    vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
                } else if (session.attribute("currentPlayer") == whitePlayer) {
                    modeOptions.put("gameOverMessage", whitePlayer.getName() + " has captured all of the pieces!");
                    vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
                }
            }
                WebServer.RESIGN_CHECK = false;

            //Uses view model to put to the variables to the game.ftl file.
            vm.put("title", "Time to play!");
            vm.put("viewMode", "PLAY");
            vm.put("currentUser", currentPlayer);
            vm.put("redPlayer", redPlayer);
            vm.put("whitePlayer", whitePlayer);
            vm.put("activeColor", board.getActiveColor());
            vm.put("board", boardView);


            //Renders the view.
            return templateEngine.render(new ModelAndView(vm, "game.ftl"));
        }
        return "";
    }
}
