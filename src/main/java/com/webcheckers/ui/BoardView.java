package com.webcheckers.ui;

import com.webcheckers.model.Board;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A single Board view
 *
 *
 * @author Aaron Schaefer
 */

public class BoardView implements Iterable {

    private static final int BOARD_SIZE = 8;
    private ArrayList<Row> rows;
    private Piece.Color activeColor;
    private Player redPlayer;
    private Player whitePlayer;


    public BoardView(Board board, Player player){
        rows = new ArrayList<Row>();

        Piece.Color playersColor = getPlayerColor(player, board);

        if(playersColor == Piece.Color.RED) {
            for (int i = 0; i < 8; i++) {
                rows.add(new Row(i, board.getRow(i)));
            }
        }
        else if(playersColor == Piece.Color.WHITE){
            for(int i = 7; i>=0; i--){
                rows.add(new Row(i, board.getBackwardsRow(i)));
            }

        }
    }

    public Piece.Color getActiveColor(){return activeColor;}

    private Piece.Color getPlayerColor(Player player, Board model) {
        if (model.getWhitePlayer().equals(player) ) {
            return Piece.Color.WHITE;
        }
        if (model.getRedPlayer().equals(player)) {
            return Piece.Color.RED;
        }
        return null;
    }


    public Player getWhitePlayer(){return whitePlayer;}

    public Player getRedPlayer(){return redPlayer;}

    public Player getActivePlayer(){
        if (activeColor == Piece.Color.RED){
            return redPlayer;
        }
        else
            return whitePlayer;
    }
    @Override
    public Iterator<Row> iterator() {
        return rows.iterator();
    }
}
