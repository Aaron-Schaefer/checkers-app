package com.webcheckers.model;

public class MoveValidator {

    public enum MoveValidation{

        VALID, TOOFAR, OCCUPIED, JUMPNEEDED;


    }

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

            if(teamHasJump(model,start.getPiece().getColor()))
                return MoveValidation.JUMPNEEDED;

            return validateSimpleMove(start.getPiece().getColor(), start.getPiece().getType(),startPos, endPos);

        }

        //jump move
        if(rowChange == 2){

            return validateJumpMove();
        }


        return MoveValidation.VALID;

    }

    private static MoveValidation validateSimpleMove(Piece.Color color, Piece.Type type, Position start, Position end){


        int colChange = Math.abs(start.getCell()-end.getCell());
        int rowChange = start.getRow()- end.getRow();

        if(colChange > 1)
            return MoveValidation.TOOFAR;

        if(color.equals(Piece.Color.RED) && type.equals(Piece.Type.SINGLE) ){

            if(rowChange > 0)
                return MoveValidation.VALID;
            else
                return MoveValidation.TOOFAR;
        }
        else if(color.equals(Piece.Color.WHITE) && type.equals(Piece.Type.SINGLE)){

            if(rowChange < 0)
                return MoveValidation.VALID;
            else
                return MoveValidation.TOOFAR;

        }
        
       return MoveValidation.TOOFAR;
    }


    private static MoveValidation validateJumpMove(){


        return MoveValidation.VALID;
    }


    private static boolean teamHasJump(Board model, Piece.Color color){


        return false;

    }
}
