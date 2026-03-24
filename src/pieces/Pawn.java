package pieces;

import board.Board;
import board.Position;
import enums.Color;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(Color color, Position position) {
        super(color, position); // Show the position the pawn is on and the color it is
    }
    public List<Position> possibleMoves(Board board) {
        List <Postion> moves = new ArrayList<>();

        int direction = (color == color.WHITE) ? -1 : 1;
        int row = position.getRow();
        int col = position.getCol();

        Position oneStep = new Position(row + direction, col);
          if (board.isInsideBoard(oneStep) && board.getPiece(oneStep) == null) {
            moves.add(oneStep);
    }
    return moves;
    }
    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "wp" : "bp";
    }
 }    

