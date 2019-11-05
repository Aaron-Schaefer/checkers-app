package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.*;

public class GameCenter {
    private Map<List<Player>, Game> games;

    public GameCenter(){
        this.games = new HashMap<>();
    }

    public void addGame(Game game){
        Player redPlayer = game.getRedPlayer();
        Player whitePlayer = game.getWhitePlayer();
        List<Player> playerList = new ArrayList<>(Arrays.asList(redPlayer, whitePlayer));
        games.put(playerList, game);
    }

    public Game getGame(Player player){
        for(List<Player> players : games.keySet()){
            if(players.contains(player)){
                return games.get(players);
            }
        }
        return null;
    }

    public boolean containsKey(Player player) {
        for (List<Player> players : games.keySet()) {
            if (players.contains(player)) {
                return true;
            }
        }
        return false;
    }
}
