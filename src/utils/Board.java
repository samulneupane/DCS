package utils;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.awt.Color;

import pieces.*;

// Board class
public class Board {

    // 8x8 board matrix to hold pieces
    private Piece[][] board;

    // List to keep track of captured pieces
    private List<Piece> capturedPieces;

    // Constructor
    public Board() {
        board = new Piece[8][8];
        capturedPieces = new ArrayList<>();
        initializeBoard();
    }

    public void initializeBoard() {

        // White major pieces (row 0)
        board[0][0] = new Rook(Color.WHITE, new Position(0, 0), "Rook");
        board[0][1] = new Knight(Color.WHITE, new Position(0, 1), "Knight");
        board[0][2] = new Bishop(Color.WHITE, new Position(0, 2), "Bishop");
        board[0][3] = new Queen(Color.WHITE, new Position(0, 3), "Queen");
        board[0][4] = new King(Color.WHITE, new Position(0, 4), "King");
        board[0][5] = new Bishop(Color.WHITE, new Position(0, 5), "Bishop");
        board[0][6] = new Knight(Color.WHITE, new Position(0, 6), "Knight");
        board[0][7] = new Rook(Color.WHITE, new Position(0, 7), "Rook");

        // White pawns (row 1)
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(Color.WHITE, new Position(1, i), "Pawn");
        }

        // Black pawns (row 6)
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(Color.BLACK, new Position(6, i), "Pawn");
        }

        // Black major pieces (row 7)
        board[7][0] = new Rook(Color.BLACK, new Position(7, 0), "Rook");
        board[7][1] = new Knight(Color.BLACK, new Position(7, 1), "Knight");
        board[7][2] = new Bishop(Color.BLACK, new Position(7, 2), "Bishop");
        board[7][3] = new Queen(Color.BLACK, new Position(7, 3), "Queen");
        board[7][4] = new King(Color.BLACK, new Position(7, 4), "King");
        board[7][5] = new Bishop(Color.BLACK, new Position(7, 5), "Bishop");
        board[7][6] = new Knight(Color.BLACK, new Position(7, 6), "Knight");
        board[7][7] = new Rook(Color.BLACK, new Position(7, 7), "Rook");
    }

    // Get the piece at a specific location
    public Piece getPiece(Position pos) {
        int row = pos.getRow();
        int col = pos.getColumn();

        if (!isValidPosition(pos)) {
            return null;
        }
        return board[row][col];
    }

    //to move a piece
    public Boolean movePiece(Position from, Position to) {
        // Checking bounds
        if (!isValidPosition(from) || !isValidPosition(to)) {
            return false;
        }

        Piece movingPiece = getPiece(from);
        if (movingPiece == null) {
            return false;
        }

        Piece targetPiece = getPiece(to);

        // Prevent capturing your own piece
        if (targetPiece != null &&
            targetPiece.getColor().equals(movingPiece.getColor())) {
            return false;
        }

        // Capture opponent piece
        if (targetPiece != null) {
            capturedPieces.add(targetPiece);
            System.out.println(targetPiece.getColor() + " " + targetPiece.getSymbol() + " captured!");
            
            // If King is captured, declare winner and terminate game
            if (targetPiece instanceof King) {
                String winner = movingPiece.getColor().equals(Color.WHITE) ? "White" : "Black";
                // Move the piece and update the board
                board[to.getRow()][to.getColumn()] = movingPiece;
                board[from.getRow()][from.getColumn()] = null;
                movingPiece.setPosition(to);
                // Show winning message
                JOptionPane.showMessageDialog(null, winner + " wins! The King was captured.");
                System.exit(0);
            }
        }

        // Move the piece to the new position (for both captures and normal moves)
        board[to.getRow()][to.getColumn()] = movingPiece;
        board[from.getRow()][from.getColumn()] = null;
        movingPiece.setPosition(to);

        return true;
    }


    /**
     * Handles the capture of a piece and adds it to captured pieces list
     * @param capturedPiece The piece being captured
     */
    private void capturePiece(Piece capturedPiece) {
        capturedPieces.add(capturedPiece);
        System.out.println("Captured " + capturedPiece.getColor() + " " + capturedPiece.getRank() + "!");
    }

    public boolean isInsideBoard(Position pos) {
        return isValidPosition(pos);
    }
}