package com.webcheckers.model;

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


    public Board(){

        boardArray = new Space[BOARD_SIZE][BOARD_SIZE];


    }

    public boolean isSpaceValid(int row, int col ){

        return (row%2==0 || col%2==0) && !(row%2==0 && col%2==0);
    }

    public boolean addPiece(int row, int col, CheckersPiece piece){

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
