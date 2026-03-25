import utils.Utils;

public class Game {

    private Board board;
    private boolean whiteTurn = true;

    public Game() {
        board = new Board();
    }

    public void start() {
        while (true) {
            board.display();

            String move = Utils.getInput("Enter move (E2 E4): ");
            String[] parts = move.split(" ");

            if (!Utils.validMoveInput(move)) {
                System.out.println("Invalid format.");
                continue;
            }

            Position from = Utils.parsePosition(parts[0]);
            Position to = Utils.parsePosition(parts[1]);

            Piece piece = board.getPiece(from);

            if (piece == null) {
                System.out.println("No piece at source square.");
                continue;
            }

            if (piece.isWhite() != whiteTurn) {
                System.out.println("Not your turn.");
                continue;
            }

            board.movePiece(from, to);
            whiteTurn = !whiteTurn;
        }
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.start();
    }
}
