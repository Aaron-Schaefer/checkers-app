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

    private ArrayList<Row> rows;
    private Piece.Color activeColor;
    //private Player redPlayer;
    //private Player whitePlayer;


    public BoardView(Board board){
        rows = new ArrayList<Row>();
        for(int i =0; i<8; i++){

            rows.add(new Row(i));
        }

    }

    public Piece.Color getActiveColor(){return activeColor;}

    //TODO will need player class to implement the following code

//    private Piece.color getPlayerColor(Player player, BoardModel model) {
//        if (model.getWhitePlayer() == player) {
//            return Piece.color.WHITE;
//        }
//        if (model.getRedPlayer() == player) {
//            return Piece.color.RED;
//        }
//        return null;
//    }

    //Will need the following for checking and getting the players/colors
//    public Player getWhitePlayer(){return whitePlayer;}
//
//    public Player getRedPlayer(){return redPlayer}
//
//    public Player getActivePlayer(){
//        if (activeColor == Piece.Color.RED){
//            return redPlayer;
//        }
//        else{return whitePlayer}
//    }
    @Override
    public Iterator<Row> iterator() {
        return rows.iterator();
    }
}
