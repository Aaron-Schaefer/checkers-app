package com.webcheckers.ui;

/** A single space on a checkers board
 *
 * @author Aaron Schaefer
 */

public class Space {
    private int cellIdx;
    private boolean isViable;
    public Space(int index, boolean isViable){

        this.cellIdx = index;
        this.isViable = isViable;
    }

    public int getCellIdx() {
        return cellIdx;
    }

    public boolean isValid(){
        return isViable;
    }

    public Piece getPiece(){
        return null;
    }
}
