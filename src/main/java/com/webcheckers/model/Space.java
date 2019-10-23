package com.webcheckers.model;

import com.webcheckers.model.Piece;

/** A single space on a checkers board
 *
 * @author Aaron Schaefer
 */

public class Space {
    private int cellIdx;
    private Piece piece;
    private boolean isViable;

    /**
     * Constructor for a space
     * @param index The index of the space
     * @param isViable If the space is viable
     */
    public Space(int index, boolean isViable){

        this.cellIdx = index;
        this.isViable = isViable;
    }

    /**
     * Method to get a specific spaces ID or index
     * @return the spaces ID
     */
    public int getCellIdx() {
        return cellIdx;
    }

    /**
     * Checks if the space can receive a piece
     * @return true if is viable, false otherwise
     */
    public boolean isValid(){
        return isViable;
    }

    /**
     * Method to get a piece
     * @return the piece
     */
    public Piece getPiece(){
        return piece;
    }

    /**
     *Adds a piece
     * @param piece The piece added
     */
    public void addPiece(Piece piece){
            this.piece = piece;
    }
}
