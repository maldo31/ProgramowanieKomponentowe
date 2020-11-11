package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldTest {

    @Test
    void setFieldValue() {
       SudokuField field = new SudokuField(11);
       int value;
       value=field.getFieldValue();
       field.setFieldValue(12);
       assertTrue(value==field.getFieldValue());
    }
    @Test
    void setFieldValueNegative() {
        SudokuField field = new SudokuField(11);
        int value;
        value = field.getFieldValue();
        field.setFieldValue(-100);
        assertTrue(value == field.getFieldValue());
    }

}