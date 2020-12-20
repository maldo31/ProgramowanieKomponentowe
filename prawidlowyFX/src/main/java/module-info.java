module sudoku.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires ModelProject;

    opens sudoku.view to javafx.fxml;
    exports sudoku.view;
}