package sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BacktrackingSudokuSolverTest {
    private SudokuBoard sudokuBoard;
    private BacktrackingSudokuSolver solver;

    @BeforeEach
    void setUp() {
        sudokuBoard = new SudokuBoard();
        solver = new BacktrackingSudokuSolver();
    }


    @Test
    void solve() {

        solver.solve(sudokuBoard);

        SudokuField[][] testBoard = sudokuBoard.getCopyOfBoard();


            boolean flaga = true;

            //Sprawdz wiersze
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    for (int i2 = i + 1; i2 < 9; i2++) {
                        if (testBoard[i][j] == testBoard[i2][j]) {
                            System.out.print("Wykryto blad w komorce: [" + i + "] [" + j + "]\n");
                            flaga = false;
                        }
                    }
                }
            }

            //Sprawdz kolumny
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    for (int j2 = j + 1; j2 < 9; j2++) {
                        if (testBoard[i][j] == testBoard[i][j2]) {
                            System.out.print("Wykryto blad w komorce: [" + i + "] [" + j + "]\n");
                            flaga = false;
                        }
                    }
                }
            }

            //Sprawdz kwadrat 3x3
            int active;
            for (int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {
                    active = testBoard[row][column].getFieldValue();
                    int subrow = row - row % 3;
                    int subcol = column - column % 3;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (active == testBoard[subrow + i][subcol + j].getFieldValue()) {
                                if (subrow + i != row) {
                                    if (subcol + j == column) {
                                        System.out.print("Wykryto blad w kwadracie zaczynajacym sie w: [" + row + "] [" + column + "]");
                                        flaga = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (flaga) {
                System.out.print("Nie wykryto bledu");
            }
            if (!flaga) {
                sudokuBoard.showBoard();
            }
        }




}