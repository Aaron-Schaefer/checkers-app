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

    public enum Type{ SINGLE, KING}

    public enum Color{ RED, WHITE}

    public Piece(Type type, Color color){
        this.type = type;
        this.color = color;
    }
    public Type getType(){ return this.type; }

    public Color getColor(){
        return this.color;
    }
}
