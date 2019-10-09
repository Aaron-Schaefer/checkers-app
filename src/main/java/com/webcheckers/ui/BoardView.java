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

    public BoardView(){
        rows = new ArrayList<Row>();
        for(int i =0; i<8; i++){

            rows.add(new Row(i));
        }

    }

    @Override
    public Iterator<Row> iterator() {
        return rows.iterator();
    }
}
