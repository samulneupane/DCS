package pieces;

import Position;

public class Pawn extends Piece {

    public Pawn(boolean isWhite, Position position) {
        super(isWhite, position);
    }

    public String getSymbol() {
        return isWhite ? "wp" : "bp";
    }
}
