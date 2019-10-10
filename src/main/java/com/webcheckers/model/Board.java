package com.webcheckers.model;

import com.webcheckers.ui.Piece;
import com.webcheckers.ui.Player;
import com.webcheckers.ui.Space;


/**
 * Model of a checkers board
 *
 *
 * @author Aaron Schaefer
 */
public class Board {

    private static final int BOARD_SIZE = 8;
    private Space boardArray[][];
    private Player whitePlayer;
    private Player redPlayer;


    /**
     * Constructor for the Board
     *
     */
    public Board(Player whitePlayer, Player redPlayer){

        boardArray = new Space[BOARD_SIZE][BOARD_SIZE];
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        initializeSpaces();
        putPieces();

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

    private void putPieces(){

        for(int r = 0; r<BOARD_SIZE; r++){
            for(int c= 0; c<BOARD_SIZE; c++){
                if(r < 2){
                    if(isSpaceValid(r,c)){
                        addPiece(r,c,new Piece(Piece.Type.SINGLE, Piece.Color.RED));

                    }
                }
                else if (r>5){
                    addPiece(r,c,new Piece(Piece.Type.SINGLE, Piece.Color.WHITE));
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

    public Player getWhitePlayer(){return whitePlayer; }

    public Player getRedPlayer(){return redPlayer;}

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

            boardArray[row][col].addPiece(piece);

        }

        return true;
    }

    public Space getSpace(int row, int col){

        return boardArray[row][col];
    }


    public boolean removePiece(int row, int col){

        if(boardArray[row][col] != null){

            boardArray[row][col].addPiece(null);
            return true;
        }
        else
            return false;

    }

}
