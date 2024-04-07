import java.util.Scanner;

public class Connect4 {

    private Space[][] display;
    private Player player1;
    private Player player2;
    private Scanner scanner;
    private boolean gameOver;
    private boolean moveValid;
    private int[] label = {1, 2, 3, 4, 5, 6, 7, 8};

    public void play() {
        setupDisplay();
        createPlayer();
        gameLoop();
    }

    private void createPlayer() {
        scanner = new Scanner(System.in);
        System.out.println("Enter Player 1 name: ");
        String name = scanner.nextLine();
        player1 = new Player(name, "\033[0;33m");
        System.out.println("Enter Player 2 name");
        String name2 = scanner.nextLine();
        player2 = new Player(name2, "\033[0;31m");
    }

    private void setupDisplay() {
        display = new Space[8][6];
        for (int i = 0; i < display.length; i++) {
            for (int k = 0; k < display[i].length; k++) {
                display[i][k] = new Space("_ ");
            }
        }
    }

    private void gameLoop() {
        boolean gameOver = false;
        int input = 0;
        while (!gameOver) {
            moveValid = false;

            while (!moveValid) {
                printDisplay();
                System.out.println(player1.getName() + ", look at the numbers above the board and enter where you want to drop your piece");
                input = scanner.nextInt();
                moveValid = dropPiece(input, player1);
            }
            if (checkConnect4(player1)) {
                gameOver = true;
                printDisplay();
                System.out.println("------------------------");
                System.out.println(player1.getName() + " wins!");
                break;
            }

            moveValid = false;
            while (!moveValid) {
                printDisplay();
                System.out.println(player2.getName() + ", look at the numbers above the board and enter where you want to drop your piece");
                input = scanner.nextInt();
                moveValid = dropPiece(input, player2);
            }
            if (checkConnect4(player2)) {
                gameOver = true;
                printDisplay();
                System.out.println("------------------------");
                System.out.println(player2.getName() + " wins!");
                break;
            }
            boolean tie = true;
            for(int i = 0; i < display.length; i ++){
                for(int k = 0; k < display[i].length; k ++){
                    if(display[i][k].getSymbol().equals("_ ")){
                        tie = false;
                    }
                }
            }
            if(tie){
                gameOver = true;
                printDisplay();
                System.out.println("------------------------");
                System.out.println( Colors.PURPLE + "It is a tie" + Colors.RESET);
                break;
            }
        }
        scanner.close();
    }

    private boolean checkLine(Player player, int startRow, int startCol, int dRow, int dCol) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int row = startRow + i * dRow;
            int col = startCol + i * dCol;
            if (row < 0 || row >= 8 || col < 0 || col >= 6) continue;
            if (display[row][col] instanceof Player && display[row][col] == player) {
                count++;
            } else {
                break;
            }
        }
        return count == 4;
    }

    private boolean checkConnect4(Player player) {
        // Check horizontal
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 5; col++) { // Adjusted for 8 columns and 6 rows
                if (checkLine(player, row, col, 0, 1)) return true; // Check right
            }
        }

        // Check vertical
        for (int row = 0; row < 3; row++) { // Adjusted for 8 columns and 6 rows
            for (int col = 0; col < 6; col++) { // Adjusted for 8 columns and 6 rows
                if (checkLine(player, row, col, 1, 0)) return true; // Check down
            }
        }

        // Check diagonal
        for (int row = 0; row < 3; row++) { // Adjusted for 8 columns and 6 rows
            for (int col = 0; col < 5; col++) { // Adjusted for 8 columns and 6 rows
                if (checkLine(player, row, col, 1, 1)) return true; // Check diagonal
            }
        }

        // Check the other diagonal? idk
        for (int row = 0; row < 3; row++) { // Adjusted for 8 columns and 6 rows
            for (int col = 3; col < 6; col++) { // Adjusted for 8 columns and 6 rows
                if (checkLine(player, row, col, 1, -1)) return true; // Check anti-diagonal
            }
        }

        return false;
    }

    public boolean dropPiece(int col, Player player) {
        if (col < 1 || col > 8) {
            System.out.println("You cannot place a piece there!");
            return false;
        }

        for (int row = 5; row >= 0; row--) {
            if (!(display[col - 1][row] instanceof Player)) {
                display[col - 1][row] = player;
                return true;
            }
        }

        System.out.println("That column is filled up!");
        return false;
    }


    private void printDisplay() {
        System.out.println();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 8; col++) {
                if (display[col][row] instanceof Player) {
                    System.out.print(display[col][row].getSymbol() + " ");
                } else {
                    System.out.print(display[col][row].getSymbol());
                }
            }
            System.out.println();
        }
        for (int i : label) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
