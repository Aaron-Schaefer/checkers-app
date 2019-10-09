package com.webcheckers.ui;

/**
 * Language: Java
 * Author: An Chang (Mark), Gavin Burris.
 * Purpose: A class to represent a player lobby.
 */
import java.util.*;

public class PlayerLobby {
    //ArrayList of players.
    private ArrayList<Player> players;

    /**
     *Initializes a new player lobby.
     */
    public PlayerLobby(){
        players = new ArrayList<>();
    }

    /**
     * Add a new player to the player lobby, return true if successful.
     * @param player the player to add.
     * @return true if successful, false otherwise.
     */
    public boolean addPlayer(Player player){
        if(!players.contains(player)){

            players.add(player);
          //  wasAdded = true;
            return true;

        }
        return false;
    }


    /**
     * Returns the list of players in a string array.
     * @return  the players in a string array with their username,
     */
    public String[] playerArray(){
        String[] playerNames = new String[players.size()];
        for(int i = 0; i < players.size(); i++){
            playerNames[i] = players.get(i).getName();
        }
        return playerNames;
    }


    /**
     * Removes a player.
     * @param player
     */
    public void remove(Player player){

        players.remove(player);

    }


}
