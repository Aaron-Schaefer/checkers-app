package com.webcheckers.model;

import com.webcheckers.model.Board;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import com.webcheckers.model.Row;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Purpose: A single Board view
 *
 *
 * @author Aaron Schaefer
 */

public class BoardView implements Iterable {

    //An ArrayList of the rows on the Board.
    private ArrayList<Row> rows;

    /**
     *Constructor for the boardview
     * @param board The board to be viewed
     * @param player The player viewing thee board
     */
    public BoardView(Board board, Player player){
        rows = new ArrayList<>();

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

    /**
     * Gets the players color based on the piece they are playing
     * @param player The player playing on the board
     * @param model The board being played on
     * @return the players piece color
     */
    private Piece.Color getPlayerColor(Player player, Board model) {
        if (model.getWhitePlayer().equals(player) ) {
            return Piece.Color.WHITE;
        }
        if (model.getRedPlayer().equals(player)) {
            return Piece.Color.RED;
        }
        return null;
    }

    /**
     *Creates a java iterable object for the row
     * @return the iterator
     */
    @Override
    public Iterator<Row> iterator() {
        return rows.iterator();
    }
}
