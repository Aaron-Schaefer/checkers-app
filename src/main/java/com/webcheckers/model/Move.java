package com.webcheckers.model;

public class Move {
    private Position start;
    private Position end;
    private MoveValidator.MoveValidation validState;

    public Move(Position start, Position end){
        this.start = start;
        this.end = end;
        this.validState = null;
    }

    public Move(){
        this.start = null;
        this.end = null;
        this.validState = null;
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd(){
        return end;
    }

    public void setValidState(MoveValidator.MoveValidation validState) {
        this.validState = validState;
    }

    public MoveValidator.MoveValidation getValidState() {
        return validState;
    }
}
