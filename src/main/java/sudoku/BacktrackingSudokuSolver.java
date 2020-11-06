package sudoku;
import java.util.*;

public class BacktrackingSudokuSolver implements SudokuSolver {
    private static final int UNASSIGNED = 0;
    public boolean solve(SudokuBoard board) {
        Random random = new Random();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board.get(row, col) == UNASSIGNED) {
                    int value =1 + random.nextInt(9);
                    for (int number = 1; number <= 9; number++) {

                        if (board.checkCell(row, col, value)) {
                            board.set(row, col, value);
                            if (solve(board)) {
                                return true;
                            } else {
                                board.set(row, col, UNASSIGNED);
                            }
                        }

                        value++;
                        if (value>9){
                            value=1;
                        }
                    }
                    return false;
                }
            }

        }
        board.showBoard();
        return true;
    }
}
        /*
            int size = board.size;
            int[] randomDigits = new int[size * size];

            Random random = new Random();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    boolean valid = false;
                    if (randomDigits[i * size + j] == 0) {
                        randomDigits[i * size + j] = 1 + random.nextInt(size);
                        board.set(i,j,randomDigits[i * size + j]);
                        do {
                            if (board.checkCell(i, j) == true) {
                                valid = true;
                                break;
                            }
                            board.set(i,j,board.get(i,j) % size + 1);
                        } while (randomDigits[i * size + j] != board.get(i,j));
                    } else {
                        board.set(i,j,board.get(i,j) % size + 1);
                        while (board.get(i,j) != randomDigits[i * size + j]) {
                            if (board.checkCell(i, j) == true) {
                                valid = true;
                                break;
                            }
                            board.set(i,j,board.get(i,j) % size + 1);
                        }
                    }
                    if (!valid) {
                        board.set(i,j,0);
                        randomDigits[i * size + j] = 0;
                        if (j > 0) {
                            j = j - 2;
                        } else if (j == 0) {
                            i = i - 1;
                            j = 7;
                        }
                    }
                }
            }
        }   */


