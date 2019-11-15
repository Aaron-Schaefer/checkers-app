package com.webcheckers.model;

import java.util.*;

public class Game {
    //The red Player
    private Player redPlayer;
    //The white Player
    private Player whitePlayer;
    //The game board
    private Board board;
    //The most recent move
    private Move recentMove;
    //The game's ID
    private int gameID;
    //A map of all the moves made in the game
    private Map<Integer, Move> allMoves;
    //Boolean value for if a turn was made
    private boolean turnMade;
    //Boolean value for if the game was resigned
    private boolean resigned;

    /**
     * Initializes a game
     * @param redPlayer The red Player
     * @param whitePlayer The white Player
     * @param gameID The game's ID
     */
    public Game(Player redPlayer, Player whitePlayer, int gameID){
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.board = new Board(redPlayer, whitePlayer);
        this.gameID = gameID;
        this.allMoves = new HashMap<Integer, Move>();
        this.turnMade = false;
        this.resigned = false;
    }

    /**
     * Gets the red Player
     * @return the red Player
     */
    public Player getRedPlayer() { return this.redPlayer; }

    /**
     * Gets the white Player
     * @return the white Player
     */
    public Player getWhitePlayer() {
        return this.whitePlayer;
    }

    /**
     * Sets the most recent move to the given move
     * @param move the recent move
     */
    public void setRecentMove(Move move){
        this.recentMove = move;
    }

    /**
     * Gets the recent move
     * @return the recent move
     */
    public Move getRecentMove(){
        return this.recentMove;
    }

    /**
     * Gets the game board
     * @return the game board
     */
    public Board getBoard(){
        return this.board;
    }

    /**
     * Returns the unique game ID.
     * @return the game ID
     */
    public int getGameID(){
        return this.gameID;
    }

    /**
     * Sets if there was a turn made to true or false
     * @param turnMade boolean value for if a turn was made
     */
    public void setTurnMade(boolean turnMade){
        this.turnMade = turnMade;
    }

    /**
     * Checks if a turn was made
     * @return if a turn was made
     */
    public boolean isTurnMade() {
        return this.turnMade;
    }

    /**
     * Sets the resigned boolean to true
     */
    public void makeResigned(){
        this.resigned = true;
    }

    /**
     * Checks if the game has been resigned
     * @return if the game has been resigned
     */
    public boolean isResigned(){
        return this.resigned;
    }

    /**
     * A helper function that makes a list of both players
     * @return the player list
     */
    public List<Player> playerList(){
        return new ArrayList<>(Arrays.asList(this.redPlayer, this.whitePlayer));
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
        Piece piece = board.getPiece(start.getRow(), start.getCell());
        board.removePiece(start.getRow(), start.getCell());
        if((end.getRow() == 0 && piece.getColor() == Piece.Color.RED)
                || (end.getRow() == 7 && piece.getColor() == Piece.Color.WHITE)) {
            piece.setTypeKing();
        }
        board.addPiece(end.getRow(), end.getCell(), piece);
    }

    /**
     * Ends the turn by switching the active color and removes the taken
     * pieces from the board
     */
    public void endTurn(){
        board.changeActiveColor();
        for(Position position : board.getPositionsTaken()){
            Piece piece = board.getPiece(position.getRow(), position.getCell());
            board.removePiece(position.getRow(), position.getCell());
            board.decrementPieces(piece);
        }
        board.clearPositionsTaken();
    }

    /**
     * Checks if the game is over
     * @return if the game is over
     */
    public boolean isGameOver(){
        return this.board.noPieces();
    }

    /**
     * Adds a move to the map of moves
     * @param move the move made
     */
    public void addMove(Move move){
        allMoves.put(allMoves.size(), move);
    }
}
