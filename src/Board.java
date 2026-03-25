import pieces.*;
import utils.Utils;

public class Board {

    private Piece[][] board = new Piece[8][8];

    public Board() {
        initialize();
    }

    public void initialize() {

        for (int c = 0; c < 8; c++) {
            board[1][c] = new Pawn(false, new Position(1, c));
            board[6][c] = new Pawn(true, new Position(6, c));
        }

        board[0][0] = new Rook(false, new Position(0,0));
        board[0][7] = new Rook(false, new Position(0,7));
        board[7][0] = new Rook(true, new Position(7,0));
        board[7][7] = new Rook(true, new Position(7,7));

        board[0][1] = new Knight(false, new Position(0,1));
        board[0][6] = new Knight(false, new Position(0,6));
        board[7][1] = new Knight(true, new Position(7,1));
        board[7][6] = new Knight(true, new Position(7,6));

        board[0][2] = new Bishop(false, new Position(0,2));
        board[0][5] = new Bishop(false, new Position(0,5));
        board[7][2] = new Bishop(true, new Position(7,2));
        board[7][5] = new Bishop(true, new Position(7,5));

        board[0][3] = new Queen(false, new Position(0,3));
        board[7][3] = new Queen(true, new Position(7,3));

        board[0][4] = new King(false, new Position(0,4));
        board[7][4] = new King(true, new Position(7,4));
    }

    public Piece getPiece(Position p) {
        return board[p.getRow()][p.getCol()];
    }

    public void movePiece(Position from, Position to) {
        Piece piece = board[from.getRow()][from.getCol()];
        board[to.getRow()][to.getCol()] = piece;
        piece.setPosition(to);
        board[from.getRow()][from.getCol()] = null;
    }

    public void display() {
        System.out.println("    A   B   C   D   E   F   G   H");
        for (int r = 0; r < 8; r++) {
            System.out.print((8 - r) + " ");
            for (int c = 0; c < 8; c++) {
                Piece p = board[r][c];
                if (p == null) System.out.print(" ## ");
                else System.out.print(" " + p.getSymbol() + " ");
            }
            System.out.println(" " + (8 - r));
        }
        System.out.println("    A   B   C   D   E   F   G   H");
    }
}
