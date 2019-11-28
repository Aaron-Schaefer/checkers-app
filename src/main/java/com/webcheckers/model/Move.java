package com.webcheckers.model;

/**
 * Purpose: The move class responsible for
 *
 *
 * @author Aaron Schaefer and Gavin Burris
 */
public class Move {
    //The start Position
    private Position start;
    //The end Position
    private Position end;
    //The Position of the taken Piece
    private Position takenPosition;
    //The Piece taken
    private Piece takenPiece;
    //The Piece moved
    private Piece movedPiece;
    //The valid state given from the MoveValidator
    private MoveValidator.MoveValidation validState;

    /**
     * Initializes the Move
     * @param start The given start Position
     * @param end The given end Position
     */
    public Move(Position start, Position end){
        this.start = start;
        this.end = end;
        this.validState = null;
    }

    /**
     * Gets the Move's start Position
     * @return The start Position
     */
    public Position getStart() {
        return start;
    }

    /**
     * Gets the Move's end Position
     * @return The end Position
     */
    public Position getEnd(){
        return end;
    }

    /**
     * Sets the Move's start Position to the given Position
     * @param start The given start Position
     */
    public void setStart(Position start){ this.start = start;}

    /**
     * Sets the taken Piece's position to the given Position
     * @param taken The given Position of the taken Piece
     */
    public void setTakenPosition(Position taken) {
        this.takenPosition = taken;
    }

    /**
     * Gets the Position of the taken Piece
     * @return The Position of the taken Piece
     */
    public Position getTakenPosition() {
        return takenPosition;
    }

    /**
     * Sets the taken Piece to the given Piece
     * @param takenPiece the taken Piece
     */
    public void setTakenPiece(Piece takenPiece) {
        this.takenPiece = takenPiece;
    }

    /**
     * Gets the taken Piece
     * @return The taken Piece
     */
    public Piece getTakenPiece() {
        return takenPiece;
    }

    /**
     * Sets the Piece moved to the given Piece
     * @param movedPiece The moved Piece
     */
    public void setMovedPiece(Piece movedPiece) {
        this.movedPiece = movedPiece;
    }

    /**
     * Gets the Piece moved
     * @return The moved Piece
     */
    public Piece getMovedPiece() {
        return movedPiece;
    }

    /**
     * Sets the state of the Move to the state assigned
     * by the MoveValidator
     * @param validState The state assigned by the Move validator
     */
    public void setValidState(MoveValidator.MoveValidation validState) {
        this.validState = validState;
    }

    /**
     * Gets the Move's state
     * @return The Move's state
     */
    public MoveValidator.MoveValidation getValidState() {
        return validState;
    }
}
