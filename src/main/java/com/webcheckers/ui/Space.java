package com.webcheckers.ui;

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
     * @param index
     * @param isViable
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
     * @param piece
     */
    public void addPiece(Piece piece){
        if(piece != null)
            this.piece = piece;
    }
}
