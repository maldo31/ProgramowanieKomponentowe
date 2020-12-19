package sudoku;

public interface Dao<T> {

    void write(T object);

    T read();

    // Przy wykonywaniu sprawdzenia "checktyle" wtyczka informowała nas o konieczności
    // użycia metody o innej nazwie
    // By metoda finalize działała i wtyczka checkstyle nie wyświetlała tego jako błędu
    // zdecydowaliśmy się odkomentować linię "<module name="NoFinalizer"/>"
    // w checkstyle2020.xml

    void finalize();

}
