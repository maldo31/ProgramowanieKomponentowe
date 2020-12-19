module org.comp {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.comp to javafx.fxml;
    exports org.comp;
}