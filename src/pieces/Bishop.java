package pieces;

import utils.Board;
import utils.Position;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class Bishop extends Piece {

    public Bishop(Color color, Position position , String rank) {
        super(color, position , rank);
        rank = "Bishop";
    }

    @Override
    public List<Position> possibleMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        int row = position.getRow();
        int col = position.getColumn();

        // up-left
        for (int r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--) {
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
        }

        // up-right
        for (int r = row - 1, c = col + 1; r >= 0 && c < 8; r--, c++) {
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
        }

        // down-left
        for (int r = row + 1, c = col - 1; r < 8 && c >= 0; r++, c--) {
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
        }

        // down-right
        for (int r = row + 1, c = col + 1; r < 8 && c < 8; r++, c++) {
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
        }

        return moves;
    }

    @Override
    public String getSymbol() {
        return Color.WHITE.equals(color) ? "wb" : "bb";
    }
}