package utils;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import pieces.*;

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
    
    public void initializeBoard() {

        Position pos = new Position(0, 0);

        // White pieces 
        board[0][0] = new Rook(Color.WHITE   , pos);
        board[0][1] = new Knight(Color.WHITE   , pos);
        board[0][2] = new Bishop(Color.WHITE   , pos);
        board[0][3] = new Queen(Color.WHITE   , pos);
        board[0][4] = new King(Color.WHITE   , pos);
        board[0][5] = new Bishop(Color.WHITE   , pos);
        board[0][6] = new Knight(Color.WHITE   , pos);
        board[0][7] = new Rook(Color.WHITE   , pos);
    
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(Color.BLACK, new Position(1, i));
        }
    
        // Black pieces
        board[7][0] = new Rook(Color.BLACK, new Position(7, 0));
        board[7][1] = new Knight(Color.BLACK, new Position(7, 1));
        board[7][2] = new Bishop(Color.BLACK, new Position(7, 2));
        board[7][3] = new Queen(Color.BLACK, new Position(7, 3));
        board[7][4] = new King(Color.BLACK, new Position(7, 4));
        board[7][5] = new Bishop(Color.BLACK, new Position(7, 5));
        board[7][6] = new Knight(Color.BLACK, new Position(7, 6));
        board[7][7] = new Rook(Color.BLACK, new Position(7, 7));
    
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(Color.BLACK, new Position(6, i));
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
        
        // Checking bounds
        if (!isValidPosition(from) || !isValidPosition(to)) {
        System.out.println("Invalid move: position out of bounds.");
        return false;
    }

        Piece movingPiece = getPiece(from);
    
        if (movingPiece == null) {
            System.out.println("Invalid move: no piece at " + from.toString());
            return false;
        }

        Piece targetPiece = getPiece(to);

        // Prevent capturing your own piece
        if (targetPiece != null && targetPiece.getColor().equals(movingPiece.getColor())) {
            System.out.println("Invalid move: cannot capture your own piece.");
            return false;
        }

        // Capture opponent piece if present
        if (targetPiece != null) {
            capturedPieces.add(targetPiece);
            System.out.println(targetPiece.getColor() + " " + targetPiece.getType() + " captured!");
        }

    //finally Moving the piece
    board[to.getRow()][to.getColumn()] = movingPiece;
    board[from.getRow()][from.getColumn()] = null;
    movingPiece.setPosition(to.toString()); // update piece's internal position

    return true;
}
    


public boolean isCheck(String color) {
    // Find the king of the given color
    Position kingPos = null;
    for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
            Piece p = board[row][col];
            if (p != null && p.getType().equals("King") && p.getColor().equals(color)) {
                kingPos = new Position(row, col);
                break;
            }
        }
        if (kingPos != null) break;
    }

    if (kingPos == null) {
        System.out.println("Error: King not found for color " + color);
        return false;
    }
// Check all opponent pieces if they can move to king's position
String opponentColor = color.equals("white") ? "black" : "white";

for (int row = 0; row < 8; row++) {
    for (int col = 0; col < 8; col++) {
        Piece p = board[row][col];
        if (p != null && p.getColor().equals(opponentColor)) {
            List<Position> moves = p.possibleMoves(board);
            for (Position pos : moves) {
                if (pos.equals(kingPos)) {
                    return true; // King is under attack
                }
            }
        }
    }

}
return false; // King is safe

}


public boolean isCheckmate(String color) {
    //if not in check, cannot be checkmate
    if (!isCheck(color)) {
        return false;
    }

    //iterate over all pieces of the given color
    for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
            Piece p = board[row][col];
            if (p != null && p.getColor().equals(color)) {
                List<Position> moves = p.possibleMoves(board);
                
                //trying each move temporarily
                for (Position to : moves) {
                    Position from = new Position(row, col);
                    Piece captured = getPiece(to);
                    
                    // Make the move
                    board[to.getRow()][to.getColumn()] = p;
                    board[row][col] = null;
                    p.setPosition(to.toString());

                    // Check if king is still in check
                    boolean stillInCheck = isCheck(color);

                    // Undo the move
                    board[row][col] = p;
                    board[to.getRow()][to.getColumn()] = captured;
                    p.setPosition(from.toString());

                    if (!stillInCheck) {
                        return false; // Found at least one move that escapes check
                    }
                }
            }
        }
    }
    //no moves can remove check → checkmate
    return true;
   
}

// src/Board.java
public void display() {
    System.out.println(); // blank line for spacing
    // Print each row from 8 down to 1 (standard chess view)
    for (int row = 7; row >= 0; row--) {
        // Print the rank number on the left
        System.out.print((row + 1) + " ");

        for (int col = 0; col < 8; col++) {
            Piece p = board[row][col];
            if (p != null) {
                // Print piece as "wP", "bQ", etc.
                String colorChar = p.getColor().substring(0, 1); // 'w' or 'b'
                String typeChar = p.getType().substring(0, 1).toUpperCase(); // 'P', 'R', 'N', etc.
                System.out.print(colorChar + typeChar + " ");
            } else {
                // Empty square
                System.out.print("## ");
            }
        }
        System.out.println(); // next row
    }

    // Print file letters at bottom
    System.out.print("  "); // indent for alignment
    for (char c = 'A'; c <= 'H'; c++) {
        System.out.print(c + "  ");
    }
    System.out.println(); // final newline
}

// TODO: Implement stalemate logic
	
public boolean isInsideBoard(Position pos) {
    int r = pos.getRow();
    int c = pos.getColumn();
    return r >= 0 && r < 8 && c >= 0 && c < 8;
}
public boolean isValidPosition(Position pos) {
    return pos.getRow() >= 0 && pos.getRow() < 8 &&
           pos.getColumn() >= 0 && pos.getColumn() < 8;
}
public boolean isStalemate(String currentTurn){
    return false;
}
}

