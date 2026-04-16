package utils;

public class Utils {

    public static boolean isValidMoveFormat(String input) {
        return input != null && input.matches("^[A-Ha-h][1-8]\\s+[A-Ha-h][1-8]$");
    }
    public static Position notationToPosition(String square) {
        if (square == null || square.length() != 2) return null;
    
        char file = Character.toUpperCase(square.charAt(0)); // A-H
        char rank = square.charAt(1);                        // 1-8
    
        int col = file - 'A';        // A->0 ... H->7
        int row = rank - '1';        // '1'->0 ... '8'->7  (matches your board indexing)
    
        return new Position(row, col);
    }
}

