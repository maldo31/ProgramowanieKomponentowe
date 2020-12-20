package sudoku.model;

import org.junit.jupiter.api.Test;
import sudoku.model.Dao;
import sudoku.model.StreamSudokuBoardFactory;
import sudoku.model.SudokuBoard;
import sudoku.model.SudokuBoardDaoFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FileSudokuBoardDaoTest {

    private SudokuBoardDaoFactory factory = new StreamSudokuBoardFactory();
    private SudokuBoard sudokuBoard = new SudokuBoard();

    private Dao<SudokuBoard> sudokuBoardDaoFile;
    private SudokuBoard testSudokuBoard = new SudokuBoard();

    @Test
    public void writeReadTest(){
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