package sudoku.view;

import java.util.ListResourceBundle;

public class Authors_pl_PL extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[0][];
    }

    private Object[][] contents = {
            {"First author", new Author("Maciej", "Włodarczyk")},
            {"Second author", new Author("Daniel", "Malicki") }
    };
}
