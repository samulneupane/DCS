package pieces;

import src.Board;
import src.Position;
import java.util.List;

public abstract class Piece {
    protected String color;   // "white" or "black"
    protected Position position;

    public Piece(String color, Position position) {
        this.color = color;
        this.position = position;
    }

    // Getters and setters
    public String getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    // Returns list of possible moves for this piece
    public abstract List<Position> possibleMoves(Board board);

    // Returns the symbol to display on the board
    public abstract String getSymbol();
}