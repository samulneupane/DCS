package pieces;

import utils.Board;
import utils.Position;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;


public class Pawn extends Piece {

    public Pawn(Color color, Position position , String rank) {
        super(color, position , rank);
        this.rank = rank;
    }   
    @Override
    public List<Position> possibleMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        // With your board indexing (row 0 at "bottom" in your display):
        // White pawns start at row 1 and move to row 2,3,... so +1
        // Black pawns start at row 6 and move to row 5,4,... so -1
        int dir = Color.WHITE.equals(color) ? 1 : -1;

        int row = position.getRow();
        int col = position.getColumn();

        // 1 step forward
        Position one = new Position(row + dir, col);
        if (board.isInsideBoard(one) && board.getPiece(one) == null) {
            moves.add(one);

            // 2 steps forward from starting row (only if 1-step is clear)
            int startRow = Color.WHITE.equals(color) ? 1 : 6;
            Position two = new Position(row + 2 * dir, col);
            if (row == startRow && board.isInsideBoard(two) && board.getPiece(two) == null) {
                moves.add(two);
            }
        }

        // capture diagonally left
        Position diagL = new Position(row + dir, col - 1);
        if (board.isInsideBoard(diagL)) {
            Piece t = board.getPiece(diagL);
            if (t != null && !t.getColor().equals(this.color)) {
                moves.add(diagL);
            }
        }

        // capture diagonally right
        Position diagR = new Position(row + dir, col + 1);
        if (board.isInsideBoard(diagR)) {
            Piece t = board.getPiece(diagR);
            if (t != null && !t.getColor().equals(this.color)) {
                moves.add(diagR);
            }
        }

        return moves;
    }

    @Override
    public String getSymbol() {
        return Color.WHITE.equals(color) ? "wp" : "bp";
    }
}