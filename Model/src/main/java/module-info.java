module ModelProject {
    requires org.apache.commons.lang3;
    requires java.desktop;
    requires javafx.base;

    opens sudoku.model;
    exports sudoku.model;
}