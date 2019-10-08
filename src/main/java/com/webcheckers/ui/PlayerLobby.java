package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.List;

public class PlayerLobby {
    private List<Player> players;

    public PlayerLobby(){
        players = new ArrayList<>();
    }

    public boolean addPlayer(Player player){
        if(!players.contains(player)){
            players.add(player);
          //  wasAdded = true;
            return true;
        }
        return false;
    }

    public String listPlayers(){
        String playString = "";
        for(int i = 0; i < players.size(); i++){
            playString = playString + players.get(i).getName();
        }
        return playString;
    }
}
