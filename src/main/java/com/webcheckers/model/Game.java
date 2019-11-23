package com.webcheckers.model;

import java.util.*;

public class Game {
    //The red Player
    private Player redPlayer;
    //The white Player
    private Player whitePlayer;
    //The winner
    private Player winner;
    //The resigned Player
    private Player resignPlayer;
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

    /**
     * Initializes a game
     * @param redPlayer The red Player
     * @param whitePlayer The white Player
     * @param gameID The game's ID
     */
    public Game(Player redPlayer, Player whitePlayer, int gameID){
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.winner = null;
        this.resignPlayer = null;
        this.board = new Board(redPlayer, whitePlayer);
        this.gameID = gameID;
        this.allMoves = new HashMap<Integer, Move>();
        this.turnMade = false;
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
     * Sets the winner to the given Player
     * @param winner the winner
     */
    public Player setWinner(Player winner){
        this.winner = winner;
        return this.winner;
    }

    /**
     * Gets the winner of the game.
     * @return the winner
     */
    public Player getWinner(){ return this.winner; }

    public void setResignPlayer(Player resignPlayer) {
        this.resignPlayer = resignPlayer;
    }


    public Player getResignPlayer() {
        return resignPlayer;
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
        System.out.println(allMoves.size());
        allMoves.put(allMoves.size(), move);
    }

    public int getNumMoves(){
        return allMoves.size();
    }

    public Move getMove(int numMove) {
        return allMoves.get(numMove);
    }
          
    public Player getOpponent(Player player){
        return (player == this.redPlayer) ? this.whitePlayer : this.redPlayer;
    }

    public void doTurn(Move move){
        Piece piece = this.board.getPiece(move.getStart().getRow(), move.getStart().getCell());
        move.setMovedPiece(piece);
        board.updateBoard(move);
        this.addMove(move);
        this.takePiece();
        this.turnMade = true;
        board.validateSpaces();
    }
}
