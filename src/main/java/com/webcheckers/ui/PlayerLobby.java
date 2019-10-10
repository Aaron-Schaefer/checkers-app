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
    private ArrayList<Player> gamePlayers;
    private Player redPlayer;
    private Player whitePlayer;

    /**
     *Initializes a new player lobby.
     */
    public PlayerLobby(){
        players = new ArrayList<>();
        gamePlayers = new ArrayList<>();
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

    public void addToGame(Player player){
        if(gamePlayers.isEmpty()){
            redPlayer = player;
        }
        else{
            whitePlayer = player;
        }
        gamePlayers.add(player);
    }

    public Player getRedPlayer(){
        return this.redPlayer;
    }

    public Player getWhitePlayer(){
        return this.whitePlayer;
    }

    /**
     * Returns the list of players in a string array.
     * @return  the players in a string array with their username,
     */
    public Player[] playerArray(){
        Player[] playerNames = new Player[players.size()];
        for(int i = 0; i < players.size(); i++){
            playerNames[i] = players.get(i);
        }
        return playerNames;
    }

    public Player getPlayer(String name){
        Player[] playerNames = this.playerArray();
        for(int i = 0; i < playerNames.length; i++){
            if(playerNames[i].getName().equals(name)){
                return playerNames[i];
            }
        }
        return null;
    }

    /**
     * Removes a player.
     * @param player
     */
    public void remove(Player player){

        players.remove(player);

    }


}
