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
    
    private void initializeBoard(){
        //only pawns for quick setup
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn("white", new Position(1, i));
            board[6][i] = new Pawn("black", new Position(6, i));
        }
    }
    
    
    // gives the piece at a specific location
    public Piece getPiece(Position pos) {
        int row = pos.getRow();
        int col = pos.getColumn();
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

