package com.webcheckers.model;

/**
 * Purpose: The move class responsible for
 *
 *
 * @author Aaron Schaefer
 */
public class Move {
    private Position start;
    private Position end;
    private Position takenPosition;
    private Piece takenPiece;
    private MoveValidator.MoveValidation validState;

    public Move(Position start, Position end){
        this.start = start;
        this.end = end;
        this.validState = null;
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd(){
        return end;
    }

    public void setTakenPosition(Position taken) {
        this.takenPosition = taken;
    }

    public Position getTakenPosition() {
        return takenPosition;
    }

    public void setTakenPiece(Piece takenPiece) {
        this.takenPiece = takenPiece;
    }

    public Piece getTakenPiece() {
        return takenPiece;
    }

    public void setValidState(MoveValidator.MoveValidation validState) {
        this.validState = validState;
    }

    public MoveValidator.MoveValidation getValidState() {
        return validState;
    }
}
