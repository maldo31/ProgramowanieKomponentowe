package sudoku.view;

import java.util.ListResourceBundle;

public class Authors_es_ES extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[0][];
    }

    private Object[][] contents = {
            {"First author", new Author("Maciej", "Wlodarczyk")},
            {"Second author", new Author("Daniel", "Malicki") }
    };
}
