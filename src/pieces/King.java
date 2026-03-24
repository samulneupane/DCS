package pieces;

import board.Board;
import board.Position;
import enums.Color;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Position> possibleMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        int[][] offsets = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
        };

        int row = position.getRow();
        int col = position.getColumn();

        for (int[] offset : offsets) {
            Position p = new Position(row + offset[0], col + offset[1]);

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
        return color == Color.WHITE ? "wk" : "bk";
    }
}
