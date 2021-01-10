package sudoku.view;

import java.util.ListResourceBundle;

public class Authors_en_EN extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[0][];
    }
    
    private Object[][] contents = {
            {"First author", new Author("Matthias", "Wlodarczyk")},
            {"Second author", new Author("Daniel", "Malicki") }
    };
}
