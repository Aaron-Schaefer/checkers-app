package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * An iterable representation of a row on a checkers board
 *
 * @author Aaron Schaefer
 */

public class Row implements Iterable{
    //The index of the row
    private int index;
    //The spaces in the row
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
     * @param index The index of the row
     * @param spaceArray The spaces in the row
     */
    public Row(int index, Space[] spaceArray){

        spaces = new ArrayList<>();
        this.index = index;
        spaces.addAll(Arrays.asList(spaceArray));
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
