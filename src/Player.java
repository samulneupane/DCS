import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {

    private String color;              // "white" or "black"
    private List<Piece> piecesRemaining;
    private Scanner scanner;

    // Constructor
    public Player(String color) {
        this.color = color.toLowerCase();
        this.piecesRemaining = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Getter for color
    public String getColor() {
        return color;
    }

    // Manage pieces
    public List<Piece> getPiecesRemaining() {
        return piecesRemaining;
    }

    public void addPiece(Piece p) {
        piecesRemaining.add(p);
    }

    public void removePiece(Piece p) {
        piecesRemaining.remove(p);
    }

    /**
     * Prompts the player to make a move and executes it if valid.
     */
    public void makeMove(Board board) {
        boolean validMove = false;

        while (!validMove) {
            System.out.println(color + "'s move (e.g., E2 E4): ");
            String input = scanner.nextLine().trim();

            // Validate input format
            if (!utils.Utils.isValidMoveFormat(input)) {
                System.out.println("Invalid format! Use something like E2 E4.");
                continue;
            }

            // Convert notation to Position
            String[] parts = input.split("\\s+");
            Position from = utils.Utils.notationToPosition(parts[0]);
            Position to = utils.Utils.notationToPosition(parts[1]);

            // Check if a piece exists at the source
            Piece piece = board.getPiece(from);
            if (piece == null) {
                System.out.println("No piece at " + parts[0]);
                continue;
            }

            // Check if the piece belongs to the player
            if (!piece.getColor().equals(color)) {
                System.out.println("You can only move your own pieces!");
                continue;
            }

            // Check if move is valid for this piece
            if (!piece.possibleMoves(board).contains(to)) {
                System.out.println("Invalid move for that piece.");
                continue;
            }

            // Execute move
            board.movePiece(from, to);
            validMove = true;
        }
    }
}
