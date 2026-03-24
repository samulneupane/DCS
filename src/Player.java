public class makeMove{
private int[] moveFrom;
 private int[] moveTo;
private Piece piece;
}

public class Player{
 private char color;
 private List<Piece> piecesRemain;
 
//modifying pieces remaining 
 public List<Piece> getPiecesRemain() { return piecesRemain; }
  public void addPiece(Piece p) { piecesRemain.add(p);}
  public void removePiece(Piece p) { piecesRemain.remove(p); }

 public int[] makeMove(int[] f,int[] t, Piece p){
 boolean valid = false;
 for (int i=0;i<possibleMoves.size();i++){
     if (possibleMoves.get(i)[0]==t[0] && possibleMoves.get(i)[1]==t[1]) {
       valid = true; 
      }
    }
 if (valid){
   movePiece(f,t);
   }
 return p.getPosition();
 }
}  
