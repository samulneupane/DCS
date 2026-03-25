import pieces.Piece;
import pieces.Pawn;
import pieces.Rook;
import pieces.Knight;
import pieces.Bishop;
import pieces.Queen;
import pieces.King;

public class Board {

    private Piece[][] board;

    public Board() {
        board = new Piece[8][8];
        initializeBoard();
    }

    // Initialize pieces in starting positions
    private void initializeBoard() {
        // White pieces
        board[0][0] = new Rook("white", new Position(0,0));
        board[0][1] = new Knight("white", new Position(0,1));
        board[0][2] = new Bishop("white", new Position(0,2));
        board[0][3] = new Queen("white", new Position(0,3));
        board[0][4] = new King("white", new Position(0,4));
        board[0][5] = new Bishop("white", new Position(0,5));
        board[0][6] = new Knight("white", new Position(0,6));
        board[0][7] = new Rook("white", new Position(0,7));
        for(int i=0; i<8; i++) {
            board[1][i] = new Pawn("white", new Position(1,i));
        }

        // Black pieces
        board[7][0] = new Rook("black", new Position(7,0));
        board[7][1] = new Knight("black", new Position(7,1));
        board[7][2] = new Bishop("black", new Position(7,2));
        board[7][3] = new Queen("black", new Position(7,3));
        board[7][4] = new King("black", new Position(7,4));
        board[7][5] = new Bishop("black", new Position(7,5));
        board[7][6] = new Knight("black", new Position(7,6));
        board[7][7] = new Rook("black", new Position(7,7));
        for(int i=0; i<8; i++) {
            board[6][i] = new Pawn("black", new Position(6,i));
        }
    }

    // Return the piece at a given position
    public Piece getPiece(Position pos) {
        if (!isInsideBoard(pos)) return null;
        return board[pos.getRow()][pos.getColumn()];
    }

    // Move a piece from one position to another
    public void movePiece(Position from, Position to) {
        if (!isInsideBoard(from) || !isInsideBoard(to)) return;
        Piece movingPiece = getPiece(from);
        if (movingPiece == null) return;

        // Capture if there is a piece at the destination
        Piece targetPiece = getPiece(to);
        if (targetPiece != null) {
            // Optionally store captured pieces somewhere
        }

        // Move the piece
        board[to.getRow()][to.getColumn()] = movingPiece;
        movingPiece.setPosition(to);
        board[from.getRow()][from.getColumn()] = null;
    }

    // Check if a position is inside the 8x8 board
    public boolean isInsideBoard(Position pos) {
        int row = pos.getRow();
        int col = pos.getColumn();
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    // Display the board in console
    public void display() {
        System.out.println("  A B C D E F G H");
        for(int row=0; row<8; row++) {
            System.out.print((8-row) + " ");
            for(int col=0; col<8; col++) {
                Piece p = board[row][col];
                if (p == null) {
                    System.out.print("-- ");
                } else {
                    System.out.print(p.getSymbol() + " ");
                }
            }
            System.out.println((8-row));
        }
        System.out.println("  A B C D E F G H");
    }
}