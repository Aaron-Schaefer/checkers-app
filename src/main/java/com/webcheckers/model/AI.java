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
                                && r+rinc >= 0
                                && r+rinc < 8
                                && c+cinc >= 0
                                && c+cinc < 8
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
        int index = random.nextInt(possibleMoves.size());
        return possibleMoves.get(index);
    }

    public boolean jumpAvailable(Game game, Move move) {
        Position start = move.getEnd();
        for (int rinc = -2; rinc < 3; rinc+=2) {
            for (int cinc = -2; cinc < 3; cinc+=2) {
                if (rinc != 0 && cinc != 0) {
                    Move testMove = new Move(start, new Position(start.getRow()+rinc, start.getCell()+cinc));
                    MoveValidator.MoveValidation isValidMulitJump = MoveValidator.validateMove(game, testMove);
                    if (isValidMulitJump == MoveValidator.MoveValidation.VALID
                        || isValidMulitJump == MoveValidator.MoveValidation.VALIDJUMP) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public Player getPlayer() {
        return cpu;
    }

    public AI() {
        cpu = new Player("CPU");
    }
}
