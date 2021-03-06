package com.webcheckers.appl;

/**
 * Language: Java
 * @Author: An Chang (Mark), Gavin Burris.
 * Purpose: A class to represent a player lobby.
 */
import com.webcheckers.model.Player;

import java.util.*;

public class PlayerLobby {
    //ArrayList of all users.
    private ArrayList<Player> users;
    //ArrayList of active Players.
    private ArrayList<Player> players;
    //ArrayList of users in a game.
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
        users = new ArrayList<>();
        players = new ArrayList<>();
        gamePlayers = new ArrayList<>();
        choseInGame = false;
    }

    /**
     * Add a new player to the player lobby, return true if successful.
     * @param player the player to add.
     * @return true if successful, false otherwise.
     */
    public boolean addUser(Player player){
        if(!users.contains(player) && player != null){
            users.add(player);
            return true;
        }
        return false;
    }

    /**
     * Adds a Players to the list of Players in Play Mode.
     * @param player the player to add.
     */
    public void addPlayer(Player player){
        if(!players.contains(player) && player != null){
            players.add(player);
        }
    }

    /**
     * Add a Player to the list of users in a game.
     * @param player the player to add.
     */
    public void addGamePlayer(Player player){
        if(!gamePlayers.contains(player) && player != null){
            gamePlayers.add(player);
        }
    }

    /**
     * Gets the list of Users in the playerLobby.
     * @return The list of Users.
     */
    public ArrayList<Player> getUsers() {
        return users;
    }

    /**
     * Gets the list of Players that are in Play Mode.
     * @return The list of Players.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Gets the list of Players that are in an active Game.
     * @return The list of Players in an active Game.
     */
    public ArrayList<Player> getGamePlayers() {
        return gamePlayers;
    }

    /**
     * Returns the list of users in a string array.
     * @return the users in a string array with their username,
     */
    public Player[] playerArray(ArrayList<Player> playerList){
        Player[] playerNames = new Player[playerList.size()];
        for(int i = 0; i < playerList.size(); i++){
            playerNames[i] = playerList.get(i);
        }
        return playerNames;
    }



    /**
     * Gets a Player based on a given name.
     * @param name the name to check.
     * @return the Player with the given name, or null if not found.
     */
    public Player getUser(String name){
        Player[] playerArray = this.playerArray(this.users);
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
    public boolean isInGame(Player player){ return gamePlayers.contains(player); }

    /**
     * Removes an active User.
     * @param player the User removed.
     */
    public void removeUser(Player player){ users.remove(player); }

    /**
     * Removes an active Player.
     * @param player the Player removed.
     */
    public void removePlayer(Player player){ players.remove(player); }

    /**
     * Removes a Player in a Game.
     * @param player the Player removed.
     */
    public void removeGamePlayer(Player player){ gamePlayers.remove(player); }

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
