package chessgame;

import pieces.Piece;
import utils.Board;
import utils.Position;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIPlayer {

    private Color color;
    private Random random;

    public AIPlayer(Color color) {
        this.color = color;
        this.random = new Random();
    }
}

public void makeMove(Board board) {
    List<Position[]> allMoves = new ArrayList<>();

    // collect all possible moves for all black pieces
    for (int r = 0; r < 8; r++) {
        for (int c = 0; c < 8; c++) {
            Piece piece = board.getPiece(new Position(r, c));
            if (piece != null && piece.getColor().equals(color)) {
                for (Position to : piece.possibleMoves(board)) {
                    allMoves.add(new Position[]{new Position(r, c), to});
                }
            }
        }
    }

