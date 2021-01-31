package sudoku.model;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.model.exception.SudokuFileException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DBSudokuBoardDaoTest {
    long timestamp = System.currentTimeMillis() / 1000;
    String tmp = timestamp+"";
    private static final Logger logger = LoggerFactory.getLogger(SudokuBoard.class);
    private SudokuBoardDaoFactory factory = new DBSudokuBoardFactory();
    private SudokuBoard sudokuBoard = new SudokuBoard();

    private Dao<SudokuBoard> sudokuBoardDao;
    private SudokuBoard testSudokuBoard = new SudokuBoard();

    @Test
    public void writeReadTest() {

        sudokuBoard.solveGame();
        sudokuBoardDao = factory.getFileDao("tab" + tmp);
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