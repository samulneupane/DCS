package pieces;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(boolean isWhite, Position position) {
        super(isWhite, position);
    }

    @Override
    public List<Position> possibleMoves() {
        List<Position> moves = new ArrayList<>();

        int[][] offsets = {
            { -2, -1 }, { -2,  1 },
            { -1, -2 }, { -1,  2 },
            {  1, -2 }, {  1,  2 },
            {  2, -1 }, {  2,  1 }
        };

        int row = position.getRow();
        int col = position.getCol();

        for (int[] offset : offsets) {
            int newRow = row + offset[0];
            int newCol = col + offset[1];

            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                moves.add(new Position(newRow, newCol));
            }
        }

        return moves;
    }
}
