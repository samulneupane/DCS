package pieces;

import board.Board;
import board.Position;
import enums.Color;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(Color color, Position position) {
        super(color, position);
    }
@Override
    public List<Position> possibleMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        int[][] offsets = {
            {-2, -1}, {-2, 1},
            {-1, -2}, {-1, 2},
            {1, -2}, {1, 2},
            {2, -1}, {2, 1}
        };
}

}
