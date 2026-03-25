import java.util.Scanner;
import pieces.*;
import utils.Utils;

public class Game {

    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private boolean whiteTurn;

    public Game() {
        board = new Board();
        whitePlayer = new Player("white");
        blackPlayer = new Player("black");
        whiteTurn = true;
    }

    // Start the game loop
    public void start() {
        Scanner scanner = new Scanner(System.in);
        board.display();

        while (true) {
            System.out.println((whiteTurn ? "White" : "Black") + "'s turn.");
            System.out.print("Enter move (e.g., E2 E4) or 'exit' to quit: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Game ended.");
                break;
            }

            String[] parts = input.split("\\s+");
            if (parts.length != 2) {
                System.out.println("Invalid input. Use format: E2 E4");
                continue;
            }

            Position from = Utils.notationToPosition(parts[0]);
            Position to = Utils.notationToPosition(parts[1]);

            if (from == null || to == null) {
                System.out.println("Invalid positions. Use A-H and 1-8.");
                continue;
            }

            Piece piece = board.getPiece(from);

            if (piece == null) {
                System.out.println("No piece at " + parts[0]);
                continue;
            }

            if ((whiteTurn && piece.getColor().equals("black")) ||
                (!whiteTurn && piece.getColor().equals("white"))) {
                System.out.println("You cannot move opponent's piece.");
                continue;
            }

            // Attempt move
            board.movePiece(from, to);

            // Display updated board
            board.display();

            // Switch turns
            whiteTurn = !whiteTurn;
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}