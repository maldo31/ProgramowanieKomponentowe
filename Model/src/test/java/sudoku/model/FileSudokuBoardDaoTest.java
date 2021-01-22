package sudoku.model;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.model.exception.SudokuFileException;
import sudoku.model.exception.SudokuFinalizeException;
import sudoku.model.exception.SudokuIOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FileSudokuBoardDaoTest {
    private static final Logger logger = LoggerFactory.getLogger(SudokuBoard.class);
    private SudokuBoardDaoFactory factory = new StreamSudokuBoardFactory();
    private SudokuBoard sudokuBoard = new SudokuBoard();

    private Dao<SudokuBoard> sudokuBoardDaoFile;
    private SudokuBoard testSudokuBoard = new SudokuBoard();

    @Test
    public void writeReadTest() throws SudokuIOException {
        sudokuBoard.solveGame();
        sudokuBoardDaoFile = factory.getFileDao("test");
        try {
            sudokuBoardDaoFile.write(sudokuBoard);
        } catch (SudokuFileException e) {
            e.printStackTrace();
        }

        try {
            testSudokuBoard = sudokuBoardDaoFile.read();
        } catch (SudokuFileException e) {
            e.printStackTrace();
        }
        System.out.println(sudokuBoard.toString());
        System.out.println(testSudokuBoard.toString());


        assertTrue(sudokuBoard.equals(testSudokuBoard));

    }

    @Test
    public void finalizeTest(){
        sudokuBoardDaoFile = factory.getFileDao("test");
        try {
            sudokuBoardDaoFile.finalize();
        } catch (SudokuFinalizeException e) {
            e.printStackTrace();
        }
    }

}