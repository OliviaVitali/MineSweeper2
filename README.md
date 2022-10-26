# MineSweeper2
An updated version of MineSweeper in Java


The Cell class contains information about each cell.  A given cell will know if it is a mine, currently exposed by the player, and the number of surrounding mines.  Upon instantition, these variables are default filled to 'exposed, is a mine, and 0 surrounding mines'.  There are setters and getters for these variables.

The Game Status is an ENUM of possible states of the game.  The states include "Won", "Lost", and "NotOverYet".  This helps the game decide if the player may continue and the game is over. 

The MineSweeperGame class contains a gameStatus and the board of Cells.  The functionalityof the game (setting/getting these variables) happens in this class.
Another way to see it, is that the MineSweeperGame contains the rules and current state of the game but does not manipulate itself.  The panel manipulates the game (like players manipulate a chess board).

The MineSweeperPanel class manipulates and instance of MineSweeperGame and defines what the look of the game should be. It is nested inside the Main class.  This is where buttons are created and stored and relate to an instance of MineSweeperGame's board.  This is where we update the display of the game.  This is where we can toggle 'cheat mode'.  'Cheat mode' shows the player the location of the mines by setting the text to "!".  It's not as fun to play this way but it is helpful for testing.

The Main Class contains some information about the GUI of the game and an instance of MineSweeperPanel
