package com.webcheckers.model;

public class Game {
    private Player whitePlayer;
    private Player redPlayer;
    private Board board;
    private Move recentMove;

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
}
