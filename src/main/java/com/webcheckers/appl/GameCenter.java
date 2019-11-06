package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.*;

public class GameCenter {
    //Map of all of the games.
    private Map<List<Player>, Game> games;

    /**
     * Initializes the GameCenter.
     */
    public GameCenter(){
        this.games = new HashMap<>();
    }

    /**
     * Adds the given game to the Map of games.
     * @param game the given game to add.
     */
    public void addGame(Game game){
        Player redPlayer = game.getRedPlayer();
        Player whitePlayer = game.getWhitePlayer();
        List<Player> playerList = new ArrayList<>(Arrays.asList(redPlayer, whitePlayer));
        games.put(playerList, game);
    }

    /**
     * Gets a game the a given Player is in.
     * @param player the Player that is in the game.
     * @return the Game the Player is in, or null if the Player
     * isn't in any of the games.
     */
    public Game getGame(Player player){
        for(List<Player> players : games.keySet()){
            if(players.contains(player)){
                return games.get(players);
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
        for (List<Player> players : games.keySet()) {
            if (players.contains(player)) {
                return true;
            }
        }
        return false;
    }
}
