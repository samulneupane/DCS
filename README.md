# DCS
CS 3354 Project
Team Members: 
Chibuikem ozueh (wjp54)	
Darien Smith (lfn7)	
samul neupane	(bnv36)



# Console Chess Game

## How to Play
- This is a two-player chess game played in the terminal.
- Players take turns, starting with White.
- Enter the row and column of the piece you want to move, then the destination.
- Moves follow standard chess rules.
- Capture the opponent’s king to win.


## How to Move
- Enter the row and column of the piece you want to move.
- Enter the row and column of where you want to move it.
- If the move is valid, the piece moves. Otherwise, try again.



### Example Move
E2 E4
This moves a pawn from E2 to E4.

**How to Compile
**Make sure you are in the project root directory (DCS/), then run:
bashjavac -d src/out src/chessgame/*.java src/pieces/*.java src/utils/*.java
Then copy the piece images to the output folder:
bashcp -r src/resources src/out/
How to Run
bashjava -cp src/out chessgame.chessGame
