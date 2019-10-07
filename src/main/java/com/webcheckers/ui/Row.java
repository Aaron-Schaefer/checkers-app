package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.Iterator;

public class Row implements Iterable{
    private int index;
    private ArrayList<Space> spaces;

    public int getIndex() {
        return index;
    }

    public Row(int index){

        this.index = index;
        for(int i =0; i<8; i++){

            spaces.add(new Space(i));
        }
    }

    @Override
    public Iterator<Space> iterator() {
        return null;
    }
}
