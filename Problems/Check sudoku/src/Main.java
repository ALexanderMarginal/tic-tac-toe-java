import java.util.*;

public class Main {
    private static int n = 0;
    private static int size = 0;

    public static void main(String[] args) {
        int[][] board = createBoard();
        boolean success = checkBoard(board);
        System.out.println(success ? "YES" : "NO");
    }

    private static int[][] createBoard() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        size = n * n;
        int[][] board = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        return board;
    }

    private static boolean checkBoard(int[][] board) {
        for (int i = 0; i < size; i++) {
            int line = i * n;
            boolean lineNumbersIsUnique = lineNumbersIsUnique(board[i]);
            if (!lineNumbersIsUnique) {
                return false;
            }
            int[] column = new int[size];
            for (int j = 0; j < size; j++) {
                column[j] = board[j][i];
                int col = j * n;
                if (line < size && col < size) {
                    boolean cellNumberIsUnique = checkBlock(board, line, col);
                    if (!cellNumberIsUnique) {
                        return false;
                    }
                }
            }
            boolean columnNumbersIsUnique = lineNumbersIsUnique(column);
            if (!columnNumbersIsUnique) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkBlock(int[][] board, int col0to2, int row0to2) {
        int[] tempArray = new int[size];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                int tempVal = board[col0to2 + x][row0to2 + y];
                if (tempVal < 1 || tempVal > size || ++tempArray[tempVal - 1] == 2) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean lineNumbersIsUnique(int[] line) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int item :
                line) {
            if (temp.contains(item)) {
                return false;
            }
            temp.add(item);
        }
        return true;
    }
}