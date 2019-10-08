package com.webcheckers.model;

/**
 * Model of a checkers board
 *
 *
 * @author Aaron Schaefer
 */
public class Board {

    private CheckersPiece boardArray[][];

    public Board(){

        boardArray = new CheckersPiece[8][8];

    }

    public boolean isSpaceValid(int row, int col ){

        return (row%2==0 || col%2==0) && !(row%2==0 && col%2==0);
    }

    public boolean addPiece(int row, int col, CheckersPiece piece){

        if(boardArray[row][col] == null && isSpaceValid(row, col)){

            boardArray[row][col] = piece;

        }

        return true;
    }

    public CheckersPiece getPiece(int row, int col){

        return boardArray[row][col];
    }

    public boolean removePiece(int row, int col){

        if(boardArray[row][col] != null){

            boardArray[row][col] = null;
            return true;
        }
        else
            return false;

    }

}
