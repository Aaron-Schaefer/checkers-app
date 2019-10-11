package com.webcheckers.model;

import com.webcheckers.ui.Piece;
import com.webcheckers.ui.Player;
import com.webcheckers.ui.Space;


/**
 * Purpose: Model of a checkers board
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
     * Creates a board model
     *
     */
    public Board(Player whitePlayer, Player redPlayer){

        boardArray = new Space[BOARD_SIZE][BOARD_SIZE];
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        initializeSpaces();
        //putPieces();
        //print();



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
                    if(r<2) {
                        addPiece(r, c, new Piece(Piece.Type.SINGLE, Piece.Color.WHITE));
                        //System.out.println(boardArray[r][c].getPiece().toString());
                    }
                    else if(r>5)
                        addPiece(r,c,new Piece(Piece.Type.SINGLE, Piece.Color.RED));

                }
            }
        }
    }

//    /**
//     * Puts a piece in a valid space
//     */
//    private void putPieces(){
//
//        for(int r = 0; r<BOARD_SIZE; r++){
//            for(int c= 0; c<BOARD_SIZE; c++){
//                if(r < 2){
//                    if(isSpaceValid(r,c)){
//                        addPiece(r,c,new Piece(Piece.Type.SINGLE, Piece.Color.WHITE));
//                        System.out.println("red piece added");
//                    }
//                }
//                else if (r>5){
//                    addPiece(r,c,new Piece(Piece.Type.SINGLE, Piece.Color.RED));
//                    System.out.println("white piece added");
//                }
//            }
//        }
//    }


    /**
     * Test print of the board to standard output
     *
     */
    public void print(){

        for(int r = 0; r<BOARD_SIZE; r++){
            for(int c =0; c<BOARD_SIZE; c++){

                if(boardArray[r][c].getPiece() != null)
                    System.out.print("["+ boardArray[r][c].getPiece().toString() +"]");

                 else
                    System.out.print("[ ]");
            }
            System.out.println();

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

            backwardsRow[(BOARD_SIZE-i)-1] = temp[i];

        }
        return backwardsRow;
    }

    /**
     *Get the white player
     * @return the white player
     */
    public Player getWhitePlayer(){return whitePlayer; }

    /**
     *Get the red player
     * @return the red player
     */
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

    /**
     *Adds a piece to a location on the board
     * @param row
     * @param col
     * @param piece
     * @return
     */
    public boolean addPiece(int row, int col, Piece piece){

        if(boardArray[row][col].getPiece() == null && isSpaceValid(row, col)){

            boardArray[row][col].addPiece(piece);

        }

        return true;
    }

    /**
     * Gets the space at a row and col
     * @param row
     * @param col
     * @return
     */
    public Space getSpace(int row, int col){

        return boardArray[row][col];
    }

    /**
     *Removes a piece from a specific space
     * @param row
     * @param col
     * @return
     */
    public boolean removePiece(int row, int col){

        if(boardArray[row][col] != null){

            boardArray[row][col].addPiece(null);
            return true;
        }
        else
            return false;

    }

}
