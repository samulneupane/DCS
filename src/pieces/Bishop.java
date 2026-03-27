package pieces;

import src.Position;
import src.Board;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;


public class Bishop extends Piece {

    public Bishop(String color, Position position) {
        super(color, position);
    }

    @Override
    public List<Position> possibleMoves(Board board) {
        List<Position> moves = new ArrayList<>();
        int row = position.getRow();
        int col = position.getColumn();

        // Top-left diagonal
        for (int r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--) {
            Position p = new Position(r, c);
            if (board.getPiece(p) == null) {
                moves.add(p);
            } else {
                if (!board.getPiece(p).getColor().equals(color)) {
                    moves.add(p);
                }
                break;
            }
        }

        // Top-right diagonal
        for (int r = row - 1, c = col + 1; r >= 0 && c < 8; r--, c++) {
            Position p = new Position(r, c);
            if (board.getPiece(p) == null) {
                moves.add(p);
            } else {
                if (!board.getPiece(p).getColor().equals(color)) {
                    moves.add(p);
                }
                break;
            }
        }

        // Bottom-left diagonal
        for (int r = row + 1, c = col - 1; r < 8 && c >= 0; r++, c--) {
            Position p = new Position(r, c);
            if (board.getPiece(p) == null) {
                moves.add(p);
            } else {
                if (!board.getPiece(p).getColor().equals(color)) {
                    moves.add(p);
                }
                break;
            }
        }

        // Bottom-right diagonal
        for (int r = row + 1, c = col + 1; r < 8 && c < 8; r++, c++) {
            Position p = new Position(r, c);
            if (board.getPiece(p) == null) {
                moves.add(p);
            } else {
                if (!board.getPiece(p).getColor().equals(color)) {
                    moves.add(p);
                }
                break;
            }
        }

        return moves;
    }

    @Override
    public String getSymbol() {
        return color.equals("white") ? "wB" : "bB";
    }
}