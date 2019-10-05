package com.webcheckers.ui;

import java.util.Iterator;

public class Row implements Iterator{
    private int index;

    public int getIndex() {
        return index;
    }

    @Override
    public Iterator<Space> iterator() {
        return null;
    }
}
