package org.comp;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PrimaryController {

    private static String level;

    @FXML
    private void switchToBoard() throws IOException {
        App.setRoot("board");
    }
    @FXML
    public void onActionButtonStartGame(ActionEvent actionEvent) throws IOException {
        if (!level == null) {
            FxmlStageSetup.buildStage("boardWindow.fxml");
        } else {
            popOutWindow.messageBox("Warning",
                    "Check if language and level has not been chosen", Alert.AlertType.WARNING);
        }
    }
}
