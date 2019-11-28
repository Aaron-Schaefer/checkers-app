package com.webcheckers.model;

import java.util.*;

/**
 * Class that performs most of the operations for the CPU player
 *
 * @author Zachary Hahn
 */
public class AI {
    private Piece.Color AIColor = Piece.Color.WHITE;
    Player cpu;
    Game game;
    Board board;

    /**
     * Class that decides which move the CPU will choose to do
     *
     * @param game
     * @return move that the cpu will perform
     */
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
        if (possibleMoves.size() > 0) { //if no possible moves return null
            int index = random.nextInt(possibleMoves.size());
            return possibleMoves.get(index); //cpu performs a random move from legal moves
        }
        else {
            return null;
        }
    }

    /**
     * Class to test if the CPU has a mutli jump available
     *
     * @param game
     * @param start start position
     * @return true if multi jump is available false otherwise
     */
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

    /**
     * helper function to make sure a position is inbounds
     * @param r row
     * @param c column
     * @param rinc row increment
     * @param cinc column increment
     * @return true if inbounds false if out of bounds
     */
    private static boolean isInBounds(int r, int c, int rinc, int cinc) {
        return (r+rinc<8 && c+cinc<8 && r+rinc>=0 && c+cinc>=0);
    }

    /**
     * getter for player object
     *
     * @return player object CPU
     */
    public Player getPlayer() {
        return cpu;
    }

    /**
     * Constructor for AI, makes player cpu
     */
    public AI() {
        cpu = new Player("CPU");
    }
}
