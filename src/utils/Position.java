package utils;
public class Position {
    private int row;
    private int column;

    //constructor
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    //getters
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    

    // Converting position into chess notation
    @Override
    public String toString() {
        char file = (char) ('A' + column); // column → A-H
        int rank = row + 1;               // row → 1-8
        return "" + file + rank;
    }

    // Compare two positions
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Position)) return false;

        Position other = (Position) obj;
        return this.row == other.row && this.column == other.column;
    }
}
