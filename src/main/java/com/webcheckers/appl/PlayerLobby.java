package com.webcheckers.appl;

/**
 * Language: Java
 * @Author: An Chang (Mark), Gavin Burris.
 * Purpose: A class to represent a player lobby.
 */
import com.webcheckers.model.Player;

import java.util.*;

public class PlayerLobby {
    //ArrayList of all Players.
    private ArrayList<Player> players;
    //ArrayList of Players in a game.
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
        if(!players.contains(player) && player != null){

            players.add(player);
            return true;

        }
        return false;
    }

    /**
     * Add a Player to the list of Players in a game.
     * @param player the player to add.
     */
    public void addGamePlayer(Player player){
        if(!gamePlayers.contains(player) && player != null){
            gamePlayers.add(player);
        }
    }

    /**
     * Returns the list of players in a string array.
     * @return the players in a string array with their username,
     */
    public Player[] playerArray(){
        Player[] playerNames = new Player[players.size()];
        for(int i = 0; i < players.size(); i++){
            playerNames[i] = players.get(i);
        }
        return playerNames;
    }

    /**
     * Gets a Player based on a given name.
     * @param name the name to check.
     * @return the Player with the given name, or null if not found.
     */
    public Player getPlayer(String name){
        Player[] playerArray = this.playerArray();
        for (Player player : playerArray) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    /**
     * Checks if a Player is in a game.
     * @param player the Player to check.
     * @return True or False if the Player is in a game or not.
     */
    public boolean isInGame(Player player){
        return gamePlayers.contains(player);
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
