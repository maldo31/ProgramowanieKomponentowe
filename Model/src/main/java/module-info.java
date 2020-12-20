module ModelProject {
    requires org.apache.commons.lang3;
    requires java.desktop;
    opens sudoku.model;
    exports sudoku.model;
}