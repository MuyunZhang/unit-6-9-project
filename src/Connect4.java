
import java.util.Scanner;
import java.util.ArrayList;
public class Connect4 {

    private Space[][] display;
    private Player player1;

    private Player player2;

    private Scanner scanner;

    private boolean gameOver;

    private boolean moveValid;

     int[] label = {1, 2, 3, 4, 5, 6, 7};

     public void play(){
         setupDisplay();
         createPlayer();
         Game();
     }

    private void createPlayer() {
         scanner = new Scanner(System.in);
        System.out.println("Enter Player 1 name: ");
        String name = scanner.nextLine();
        player1 = new Player(name, "\033[0;33m");
        System.out.println("Enter Player 2 name");
        String name2 = scanner.nextLine();
        player2 = new Player(name2,"\033[0;31m");
    }
    private void setupDisplay() {
        display = new Space[8][7];
        for(int i = 0; i < display.length; i ++){
            for(int k = 0; k < display[i].length; k++){
                display[i][k] = new Space("_ ");
            }
        }
    }



    public void Game(){
         boolean gameOver = false;
         int input = 0;
         while(!gameOver){
             moveValid = false;
             //fix the bug where it skips a players turn if they drop a piece on a filled up row
             //players 1's turn
             while (!moveValid) {
                 printDisplay();
                 System.out.println(player1.getName() + ", look at the numbers above the board and enter where you want to drop your piece");
                 input = scanner.nextInt();
                 moveValid = dropPiece(input, player1);
             }

             //player 2's turn
             moveValid = false;
             while (!moveValid) {
                 printDisplay();
                 System.out.println(player2.getName() + ", look at the numbers above the board and enter where you want to drop your piece");
                 input = scanner.nextInt();
                 moveValid = dropPiece(input, player2);
             }
         }
    }

    public boolean dropPiece(int col, Player player) {
         if (col > 7 || col < 1) {
            System.out.println("You cannot place a piece there!");
        } else {
             // creates a null space to get cords for an empty space in a col
            int theRowToChange = -1;
            for (int row = 7; row >= 0; row--) {
                // uses the row that isn't a Player space
                if (!(display[row][col - 1] instanceof Player)) {
                    theRowToChange = row;
                    break;
                }
            }
            if (theRowToChange == -1) {
                System.out.println("That column is filled up!");
            } else {
                // changes the row to be the given Player space
                display[theRowToChange][col - 1] = player;
                System.out.println("You have placed a piece in column " + col + "!");
                return true;
            }
        }
        return false;
    }
    private void printDisplay() {
        for(int i : label){
            System.out.print(i + " ");
        }
        System.out.println();
        for (Space[] spaces : display) {
            for (Space space : spaces) {
                if (space instanceof Player) {
                    System.out.print(space.getSymbol() + " ");
                } else {
                    System.out.print(space.getSymbol());
                }
            }
            System.out.println();
        }
    }

}
