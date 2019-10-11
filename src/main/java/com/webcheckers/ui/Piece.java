package com.webcheckers.ui;

/**
 * A single checkers piece
 * Can be a single piece or a king
 * Can be red or white
 *
 * @author Ries Scerbin
 */

public class Piece {
    private Type type;
    private Color color;
    //Type of chest piece.
    public enum Type{ SINGLE, KING}
    //Color of chest piece.
    public enum Color{ RED, WHITE}

    /**
     *Constructor for a piece
     *
     * @param type
     * @param color
     */
    public Piece(Type type, Color color){
        this.type = type;
        this.color = color;
    }

    /**
     *Gets the type of the piece
     * @return
     */
    public Type getType(){ return this.type; }

    /**
     *Gets the color of the piece
     * @return
     */
    public Color getColor(){
        return this.color;
    }

    @Override
    public String toString() {
        return "" + color + "";
    }
}
