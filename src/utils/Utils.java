package utils;

public class Utils {

    public static boolean isValidMoveFormat(String input) {
        return input != null && input.matches("^[A-Ha-h][1-8]\\s+[A-Ha-h][1-8]$");
    }
}