package sudoku.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sudoku.model.exception.WrongFieldValueException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SudokuBoardTest {
    private SudokuBoard sudokuBoard;

    @BeforeEach
    void setUp() {
        sudokuBoard = new SudokuBoard();
    }

    //Test sprawdzający czy wypełniona tablica jest zgodna z zasadami Sudoku
    @Test
    public void checkFillBoardTest() {
        sudokuBoard.solveGame();

        List<List<SudokuField>> testBoard = null;
        try {
            testBoard = sudokuBoard.getCopyOfBoard();
        } catch (WrongFieldValueException e) {
            e.printStackTrace();
        }


        boolean flaga = true;

        //Sprawdz wiersze
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int i2 = i + 1; i2 < 9; i2++) {
                    if (testBoard.get(i).get(j).getFieldValue() == testBoard.get(i2).get(j).getFieldValue()) {
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
                    if (testBoard.get(i).get(j).getFieldValue() == testBoard.get(i).get(j2).getFieldValue()) {
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
                active = testBoard.get(row).get(column).getFieldValue();
                int subrow = row - row % 3;
                int subcol = column - column % 3;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (active == testBoard.get(subrow + i).get(subcol + j).getFieldValue()) {
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
            sudokuBoard.toString();
        }
        if (!flaga) {
            sudokuBoard.toString();
        }
    }

    //Test sprawdzający czy inny układ liczb na planszy po każdym uruchomieniu fillBoard jest inny
    @Test
    public void repeatFillBoardTest() throws WrongFieldValueException {
        List<List<SudokuField>> testBoard1;
        List<List<SudokuField>> testBoard2;

        sudokuBoard.solveGame();
        testBoard1 = sudokuBoard.getCopyOfBoard();
        sudokuBoard.toString();
        sudokuBoard.solveGame();
        testBoard2 = sudokuBoard.getCopyOfBoard();
        sudokuBoard.toString();
        boolean flag = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (testBoard1.get(i).get(j) != testBoard2.get(i).get(j)) {
                    flag = true;
                }
            }
        }
        if (flag) {
            System.out.print("Tablice maja inny uklad liczb");

        }
        else{
            System.out.print("Tablice są identyczne");
        }
    }
    @Test
    void checkCellBox1() {
        sudokuBoard.solveGame();
        sudokuBoard.set(0,0,0);
        assertFalse(sudokuBoard.checkCell(2,2,0));
    }

    @Test
    void checkBoardTest() {
        sudokuBoard.solveGame();
        assertTrue(sudokuBoard.checkBoardTest());

    }
    @Test
    void checkBoardTestRowFalse() {
        sudokuBoard.solveGame();
        sudokuBoard.set(0,0,1);
        sudokuBoard.set(0,1,1);
        assertFalse(sudokuBoard.checkBoardTest());

    }
    @Test
    void checkBoardTestColumnFalse() {
        sudokuBoard.solveGame();
        int nonvalidvalue;
        nonvalidvalue=sudokuBoard.get(0,0);
        sudokuBoard.set(1,0,nonvalidvalue);
        sudokuBoard.set(2,0,nonvalidvalue);
        assertFalse(sudokuBoard.checkBoardTest());

    }
    @Test
    void checkBoardTestBoxFalse() {
        sudokuBoard.solveGame();
        int nonValidValue;
        nonValidValue=sudokuBoard.get(0,0);
        sudokuBoard.set(1,1,nonValidValue);
        assertFalse(sudokuBoard.checkBoardTest());

    }
    @Test
    void ShowBoardTest() {
        sudokuBoard.toString();
    }


    @Test
    void testToString() {
        sudokuBoard.solveGame();
        System.out.println(sudokuBoard.toString());
    }

    @Test
    void testEqualsDiffrentInstance() {
        SudokuBoard sudoku2 = new SudokuBoard();
        assertTrue(sudokuBoard.equals(sudoku2));
    }

    @Test
    void testEqualsWrongObject() {
        SudokuField field = new SudokuField();
        assertFalse(sudokuBoard.equals(field));
    }
    @Test
    void testEqualsSame() {
        assertTrue(sudokuBoard.equals(sudokuBoard));
    }
    @Test
    void testEqualsNull() {
        assertFalse(sudokuBoard.equals(null));
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuSolver.solve(sudokuBoard);
        SudokuBoard testSudokuBoard = (SudokuBoard) sudokuBoard.clone();

        assertTrue (sudokuBoard.equals(testSudokuBoard));
    }


}