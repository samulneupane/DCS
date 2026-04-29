# DCS
CS 3354 Project

**Team Members:**
- Chibuikem Ozueh (wjp54)
- Darien Smith (lfn7)
- Samul Neupane (bnv36)

---

# Console Chess Game (Part 1)

## How to Play
- This is a two-player chess game played in the terminal.
- Players take turns, starting with White.
- Enter the row and column of the piece you want to move, then the destination.
- Moves follow standard chess rules.
- Capture the opponent's king to win.

## How to Move
- Enter the row and column of the piece you want to move.
- Enter the row and column of where you want to move it.
- If the move is valid, the piece moves. Otherwise, try again.

### Example Move
```
E2 E4
```
This moves a pawn from E2 to E4.

---

# GUI Chess Game (Part 2 & 3)

## Features
- Graphical chessboard with PNG piece images
- Turn-based play — White goes first
- Move validation using each piece's possible moves
- Check and checkmate detection
- Selected piece highlighted in yellow
- Status bar showing whose turn it is
- New Game button to reset the board
- Settings window to change board theme and size

## How to Compile

Make sure you are in the project root directory (`DCS/`), then run:


javac -d src/out src/chessgame/*.java src/pieces/*.java src/utils/*.java


Then copy the piece images to the output folder:


cp -r src/resources src/out/

## How to Run

java -cp src/out chessgame.chessGame

## How to Play
- Launch the game using the run command.
- White always moves first.
- Click on one of your pieces to select it — it will highlight in yellow.
- Click on a destination square to move. Only valid moves are accepted.
- If you change your mind, click another one of your own pieces to switch selection.
- Players alternate turns after each valid move.
- The status bar at the bottom shows whose turn it is.
- If your king is in check, the status bar will warn you.
- The game ends when a king is captured — a popup will announce the winner.

## New Game
- Click **Game** in the menu bar, then click **New Game**.
- This resets the board to the starting position and gives White the first turn.

## Settings
- Click **Game** in the menu bar, then click **Settings**.
- **Board Theme** — choose between three color schemes:
  - Chess.com (green, default)
  - Wooden (brown)
  - Classic (gray)
- **Board Size** — choose between Small, Medium, or Large.
- Click **Apply** to save your changes.

