package sudoku.view;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

public class PrimaryController {

    private static String level;
    @FXML
    private ComboBox comboBoxSystemDifficult;
    private PopOutWindow popOutWindow = new PopOutWindow();
    private static String language = App.getLanguage();

    public static String getLevel() {
        return level;
    }

    @FXML
    public void onActionButtonStartGame(ActionEvent actionEvent) throws IOException {
        Locale.setDefault(new Locale(language));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.languages");
        if (!(level == null)) {
           SecondaryController secondaryController = new SecondaryController();
           secondaryController.showStage();
        } else {
            popOutWindow.messageBox(bundle.getString("error_title"),
                    bundle.getString("error_level_choice"), Alert.AlertType.WARNING);
        }
    }

    @FXML
    public void onActionButtonConfirmLevel(ActionEvent actionEvent) {
        Locale.setDefault(new Locale(language));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.languages");
        try {
            this.level = comboBoxSystemDifficult.getSelectionModel().getSelectedItem().toString();
        } catch (NullPointerException e) {
            popOutWindow.messageBox(bundle.getString("error_title"),
                    bundle.getString("error_level_choice"), Alert.AlertType.WARNING);
        }
    }

    public void onActionButtonChangeLanguage(ActionEvent actionEvent) {
        Locale.setDefault(new Locale(language));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.languages");
        try {
            //

        } catch (NullPointerException e) {
            popOutWindow.messageBox(bundle.getString("error_title"),
                    bundle.getString("error_language_choice"), Alert.AlertType.WARNING);
        }
    }
}
