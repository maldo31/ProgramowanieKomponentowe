package sudoku;
import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver {
    public void solve(final SudokuBoard board) {
        {
            int[] randomDigits = new int[81];

            Random random = new Random();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    boolean valid = false;
                    if (randomDigits[i * 9 + j] == 0) {
                        randomDigits[i * 9 + j] = 1 + random.nextInt(9);
                        board.set(i,j,randomDigits[i * 9 + j]);

                        do {
                            if (board.checkCell(i, j) == true) {
                                valid = true;
                                break;
                            }

                            board.set(i,j,board.get(i,j) % 9 + 1);
                        } while (randomDigits[i * 9 + j] != board.get(i,j));
                    } else {
                        board.set(i,j,board.get(i,j) % 9 + 1);
                        while (board.get(i,j) != randomDigits[i * 9 + j]) {
                            if (board.checkCell(i, j) == true) {
                                valid = true;
                                break;
                            }
                            board.set(i,j,board.get(i,j) % 9 + 1);

                        }
                    }
                    if (!valid) {
                        board.set(i,j,0);
                        randomDigits[i * 9 + j] = 0;
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
