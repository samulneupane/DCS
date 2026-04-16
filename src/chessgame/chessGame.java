package chessgame;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;

import pieces.Piece;
import utils.Board;
import utils.Position;

public class chessGame extends JFrame {
  
    Board board = new Board();

    public chessGame() {
        setTitle("8x8 Chess Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 8));
        setSize(600, 600);
        setLocationRelativeTo(null);

        boolean lightSquare = true;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JPanel square = new JPanel();
                square.setBackground(lightSquare ? Color.LIGHT_GRAY : Color.DARK_GRAY);
                Piece piece = board.getPiece(new Position(row, col));
                JLabel label = new JLabel(getSymbol(piece), SwingConstants.CENTER);
                label.setFont(new Font("Serif", Font.PLAIN, 52));    
                square.add(label);                
                add(square);
                lightSquare = !lightSquare;
            }
            lightSquare = !lightSquare;
        }
    }
private String getSymbol(Piece piece) {
        if (piece == null) {
            return "";
        }
        switch (piece.getRank()) {
            case "Pawn":
                return piece.getColor() == piece.getColor().WHITE ? "♙" : "♟";
            case "Rook":
                return piece.getColor() == piece.getColor().WHITE ? "♖" : "♜";
            case "Knight":
                return piece.getColor() == piece.getColor().WHITE ? "♘" : "♞";
            case "Bishop":
                return piece.getColor() == piece.getColor().WHITE ? "♗" : "♝";
            case "Queen":
                return piece.getColor() == piece.getColor().WHITE ? "♕" : "♛";
            case "King":
                return piece.getColor() == piece.getColor().WHITE ? "♔" : "♚";
            default:
                return "";
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            chessGame frame = new chessGame();
            frame.setVisible(true);
        });
    }
}

