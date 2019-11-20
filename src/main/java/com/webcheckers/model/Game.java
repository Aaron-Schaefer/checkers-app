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
        this.allMoves = new HashMap<>();
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
     * Removes the taken piece from the board
     */
    public void takePiece(){
        Position takenPosition = this.recentMove.getTakenPosition();
        if(takenPosition != null) {
            Piece piece = board.getPiece(takenPosition.getRow(), takenPosition.getCell());
            board.removePiece(takenPosition.getRow(), takenPosition.getCell());
            board.decrementPieces(piece);
        }
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

    public int getNumMoves(){
        return allMoves.size();
    }

    public Move getMove(int numMove){
        return allMoves.get(numMove);
    }

    public void doTurn(Move move){
        Piece piece = this.board.getPiece(move.getStart().getRow(), move.getStart().getCell());
        move.setMovedPiece(piece);
        board.updateBoard(move);
        this.addMove(move);
        this.takePiece();
        this.turnMade = true;
    }
}
