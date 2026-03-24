public class Game{
  public Board board;
  private Player white;
  private Player black;
  public int currentTurn; 

   public Game() {
     
   Color whiteColor = new Color('W', 1);
   Color blackColor = new Color('B', 2);

   white = new Player(whiteColor);
   black = new Player(blackColor);
   currentTurn = 1;

}
  public void start() {
        board = new Board();
        board.initialize();
    }
  public string end(){
    
  }
}
