package sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SudokuFieldTest {


    @BeforeEach
    void setUp() {
        SudokuField field = new SudokuField(1);
    }

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

    @Test
    void testToString() {
        SudokuField field = new SudokuField(5);
        field.setFieldValue(5);
        System.out.println(field.toString());
    }

    @Test
    void testEquals() {
        SudokuField field = new SudokuField(5);
        SudokuField field1 = field;
        assertTrue(field.equals(field1));
    }
    @Test
    void testEqualsFalse() {
        SudokuField field = new SudokuField(5);
        SudokuField field1 = new SudokuField(6);
        assertFalse(field.equals(field1));
    }
    @Test
    void testEqualsSame() {
        SudokuField field = new SudokuField(5);
        assertTrue(field.equals(field));
    }
    @Test
    void testEqualsDiffrentClass() {
        SudokuField field = new SudokuField(5);
        assertFalse(field.equals(1));
    }
    @Test
    void testEqualsNull() {
        SudokuField field = new SudokuField(5);
        assertFalse(field.equals(null));
    }
    @Test
    void testListeners() {

        SudokuField field = new SudokuField(5);
        SudokuBoard listener = new SudokuBoard();
        field.addPropertyChangeListener(listener);
        field.removePropertyChangeListener(listener);

    }


}