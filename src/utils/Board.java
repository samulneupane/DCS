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

    // Move a piece
    public boolean movePiece(Position from, Position to) {

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

            // If King is captured
            if (targetPiece instanceof King) {
                String winner =
                        movingPiece.getColor().equals(Color.WHITE) ? "White" : "Black";

                board[to.getRow()][to.getColumn()] = movingPiece;
                board[from.getRow()][from.getColumn()] = null;
                movingPiece.setPosition(to);

                JOptionPane.showMessageDialog(
                        null,
                        winner + " wins! The king was captured!"
                );
                System.exit(0);
            }
        }

        // Final move
        board[to.getRow()][to.getColumn()] = movingPiece;
        board[from.getRow()][from.getColumn()] = null;
        movingPiece.setPosition(to);

        return true;
    }

    // Check if position is inside board
    public boolean isValidPosition(Position pos) {
        return pos.getRow() >= 0 && pos.getRow() < 8 &&
               pos.getColumn() >= 0 && pos.getColumn() < 8;
    }

    public boolean isInsideBoard(Position pos) {
        return isValidPosition(pos);
    }

    // Check if the given color's king is in check
public boolean isInCheck(Color color) {
    // Find the king
    Position kingPos = null;
    for (int r = 0; r < 8; r++) {
        for (int c = 0; c < 8; c++) {
            Piece p = board[r][c];
            if (p instanceof King && p.getColor().equals(color)) {
                kingPos = new Position(r, c);
            }
        }
    }
    if (kingPos == null) return false;

    // Check if any opponent piece can attack the king
    Color opponent = color.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
    for (int r = 0; r < 8; r++) {
        for (int c = 0; c < 8; c++) {
            Piece p = board[r][c];
            if (p != null && p.getColor().equals(opponent)) {
                if (p.possibleMoves(this).contains(kingPos)) {
                    return true;
                }
            }
        }
    }
    return false;
}

// Check if the given color is in checkmate
public boolean isCheckmate(Color color) {
    if (!isInCheck(color)) return false;

    // Try every possible move for every piece of this color
    for (int r = 0; r < 8; r++) {
        for (int c = 0; c < 8; c++) {
            Piece p = board[r][c];
            if (p != null && p.getColor().equals(color)) {
                for (Position to : p.possibleMoves(this)) {
                    // Simulate the move
                    Piece captured = board[to.getRow()][to.getColumn()];
                    Position from = p.getPosition();

                    board[to.getRow()][to.getColumn()] = p;
                    board[from.getRow()][from.getColumn()] = null;
                    p.setPosition(to);

                    boolean stillInCheck = isInCheck(color);

                    // Undo the move
                    board[from.getRow()][from.getColumn()] = p;
                    board[to.getRow()][to.getColumn()] = captured;
                    p.setPosition(from);

                    if (!stillInCheck) return false; // found a way out
                }
            }
        }
    }
    return true; // no way out
}
}
