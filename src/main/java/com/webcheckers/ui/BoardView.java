package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.Iterator;

public class BoardView implements Iterable {

    private ArrayList<Row> rows;

    public BoardView(){

        for(int i =0; i<8; i++){


            rows.add(new Row(i));
        }

    }

    @Override
    public Iterator<Row> iterator() {
        return null;
    }
}
