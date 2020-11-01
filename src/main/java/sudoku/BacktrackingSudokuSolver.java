package sudoku;

import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver {

    public void solve(SudokuBoard board) {
        {
            int size = 9;
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
        }
    }
}
