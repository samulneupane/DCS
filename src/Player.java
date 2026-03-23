public class makeMove{
private int[] moveFrom;
 private int[] moveTo;
private Piece piece;
}
public class Player{
 private Char Color;
  private List <piece> piecesRemain; //what pieces they got left
 
 public int[] makeMove(int[] f,int[] t, piece p){
 boolean valid = false;
 for (int i=0;i<possibleMoves.size();i++){
     if (possibleMoves.get(i)[0]==t[0] && possibleMoves.get(i)[1]==t[1]) {
       valid = true; 
      }
    }
 if (valid){
  p.setPosition(t[0], t[1]); 
   }
 return p.position;
 }
}  
