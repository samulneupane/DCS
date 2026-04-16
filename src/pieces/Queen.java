package pieces;

import utils.Board;
import utils.Position;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class Queen extends Piece {

    public Queen(Color color, Position position, String rank) {
        super(color, position, rank);
        rank = "Queen";
    }

    @Override
    public List<Position> possibleMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        int row = position.getRow();
        int col = position.getColumn();

        int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };

        for (int[] dir : directions) {
            int r = row + dir[0];
            int c = col + dir[1];

            while (r >= 0 && r < 8 && c >= 0 && c < 8) {
                Position p = new Position(r, c);
                Piece target = board.getPiece(p);

                if (target == null) {
                    moves.add(p);
                } else {
                    if (!target.getColor().equals(this.color)) {
                        moves.add(p); // capture
                    }
                    break; // blocked
                }

                r += dir[0];
                c += dir[1];
            }
        }

        return moves;
    }

    @Override
    public String getSymbol() {
        return Color.WHITE.equals(color) ? "wq" : "bq";
    }
}