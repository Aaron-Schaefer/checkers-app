package com.webcheckers.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Purpose: Model of a checkers board
 *
 * @author Aaron Schaefer
 */
public class Board {

    //The dimension of the board
    private static final int BOARD_SIZE = 8;
    //The matrix of Spaces on the board
    private Space[][] boardArray;
    //The white Player
    private Player whitePlayer;
    //The red Player
    private Player redPlayer;
    //The active color on the board
    private Piece.Color activeColor;
    //The list of Positions where Pieces have been taken
    private List<Position> positionsTaken;
    //Number of white pieces on the board.
    private int whitePieces;
    //Number of red pieces on the board.
    private int redPieces;


    /**
     * Constructor for the Board
     * Creates a board model
     *
     */
    public Board(Player redPlayer, Player whitePlayer){

        boardArray = new Space[BOARD_SIZE][BOARD_SIZE];
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.activeColor = Piece.Color.RED;
        this.positionsTaken = new ArrayList<>();
        this.whitePieces = 12;
        this.redPieces = 12;
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
                    if(r<3) {
                        addPiece(r, c, new Piece(Piece.Type.SINGLE, Piece.Color.WHITE));
                        //System.out.println(boardArray[r][c].getPiece().toString());
                    }
                    else if(r>4)
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
     * @param row The row the piece is added to
     * @param col The column the piece is added to
     * @param piece The piece added
     */
    public void addPiece(int row, int col, Piece piece){

        if(boardArray[row][col].getPiece() == null && isSpaceValid(row, col)){

            boardArray[row][col].addPiece(piece);

        }

    }

    /**
     * Gets the space at a row and col
     * @param row The row of the space
     * @param col The column of the space
     * @return The space
     */
    public Space getSpace(int row, int col){

        return boardArray[row][col];
    }

    /**
     *Removes a piece from a specific space
     * @param row The row of the removed piece
     * @param col The column of the removed piece
     */
    public void removePiece(int row, int col){
        if(boardArray[row][col] != null){
            boardArray[row][col].addPiece(null);
        }
    }

    /**
     * Gets the piece at a certain row and column
     * @param row the row the piece is at
     * @param col the column the piece is at
     * @return the piece at the specified row and column
     */
    public Piece getPiece(int row, int col){
        Space space = this.getSpace(row, col);
        return space.getPiece();
    }

    /**
     * Changes thee active color
     */
    public void changeActiveColor(){
        this.activeColor = (this.activeColor == Piece.Color.RED) ? Piece.Color.WHITE :Piece.Color.RED;
    }

    /**
     * Gets the active color
     * @return the active color
     */
    public Piece.Color getActiveColor(){
        return this.activeColor;
    }

    /**
     * Adds a taken position to the list of taken positions.
     * @param position the position of the taken piece.
     */
    public void addPositionTaken(Position position){
        positionsTaken.add(position);
    }

    /**
     * Clears the list of taken positions
     */
    public void clearPositionsTaken() { positionsTaken.clear(); }

    /**
     * Gets the list of taken positions
     * @return the list of taken positions
     */
    public List<Position> getPositionsTaken(){
        return this.positionsTaken;
    }

    public boolean noPieces() {
        return (this.whitePieces == 11 || this.redPieces == 11);
    }

    public String getPieces(){
        return "White: " + this.whitePieces + "\nRed: " + this.redPieces;
    }

    public void decrementPieces(Piece piece){
        Piece.Color color = piece.getColor();
        if(color == Piece.Color.RED){
            this.redPieces--;
        }
        else{
            this.whitePieces--;
        }
    }

    /**
     * Updates the game board when a move has been made. This takes account
     * of the new move changes the piece to a king piece if it reaches the
     * end of the board
     * @param move The move made
     */
    public void updateBoard(Move move){
        Position start = move.getStart();
        Position end = move.getEnd();
        Piece piece = this.getPiece(start.getRow(), start.getCell());
        this.removePiece(start.getRow(), start.getCell());
        if((end.getRow() == 0 && piece.getColor() == Piece.Color.RED)
                || (end.getRow() == 7 && piece.getColor() == Piece.Color.WHITE)) {
            piece.setTypeKing();
        }
        this.addPiece(end.getRow(), end.getCell(), piece);
    }
}
