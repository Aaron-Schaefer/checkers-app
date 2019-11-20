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

/**
 *
 */
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
        Player currentPlayer = session.attribute("currentPlayer");

        System.out.println(currentPlayer.getName() + " is in Game");

        //Get the PlayerLobby and GameCenter from the WebServer.
        PlayerLobby playerLobby = WebServer.PLAYER_LOBBY;
        GameCenter gameCenter = WebServer.GAME_CENTER;

        //The game the current Player is in.
        Game game = gameCenter.getGame(currentPlayer);

        //If the game is null, create a new one. Sets the Red Player as the current Player
        //who selected to start a game. Sets the White Player as the player who was selected
        //on the home page. Adds both to PlayerLobby.
        if (game == null) {
            String name = request.queryParams("playerName");

            //Checks if the current Player chose an opponent that's already in a game. If the
            //opponent is already in a game they're redirected to the home page.
            if (playerLobby.isInGame(playerLobby.getUser(name))) {
                playerLobby.playerChoseInGame();
                response.redirect("/?mode=PLAY");
            }
            else {
                playerLobby.notChoseInGame();
                playerLobby.addGamePlayer(currentPlayer);
                Player whitePlayer = playerLobby.getUser(name);
                playerLobby.addGamePlayer(whitePlayer);
                game = gameCenter.makeGame(currentPlayer, whitePlayer);
            }
        }

        //Set the Red Player and the White player as the Red Player and White Player
        //given by the game.
        Player redPlayer = game.getRedPlayer();
        Player whitePlayer = game.getWhitePlayer();

        //The game Board.
        Board board = game.getBoard();

        //Creates the BoardView.
        BoardView boardView = new BoardView(board, currentPlayer);

        final Map<String, Object> modeOptions = new HashMap<>(2);

        //Sets the game to over by resignation from the opponent
        if (game.isResigned()) {
            Player winner = game.getWinner();
            if(winner == null) {
                winner = game.getOpponent(currentPlayer);
                game.setWinner(winner);
            }
            else{
                playerLobby.removePlayer(redPlayer);
                playerLobby.removeGamePlayer(redPlayer);
                playerLobby.removePlayer(whitePlayer);
                playerLobby.removeGamePlayer(whitePlayer);
                gameCenter.addGameOver(game);
            }
            modeOptions.put("isGameOver", true);
            if (currentPlayer == winner) {
                modeOptions.put("gameOverMessage","You win! " + game.getOpponent(currentPlayer).getName() + " has resigned!");
            }
            else{
                modeOptions.put("gameOverMessage", "You lose! You resigned the game!");
            }
        }
        else if(game.isGameOver()){
            Player winner = game.getWinner();
            if(winner == null){
                winner = game.setWinner(currentPlayer);
            }
            else{
                playerLobby.removePlayer(redPlayer);
                playerLobby.removeGamePlayer(redPlayer);
                playerLobby.removePlayer(whitePlayer);
                playerLobby.removeGamePlayer(whitePlayer);
                gameCenter.addGameOver(game);
            }
            modeOptions.put("isGameOver", true);
            if (currentPlayer == winner) {
                modeOptions.put("gameOverMessage", "You win! You've captured all the pieces!");
            }
            else{
                modeOptions.put("gameOverMessage", "You lose! " + winner.getName() + " has captured all of the pieces!");
            }
        }

        //Uses view model to put to the variables to the game.ftl file.
        vm.put("title", "Time to play!");
        vm.put("gameID", game.getGameID());
        vm.put("viewMode", "PLAY");
        vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
        vm.put("currentUser", currentPlayer);
        vm.put("redPlayer", redPlayer);
        vm.put("whitePlayer", whitePlayer);
        vm.put("activeColor", board.getActiveColor());
        vm.put("board", boardView);


        //Renders the view.
        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }
}
