package com.webcheckers.model;

import java.util.*;

public class AI {
    private Piece.Color AIColor = Piece.Color.WHITE;
    Player cpu;
    Game game;
    Board board;
    protected static Move decideMove(Game game) {
        ArrayList<Move> possibleMoves = new ArrayList<Move>();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                for (int rinc = -2; rinc < 3; rinc++) {
                    for (int cinc = -2; cinc < 3; cinc++) {
                        if (rinc != 0 && cinc != 0 && (rinc+cinc)%2 == 0) {
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
        Random random = new Random();
        return possibleMoves.get(random.nextInt(possibleMoves.size()));
    }

    public Player getPlayer() {
        return cpu;
    }

    public AI() {
        cpu = new Player("CPU");
    }
}
