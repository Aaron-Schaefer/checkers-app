package com.webcheckers.model;

/**
 * Language: Java
 * @Author: Gavin Burris.
 * Purpose: Class the represents a Position on the Game Board.
 */
public class Position {
    //The row index
    private int row;
    //The cell index
    private int cell;

    /**
     * Creates a Position
     * @param row the row of the position
     * @param cell the cell of the position
     */
    public Position(int row, int cell){
        this.row = row;
        this.cell = cell;
    }

    /**
     * Gets the index of the Position's row
     * @return the row's index
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the index of the Position's cell
     * @return the cell's index
     */
    public int getCell() {
        return cell;
    }
}
