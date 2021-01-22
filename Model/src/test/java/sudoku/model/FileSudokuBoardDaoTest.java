package sudoku.model;

import org.junit.jupiter.api.Test;
import sudoku.model.exception.SudokuIOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FileSudokuBoardDaoTest {

    private SudokuBoardDaoFactory factory = new StreamSudokuBoardFactory();
    private SudokuBoard sudokuBoard = new SudokuBoard();

    private Dao<SudokuBoard> sudokuBoardDaoFile;
    private SudokuBoard testSudokuBoard = new SudokuBoard();

    @Test
    public void writeReadTest() throws SudokuIOException {
        sudokuBoard.solveGame();
        sudokuBoardDaoFile = factory.getFileDao("test");
        sudokuBoardDaoFile.write(sudokuBoard);
        testSudokuBoard = sudokuBoardDaoFile.read();
        System.out.println(sudokuBoard.toString());
        System.out.println(testSudokuBoard.toString());


        assertTrue(sudokuBoard.equals(testSudokuBoard));

    }

    @Test
    public void finalizeTest(){
        sudokuBoardDaoFile = factory.getFileDao("test");
        sudokuBoardDaoFile.finalize();
    }

}