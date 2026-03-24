import java.util.ArrayList;
import java.util.List;

//Board class
public class Board {

    // 8x8 board matrix to hold pieces
    private Piece[][] board;

    // List to keep track of captured pieces
    private List<Piece> capturedPieces;
    
    // Constructor
    public Board() {
    board = new Piece[8][8]; // initialize empty 8x8 board
	capturedPieces = new ArrayList<>(); // initialize empty list
    initializeBoard(); // set up initial pieces
    }
    
    private void initializeBoard() {
        // White pieces 
        board[0][0] = new Rook("white", new Position(0, 0));
        board[0][1] = new Knight("white", new Position(0, 1));
        board[0][2] = new Bishop("white", new Position(0, 2));
        board[0][3] = new Queen("white", new Position(0, 3));
        board[0][4] = new King("white", new Position(0, 4));
        board[0][5] = new Bishop("white", new Position(0, 5));
        board[0][6] = new Knight("white", new Position(0, 6));
        board[0][7] = new Rook("white", new Position(0, 7));
    
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn("white", new Position(1, i));
        }
    
        // Black pieces
        board[7][0] = new Rook("black", new Position(7, 0));
        board[7][1] = new Knight("black", new Position(7, 1));
        board[7][2] = new Bishop("black", new Position(7, 2));
        board[7][3] = new Queen("black", new Position(7, 3));
        board[7][4] = new King("black", new Position(7, 4));
        board[7][5] = new Bishop("black", new Position(7, 5));
        board[7][6] = new Knight("black", new Position(7, 6));
        board[7][7] = new Rook("black", new Position(7, 7));
    
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn("black", new Position(6, i));
        }
    }
    
    
    // gives the piece at a specific location
    public Piece getPiece(Position pos) {
        int row = pos.getRow();
        int col = pos.getColumn();
        
        // Check if position is within bounds
        if (row < 0 || row >= 8 || col < 0 || col >= 8) {
        System.out.println("Invalid position: " + pos.toString());
        return null;
     }
        //return the piece at the given position
        return board[row][col];
    }

    //to move a piece
    public boolean movePiece(Position from, Position to) {
    Piece movingPiece = getPiece(from);
    
    if (movingPiece == null) {
        // No piece at source
        return false;
    }

    Piece targetPiece = getPiece(to);
    
    if (targetPiece != null) {
        // Capture the piece
        capturedPieces.add(targetPiece);
    }

    // Move the piece
    board[to.getRow()][to.getColumn()] = movingPiece;
    board[from.getRow()][from.getColumn()] = null;
    movingPiece.setPosition(to.toString()); // update piece's internal position

    return true;
}
}

