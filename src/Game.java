public class Game{
  public Board board;
  private player white;
  private player black;
  public int currentTurn; 

   public Game() {
     
   Color white = new Color('W', 1);
  Color black = new Color('B', 2);

   white = new Player(white);
   black = new Player(black);
   currentTurn= 1;
}
}
