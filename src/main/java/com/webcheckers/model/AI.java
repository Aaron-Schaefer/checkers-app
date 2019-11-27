package com.webcheckers.model;

import java.util.*;

public class AI {
    private Piece.Color AIColor = Piece.Color.WHITE;
    Player cpu;
    Game game;
    Board board;
    public static Move decideMove(Game game) {
        ArrayList<Move> possibleMoves = new ArrayList<Move>();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                for (int rinc = -2; rinc < 3; rinc++) {
                    for (int cinc = -2; cinc < 3; cinc++) {
                        if (       rinc != 0
                                && cinc != 0
                                && ((rinc+cinc)%2 == 0)
                                && isInBounds(r, c, rinc, cinc)
                                && (game.getBoard().getPiece(r, c) != null)
                                && (game.getBoard().getPiece(r, c).getColor().equals(Piece.Color.WHITE))
                                && (game.getBoard().getPiece(r+rinc,c+cinc) == null)) {
                            Move testMove = new Move(new Position(r, c), new Position(r+rinc, c+cinc));
                            MoveValidator.MoveValidation isValidMove = MoveValidator.validateMove(game, testMove);
                            if (isValidMove == MoveValidator.MoveValidation.VALID
                                || isValidMove == MoveValidator.MoveValidation.VALIDJUMP) {
                                possibleMoves.add(testMove);
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Possible Moves: " + possibleMoves.size());
        Random random = new Random();
        if (possibleMoves.size() > 0) {
            int index = random.nextInt(possibleMoves.size());
            return possibleMoves.get(index);
        }
        else {
            return null;
        }
    }

    public static boolean jumpAvailable(Game game, Position start) {
        for (int rinc = -2; rinc < 3; rinc+=2) {
            for (int cinc = -2; cinc < 3; cinc+=2) {
                if (rinc != 0 && cinc != 0 && isInBounds(start.getRow(), start.getCell(), rinc, cinc)) {
                    Move testMove = new Move(start, new Position(start.getRow()+rinc, start.getCell()+cinc));
                    MoveValidator.MoveValidation isValidMultiJump = MoveValidator.validateMove(game, testMove);
                    if (isValidMultiJump == MoveValidator.MoveValidation.VALID
                        || isValidMultiJump == MoveValidator.MoveValidation.VALIDJUMP) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isInBounds(int r, int c, int rinc, int cinc) {
        return (r+rinc<8 && c+cinc<8 && r+rinc>=0 && c+cinc>=0);
    }

    public Player getPlayer() {
        return cpu;
    }

    public AI() {
        cpu = new Player("CPU");
    }
}
