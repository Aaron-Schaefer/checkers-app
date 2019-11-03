package com.webcheckers.model;

import javafx.geometry.Pos;
import spark.ModelAndView;

public class MoveValidator {

    public enum MoveValidation{

        VALID, TOOFAR, OCCUPIED, JUMPNEEDED;


    }

    public static boolean validateMove(Board model, Move move){

        final Position startPos = move.getStart();
        final Position endPos = move.getEnd();

        final Space start = model.getSpace(startPos.getRow(),startPos.getCell());
        final Space end = model.getSpace(endPos.getRow(),endPos.getCell());

        final Piece piece = start.getPiece();

        if(!end.isValid())
            return false;


        int rowChange = Math.abs(startPos.getRow() - endPos.getRow());

        if(rowChange > 2)
            return false;

        //simple move
        if(rowChange == 1){

            if(teamHasJump(model,start.getPiece().getColor()))
                return false;

            return validateSimpleMove(start.getPiece().getColor(), start.getPiece().getType(),startPos, endPos);

        }


        //jump move
        if(rowChange == 2){

            return validateJumpMove();
        }


        return false;
    }

    private static boolean validateSimpleMove(Piece.Color color, Piece.Type type, Position start, Position end){


        int colChange = Math.abs(start.getCell()-end.getCell());
        int rowChange = start.getRow()- end.getRow();

        if(colChange > 1)
            return false;

        if(color.equals(Piece.Color.RED) && type.equals(Piece.Type.SINGLE) ){

            if(rowChange > 0)
                return true;
            else
                return false;
        }
        else if(color.equals(Piece.Color.WHITE) && type.equals(Piece.Type.SINGLE)){

            if(rowChange < 0)
                return true;
            else
                return false;

        }


        return false;
    }


    private static boolean validateJumpMove(){


        return false;
    }


    private static boolean teamHasJump(Board model, Piece.Color color){


        return false;

    }
}
