package sudoku.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BacktrackingSudokuSolver implements SudokuSolver, Serializable {
    private static final int UNASSIGNED = 0;
    /*Algorytm backtracking zaczerpniety ze strony:
     https://www.geeksforgeeks.org/sudoku-backtracking-7/
    (jednakże jest to jego powszechna wersja,
     i wiele innych źródeł implementuje algorytm w ten sam sposób),
    został odpowiednio zmodyfikowany do zaleceń z zadania.
     */

    public boolean solve(SudokuBoard board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board.get(row, col) == UNASSIGNED) {
                    int value = randomValues().get(0);
                    for (int number = 0; number < 9; number++) {

                        if (board.checkCell(row, col, value)) {
                            board.set(row, col, value);
                            if (solve(board)) {
                                return true;
                            } else {
                                board.set(row, col, UNASSIGNED);
                            }
                        }

                        value++;
                        if (value > 9) {
                            value = 1;
                        }
                    }
                    return false;
                }
            }

        }


        return true;
    }

    private List<Integer> randomValues() {
        List<Integer> numbers = new ArrayList<>();
        for (int number = 1; number < 10; number++) {
            numbers.add(number);
        }
        //użyto implementacji metody shuffle https://docs.oracle.com/javase/7/docs/api/java/util/Collections.html
        Collections.shuffle(numbers);
        return numbers;
    }

        }



