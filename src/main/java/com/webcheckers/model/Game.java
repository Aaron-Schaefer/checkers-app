package com.webcheckers.model;

public class Game {
    private Player whitePlayer;
    private Player redPlayer;
    private Board board;
    private Move recentMove;
    public boolean turnMade = false;

    public Game(Player redPlayer, Player whitePlayer){
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.board = new Board(redPlayer, whitePlayer);
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
            board.removePiece(position.getRow(), position.getCell());
        }
        board.clearPositionsTaken();
    }

}