package com.webcheckers.model;

/**
 * Language: Java
 * @Author: An Chang (Mark), Gavin Burris.
 * Purpose: A class to represent a player lobby.
 */
import com.webcheckers.model.Player;

import java.util.*;

public class PlayerLobby {
    //ArrayList of Players.
    private ArrayList<Player> players;
    //ArrayList of Players in the game.
    private ArrayList<Player> gamePlayers;
    //The red Player.
    private Player redPlayer;
    //The white Player.
    private Player whitePlayer;
    //The boolean for the error message.
    private boolean choseInGame;

    /**
     *Initializes a new player lobby.
     */
    public PlayerLobby(){
        players = new ArrayList<>();
        gamePlayers = new ArrayList<>();
        choseInGame = false;
    }

    /**
     * Add a new player to the player lobby, return true if successful.
     * @param player the player to add.
     * @return true if successful, false otherwise.
     */
    public boolean addPlayer(Player player){
        if(!players.contains(player)){

            players.add(player);
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
        for (Player playerName : playerNames) {
            if (playerName.getName().equals(name)) {
                return playerName;
            }
        }
        return null;
    }

    /**
     * Removes a player.
     * @param player the player removed.
     */
    public void remove(Player player){

        players.remove(player);

    }

    /**
     * Sets choseInGame to true.
     */
    public void playerChoseInGame(){
        choseInGame = true;
    }

    /**
     * Sets choseInGame to false.
     */
    public void notChoseInGame(){
        choseInGame = false;
    }

    /**
     * Returns choseInGame.
     * @return choseInGame
     */
    public boolean isChoseInGame(){
        return choseInGame;
    }

}
