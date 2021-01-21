module ModelProject {
    requires org.apache.commons.lang3;
    requires java.desktop;
    requires javafx.base;
    requires org.slf4j;
    opens sudoku.model;
    exports sudoku.model;
}