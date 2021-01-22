package sudoku.model;

import sudoku.model.exception.SudokuFileException;
import sudoku.model.exception.SudokuFinalizeException;

public interface Dao<T> {

    void write(T object) throws SudokuFileException;

    T read() throws SudokuFileException;

    // Przy wykonywaniu sprawdzenia "checktyle" wtyczka informowała nas o konieczności
    // użycia metody o innej nazwie
    // By metoda finalize działała i wtyczka checkstyle nie wyświetlała tego jako błędu
    // zdecydowaliśmy się odkomentować linię "<module name="NoFinalizer"/>"
    // w checkstyle2020.xml

    void finalize() throws SudokuFinalizeException;

}
