package pieces;

import utils.Board;
import utils.Position;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class Knight extends Piece {

    public Knight(Color color, Position position , String symbol) {
        super(color, position , symbol);
        rank = "Knight";
    }

    @Override
    public List<Position> possibleMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        int[][] offsets = {
            { 2, 1}, { 2,-1}, {-2, 1}, {-2,-1},
            { 1, 2}, { 1,-2}, {-1, 2}, {-1,-2}
        };

        int row = position.getRow();
        int col = position.getColumn();

        for (int[] o : offsets) {
            Position to = new Position(row + o[0], col + o[1]);
            if (!board.isInsideBoard(to)) continue;

            Piece target = board.getPiece(to);
            if (target == null || !target.getColor().equals(this.color)) {
                moves.add(to);
            }
        }

        return moves;
    }

    @Override
    public String getSymbol() {
        return Color.WHITE.equals(color) ? "wn" : "bn";
    }
}