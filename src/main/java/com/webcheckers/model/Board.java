package com.webcheckers.model;

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


    public Board(){

        boardArray = new Space[BOARD_SIZE][BOARD_SIZE];
        initializeSpaces();


    }

    private void initializeSpaces(){

        for(int r = 0; r< BOARD_SIZE; r++){
            for (int c = 0; c< BOARD_SIZE; c++){
                if(r%2 == 0){
                    if(c%2 == 0)
                    boardArray[r][c] = new Space(c,false );
                    else
                        boardArray[r][c] = new Space(c, true);
                }
                else {
                    if(c%2 == 0)
                        boardArray[r][c] = new Space(c, true);
                    else
                        boardArray[r][c] = new Space(c, false);
                }
            }
        }
    }

    public Space[] getRow(int row){ return boardArray[row];}

    public boolean isSpaceValid(int row, int col ){

        return boardArray[row][col].isValid() && boardArray[row][col].getPiece() == null;
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
