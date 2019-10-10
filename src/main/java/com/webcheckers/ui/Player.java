package com.webcheckers.ui;

/**
 * Language: Java
 * Author: An Chang (Mark), Gavin Burris.
 * Purpose: A class to represent a player.
 */
public class Player {

    //The username of the player.
    private String name;

    /**
     * Constructor for the player.
     * @param name the name of the user.
     */
    public Player(String name){
        this.name = name;
    }

    /**
     * Method to return the username of the player.
     * @return the username of the player.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Check if the player is equal to another player (only checks username).
     * @param object The object to compare to.
     * @return  true if they are the same player, false otherwise.
     */
    @Override
    public boolean equals(Object object){

        if (object ==this) return true;

        if (!(object instanceof Player)) return false;

        final Player that = (Player) object;

        return (this.name.equals(that.name));

    }
}
