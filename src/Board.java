import java.util.ArrayList;
import java.util.List;

//board class
public class Board {

    // 8x8 board matrix to hold pieces
    private Piece[][] board;

    // List to keep track of captured pieces
    private List<Piece> capturedPieces;
    
    // Constructor
    public Board() {
        board = new Piece[8][8]; // initialize empty 8x8 board
	capturedPieces = new ArrayList<>(); // initialize empty list
    }
}

