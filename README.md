# DCS Chess Game
CS 3354 Project — Texas State University

**Team Members:**
- Samul Neupane (bnv36)
- Chibuikem Ozueh (wjp54)
- Darien Smith (lfn7)


---

## Project Overview

A fully functional two-player chess game built in Java, developed across three phases:
- **Phase 1** — Console-based chess game with full piece logic and rules
- **Phase 2** — Graphical user interface built with Java Swing
- **Phase 3** — Integration of backend logic with GUI, plus additional features

---

## Features

- Graphical chessboard with PNG piece images
- Turn-based play — White always goes first
- Full move validation using each piece's legal moves
- Check detection — status bar warns when a king is in check
- Checkmate detection — popup announces the winner and ends the game
- Selected piece highlighted in yellow
- Status bar showing whose turn it is at all times
- vs AI mode — play against a computer opponent that makes legal moves automatically
- New Game button to reset the board at any time
- Settings window with board theme and size customization

---

## How to Compile

Make sure you are in the project root directory (DCS/), then run:


javac -d src/out src/chessgame/*.java src/pieces/*.java src/utils/*.java


Then copy the piece images to the output folder:


cp -r src/resources src/out/


---

## How to Run


java -cp src/out chessgame.chessGame


---

## How to Play

1. Launch the game using the run command above.
2. White always moves first.
3. Click one of your pieces to select it — it will highlight in yellow.
4. Click a destination square to move. Only valid moves are accepted.
5. To change your selection, click another one of your own pieces.
6. The status bar at the bottom always shows whose turn it is.
7. If your king is in check, the status bar will warn you.
8. The game ends when checkmate is reached — a popup will announce the winner.

---

## Menu Options

### Game
- **New Game** — Resets the board and starts a fresh game with White going first.

### Mode
- **vs Human** — Two players take turns on the same machine (default).
- **vs AI** — Play as White against a computer opponent that plays Black.

### Settings
- **Board Theme** — Choose between three color schemes:
  - Chess.com green (default)
  - Wooden brown
  - Classic gray
- **Board Size** — Choose between Small (480px), Medium (600px), or Large (720px).
- Click **Apply** to save your changes.

---

## Console Chess Game (Phase 1)

### How to Move
- Enter the source square followed by the destination square.
- If the move is valid, the piece moves. Otherwise, try again.

### Example
E2 E4
This moves the pawn from E2 to E4.
