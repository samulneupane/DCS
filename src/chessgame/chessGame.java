package chessgame;

import javax.swing.*;
import java.awt.*;
import pieces.Piece;
import utils.Board;
import utils.Position;

public class chessGame extends JFrame {
  
    Board board = new Board();

    public chessGame() {
        setTitle("8x8 Chess Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 8));
        setSize(640, 640);
        setLocationRelativeTo(null);

        boolean lightSquare = true;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JPanel square = new JPanel();
                square.setBackground(lightSquare ? Color.LIGHT_GRAY : Color.DARK_GRAY);
                add(square);
                lightSquare = !lightSquare;
            }
            lightSquare = !lightSquare;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            chessGame frame = new chessGame();
            frame.setVisible(true);
        });
    }
}

