package pieces;

import board.Board;
import board.Position;
import enums.Color;
import java.util.List;

public abstract class Piece {
    protected Color color;
    protected Position position;

    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    
    }
}

}

