module sudoku.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires ModelProject;
    requires org.apache.commons.lang3;

    opens sudoku.view to javafx.fxml;
    exports sudoku.view;
}