package com.webcheckers.model;

public class Move {
    private Position start;
    private Position end;

    public Move(Position start, Position end){
        this.start = start;
        this.end = end;
    }

    public Move(){
        this.start = null;
        this.end = null;
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd(){
        return end;
    }
}
