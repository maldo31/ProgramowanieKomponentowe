package org.comp;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToBoard() throws IOException {
        App.setRoot("board");
    }
}
