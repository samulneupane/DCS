package chessgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import pieces.Piece;
import utils.Board;
import utils.Position;

public class chessGame extends JFrame {

    private Board board;
    private JPanel boardPanel;

    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenuItem newGameItem;

    private Position selectedPosition = null;

    public chessGame() {

        board = new Board();

        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== MENU BAR (EXTRA FEATURE) =====
        menuBar = new JMenuBar();
        gameMenu = new JMenu("Game");
        newGameItem = new JMenuItem("New Game");

        newGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board = new Board();
                selectedPosition = null;
                drawBoard();
            }
        });

        gameMenu.add(newGameItem);
        menuBar.add(gameMenu);
        setJMenuBar(menuBar);

        // ===== BOARD PANEL =====
        boardPanel = new JPanel(new GridLayout(8, 8));
        add(boardPanel, BorderLayout.CENTER);

        drawBoard();
    }

    private void drawBoard() {
        boardPanel.removeAll();
        boolean lightSquare = true;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                JPanel square = new JPanel();
                square.setBackground(lightSquare ? Color.LIGHT_GRAY : Color.DARK_GRAY);

                Position pos = new Position(row, col);
                Piece piece = board.getPiece(pos);

                JLabel label = new JLabel(getSymbol(piece), SwingConstants.CENTER);
                label.setFont(new Font("Serif", Font.PLAIN, 52));

                final int r = row;
                final int c = col;

                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Position clickedPos = new Position(r, c);
                        Piece clickedPiece = board.getPiece(clickedPos);

                        if (selectedPosition == null) {
                            if (clickedPiece != null) {
                                selectedPosition = clickedPos;
                            }
                        } else {
                            board.movePiece(selectedPosition, clickedPos);
                            selectedPosition = null;
                            drawBoard();
                        }
                    }
                });

                square.add(label);
                boardPanel.add(square);

                lightSquare = !lightSquare;
            }
            lightSquare = !lightSquare;
        }

        boardPanel.revalidate();
        boardPanel.repaint();
    }

    private String getSymbol(Piece piece) {
        if (piece == null) return "";

        switch (piece.getRank()) {
            case "Pawn":   return piece.getColor() == Color.WHITE ? "♙" : "♟";
            case "Rook":   return piece.getColor() == Color.WHITE ? "♖" : "♜";
            case "Knight": return piece.getColor() == Color.WHITE ? "♘" : "♞";
            case "Bishop": return piece.getColor() == Color.WHITE ? "♗" : "♝";
            case "Queen":  return piece.getColor() == Color.WHITE ? "♕" : "♛";
            case "King":   return piece.getColor() == Color.WHITE ? "♔" : "♚";
            default: return "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                chessGame frame = new chessGame();
                frame.setVisible(true);
            }
        });
    }
}