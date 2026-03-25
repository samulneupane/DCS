package pieces;

import Position;

public abstract class Piece {

    protected boolean isWhite;
    protected Position position;

    public Piece(boolean isWhite, Position position) {
        this.isWhite = isWhite;
        this.position = position;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract String getSymbol();
}
