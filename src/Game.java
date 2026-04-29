// import java.awt.Color;
// import utils.Board;

// public class Game {

//     private Board board;          // The chessboard
//     private Player whitePlayer;   // White player
//     private Player blackPlayer;   // Black player
//     private String currentTurn;   // "white" or "black"

//     // Constructor
//     public Game(Player white, Player black) {
//         this.board = new Board();
//         this.whitePlayer = white;
//         this.blackPlayer = black;
//         this.currentTurn = "white"; // White starts first
//     }

//     // Initialize the game
//     public void start() {
//         board.initializeBoard();
//         System.out.println("Game started!");
//     }

//     // End the game and declare winner
//     public void end(String winner) {
//         System.out.println("Game Over!");
//         System.out.println("Winner: " + winner);
//     }

//     // Main game loop
//     public void play() {
//         boolean gameOver = false;

//         while (!gameOver) {
//             System.out.println(currentTurn + "'s turn.");

//             Player currentPlayer =
//                 currentTurn.equals("white") ? whitePlayer : blackPlayer;

//             currentPlayer.makeMove(board);

//             // Convert String turn → Color
//             Color turnColor =
//                 currentTurn.equals("white") ? Color.WHITE : Color.BLACK;

//             // Checkmate only (no stalemate)
//             if (board.isCheckmate(turnColor)) {
//                 end(currentTurn.equals("white") ? "black" : "white");
//                 gameOver = true;
//             } else {
//                 // Switch turns
//                 currentTurn = currentTurn.equals("white") ? "black" : "white";
//             }
//         }
//     }

//     // Main method
//     public static void main(String[] args) {
//         Board board = new Board();
//         Player white = new Player(Color.WHITE, board);
//         Player black = new Player(Color.BLACK, board);

//         Game game = new Game(white, black);
//         game.start();
//         game.play();
//     }
// }