package com.webcheckers.model;

import com.webcheckers.ui.WebServer;

public class MoveValidator {

    public enum MoveValidation{

        VALID, TOOFAR, OCCUPIED, JUMPNEEDED, VALIDJUMP;


    }

    /**
     * Takes a move and validates whether or not it is valid based on American Checker rules
     *
     * @param model The active populated checkers board
     * @param move The move made
     * @return enum of if the move is valid or if not why it is not
     */
    public static MoveValidation validateMove(Board model, Move move){

        final Position startPos = move.getStart();
        final Position endPos = move.getEnd();

        final Space start = model.getSpace(startPos.getRow(),startPos.getCell());
        final Space end = model.getSpace(endPos.getRow(),endPos.getCell());

        final Piece piece = start.getPiece();

        if(!end.isValid())
            return MoveValidation.OCCUPIED;


        int rowChange = Math.abs(startPos.getRow() - endPos.getRow());

        if(rowChange > 2)
            return MoveValidation.TOOFAR;

        //simple move
        if(rowChange == 1){

            System.out.println("Simple move");
            if(teamHasJump(model,start.getPiece().getColor()))
                return MoveValidation.JUMPNEEDED;

            return validateSimpleMove(start.getPiece().getColor(), start.getPiece().getType(),startPos, endPos);

        }

        //jump move
        if(rowChange == 2){

            System.out.println("jump move 1");
            return validateJumpMove(model, start, move);
        }


        return MoveValidation.VALID;

    }

    /**
     * Validates a simple move
     *
     * @param color color of the piece moved
     * @param type type of the pice (King or Single)
     * @param start The start position of the move
     * @param end The end Position of the move
     * @return enum of if the move is valid or if not why it is not
     */
    private static MoveValidation validateSimpleMove(Piece.Color color, Piece.Type type, Position start, Position end){


        int colChange = Math.abs(start.getCell()-end.getCell());
        int rowChange = start.getRow()- end.getRow();
        System.out.println("col change " + colChange);
        System.out.println("row change " + rowChange);

        if(colChange > 1)
            return MoveValidation.TOOFAR;

        if(color.equals(Piece.Color.RED) && type.equals(Piece.Type.SINGLE) ){

            System.out.println("red single");
            if(rowChange == 1) {
                System.out.println("valid");
                return MoveValidation.VALID;
            }
            else
                return MoveValidation.TOOFAR;
        }
        else if(color.equals(Piece.Color.WHITE) && type.equals(Piece.Type.SINGLE)){

            if(rowChange < 0 && rowChange > -2)
                return MoveValidation.VALID;
            else
                return MoveValidation.TOOFAR;

        }
        
       return MoveValidation.TOOFAR;
    }


    private static MoveValidation validateJumpMove(Board model, Space start, Move move){



        if(!teamHasJump(model, start.getPiece().getColor())) {
            System.out.println("team has jump");
            return MoveValidation.TOOFAR;
        }

        if(checkSimpleJump(move,model)){

            System.out.println("check jump move");
            return MoveValidation.VALIDJUMP;
        }
        else
            return MoveValidation.TOOFAR;

    }


    private static boolean teamHasJump(Board model, Piece.Color color){

        for(int r = 0; r < 8; r++){

            for(int c = 0; c < 8; c++){

                if(model.getSpace(r,c).getPiece() != null && model.getSpace(r,c).getPiece().getColor().equals(color)){

                    Position position = new Position(r,c);

                    if(pieceHasJump(position, model, color))
                        return true;
                }

            }

        }
        return false;
    }

    public static boolean pieceHasJump( Position pos, Board model, Piece.Color color){

        int teamOffset = 1;

        if(color.equals(Piece.Color.RED))
            teamOffset = -1;

        int leftCell = pos.getCell()-2;
        int rightCell = pos.getCell() +2;

        int forwardRow = pos.getRow() +2*teamOffset;

        if(forwardRow>7 || forwardRow<0)
            return false;

        if(leftCell<8 && leftCell>0){

            Position end = new Position(forwardRow, leftCell);
            Move move = new Move(pos, end);

            return checkSimpleJump(move,model);


        }

        if(rightCell<8 && rightCell>0){

            Position end = new Position(forwardRow,rightCell);
            Move move = new Move(pos, end);

            return checkSimpleJump(move,model);

        }

        return false;
    }

    private static boolean checkSimpleJump(Move move, Board model){

        Position start = move.getStart();
        Position end = move.getEnd();

        int rowdif = Math.abs(start.getRow()- end.getRow());
        int coldif = Math.abs(start.getCell() - end.getCell());

        if(coldif ==2 && rowdif ==2){

            Position taken = new Position((start.getRow() + end.getRow())/2, (start.getCell() + end.getCell())/2 );
            Piece takenPiece = model.getSpace(taken.getRow(),taken.getCell()).getPiece();
            Piece startPiece = model.getSpace(start.getRow(),start.getCell()).getPiece();

            if(takenPiece == null) {
                return false;
            }
            else if(takenPiece.getColor() == startPiece.getColor()) {
                return false;
            }
            else {
                WebServer.BOARD.addSpaceTaken(model.getSpace(taken.getRow(),taken.getCell()));
                return true;
            }
        }

        return false;
    }

}
