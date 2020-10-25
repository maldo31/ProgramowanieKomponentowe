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

        
    }

    //Test sprawdzający czy inny układ liczb na planszy po każdym uruchomieniu fillBoard jest inny
    @Test
    public void repeatFillBoardTest(){
        int[][] testBoard1 = new int[9][9];
        int[][] testBoard2 = new int[9][9];

        int pom=0;

        sudokuBoard.fillBoard();
        testBoard1 = sudokuBoard.getCopyOfBoard();
        sudokuBoard.fillBoard();
        testBoard2 = sudokuBoard.getCopyOfBoard();

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(testBoard1[i][j]==testBoard2[i][j]){
                    pom = 1;
                }
                System.out.print(pom);
            }
        }
        if (pom==1){
            System.out.print("Układ liczb nie jest inny");
        }
        else {
            System.out.print("Układ liczb jest inny");
        }
    }
}