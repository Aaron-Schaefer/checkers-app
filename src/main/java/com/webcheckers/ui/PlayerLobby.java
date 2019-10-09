package com.webcheckers.ui;

import java.util.*;

public class PlayerLobby {
    private ArrayList<Player> players;

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

    public String[] playerArray(){
        String[] playerNames = new String[players.size()];
        for(int i = 0; i < players.size(); i++){
            playerNames[i] = players.get(i).getName();
        }
        return playerNames;
    }


    public void remove(Player player){

        players.remove(player);

    }


}
