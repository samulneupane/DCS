public class makeMove{
private int[] moveFrom;
 private int[] moveTo;
private Piece piece;
}


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
 private char color;
 private List<Piece> piecesRemain;
 
//modifying pieces remaining 
 public List<Piece> getPiecesRemain() { return piecesRemain; }
  public void addPiece(Piece p) { piecesRemain.add(p);}
  public void removePiece(Piece p) { piecesRemain.remove(p); }

 public int[] makeMove(position fr, position To, Piece p){
 boolean valid = false;
 for (int i=0;i<possibleMoves.size();i++){
     if (possibleMoves.get(i)[0]==To[0] && possibleMoves.get(i)[1]==To[1]) {
       valid = true; 
      }
    }
 if (valid){
   movePiece(fr,To);
   }
 return p.getPosition();
 }
}  
