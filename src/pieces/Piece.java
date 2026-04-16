package pieces;

import utils.Board;
import utils.Position;
import java.util.List;
import java.awt.Color;



public abstract class Piece {
    protected Color color;
    protected Position position;
  //  private String symbol; 


    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
       // this.symbol = symbol;
    }
    public Color getColor(){
        return color;
    }
    public Position getPosition(){
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public abstract List<Position> possibleMoves(Board board);

    public abstract String getSymbol();

}

