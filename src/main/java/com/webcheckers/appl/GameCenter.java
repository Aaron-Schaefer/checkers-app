package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.*;

public class GameCenter {
    //Map of all of the games.
    private Map<List<Player>, Game> Activegames;
    //List of all finished games
    private List<Game> gamesOver;
    //Tracker for gameID, number of games.
    private int numOfGames;
    /**
     * Initializes the GameCenter.
     */
    public GameCenter(){
        this.Activegames = new HashMap<>();
        this.gamesOver = new ArrayList<>();
        this.numOfGames = 0;
    }

    /**
     * Adds the given game to the Map of games.
     * @param game the given game to add.
     */
    public void addGame(Game game){
        Player redPlayer = game.getRedPlayer();
        Player whitePlayer = game.getWhitePlayer();
        List<Player> playerList = new ArrayList<>(Arrays.asList(redPlayer, whitePlayer));
        Activegames.put(playerList, game);
        this.numOfGames++;
    }

    public void addGameOver(Game game){
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
        for(List<Player> players : Activegames.keySet()){
            if(players.contains(player)){
                return Activegames.get(players);
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
        for (List<Player> players : Activegames.keySet()) {
            if (players.contains(player)) {
                return true;
            }
        }
        return false;
    }

    public List<Game> getGamesOver(){
        return gamesOver;
    }

    public int getNumOfGames(){
        return this.numOfGames;
    }


}
