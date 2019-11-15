package com.webcheckers.model;

import java.util.*;

public class Game {
    private Player whitePlayer;
    private Player redPlayer;
    private Board board;
    private Move recentMove;
    private Map<Integer, Move> allMoves;
    private boolean turnMade;
    private boolean resigned;
    private int gameID;

    public Game(Player redPlayer, Player whitePlayer, int gameID){
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.board = new Board(redPlayer, whitePlayer);
        this.gameID = gameID;
        this.allMoves = new HashMap<Integer, Move>();
        this.turnMade = false;
        this.resigned = false;
    }

    public Player getRedPlayer() { return redPlayer; }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public void setRecentMove(Move move){
        this.recentMove = move;
    }

    public Move getRecentMove(){
        return this.recentMove;
    }

    public Board getBoard(){
        return this.board;
    }

    public void addPositionTaken(Position taken){
        board.addPositionTaken(taken);
    }

    public void setTurnMade(boolean turnMade){
        this.turnMade = turnMade;
    }

    public boolean isTurnMade() {
        return turnMade;
    }

    public void makeResigned(){
        this.resigned = true;
    }

    public boolean isResigned(){
        return this.resigned;
    }

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

    public void endTurn(){
        board.changeActiveColor();
        for(Position position : board.getPositionsTaken()){
            Piece piece = board.getPiece(position.getRow(), position.getCell());
            board.removePiece(position.getRow(), position.getCell());
            board.decrementPieces(piece);
        }
        board.clearPositionsTaken();
    }

    public boolean isGameOver(){
        return this.board.noPieces();
    }

    public void addMove(Move move){
        System.out.println(allMoves.size());
        allMoves.put(allMoves.size(), move);
    }

    /**
     * Returns the unique game ID.
     * @return
     */
    public int getGameID(){
        return this.gameID;
    }
}
