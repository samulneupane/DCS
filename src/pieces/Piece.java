package pieces;

import utils.Board;
import utils.Position;
import java.util.List;
import java.awt.Color;



public abstract class Piece {
    protected Color color;
    protected Position position;
    protected String rank;
  //  private String symbol; 


    public Piece(Color color, Position position , String rank) {
        this.color = color;
        this.position = position;
        this.rank = rank;
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
    public String getRank() {
        return rank;
    }
    public abstract List<Position> possibleMoves(Board board);

    public abstract String getSymbol();

}

