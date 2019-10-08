package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.List;

public class PlayerLobby {
    private List<Player> players;

    public PlayerLobby(){
        players = new ArrayList<>();
    }

    public void addPlayer(Player player){
        players.add(player);
        //boolean wasAdded = false;
//        if(!players.contains(player)){
//            players.add(player);
//          //  wasAdded = true;
//        }
        //return wasAdded;

    }

    public String listPlayers(){
        String playString = "";
        for(int i = 0; i < players.size(); i++){
            playString = playString + players.get(i).getName();
        }
        return playString;
    }
}
