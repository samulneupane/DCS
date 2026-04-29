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
    private Color lightSquareColor = new Color(0xEEEED2); // chess.com light
    private Color darkSquareColor = new Color(0x769656);  // chess.com dark
    
    private boolean isAIMode = false;
    private AIPlayer aiPlayer = new AIPlayer(Color.BLACK);


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


            // AI makes a move if in AI mode
        if (isAIMode && currentTurn.equals(Color.BLACK)) {
            aiPlayer.makeMove(board);
            currentTurn = Color.WHITE;
            statusLabel.setText("White's Turn");
            drawBoard();
}
            }
        });

        gameMenu.add(newGameItem);
        
        JMenu settingsMenu = new JMenu("Settings");
        JMenuItem openSettings = new JMenuItem("Open Settings");
        openSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSettingsDialog();
            }
        });
        settingsMenu.add(openSettings);
        menuBar.add(settingsMenu);

        menuBar.add(gameMenu);
        setJMenuBar(menuBar);
        
        //AI mode
        JMenu modeMenu = new JMenu("Mode");
        JRadioButtonMenuItem vsHuman = new JRadioButtonMenuItem("vs Human", true);
        JRadioButtonMenuItem vsAI = new JRadioButtonMenuItem("vs AI", false);

        ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(vsHuman);
        modeGroup.add(vsAI);

        vsHuman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isAIMode = false;
            }
        });

        vsAI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isAIMode = true;
            }
        });

        modeMenu.add(vsHuman);
        modeMenu.add(vsAI);
        menuBar.add(modeMenu);

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
                square.setBackground(lightSquare ? lightSquareColor : darkSquareColor);

                //if selected highlight
                if (selectedPosition != null && selectedPosition.getRow() == row && selectedPosition.getColumn() == col) {
                    square.setBackground(new Color(255, 255, 100)); // yellow highlight
                }


                Position pos = new Position(row, col);
                Piece piece = board.getPiece(pos);

                JLabel label = new JLabel("",SwingConstants.CENTER);
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

    private void showSettingsDialog() {
        JDialog dialog = new JDialog(this, "Settings", true);
        dialog.setSize(300, 250);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
    
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    
        // Theme selection
        JLabel themeLabel = new JLabel("Board Theme:");
        ButtonGroup themeGroup = new ButtonGroup();
        JRadioButton chesscom = new JRadioButton("Chess.com");
        JRadioButton wooden = new JRadioButton("Wooden");
        JRadioButton classic = new JRadioButton("Classic");
        themeGroup.add(chesscom);
        themeGroup.add(wooden);
        themeGroup.add(classic);
        chesscom.setSelected(true); // default
    
        // Size selection
        JLabel sizeLabel = new JLabel("Board Size:");
        ButtonGroup sizeGroup = new ButtonGroup();
        JRadioButton small = new JRadioButton("Small (480x480)");
        JRadioButton medium = new JRadioButton("Medium (600x600)");
        JRadioButton large = new JRadioButton("Large (720x720)");
        sizeGroup.add(small);
        sizeGroup.add(medium);
        sizeGroup.add(large);
        medium.setSelected(true); // default
    
        panel.add(themeLabel);
        panel.add(chesscom);
        panel.add(wooden);
        panel.add(classic);
        panel.add(Box.createVerticalStrut(10));
        panel.add(sizeLabel);
        panel.add(small);
        panel.add(medium);
        panel.add(large);
    
        // Apply button
        JButton applyBtn = new JButton("Apply");
        applyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Apply theme
                if (chesscom.isSelected()) {
                    lightSquareColor = new Color(0xEEEED2);
                    darkSquareColor = new Color(0x769656);
                } else if (wooden.isSelected()) {
                    lightSquareColor = new Color(0xF0D9B5);
                    darkSquareColor = new Color(0xB58863);
                } else {
                    lightSquareColor = Color.LIGHT_GRAY;
                    darkSquareColor = Color.DARK_GRAY;
                }
    
                // Apply size
                if (small.isSelected()) {
                    setSize(480, 510);
                } else if (medium.isSelected()) {
                    setSize(600, 630);
                } else {
                    setSize(720, 750);
                }
    
                drawBoard();
                dialog.dispose();
            }
        });
    
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(applyBtn, BorderLayout.SOUTH);
        dialog.setVisible(true);
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
