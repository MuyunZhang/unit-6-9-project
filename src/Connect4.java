
import java.util.Scanner;
import java.util.ArrayList;
public class Connect4 {

    private Space[][] display;
    private Player player1;

    private Player player2;

    private Scanner scanner;

    int[] label = {1, 2, 3, 4, 5, 6, 7};

    public void play(){
         setUp();
         setupDisplay();
         printDisplay();
    }
    private void createPlayer() {
        System.out.print("Enter Player 1 name: ");
        String name = scanner.nextLine();
        player1 = new Player(name);
        System.out.println("Enter Player 2 name");
        String name2 = scanner.nextLine();
        player2 = new Player(name2);
    }
    private void setupDisplay() {
        display = new Space[8][7];
        for(int i = 0; i < display.length; i ++){
            for(int k = 0; k < display[i].length; k++){
                display[i][k] = new Space("_ ");
            }
        }
    }

    public void setUp(){

    }


    public void dropPiece(int col, Player player) {
        // this code is not very good change it later
        if (col > 7 || col < 0) {
            System.out.println("You cannot place a piece there!");
        } else {
            Space spaceToChange = null;
            for (int row = 7; row > 0; row--) {
                if (!(display[row][col] instanceof Player)) {
                    spaceToChange = display[row][col];
                    break;
                }
            }
            if (spaceToChange == null) {
                System.out.println("That column is filled up!");
            } else {
                spaceToChange = player;
                System.out.println("You have placed a piece in column " + col + "!");
            }
        }
    }

    private void printDisplay() {
        for(int i : label){
            System.out.print(i + " ");
        }
        System.out.println();
        for(int i = 0; i < display.length; i ++){
            for(int k = 0; k < display[i].length; k ++){
                System.out.print(display[i][k].getSymbol());
            }
            System.out.println();
        }
    }
}
