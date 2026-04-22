package chessgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import pieces.Piece;
import utils.Board;
import utils.Position;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class chessGame extends JFrame {
  
    Board board = new Board();
    Position selecetedPosition = null;

    public chessGame() {

        board = new Board();

        setTitle("8x8 Chess Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== MENU BAR =====
        menuBar = new JMenuBar();
        gameMenu = new JMenu("Game");
        newGameItem = new JMenuItem("New Game");

        newGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board = new Board();
                drawBoard();
            }
        });

        gameMenu.add(newGameItem);
        menuBar.add(gameMenu);
        setJMenuBar(menuBar);

        // ===== BOARD PANEL =====
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8, 8));
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

                Piece piece = board.getPiece(new Position(row, col));
                JLabel label = new JLabel(getSymbol(piece), SwingConstants.CENTER);
                label.setFont(new Font("Serif", Font.PLAIN, 52));

                square.add(label);
                boardPanel.add(square);

                lightSquare = !lightSquare;
       
                
               // mouse listener being added for each square developed. 
               final int r = row;
               final int c= col;

                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Position clickedPos = new Position(r, c);
                        Piece clickedPiece = board.getPiece(clickedPos);

                        if (selecetedPosition == null) {
                            // No piece selected yet, select the clicked piece if it exists
                            if (clickedPiece != null) {
                                selecetedPosition = clickedPos;
                                System.out.println("Selected: " + clickedPiece.getRank() + " at " + clickedPos);
                            }
                        } else {
                            // A piece is already selected, attempt to move it to the clicked position
                            Piece selectedPiece = board.getPiece(selecetedPosition);
                            if (selectedPiece != null) {
                                System.out.println("Attempting to move " + selectedPiece.getRank() + " from " + selecetedPosition + " to " + clickedPos);
                                if(selectedPiece.possibleMoves(board).contains(clickedPos)) {
                                    System.out.println("Move is valid!");
                                    refreshBoard(selecetedPosition, clickedPos);
                                } else {
                                    System.out.println("Move is invalid!");
                                }
                                // Here you would add logic to validate the move and update the board state
                                // For now, we just print the attempted move
                            }
                            selecetedPosition = null; // Deselect after attempting a move
                        
                        }
                    
                                    }
                });
            }
            lightSquare = !lightSquare;
        }
    }

    /**
     * Refreshes the board by moving a piece from one position to another and updating the UI.
     * @param fromPosition The position of the piece to move
     * @param toPosition The destination position for the piece
     */
    public void refreshBoard(Position fromPosition, Position toPosition) {
        // Use board's movePiece to handle all movement logic and validation
        if (board.movePiece(fromPosition, toPosition)) {
            // Rebuild the UI to reflect the new board state
            rebuildBoardUI();
        }
    }

    /**
     * Rebuilds the entire board UI to reflect the current board state.
     */
    private void rebuildBoardUI() {
        // Remove all existing components
        getContentPane().removeAll();
        
        // Recreate the board squares with current pieces
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

                // Add mouse listener to the new square
                final int r = row;
                final int c = col;

                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Position clickedPos = new Position(r, c);
                        Piece clickedPiece = board.getPiece(clickedPos);

                        if (selecetedPosition == null) {
                            // No piece selected yet, select the clicked piece if it exists
                            if (clickedPiece != null) {
                                selecetedPosition = clickedPos;
                                System.out.println("Selected: " + clickedPiece.getRank() + " at " + clickedPos);
                            }
                        } else {
                            // A piece is already selected, attempt to move it to the clicked position
                            Piece selectedPiece = board.getPiece(selecetedPosition);
                            if (selectedPiece != null) {
                                System.out.println("Attempting to move " + selectedPiece.getRank() + " from " + selecetedPosition + " to " + clickedPos);
                                if(selectedPiece.possibleMoves(board).contains(clickedPos)) {
                                    System.out.println("Move is valid!");
                                    refreshBoard(selecetedPosition, clickedPos);
                                } else {
                                    System.out.println("Move is invalid!");
                                }
                            }
                            selecetedPosition = null; // Deselect after attempting a move
                        }
                    }
                });
            }
            lightSquare = !lightSquare;
        }
        
        // Repaint the frame to display the updated board
        revalidate();
        repaint();
    }

    // draws each piece on the board using unicode chess symbols
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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                chessGame frame = new chessGame();
                frame.setVisible(true);
            }
        });
    }
}
