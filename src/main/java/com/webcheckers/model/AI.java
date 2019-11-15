package com.webcheckers.model;

import java.util.*;

public class AI {
    private Piece.Color AIColor = Piece.Color.WHITE;
    Board board;
    private Move decideMove(Game game) {
        board = game.getBoard();
        ArrayList<Move> possibleMoves = new ArrayList<Move>();
        if (board.getActiveColor() == Piece.Color.WHITE) {
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    for (int rinc = -2; rinc < 3; rinc++) {
                        for (int cinc = -2; cinc < 3; cinc++) {
                            if (rinc != 0 && cinc != 0) {
                                Move testMove = new Move(new Position(r, c), new Position(r+rinc, c+cinc));
                                MoveValidator.MoveValidation isValidMove = new MoveValidator().validateMove(game, testMove);
                                if (isValidMove == MoveValidator.MoveValidation.VALID
                                    || isValidMove == MoveValidator.MoveValidation.VALIDJUMP) {
                                    possibleMoves.append(testMove);
                                }
                            }
                        }
                    }
                }
            }
            return possibleMoves.get(Random.nextInt(possibleMoves.size()));
        }
        else {
            return null;
        }
    }
}
