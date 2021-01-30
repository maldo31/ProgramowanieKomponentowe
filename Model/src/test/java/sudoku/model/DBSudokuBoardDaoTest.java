package sudoku.model;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.model.exception.SudokuFileException;
import sudoku.model.exception.SudokuIOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DBSudokuBoardDaoTest {
    private static final Logger logger = LoggerFactory.getLogger(SudokuBoard.class);
    private SudokuBoardDaoFactory factory = new DBSudokuBoardFactory();
    private SudokuBoard sudokuBoard = new SudokuBoard();

    private Dao<SudokuBoard> sudokuBoardDao;
    private SudokuBoard testSudokuBoard = new SudokuBoard();

    @Test
    public void writeReadTest() throws SudokuIOException {
        sudokuBoard.solveGame();
        sudokuBoardDao = factory.getFileDao("test");
        try {
            sudokuBoardDao.write(sudokuBoard);
        } catch (SudokuFileException e) {
            e.printStackTrace();
        }

        try {
            testSudokuBoard = sudokuBoardDao.read();
        } catch (SudokuFileException e) {
            e.printStackTrace();
        }
        System.out.println(sudokuBoard.toString());
        System.out.println(testSudokuBoard.toString());


        assertTrue(sudokuBoard.equals(testSudokuBoard));

    }



}