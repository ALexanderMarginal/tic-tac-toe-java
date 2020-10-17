package tictactoe;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static final String[][] board = {
            {"_", "_", "_"},
            {"_", "_", "_"},
            {"_", "_", "_"}
    };
    private static String currentPlayer = "X";

    public static void main(String[] args) {
        printBoard();
        move();
    }

    private static void move() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the coordinates: ");
        String[] input = sc.nextLine().split(" ");

        int lineIndex = 0;
        int colIndex = 0;
        try {
            colIndex = Integer.parseInt(input[0]);
            lineIndex = Integer.parseInt(input[1]);
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            move();
            return;
        }

        if (lineIndex < 1 || lineIndex > board.length || colIndex < 1 || colIndex > board.length) {
            System.out.println("Coordinates should be from 1 to 3!");
            move();
            return;
        }

        lineIndex = board.length - lineIndex;
        colIndex = colIndex - 1;

        if (!"_".equals(board[lineIndex][colIndex])) {
            System.out.println("This cell is occupied! Choose another one!");
            move();
            return;
        }

        board[lineIndex][colIndex] = currentPlayer;
        currentPlayer = "X".equals(currentPlayer) ? "O" : "X";
        printBoard();
        checkGameStatus();
    }

    private static void printBoard() {
        System.out.println("---------");
        for (String[] line :
                board) {
            System.out.print("| ");
            for (String col :
                    line) {
                System.out.print(col + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static Map<String, Boolean> checkForWin() {
        Map<String, Boolean> win = new java.util.HashMap<>(Map.of("X", false, "O", false));

        //loops through rows checking if win-condition exists
        for (int r = 0; r < 3; r++) {
            if (board[r][0].equals(board[r][1]) && board[r][1].equals(board[r][2]) && !"_".equals(board[r][0])) {
                win.put(board[r][0], true);
            }
        }
        //loops through columns checking if win-condition exists
        for (int c = 0; c < 3; c++) {
            if (Objects.equals(board[0][c], board[1][c]) && Objects.equals(board[1][c], board[2][c]) && !"_".equals(board[0][c])) {
                win.put(board[0][c], true);
            }
        }
        //checks diagonals for win-condition
        if (Objects.equals(board[0][0], board[1][1]) && Objects.equals(board[1][1], board[2][2]) && !"_".equals(board[0][0])) {
            win.put(board[0][0], true);
        }

        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !"_".equals(board[0][0])) {
            win.put(board[0][2], true);
        }

        return win;
    }

    private static void checkGameStatus() {
        Map<String, Boolean> winners = checkForWin();

        if (winners.get("X") && winners.get("O")) {
            System.out.println("Impossible");
            return;
        }
        if (winners.get("X") || winners.get("O")) {
            String winPlayer = winners.get("X") ? "X" : "O";
            System.out.println(winPlayer + " wins");
            return;
        }

        int countX = 0;
        int countO = 0;
        int countEmpty = 0;

        for (String[] line :
                board) {
            for (String cell :
                    line) {
                switch (cell) {
                    case "X":
                        countX++;
                        break;
                    case "O":
                        countO++;
                        break;
                    default:
                        countEmpty++;
                }
            }
        }

        if (Math.abs(countX - countO) >= 2) {
            System.out.println("Impossible");
            return;
        }

        if (countEmpty == 0) {
            System.out.println("Draw");
            return;
        }

        System.out.println("Game not finished");
        move();
    }
}
