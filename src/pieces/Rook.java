package pieces;

import utils.Board;
import utils.Position;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class Rook extends Piece {

    public Rook(Color color, Position position , String rank) {
        super(color, position , rank);
        rank = "Rook";
    }

    @Override
    public List<Position> possibleMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        int row = position.getRow();
        int col = position.getColumn();

        // up
        for (int r = row - 1; r >= 0; r--) {
            Position p = new Position(r, col);
            Piece target = board.getPiece(p);

            if (target == null) {
                moves.add(p);
            } else {
                if (!target.getColor().equals(this.color)) {
                    moves.add(p); // capture
                }
                break; // blocked
            }
        }

        // down
        for (int r = row + 1; r < 8; r++) {
            Position p = new Position(r, col);
            Piece target = board.getPiece(p);

            if (target == null) {
                moves.add(p);
            } else {
                if (!target.getColor().equals(this.color)) {
                    moves.add(p); // capture
                }
                break; // blocked
            }
        }

        // left
        for (int c = col - 1; c >= 0; c--) {
            Position p = new Position(row, c);
            Piece target = board.getPiece(p);

            if (target == null) {
                moves.add(p);
            } else {
                if (!target.getColor().equals(this.color)) {
                    moves.add(p); // capture
                }
                break; // blocked
            }
        }

        // right
        for (int c = col + 1; c < 8; c++) {
            Position p = new Position(row, c);
            Piece target = board.getPiece(p);

            if (target == null) {
                moves.add(p);
            } else {
                if (!target.getColor().equals(this.color)) {
                    moves.add(p); // capture
                }
                break; // blocked
            }
        }

        return moves;
    }

    @Override
    public String getSymbol() {
        return Color.WHITE.equals(color) ? "wr" : "br";
    }
}