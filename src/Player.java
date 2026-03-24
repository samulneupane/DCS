
public class Color {
    private char color;
    private int turn; // 1 = White, 2 = Black

    public Color(char color, int turn) {
        this.color = color;
        this.turn = turn;
    }

    public char getColor() { return color; }
    public int getTurn() { return turn; }

    public void setColor(char color) { this.color = color; }
    public void setTurn(int turn) { this.turn = turn; }

}



public class Player{
 private Color color;
 private List<Piece> piecesRemain;

    public Player(Color color) {
    this.color = color;
    this.piecesRemain = new ArrayList<>();
}
 
//modifying pieces remaining 
 public List<Piece> getPiecesRemain() { return piecesRemain; }
  public void addPiece(Piece p) { piecesRemain.add(p);}
  public void removePiece(Piece p) { piecesRemain.remove(p); }

 public Position makeMove(Position from, Position to, Piece p, Board board){
 boolean valid = false;
 List<Position> possibleMoves = p.possibleMoves(Board);
 for (int i=0;i<possibleMoves.size();i++){
     if (possibleMoves.get(i).getRow()==to.getRow() && possibleMoves.get(i).getColumn()==to.getColumn()) {
       valid = true; 
      }
    }
 if (valid){
   board.movePiece(from,to);
   }
 return p.getPosition();
 }
}  
