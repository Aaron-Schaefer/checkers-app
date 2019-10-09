package com.webcheckers.model;

import com.webcheckers.ui.Piece;
import com.webcheckers.ui.Row;
import com.webcheckers.ui.Space;
import javafx.scene.layout.BackgroundSize;

/**
 * Model of a checkers board
 *
 *
 * @author Aaron Schaefer
 */
public class Board {

    private static final int BOARD_SIZE = 8;
    private Space boardArray[][];


    /**
     * Constructor for the Board
     *
     */
    public Board(){

        boardArray = new Space[BOARD_SIZE][BOARD_SIZE];
        initializeSpaces();


    }

    /**
     * Initializes all the spaces on the board with their respective validity
     *
     */
    private void initializeSpaces(){

        for(int r = 0; r< BOARD_SIZE; r++){
            for (int c = 0; c< BOARD_SIZE; c++){
                if((c+r)%2 ==0){

                    boardArray[r][c] = new Space(c, false);
                }
                else if( (c+r)%2 != 0){
                    boardArray[r][c] = new Space(c, true);
                }
            }
        }
    }

    /**
     * Gets a row from the board model
     *
     * @param row (index)
     * @return row of spaces
     */
    public Space[] getRow(int row){ return boardArray[row];}

    /**
     * Flips a row in the model from left to right so it can be shown in the view corectly
     *
     * @param row (index)
     * @return  backwardsRow (A row but flipped)
     */
    public Space[] getBackwardsRow(int row){

        Space[] temp = this.getRow(row);
        Space[] backwardsRow = new Space[BOARD_SIZE];

        for(int i = 0; i<BOARD_SIZE; i++){

            backwardsRow[(BOARD_SIZE-i)-1] = temp[i-1];

        }
        return backwardsRow;
    }

    /**
     * Checks if space is a valid space for a piece to exist
     *
     * @param row (index)
     * @param col (index)
     * @return true if valid(black space) false if not(white space)2w
     */
    public boolean isSpaceValid(int row, int col ){

        return boardArray[row][col].isValid() && boardArray[row][col].getPiece() == null;
    }

    public boolean addPiece(int row, int col, Piece piece){

        if(boardArray[row][col] == null && isSpaceValid(row, col)){

            //TODO
            //boardArray[row][col] = ;

        }

        return true;
    }

    public Space getSpace(int row, int col){

        return boardArray[row][col];
    }

    //TODO
    /*public boolean removePiece(int row, int col){

        if(boardArray[row][col] != null){

            boardArray[row][col] = null;
            return true;
        }
        else
            return false;

    }*/

}
