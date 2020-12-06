package sudoku;

public interface Dao<T> {

    void write(T object);

    T read();

    void finalize();

}
