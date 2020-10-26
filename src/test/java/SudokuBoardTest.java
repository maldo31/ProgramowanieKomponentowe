import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {
    private SudokuBoard sudokuBoard;

    @BeforeEach
    void setUp() {
        sudokuBoard = new SudokuBoard();
    }

    //Test sprawdzający czy wypełniona tablica jest zgodna z zasadami Sudoku
    @Test
    public void checkFillBoardTest(){
        sudokuBoard.fillBoard();
        int[][] testBoard = sudokuBoard.getCopyOfBoard();

        //Sprawdz wiersze
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                for(int j2=0; j2<9; j2++){
                    if(testBoard[i][j] == testBoard[i][j2]){
                        fail("Error in row " + i);
                    }
                }
            }
        }

        //Sprawdz kolumny
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                for(int i2=0; i2<9; i2++){
                    if(testBoard[i][j] == testBoard[i2][j]){
                        fail("Error in column " + i);
                    }
                }
            }
        }

        //Sprawdz kwadrat 3x3
        int active;
        for(int row=0; row<3; row++){
            for(int column=0; column<3; column++){
                active = testBoard[row][column];
                int subrow = row - row%3;
                int subcol = column - column%3;
                for(int i=0; i<3; i++){
                    for(int j=0; j<3; j++){
                        if(active == testBoard[subrow + i][subcol + j]){
                            if (subrow + i != row){
                                if (subcol + j==column){
                                    fail("Error in 3x3 cell which starts at [" + row + "] [" + column + "]");
                                }
                            }
                        }
                    }
                }
            }
        }
   }

    //Test sprawdzający czy inny układ liczb na planszy po każdym uruchomieniu fillBoard jest inny
    @Test
    public void repeatFillBoardTest(){
        int[][] testBoard1 = new int[9][9];
        int[][] testBoard2 = new int[9][9];

        boolean same=true;

        sudokuBoard.fillBoard();
        testBoard1 = sudokuBoard.getCopyOfBoard();
        sudokuBoard.fillBoard();
        testBoard2 = sudokuBoard.getCopyOfBoard();

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(testBoard1[i][j]!=testBoard2[i][j]){
                    same=false;
                }
            }
        }
        assertTrue(!same);
    }
}