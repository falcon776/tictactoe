import java.util.Scanner;
/**
 * Tic-Tac-Toe: Three-player console with 4x4 grid, non-graphics, non-OO version.
 * All variables/methods are declared as static (belong to the class)
 *  in the non-OO version.
 */
public class TTTConsoleNonOO2P {
   // Name-constants to represent the seeds and cell contents
   public static final int EMPTY = 0;
   public static final int CROSS = 1;
   public static final int NOUGHT = 2;
   public static final int ZONK= 3;
 
   // Name-constants to represent the various states of the game
   public static final int PLAYING = 0;
   public static final int DRAW = 1;
   public static final int CROSS_WON = 2;
   public static final int NOUGHT_WON = 3;
   public static final int ZONK_WON = 4;
   
 
   // The game board and the game status
   public static final int ROWS = 4, COLS = 4; // number of rows and columns
   public static int[][] board = new int[ROWS][COLS]; // game board in 2D array
                                                      //  containing (EMPTY, CROSS, NOUGHT)
   public static int currentState;  // the current state of the game
                                    // (PLAYING, DRAW, CROSS_WON, NOUGHT_WON)
   public static int currentPlayer; // the current player (CROSS or NOUGHT)
   public static int currntRow, currentCol; // current seed's row and column
 
   public static Scanner in = new Scanner(System.in); // the input Scanner
 
   /** The entry main method (the program starts here) */
   public static void main(String[] args) {
      // Initialize the game-board and current status
      initGame();
      // Play the game once
      do {
         playerMove(currentPlayer); // update currentRow and currentCol
         updateGame(currentPlayer, currntRow, currentCol); // update currentState
         printBoard();
         // Print message if game-over
         if (currentState == CROSS_WON) {
            System.out.println("'X' won! Bye!");
             
         } else if (currentState == NOUGHT_WON) {
            System.out.println("'O' won! Bye!");
             
         } 
         else if (currentState == ZONK_WON) {
             System.out.println("'Z' won! Bye!");
              
          } 
         else if (currentState == DRAW) {    
             System.out.println("Its Draw!!");
              
          } 
         // Switch player
         currentPlayer = (currentPlayer == CROSS) ? NOUGHT : (currentPlayer == NOUGHT) ? ZONK : CROSS;
      } while (currentState == PLAYING); // repeat if not game-over
     
   }
 
   /** Initialize the game-board contents and the current states */
   public static void initGame() {
      for (int row = 0; row < ROWS; ++row) {
         for (int col = 0; col < COLS; ++col) {
            board[row][col] = EMPTY;  // all cells empty
         }
      }
      currentState = PLAYING; // ready to play
      currentPlayer = ZONK;  // cross plays first
   }
 
   /** Player with the "theSeed" makes one move, with input validation.
       Update global variables "currentRow" and "currentCol". */
   public static void playerMove(int theSeed) {
      boolean validInput = false;  // for input validation
      do {
         if (theSeed == CROSS) {
            System.out.print("Player 'X', enter your move (row[1-4] column[1-4]): ");
         } else if (theSeed == NOUGHT){
            System.out.print("Player 'O', enter your move (row[1-4] column[1-4]): ");
         } 
         	else {
             System.out.print("Player 'Z', enter your move (row[1-4] column[1-4]): ");
          } 
         int row = in.nextInt() - 1;  // array index starts at 0 instead of 1
         int col = in.nextInt() - 1;
         if (row >= 0 && row < ROWS && col >= 0 && col < COLS && board[row][col] == EMPTY) {
            currntRow = row;
            currentCol = col;
            board[currntRow][currentCol] = theSeed;  // update game-board content
            validInput = true;  // input okay, exit loop
         } else {
            System.out.println("This move at (" + (row + 1) + "," + (col + 1)
                  + ") is not valid. Try again...");
         }
      } while (!validInput);  // repeat until input is valid
   }
 
   /** Update the "currentState" after the player with "theSeed" has placed on
       (currentRow, currentCol). */
   public static void updateGame(int theSeed, int currentRow, int currentCol) {
      if (hasWon(theSeed, currentRow, currentCol)) {  // check if winning move
         currentState = (theSeed == CROSS) ? CROSS_WON : (theSeed == NOUGHT) ? NOUGHT_WON : ZONK_WON;
      } else if (isDraw()) {  // check for draw
         currentState = DRAW;
         
      }
      // Otherwise, no change to currentState (still PLAYING).
   }
 
   /** Return true if it is a draw (no more empty cell) */
   // TODO: Shall declare draw if no player can "possibly" win
   public static boolean isDraw() {
      for (int row = 0; row < ROWS; ++row) {
         for (int col = 0; col < COLS; ++col) {
            if (board[row][col] == EMPTY) {
               return false;  // an empty cell found, not draw, exit
            }
         }
      }
      return true;  // no empty cell, it's a draw
   }
 
   /** Return true if the player with "theSeed" has won after placing at
       (currentRow, currentCol) */
   public static boolean hasWon(int theSeed, int currentRow, int currentCol) {
      return (board[currentRow][0] == theSeed         // 3-in-the-row section 1 //
                   && board[currentRow][1] == theSeed
                   && board[currentRow][2] == theSeed
                		   ||board[currentRow][1] == theSeed         // 3-in-the-row section 2//
                                   && board[currentRow][2] == theSeed
                                   && board[currentRow][3] == theSeed
                                		           
                  
              || board[0][currentCol] == theSeed      // 3-in-the-column section 1//
                   && board[1][currentCol] == theSeed
                   && board[2][currentCol] == theSeed
                		   || board[1][currentCol] == theSeed      // 3-in-the-column section 2//
                           && board[2][currentCol] == theSeed
                           && board[3][currentCol] == theSeed
                        		    
                   
              || currentRow == currentCol            // 3-in-the-diagonal section 1//
                   && board[0][0] == theSeed
                   && board[1][1] == theSeed
                   && board[2][2] == theSeed 
                   ||currentRow == currentCol            // 3-in-the-diagonal section 2 //
                           && board[1][1] == theSeed
                           && board[2][2] == theSeed
                           && board[3][3] == theSeed 
                           || (currentRow + currentCol ) % 2==1    // 3-in-the-diagonal section 3 //
                        		   && board[0][1] == theSeed
                        		   && board[1][2] == theSeed
                        		   && board[2][3] == theSeed                                  
                                   ||(currentRow + currentCol ) % 2==1            // 3-in-the-diagonal section 4 //
                                           && board[1][0] == theSeed
                                           && board[2][1] == theSeed
                                           && board[3][2] == theSeed 
                                          
                   
              || currentRow + currentCol == 2  // 3-in-the-opposite-diagonal section 1//
                   && board[0][2] == theSeed
                   && board[1][1] == theSeed
                   && board[2][0] == theSeed
                   ||currentRow + currentCol == 3  // 3-in-the-opposite-diagonal section 2//
                           && board[0][3] == theSeed
                           && board[1][2] == theSeed
                           && board[2][1] == theSeed
                           ||currentRow + currentCol == 3  // 3-in-the-opposite-diagonal section 3 //
                                   && board[1][2] == theSeed
                                   && board[2][1] == theSeed
                                   && board[3][0] == theSeed
                                   ||currentRow + currentCol == 4  // 3-in-the-opposite-diagonal section 4 //
                                           && board[1][3] == theSeed
                                           && board[2][2] == theSeed
                                           && board[3][1] == theSeed);
   }
 
   /** Print the game board */
   public static void printBoard() {
      for (int row = 0; row < ROWS; ++row) {
         for (int col = 0; col < COLS; ++col) {
            printCell(board[row][col]); // print each of the cells
            if (col != COLS - 1) {
               System.out.print("|");   // print vertical partition
            }
         }
         System.out.println();
         if (row != ROWS - 1) {
            System.out.println("---------------"); // print horizontal partition
         }
      }
      System.out.println();
   }
 
   /** Print a cell with the specified "content" */
   public static void printCell(int content) {
      switch (content) {
         case EMPTY:  System.out.print("   "); break;
         case NOUGHT: System.out.print(" O "); break;
         case CROSS:  System.out.print(" X "); break;
         case ZONK:  System.out.print(" Z "); break;
      }
   }
}