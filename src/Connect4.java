
import java.util.Scanner;
import java.util.ArrayList;
public class Connect4 {

    private Space[][] display;
    private Player player1;

    private Player player2;

    private Scanner scanner;

     int[] label = {1, 2, 3, 4, 5, 6, 7};

    private void createPlayer() {
        System.out.print("Enter Player 1 name: ");
        String name = scanner.nextLine();
        player1 = new Player(name);
        System.out.println("Enter Player 2 name");
        String name2 = scanner.nextLine();
        player2 = new Player(name2);
    }
    private void setupDisplay() {
        for(int i : label){
            System.out.println(i);
        }
        display = new Space[8][7];
    }



    public void setUp(){

    }

    public void play() {

    }
    private void printDisplay() {
        for(int i = 0; i < display.length; i ++){
            for(int k = 0; k < display[i].length; k ++){
                System.out.print(display[i][k].getSymbol());
            }
            System.out.println();
        }
    }

}
