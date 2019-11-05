package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.HashMap;
import java.util.Map;

public class GameCenter {
    private Map<Player, Game> games;

    public GameCenter(){
        this.games = new HashMap<>();
    }

    public Game makeGame(Player redPlayer, Player whitePlayer){
        Game game = new Game(redPlayer, whitePlayer);
        return games.put(redPlayer, game);
    }

    public Game getGame(Player player){
        if(games.containsKey(player)){
            return games.get(player);
        }
        return null;
    }

    public boolean containsKey(Player player){
        if(games.containsKey(player)){
            return true;
        }
        return false;
    }
}
