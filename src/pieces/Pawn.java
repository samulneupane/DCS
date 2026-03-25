package pieces;

import src.Board;
import src.Position;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(String color, Position position) {
        super(color, position);
    }

    @Override
    public List<Position> possibleMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        int direction = color.equals("white") ? -1 : 1; // white moves up, black moves down
        int row = position.getRow();
        int col = position.getColumn();

        // One step forward
        Position oneStep = new Position(row + direction, col);
        if (board.isInsideBoard(oneStep) && board.getPiece(oneStep) == null) {
            moves.add(oneStep);
        }

        // TODO: add two-step initial move and capture diagonals if needed

        return moves;
    }

    @Override
    public String getSymbol() {
        return color.equals("white") ? "wP" : "bP";
    }
}