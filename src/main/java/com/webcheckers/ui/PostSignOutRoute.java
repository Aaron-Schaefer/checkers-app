package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.*;

import java.util.Objects;
import java.util.logging.Logger;
/**
 * Language: Java
 * @Author: An Chang (Mark), Gavin Burris.
 * Purpose: A class to post a sign out route..
 */
public class PostSignOutRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /signout} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public PostSignOutRoute(final TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        //
        LOG.config("PostSignOutRoute is initialized.");
    }


    /**
     * Post information from the current page about the current User
     * that is signing out
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Home page
     */
    public Object handle(Request request, Response response) {
        LOG.finer("PostSignOutRoute is invoked.");

        //Gets the Player from the session.
        Player player = request.session().attribute("currentPlayer");

        //Gets the PlayerLobby and GameCenter from the WebServer, and the Game
        //from the GameCenter.
        PlayerLobby playerLobby = WebServer.PLAYER_LOBBY;
        GameCenter gameCenter = WebServer.GAME_CENTER;
        Game game = gameCenter.getGame(player);

        //Sets the resign Player to the player signing out if they were in a Game.
        if(game != null){
            game.setResignPlayer(player);
        }

        //Removes the Player from the PlayerLobby's User, Player, and GamePlayer lists.
        playerLobby.removeUser(player);
        playerLobby.removePlayer(player);
        playerLobby.removeGamePlayer(player);

        //Invalidates the session and redirects to the Home page.
        request.session().invalidate();
        response.redirect("/");
        return "";

    }


}
