package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * An iterable representation of a row on a checkers board
 *
 * @author Aaron Schaefer
 */

public class Row implements Iterable{
    private int index;
    private ArrayList<Space> spaces;

    /**
     *Gets the index of a row
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     *Constructor for Row
     * @param index
     * @param spaceArray
     */
    public Row(int index, Space[] spaceArray){

        spaces = new ArrayList<Space>();
        this.index = index;
        for(int i =0; i<8; i++){

            spaces.add(spaceArray[i]);
        }
    }

    /**
     *Creates an iterator for the row class
     * @return the iterator
     */
    @Override
    public Iterator<Space> iterator() {
        return spaces.iterator();
    }
}
