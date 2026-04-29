package pieces;

import utils.Board;
import utils.Position;
import java.util.List;
import java.awt.Color;



public abstract class Piece {
    public Color color;
    public  Position position;
    public String rank;
  

    public Piece(Color color, Position position , String rank) {
        this.color = color;
        this.position = position;
        this.rank = rank;
    }
    public Color getColor(){
        return color;
    }
    public Position getPosition(){
        return position;
    }
    public int[][] setPosition(Position position) {
        this.position = position;
        return new int[][]{{position.getRow()}, {position.getColumn()}};
    }
    public String getRank() {
        return rank;
    }
    public abstract List<Position> possibleMoves(Board board);

    public abstract String getSymbol();

}

