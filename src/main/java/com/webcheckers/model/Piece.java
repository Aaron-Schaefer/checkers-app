package com.webcheckers.model;

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
     * @param type The type of the piece
     * @param color The color of the piece
     */
    public Piece(Type type, Color color){
        this.type = type;
        this.color = color;
    }

    /**
     *Gets the type of the piece
     * @return Return the type of the piece
     */
    public Type getType(){ return this.type; }

    /**
     *Gets the color of the piece
     * @return Returns the color of the piece
     */
    public Color getColor(){
        return this.color;
    }

    public void setTypeKing(){
        this.type = Type.KING;
    }

    @Override
    public String toString() {
        return "" + color + "";
    }
}
