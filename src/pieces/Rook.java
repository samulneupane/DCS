package pieces;

import board.Board;
import board.Position;
import enums.Color;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Position> possibleMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        int row = position.getRow();
        int col = position.getCol();
for (int r = row - 1; r >= 0; r--) {
            Position p = new Position(r, col);
            if (board.getPiece(p) == null) {
                moves.add(p);
            } else {
                if (board.getPiece(p).getColor() != color) {
                    moves.add(p);
                }
                break;
            }
        }
        for (int r = row + 1; r < 8; r++) {
            Position p = new Position(r, col);
            if (board.getPiece(p) == null) {
                moves.add(p);
            } else {
                if (board.getPiece(p).getColor() != color) {
                    moves.add(p);
                }
                break;
            }
        }

        for (int c = col - 1; c >= 0; c--) {
            Position p = new Position(row, c);
            if (board.getPiece(p) == null) {
                moves.add(p);
            } else {
                if (board.getPiece(p).getColor() != color) {
                    moves.add(p);
                }
                break;
            }
        }
        for (int c = col + 1; c < 8; c++) {
            Position p = new Position(row, c);
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
        return color == Color.WHITE ? "wr" : "br";
    }
}