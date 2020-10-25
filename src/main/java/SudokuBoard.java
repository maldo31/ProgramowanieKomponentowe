import java.util.Random;

public class SudokuBoard {
    private int[][] board = new int[9][9];

    public int[][] getCopyOfBoard() {
        int[][] copiedBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                copiedBoard[i][j] = board[i][j];
            }
        }
        return copiedBoard;
    }

    public void showBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    private boolean checkCell(int row, int column) {
        int active = board[row][column];
        for (int i = 0; i < 9; i++) {
            if (active == board[row][i]) {
                if (column != i) {
                    return false;
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            if (active == board[column][i]) {
                if (row != i) {
                    return false;
                }
            }
        }

        return true;
    }
}


