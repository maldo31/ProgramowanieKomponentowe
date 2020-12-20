package sudoku.model;

import org.junit.jupiter.api.Test;
import sudoku.model.StreamSudokuBoardFactory;
import sudoku.model.SudokuBoardDaoFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class SudokuBoardDaoFactoryTest {
    private SudokuBoardDaoFactory factory = StreamSudokuBoardFactory.getSudokuBoardDaoFactory("stream");
    private SudokuBoardDaoFactory factoryNull = StreamSudokuBoardFactory.getSudokuBoardDaoFactory("strea");
    @Test
    public void CreateFactoryTest(){
        assertNotNull(factory.getFileDao("test"));
    }
    @Test
    public void NullFactoryTest(){
        assertNull(factoryNull);
    }

}