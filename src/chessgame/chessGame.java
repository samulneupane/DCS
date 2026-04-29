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
    private Color currentTurn = Color.WHITE;
    private JLabel statusLabel;


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
                currentTurn = Color.WHITE;  
        statusLabel.setText("White's Turn");
                drawBoard();
            }
        });

        gameMenu.add(newGameItem);
        menuBar.add(gameMenu);
        setJMenuBar(menuBar);

        // ===== BOARD PANEL =====
        boardPanel = new JPanel(new GridLayout(8, 8));
        statusLabel = new JLabel("White's Turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(statusLabel, BorderLayout.SOUTH);
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
                            if (clickedPiece != null && clickedPiece.getColor().equals(currentTurn)) {
                                selectedPosition = clickedPos;
                            }
                        } else {
                            if (clickedPiece != null && clickedPiece.getColor().equals(currentTurn)) {
                                // clicked own piece — switch selection
                                selectedPosition = clickedPos;
                            } else {
                                board.movePiece(selectedPosition, clickedPos);
                                selectedPosition = null;
                                currentTurn = currentTurn.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
                                statusLabel.setText(currentTurn.equals(Color.WHITE) ? "White's Turn" : "Black's Turn");
                                drawBoard();
                            }
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
            case "Pawn":   return piece.getColor().equals(Color.WHITE) ? "♙" : "♟";
            case "Rook":   return piece.getColor().equals(Color.WHITE) ? "♖" : "♜";
            case "Knight": return piece.getColor().equals(Color.WHITE) ? "♘" : "♞";
            case "Bishop": return piece.getColor().equals(Color.WHITE) ? "♗" : "♝";
            case "Queen":  return piece.getColor().equals(Color.WHITE) ? "♕" : "♛";
            case "King":   return piece.getColor().equals(Color.WHITE) ? "♔" : "♚";
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
