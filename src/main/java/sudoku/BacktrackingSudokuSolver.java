package sudoku;

import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver {
    private static final int UNASSIGNED = 0;
    /*Algorytm backtracking zaczerpniety ze strony:
     https://www.geeksforgeeks.org/sudoku-backtracking-7/
    (jednakże jest to jego powszechna wersja,
     i wiele innych źródeł implementuje algorytm w ten sam sposób),
    został odpowiednio zmodyfikowany do zaleceń z zadania.
     */

    public boolean solve(SudokuBoard board){
        Random random = new Random();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board.get(row, col) == UNASSIGNED) {
                    int value = 1 + random.nextInt(9);
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
/*
    public boolean solve(SudokuBoard board) {

        Random random = new Random();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                board.set(row, col, 0);
            }
        }
        int value = 1 + random.nextInt(9);
        board.set(0, 0, value);
        solve(board,1);
        return true;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board.get(row, col) == UNASSIGNED) {
                    int value = 1 + random.nextInt(9);
                    for (int number = 1; number <= 9; number++) {

                        if (board.checkCell(row, col, value)) {
                            board.set(row, col, value);
                            solve(board,1);

                            }
                        }
                    }
                }
            }
        */

        }



