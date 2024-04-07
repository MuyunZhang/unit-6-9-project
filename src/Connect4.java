import java.util.Scanner;

public class Connect4 {

    private Space[][] display;
    private Player player1;
    private Player player2;
    private Scanner scanner;
    private boolean gameOver;
    private boolean moveValid;
    private int[] label = {1, 2, 3, 4, 5, 6, 7};

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
        display = new Space[8][7];
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

            // Player 1's turn
            while (!moveValid) {
                printDisplay();
                System.out.println(player1.getName() + ", look at the numbers above the board and enter where you want to drop your piece");
                input = scanner.nextInt();
                moveValid = dropPiece(input, player1);
            }
            if (checkConnect4(player1)) {
                gameOver = true;
                System.out.println(player1.getName() + " wins!");
                break;
            }

            // Player 2's turn
            moveValid = false;
            while (!moveValid) {
                printDisplay();
                System.out.println(player2.getName() + ", look at the numbers above the board and enter where you want to drop your piece");
                input = scanner.nextInt();
                moveValid = dropPiece(input, player2);
            }
            if (checkConnect4(player2)) {
                gameOver = true;
                System.out.println(player2.getName() + " wins!");
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
            if (row < 0 || row >= 8 || col < 0 || col >= 7) continue; // Out of bounds check
            if (display[row][col] instanceof Player && display[row][col] == player) {
                count++;
            } else {
                break; // Break the sequence if not matching
            }
        }
        return count == 4; // Return true if there are 4 consecutive pieces
    }

    private boolean checkConnect4(Player player) {
        // Check horizontal
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 4; col++) {
                if (checkLine(player, row, col, 0, 1)) return true; // Check right
            }
        }

        // Check vertical
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 7; col++) {
                if (checkLine(player, row, col, 1, 0)) return true; // Check down
            }
        }

        // Check diagonal
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 4; col++) {
                if (checkLine(player, row, col, 1, 1)) return true; // Check diagonal
            }
        }

        // Check anti-diagonal
        for (int row = 0; row < 5; row++) {
            for (int col = 3; col < 7; col++) {
                if (checkLine(player, row, col, 1, -1)) return true; // Check anti-diagonal
            }
        }

        return false; // No Connect Four found
    }

    public boolean dropPiece(int col, Player player) {
        if (col < 1 || col > 7) {
            System.out.println("You cannot place a piece there!");
            return false;
        }

        for (int row = 7; row >= 0; row--) {
            if (!(display[row][col - 1] instanceof Player)) {
                display[row][col - 1] = player;
                return true;
            }
        }

        System.out.println("That column is filled up!");
        return false;
    }

    private void printDisplay() {
        for (int i : label) {
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
        for (int i : label) {
            System.out.print(i + " ");
        }
    }
}

