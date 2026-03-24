package pieces;

import board.Board;
import board.Position;
import enums.Color;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Position> possibleMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        int row = position.getRow();
        int col = position.getCol();

        for (int r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--) {
            Position p = new Position(r, c);
            if (board.getPiece(p) == null) {
                moves.add(p);
            } else {
                if (board.getPiece(p).getColor() != color) {
                    moves.add(p);
                }
                break;
            }
        }
        for (int r = row - 1, c = col + 1; r >= 0 && c < 8; r--, c++) {
            Position p = new Position(r, c);
            if (board.getPiece(p) == null) {
                moves.add(p);
            } else {
                if (board.getPiece(p).getColor() != color) {
                    moves.add(p);
                }
                break;
            }
        }
        for (int r = row + 1, c = col - 1; r < 8 && c >= 0; r++, c--) {
            Position p = new Position(r, c);
            if (board.getPiece(p) == null) {
                moves.add(p);
            } else {
                if (board.getPiece(p).getColor() != color) {
                    moves.add(p);
                }
                break;
            }
        }
        for (int r = row + 1, c = col + 1; r < 8 && c < 8; r++, c++) {
            Position p = new Position(r, c);
            if (board.getPiece(p) == null) {
                moves.add(p);
            } else {
                if (board.getPiece(p).getColor() != color) {
                    moves.add(p);
                }
                break;
            }
        }
        return moves;
    }

    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "wb" : "bb";
    }
}