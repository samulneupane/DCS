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

                JPanel square = new JPanel(new BorderLayout());
                square.setBackground(lightSquare ? Color.LIGHT_GRAY : Color.DARK_GRAY);

                //if selected highlight
                if (selectedPosition != null && selectedPosition.getRow() == row && selectedPosition.getColumn() == col) {
                    square.setBackground(new Color(255, 255, 100)); // yellow highlight
                }


                Position pos = new Position(row, col);
                Piece piece = board.getPiece(pos);

                JLabel label = new JLabel(SwingConstants.CENTER);
                label.setOpaque(false);
                ImageIcon icon = getPieceImage(piece, 60);
                if (icon != null) label.setIcon(icon);

                final int r = row;
                final int c = col;

                MouseAdapter clickHandler = new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Position clickedPos = new Position(r, c);
                        Piece clickedPiece = board.getPiece(clickedPos);

                        if (selectedPosition == null) {
                            if (clickedPiece != null && clickedPiece.getColor().equals(currentTurn)) {
                                selectedPosition = clickedPos;
                                drawBoard();
                            }
                        } else {
                            if (clickedPiece != null && clickedPiece.getColor().equals(currentTurn)) {
                                // clicked own piece — switch selection
                                selectedPosition = clickedPos;
                                drawBoard();
                            } else {
                                Piece selectedPiece = board.getPiece(selectedPosition);
                                drawBoard();
                                if (selectedPiece.possibleMoves(board).contains(clickedPos)) {
                                    board.movePiece(selectedPosition, clickedPos);
                                    selectedPosition = null;
                                    currentTurn = currentTurn.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
                                    
                                    if (board.isInCheck(currentTurn)) {
                                        statusLabel.setText(currentTurn.equals(Color.WHITE) ? "White is in Check!" : "Black is in Check!");
                                    } else {
                                        statusLabel.setText(currentTurn.equals(Color.WHITE) ? "White's Turn" : "Black's Turn");
                                    }
                                    drawBoard();

                                    if (board.isCheckmate(currentTurn)) {
                                        String winner = currentTurn.equals(Color.WHITE) ? "Black" : "White";
                                        drawBoard();
                                        JOptionPane.showMessageDialog(null, winner + " wins by checkmate!");
                                        System.exit(0);
                                    }

                                } else {
                                    selectedPosition = null; // invalid move, deselect
                                }
                            }
                        }
                    }
                };
                
                square.addMouseListener(clickHandler);
                label.addMouseListener(clickHandler);

                square.add(label, BorderLayout.CENTER);
                boardPanel.add(square);

                lightSquare = !lightSquare;
            }
            lightSquare = !lightSquare;
        }

        boardPanel.revalidate();
        boardPanel.repaint();
    }

    private ImageIcon getPieceImage(Piece piece, int size) {
        if (piece == null) return null;
    
        String color = piece.getColor().equals(Color.WHITE) ? "w" : "b";
        String rank;
        switch (piece.getRank()) {
            case "Pawn":   rank = "P"; break;
            case "Rook":   rank = "R"; break;
            case "Knight": rank = "N"; break;
            case "Bishop": rank = "B"; break;
            case "Queen":  rank = "Q"; break;
            case "King":   rank = "K"; break;
            default: return null;
        }
    
        String path = "/resources/pieces/" + color + rank + ".png";
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL == null) {
            System.out.println("Image not found: " + path);
            return null;
        }
        ImageIcon icon = new ImageIcon(imgURL);
        Image scaled = icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
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
