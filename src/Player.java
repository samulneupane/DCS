
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
 
//modifying pieces remaining 
 public List<Piece> getPiecesRemain() { return piecesRemain; }
  public void addPiece(Piece p) { piecesRemain.add(p);}
  public void removePiece(Piece p) { piecesRemain.remove(p); }

 public Position makeMove(Position from, Position to, Piece p){
 boolean valid = false;
 List<Position> possibleMoves = p.possibleMoves(board);
 for (int i=0;i<possibleMoves.size();i++){
     if (possibleMoves.get(i).getRow()==to.getRow() && possibleMoves.get(i).getCol()==to.getCol() {
       valid = true; 
      }
    }
 if (valid){
   movePiece(from,to);
   }
 return p.getPosition();
 }
}  
