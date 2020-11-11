package sudoku;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


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
        SudokuField[][] testBoard = sudokuBoard.getCopyOfBoard();

        boolean flaga = true;

        //Sprawdz wiersze
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int i2 = i + 1; i2 < 9; i2++) {
                    if (testBoard[i][j].getFieldValue() == testBoard[i2][j].getFieldValue()) {
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
                    if (testBoard[i][j].getFieldValue() == testBoard[i][j2].getFieldValue()) {
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

    //Test sprawdzający czy inny układ liczb na planszy po każdym uruchomieniu fillBoard jest inny
    @Test
    public void repeatFillBoardTest() {
        SudokuField[][] testBoard1;
        SudokuField[][] testBoard2;

        sudokuBoard.solveGame();
        testBoard1 = sudokuBoard.getCopyOfBoard();
        sudokuBoard.solveGame();
        testBoard2 = sudokuBoard.getCopyOfBoard();
        boolean flag = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (testBoard1[i][j] != testBoard2[i][j]) {
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
    void checkBoardTestCoulumnFalse() {
        sudokuBoard.solveGame();
        int nonvalidvalue;
        nonvalidvalue=sudokuBoard.get(0,0);
        sudokuBoard.set(1,0,nonvalidvalue);
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
        sudokuBoard.showBoard();
    }




}