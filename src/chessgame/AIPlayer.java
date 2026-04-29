package chessgame;

import pieces.Piece;
import utils.Board;
import utils.Position;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIPlayer {

    private Color color;
    private Random random;

    public AIPlayer(Color color) {
        this.color = color;
        this.random = new Random();
    }
}
