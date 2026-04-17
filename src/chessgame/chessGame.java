package chessgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

import pieces.Piece;
import utils.Board;
import utils.Position;

public class chessGame extends JFrame {

    private Board board;
    private JPanel boardPanel;

    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenuItem newGameItem;

    private JButton undoButton;

    private Stack<Move> moveHistory = new Stack<>();

    private Position selectedPosition = null;

    public chessGame() {

        board = new Board();

        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
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
                moveHistory.clear();
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

        // ===== UNDO BUTTON =====
        undoButton = new JButton("Undo");
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undoLastMove();
            }
        });
        add(undoButton, BorderLayout.SOUTH);

        drawBoard();
    }

    // ===== DRAW BOARD =====
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
                label.setFont(new Font("Serif", Font.PLAIN, 48));

                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleSquareClick(pos);
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

    // ===== HANDLE CLICK-TO-MOVE =====
    private void handleSquareClick(Position pos) {
        if (selectedPosition == null) {
            Piece selected = board.getPiece(pos);
            if (selected != null) {
                selectedPosition = pos;
            }
        } else {
            movePiece(selectedPosition, pos);
            selectedPosition = null;
        }
    }

    // ===== MOVE PIECE & RECORD =====
    private void movePiece(Position from, Position to) {
        Piece moving = board.getPiece(from);
        if (moving == null) return;

        Piece captured = board.getPiece(to);

        Move move = new Move(moving, from, to, captured);
        moveHistory.push(move);

        // Move main piece
        moving.setPosition(to);

        // Remove captured piece
        if (captured != null) {
            captured.setPosition(null);
        }

        drawBoard();
    }

    // ===== UNDO LAST MOVE =====
    private void undoLastMove() {
        if (moveHistory.isEmpty()) return;

        Move last = moveHistory.pop();

        // Restore moved piece
        last.movedPiece.setPosition(last.from);

        // Restore captured piece
        if (last.capturedPiece != null) {
            last.capturedPiece.setPosition(last.to);
        }

        drawBoard();
    }

    // ===== PIECE SYMBOLS =====
    private String getSymbol(Piece piece) {
        if (piece == null) return "";

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

// ===== MOVE CLASS =====
class Move {
    Piece movedPiece;
    Position from;
    Position to;
    Piece capturedPiece;

    public Move(Piece movedPiece, Position from, Position to, Piece capturedPiece) {
        this.movedPiece = movedPiece;
        this.from = from;
        this.to = to;
        this.capturedPiece = capturedPiece;
    }
}
