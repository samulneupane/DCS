 package pieces;

import board.Board;
import board.Position;
import enums.Color;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Position> possibleMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        int[][] offsets = {
            {-2, -1}, {-2, 1},
            {-1, -2}, {-1, 2},
            {1, -2}, {1, 2},
            {2, -1}, {2, 1}
        };

        int row = position.getRow();
        int col = position.getColumn();

        for (int[] offset : offsets) {
            Position p = new Position(row + offset[0], column + offset[1]);

            if (board.isInsideBoard(p)) {
                if (board.getPiece(p) == null || board.getPiece(p).getColor() != color) {
                    moves.add(p);
                }
            }
        }

        return moves;
    }

    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "wn" : "bn";
    }
}
