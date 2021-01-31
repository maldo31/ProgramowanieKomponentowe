package sudoku.model;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.model.exception.SudokuFileException;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DBSudokuBoardDaoTest {
    Date date= new Date();
    private static final Logger logger = LoggerFactory.getLogger(SudokuBoard.class);
    private SudokuBoardDaoFactory factory = new DBSudokuBoardFactory();
    private SudokuBoard sudokuBoard = new SudokuBoard();

    private Dao<SudokuBoard> sudokuBoardDao;
    private SudokuBoard testSudokuBoard = new SudokuBoard();

    @Test
    public void writeReadTest() {
        String timestamp = String.valueOf(date.getTime());
        sudokuBoard.solveGame();
        sudokuBoardDao = factory.getFileDao("test2");
        try {
            sudokuBoardDao.write(sudokuBoard);
            System.out.println("Utworzono tablice");
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