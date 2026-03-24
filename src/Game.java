// src/Player.java
package src;

import java.util.Scanner;

public class Player {

    private String color;         // "white" or "black"
    private Board board;          // reference to the board
    private Scanner scanner;

    // Constructor
    public Player(String color, Board board) {
        this.color = color.toLowerCase();
        this.board = board;
        this.scanner = new Scanner(System.in);
    }

    public String getColor() {
        return color;
    }

    /**
     * Prompts the player to make a move and executes it on the board.
     * Validates input and move legality.
     */
    public void makeMove(Board board) {
        boolean validMove = false;

        while (!validMove) {
            System.out.println(color + "'s move (e.g., E2 E4): ");
            String input = scanner.nextLine().trim();

            // 1️⃣ Validate format
            if (!utils.Utils.isValidMoveFormat(input)) {
                System.out.println("Invalid format! Use something like E2 E4.");
                continue;
            }

            // 2️⃣ Convert notation to Position objects
            String[] parts = input.split("\\s+");
            Position from = utils.Utils.notationToPosition(parts[0]);
            Position to = utils.Utils.notationToPosition(parts[1]);

            // 3️⃣ Check if there's a piece at "from" and belongs to the player
            Piece piece = board.getPiece(from);
            if (piece == null) {
                System.out.println("No piece at " + parts[0]);
                continue;
            }
            if (!piece.getColor().equals(color)) {
                System.out.println("You can only move your own pieces!");
                continue;
            }

            // 4️⃣ Check if move is valid for the piece
            if (!piece.possibleMoves(board).contains(to)) {
                System.out.println("Invalid move for that piece.");
                continue;
            }

            // 5️⃣ Execute move
            board.movePiece(from, to);
            validMove = true;
        }
    }
}
