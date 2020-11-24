package sudoku;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SudokuFieldListener implements PropertyChangeListener, java.io.Serializable {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Praawidłowa zmiana pola="+evt.getPropertyName());

    }
}
