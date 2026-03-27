package pieces;

import src.Board;
import src.Position;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;


public class Queen extends Piece {

    public Queen(String color, Position position) {
        super(color, position);
    }

    @Override
    public List<Position> possibleMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        int row = position.getRow();
        int col = position.getColumn();

        // 8 directions: vertical, horizontal, diagonals
        int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},    // up, down, left, right
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}   // diagonals
        };

        for (int[] dir : directions) {
            int r = row + dir[0];
            int c = col + dir[1];

            while (r >= 0 && r < 8 && c >= 0 && c < 8) {
                Position p = new Position(r, c);

                if (board.getPiece(p) == null) {
                    moves.add(p);
                } else {
                    // Add opponent piece if present
                    if (!board.getPiece(p).getColor().equals(this.color)) {
                        moves.add(p);
                    }
                    break; // stop in this direction
                }

                r += dir[0];
                c += dir[1];
            }
        }

        return moves;
    }

    @Override
    public String getSymbol() {
        return color.equals("white") ? "wQ" : "bQ";
    }
}