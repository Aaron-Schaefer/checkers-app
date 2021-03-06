package com.webcheckers.ui;

import static spark.Spark.*;

import java.util.Objects;
import java.util.logging.Logger;

import com.google.gson.Gson;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Board;
import com.webcheckers.model.Move;
import com.webcheckers.appl.PlayerLobby;
import spark.TemplateEngine;


/**
 * The server that initializes the set of HTTP request handlers.
 * This defines the <em>web application interface</em> for this
 * WebCheckers application.
 *
 * <p>
 * There are multiple ways in which you can have the client issue a
 * request and the application generate responses to requests. If your team is
 * not careful when designing your approach, you can quickly create a mess
 * where no one can remember how a particular request is issued or the response
 * gets generated. Aim for consistency in your approach for similar
 * activities or requests.
 * </p>
 *
 * <p>Design choices for how the client makes a request include:
 * <ul>
 *     <li>Request URL</li>
 *     <li>HTTP verb for request (GET, POST, PUT, DELETE and so on)</li>
 *     <li><em>Optional:</em> Inclusion of request parameters</li>
 * </ul>
 * </p>
 *
 * <p>Design choices for generating a response to a request include:
 * <ul>
 *     <li>View templates with conditional elements</li>
 *     <li>Use different view templates based on results of executing the client request</li>
 *     <li>Redirecting to a different application URL</li>
 * </ul>
 * </p>
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>, An Chang (Mark)
 */
public class WebServer {
  private static final Logger LOG = Logger.getLogger(WebServer.class.getName());

  //
  // Constants
  //

  /**
   * The URL pattern to request the Home page.
   */
  public static final String HOME_URL = "/";

  /**
   * The URL pattern to request the Sign In page.
   */
  public static final String SIGN_IN_URL = "/signin";

  /**
   * The URL pattern to request the Sign Out page.
   */
  public static final String SIGN_OUT_URL = "/signout";

  /**
   * The URL pattern to request the Game page.
   */
  public static final String GAME_URL = "/game";

  /**
   * The URL pattern to request the Validate Move page.
   */
  public static final String VALIDATE_MOVE_URL = "/validateMove";

  /**
   * The URL pattern to request the Submit Turn page.
   */
  public static final String SUBMIT_TURN_URL = "/submitTurn";

  /**
   * The URL pattern to request the Backup Move page.
   */
  public static final String BACKUP_MOVE_URL = "/backupMove";

  /**
   * The URL pattern to request the Resign Game page.
   */
  public static final String RESIGN_GAME_URL = "/resignGame";

  /**
   * The URL pattern to request the Check Turn page.
   */
  public static final String CHECK_TURN_URL = "/checkTurn";

  /**
   * The URL pattern to request the Replay Game page.
   */
  public static final String REPLAY_GAME_URL = "/replay/game";

  /**
   * The URL pattern to request the Replay Stop Watching page.
   */
  public static final String REPLAY_STOP_WATCHING = "/replay/stopWatching";

  /**
   * The URL pattern to request the Replay Next Turn page.
   */
  public static final String REPLAY_NEXT_TURN = "/replay/nextTurn";

  /**
   * The URL pattern to request the Replay Previous Turn page.
   */
  public static final String REPLAY_PREVIOUS_TURN = "/replay/previousTurn";

  /**
   * The URL pattern to request the Spectator Page.
   */
  public static final String SPECTATOR_URL = "/spectator/game";

  /**
   * The URL pattern to request the Spectator stopWatching page.
   */
  public static final String SPECTATOR_STOP_WATCHING = "/spectator/stopWatching";

  /**
   * The URL pattern to request the Spectator Check turn page.
   */
  public static final String SPECTATOR_CHECK_TURN = "/spectator/checkTurn";

  //Initializes the Games PlayerLobby.
  public static PlayerLobby PLAYER_LOBBY = new PlayerLobby();

  //Initializes the Game Center.
  public static GameCenter GAME_CENTER = new GameCenter();


  //
  // Attributes
  //

  private final TemplateEngine templateEngine;
  private final Gson gson;

  //
  // Constructor
  //

  /**
   * The constructor for the Web Server.
   *
   * @param templateEngine
   *    The default {@link TemplateEngine} to render page-level HTML views.
   * @param gson
   *    The Google JSON parser object used to render Ajax responses.
   *
   * @throws NullPointerException
   *    If any of the parameters are {@code null}.
   */
  public WebServer(final TemplateEngine templateEngine, final Gson gson) {
    // validation
    Objects.requireNonNull(templateEngine, "templateEngine must not be null");
    Objects.requireNonNull(gson, "gson must not be null");
    //
    this.templateEngine = templateEngine;
    this.gson = gson;
  }

  //
  // Public methods
  //

  /**
   * Initialize all of the HTTP routes that make up this web application.
   *
   * <p>
   * Initialization of the web server includes defining the location for static
   * files, and defining all routes for processing client requests. The method
   * returns after the web server finishes its initialization.
   * </p>
   */
  public void initialize() {

    // Configuration to serve static files
    staticFileLocation("/public");

    //// Setting any route (or filter) in Spark triggers initialization of the
    //// embedded Jetty web server.

    //// A route is set for a request verb by specifying the path for the
    //// request, and the function callback (request, response) -> {} to
    //// process the request. The order that the routes are defined is
    //// important. The first route (request-path combination) that matches
    //// is the one which is invoked. Additional documentation is at
    //// http://sparkjava.com/documentation.html and in Spark tutorials.

    //// Each route (processing function) will check if the request is valid
    //// from the client that made the request. If it is valid, the route
    //// will extract the relevant data from the request and pass it to the
    //// application object delegated with executing the request. When the
    //// delegate completes execution of the request, the route will create
    //// the parameter map that the response template needs. The data will
    //// either be in the value the delegate returns to the route after
    //// executing the request, or the route will query other application
    //// objects for the data needed.

    //// FreeMarker defines the HTML response using templates. Additional
    //// documentation is at
    //// http://freemarker.org/docs/dgui_quickstart_template.html.
    //// The Spark FreeMarkerEngine lets you pass variable values to the
    //// template via a map. Additional information is in online
    //// tutorials such as
    //// http://benjamindparrish.azurewebsites.net/adding-freemarker-to-java-spark/.

    //// These route definitions are examples. You will define the routes
    //// that are appropriate for the HTTP client interface that you define.
    //// Create separate Route classes to handle each route; this keeps your
    //// code clean; using small classes.

    // Shows the Checkers game Home page.
    get(HOME_URL, new GetHomeRoute(templateEngine));

    // Shows the Checkers game Sign In page.
    get(SIGN_IN_URL, new GetSignInRoute(templateEngine));

    //Display of the game for the player
    get(GAME_URL, new GetGameRoute(templateEngine, gson));

    //Display the a finished game for the player to replay it.
    get(REPLAY_GAME_URL, new GetReplayGameRoute(templateEngine, gson));

    //Returns the User back to the Home page.
    get(REPLAY_STOP_WATCHING, new GetReplayStopWatchingRoute(templateEngine, gson));

    //Displays the game for the player who the spectator is spectating.
    get(SPECTATOR_URL, new GetSpectatorGameRoute(templateEngine, gson));

    //Returns the Spectator back to the Home page.
    get(SPECTATOR_STOP_WATCHING, new GetSpectatorStopWatchingRoute(templateEngine, gson));

    //Takes in the username, checks if its valid, if yes, sign in and redirect to home page.
    //Player gets added to player lobby.
    post(SIGN_IN_URL, new PostSignInRoute(templateEngine));

    //Sign the player out and remove player from player lobby. Redirect back to home page.
    post(SIGN_OUT_URL, new PostSignOutRoute(templateEngine));

    //Display of the game for the player
    get(GAME_URL, new GetGameRoute(templateEngine, gson));

    //Takes a move from the game and checks if it is valid.
    post(VALIDATE_MOVE_URL, new PostValidateMoveRoute(templateEngine, gson));

    //Takes the most recent move and submits it to the game board if it  is valid.
    post(SUBMIT_TURN_URL, new PostSubmitTurnRoute(templateEngine, gson));

    //Removes the most recent move.
    post(BACKUP_MOVE_URL, new PostBackupMoveRoute(templateEngine, gson));

    //Resigns the Player from the game.
    post(RESIGN_GAME_URL, new PostResignGameRoute(templateEngine, gson));

    //Checks to see if it is the Players turn. If it is it refreshes the
    post(CHECK_TURN_URL, new PostCheckTurnRoute(templateEngine, gson));

    //Checks if there's a next turn available while replaying a game.
    post(REPLAY_NEXT_TURN, new PostReplayNextTurnRoute(templateEngine, gson));

    //Checks if there's a next turn available while replaying a game.
    post(REPLAY_PREVIOUS_TURN, new PostReplayPreviousTurnRoute(templateEngine, gson));

    //Checks if there's been a new turn in the game that's being played.
    post(SPECTATOR_CHECK_TURN, new PostSpectatorcheckTurnRoute(templateEngine, gson));

    //
    LOG.config("WebServer is initialized.");
  }

}