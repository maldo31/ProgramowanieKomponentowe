/**
 *
 */
module sudoku.model {
    requires org.apache.commons.lang3;
    requires java.desktop;
    requires javafx.base;
    requires org.slf4j;
    requires hibernate.entitymanager;
    requires org.hibernate.commons.annotations;
    requires java.persistence;
    requires java.sql;


    opens sudoku.model;
    exports sudoku.model;
    exports sudoku.model.exception;
}