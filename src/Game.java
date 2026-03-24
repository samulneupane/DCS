package src;

public class Game {

    private Board board;          // The chessboard
    private Player whitePlayer;   // White player
    private Player blackPlayer;   // Black player
    private String currentTurn;   // "white" or "black"

    // Constructor
    public Game(Player white, Player black) {
        this.board = new Board();
        this.whitePlayer = white;
        this.blackPlayer = black;
        this.currentTurn = "white"; // White starts first
    }

    // Initialize the game
    public void start() {
        board.initialize(); // Place all pieces in starting positions
        System.out.println("Game started!");
        board.display();    // Show initial board
    }

    // End the game and declare winner or draw
    public void end(String winner) {
        System.out.println("Game Over!");
        if (winner == null) {
            System.out.println("It's a draw!");
        } else {
            System.out.println("Winner: " + winner);
        }
    }

    // Main game loop
    public void play() {
        boolean gameOver = false;

        while (!gameOver) {
            board.display();
            System.out.println(currentTurn + "'s turn.");

            Player currentPlayer = currentTurn.equals("white") ? whitePlayer : blackPlayer;
            currentPlayer.makeMove(board); // Player makes a move

            // Check if current player is in checkmate
            if (board.isCheckmate(currentTurn)) {
                end(currentTurn.equals("white") ? "black" : "white");
                gameOver = true;
            } 
            // Optional: check for stalemate
            else if (board.isStalemate(currentTurn)) {
                end(null);
                gameOver = true;
            } 
            else {
                // Switch turns
                currentTurn = currentTurn.equals("white") ? "black" : "white";
            }
        }
    }

    // Main method to run the game
    public static void main(String[] args) {
        Board board = new Board();
        Player white = new Player("white", board);
        Player black = new Player("black", board);

        Game game = new Game(white, black);
        game.start();
        game.play();
    }
}
