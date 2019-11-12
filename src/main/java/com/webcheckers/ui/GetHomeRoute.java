package com.webcheckers.ui;

import java.util.*;
import java.util.logging.Logger;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.*;

import com.webcheckers.util.Message;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

  private static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");

  private final TemplateEngine templateEngine;

  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetHomeRoute(final TemplateEngine templateEngine) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    //
    LOG.config("GetHomeRoute is initialized.");
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
    LOG.finer("GetHomeRoute is invoked.");

    //Hash map for the view model.
    Map<String, Object> vm = new HashMap<>();
    vm.put("title", "Welcome!");

    //Get current session, and get the current Player from the session.
    Session session = request.session();
    Player player = session.attribute("currentPlayer");

    String mode = request.queryParams("mode");

    PlayerLobby playerLobby = WebServer.PLAYER_LOBBY;
    GameCenter gameCenter = WebServer.GAME_CENTER;

    Game game = gameCenter.getGame(player);

    //Sets the current User as the current Player if not already set.
    if(player != null){
      vm.put("currentUser", player);

      //Redirects the current Player to the /game URL if the current Player
      //is the White Player.
      if(game != null) {
        if (game.getWhitePlayer() != null){
          if (player.equals(game.getWhitePlayer())) {
            response.redirect("/game");
          }
        }
      }
    }

    if(mode != null){
      vm.put("mode", mode);
      if(mode.equals("PLAY")){
        playerLobby.addPlayer(player);
      }
    }

    //Makes a list of players and puts it, and the number of Players to the
    //home.ftl file.
    List<Player> players = Arrays.asList(playerLobby.playerArray());
    vm.put("players", players);
    vm.put("numPlayers", players.size());

    //Displays a user error if the current Player chose a Player who's already
    //in a game. Otherwise it displaces the WELCOME_MSG.
    if(playerLobby.isChoseInGame()){
      Message inGame = Message.info("Error! This player is already in a game!");
      vm.put("message", inGame);
      playerLobby.notChoseInGame();
    }
    else{
      vm.put("message", WELCOME_MSG);
    }

    //Render the View
    return templateEngine.render(new ModelAndView(vm , "home.ftl"));
  }
}
