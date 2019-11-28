package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.*;

/**
 * Language: Java
 * @Author: Gavin Burris.
 * Purpose: Class that records the played Games.
 */
public class GameCenter {
    //Map of all of the games.
    private Map<List<Player>, Game> activeGames;
    //List of all finished games
    private List<Game> gamesOver;
    //Tracker for gameID, number of games.
    private int numOfGames;
    /**
     * Initializes the GameCenter.
     */
    public GameCenter(){
        this.activeGames = new HashMap<>();
        this.gamesOver = new ArrayList<>();
        this.numOfGames = 0;
    }

    /**
     * Makes a new Game and adds it to the list
     * @param redPlayer The red Player
     * @param whitePlayer The white Player
     * @return The new Game
     */
    public Game makeGame(Player redPlayer, Player whitePlayer){
        this.numOfGames++;
        Game game = new Game(redPlayer, whitePlayer, this.numOfGames);
        activeGames.put(game.playerList(), game);
        return game;
    }

    /**
     * Adds a finished game to the list of finished games, and removes
     * it from the list of active games.
     * @param game The game that's over
     */
    public void addGameOver(Game game){
        if(activeGames.containsValue(game))
            activeGames.remove(game.playerList());
        if(!gamesOver.contains(game))
            gamesOver.add(game);
    }

    /**
     * Gets a game the a given Player is in.
     * @param player the Player that is in the game.
     * @return the Game the Player is in, or null if the Player
     * isn't in any of the games.
     */
    public Game getGame(Player player){
        for(List<Player> players : activeGames.keySet()){
            if(players.contains(player)){
                return activeGames.get(players);
            }
        }
        return null;
    }

    /**
     * Gets a game given the game's ID
     * @param gameID the game's ID
     * @return the Game with that game ID, or null if no Game has
     * the given game ID
     */
    public Game getGame(int gameID){
        for(Game game : gamesOver){
            if(game.getGameID() == gameID){
                return game;
            }
        }
        return null;
    }


    /**
     * Checks if a Player is in any of the games
     * @param player the given Player.
     * @return True or False based on if the Player is in any
     * of the games.
     */
    public boolean containsKey(Player player) {
        for (List<Player> players : activeGames.keySet()) {
            if (players.contains(player)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets a list of the finished games
     * @return the list of finished games
     */
    public List<Game> getGamesOver(){
        return gamesOver;
    }

    /**
     * Gets the total number of games
     * @return The total number of games
     */
    public int getNumOfGames(){
        return this.numOfGames;
    }


}
