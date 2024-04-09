import java.util.Scanner;

public class Connect4 {

    private Space[][] display;
    private Player player1;
    private Player player2;
    private Scanner scanner;
    private boolean gameOver;
    private boolean moveValid;

    private boolean endMatch;
    private int[] label = {1, 2, 3, 4, 5, 6, 7, 8};

    public void play() {
        endMatch = false;
        createPlayer();
        while (!endMatch) {
            setupDisplay();
            gameLoop();
            System.out.println(player1.getName() + "'s points: " + player1.getScore());
            System.out.println(player2.getName() + "'s points: " + player2.getScore());
            System.out.println("Would you like to play again? (y/n)");
            scanner.nextLine();
            String playerInput = "";
            while (!playerInput.equals("n") || !playerInput.equals("y")) {
                playerInput = scanner.nextLine();
                if (playerInput.equals("n")) {
                    endMatch = true;
                    scanner.close();
                    break;
                } else if (playerInput.equals("y")) {
                    System.out.println("Starting a new game...");
                    break;
                } else {
                    System.out.println("Can you retype your answer?");
                }
            }
        }
        System.out.println("Goodbye " + player1.getName() + " and " + player2.getName() + "!");
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
                player1.addPoints(1);
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
                player2.addPoints(1);
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
    }

    private boolean checkLine(Player player, int startRow, int startCol, int dRow, int dCol) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int row = startRow + i * dRow;
            int col = startCol + i * dCol;
            if (row < 0 || row >= 8 || col < 0 || col >= 6) continue;
            if (display[row][col] instanceof Player && display[row][col] == player) {
                count++;
                if(count == 4){
                    return true;
                }
            } else {
                break;
            }
        }
        return count == 4;
    }

    private boolean checkConnect4(Player player) {
        // Check horizontal
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 3; col++) {
                if (checkLine(player, row, col, 0, 1)) {
                    return true; // Check right
                }
            }
        }

        // Check vertical
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 6; col++) {
                if (checkLine(player, row, col, 1, 0)) {
                    return true; // Check down
                }
            }
        }

        // Check diagonal
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 3; col++) {
                if (checkLine(player, row, col, 1, 1)) {
                    return true; // Check diagonal
                }
            }
        }

        // Check the other diagonal
        for (int row = 0; row < 5; row++) {
            for (int col = 3; col < 6; col++) {
                if (checkLine(player, row, col, 1, -1)) {
                    return true; // Check anti-diagonal
                }
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
