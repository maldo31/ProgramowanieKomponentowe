package sudoku.view;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

public class PrimaryController {

    private static String level;
    @FXML
    private ComboBox comboBoxSystemDifficult;
    private PopOutWindow popOutWindow = new PopOutWindow();

    @FXML
    private void switchToBoard() throws IOException {
        App.setRoot("board");
    }
    @FXML
    public void onActionButtonStartGame(ActionEvent actionEvent) throws IOException {
        if (!(level == null)) {
            App.setRoot("board");
        }
    }
    @FXML
    public void onActionButtonConfirmLevel(ActionEvent actionEvent) {
        try {
            this.level = comboBoxSystemDifficult.getSelectionModel().getSelectedItem().toString();
        } catch (NullPointerException e) {
            popOutWindow.messageBox("Warning",
                    "Level of difficulty has not been chosen", Alert.AlertType.WARNING);
        }
    }
}
