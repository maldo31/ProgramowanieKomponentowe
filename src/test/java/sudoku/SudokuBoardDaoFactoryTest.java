package sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SudokuBoardDaoFactoryTest {
    private SudokuBoardDaoFactory factory = new StreamSudokuBoardFactory();

    @Test
    public void getFileDaoTest(){
        assertNotNull(factory.getFileDao("test"));
    }

}