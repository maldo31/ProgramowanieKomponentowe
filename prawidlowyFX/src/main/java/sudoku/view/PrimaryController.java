package sudoku.view;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sudoku.model.Dao;
import sudoku.model.StreamSudokuBoardFactory;
import sudoku.model.SudokuBoard;
import sudoku.model.SudokuBoardDaoFactory;

public class PrimaryController {

    private Stage thisStage;

    private static String level;
    @FXML
    private ComboBox comboBoxSystemDifficult;
    @FXML
    private ComboBox comboBoxLanguageSetting;
    private FileChooser fileChooser;
    private File file;
    private PopOutWindow popOutWindow = new PopOutWindow();
    private ResourceBundle bundle = ResourceBundle.getBundle("bundles.languages");
    private static String language = App.getLanguage();

    public static String getLevel() {
        return level;
    }

    private void changeLanguage(String lang) {
        switch (lang) {
            case "Polish": case "Polski": case "Polaco":
                App.setLanguage("pl_PL");
                break;
            case "English": case "Angielski": case "Inglés":
                App.setLanguage("en_EN");
                break;
            case "Spanish": case "Hiszpański": case "Español":
                App.setLanguage("es_ES");
                break;
            default:
                break;
        }
    }

    @FXML
    private void initialize() throws IOException {
        comboBoxLanguageSetting.getItems().addAll(
                bundle.getString("language_Polish"),
                bundle.getString("language_English"),
                bundle.getString("language_Spanish")
        );
        comboBoxSystemDifficult.getItems().addAll(
                bundle.getString("difficulty_easy"),
                bundle.getString("difficulty_medium"),
                bundle.getString("difficulty_hard")
        );
    }

    @FXML
    public void onActionButtonStartGame(ActionEvent actionEvent) throws IOException {
        try {
           this.level = comboBoxSystemDifficult.getSelectionModel().getSelectedItem().toString();
           SecondaryController secondaryController = new SecondaryController();
           secondaryController.showStage();
        } catch (NullPointerException e) {
          popOutWindow.messageBox(bundle.getString("error_title"),
                bundle.getString("error_level_choice"), Alert.AlertType.WARNING);
        }
    }

    @FXML
    public void onActionButtonChangeLanguage(ActionEvent actionEvent) {
        try {
            String lang =
                    comboBoxLanguageSetting.getSelectionModel().getSelectedItem().toString();
                    changeLanguage(lang);

        } catch (NullPointerException e) {
            e.printStackTrace();
            popOutWindow.messageBox(bundle.getString("error_title"),
                    bundle.getString("error_language_choice"), Alert.AlertType.WARNING);
        }
    }

    @FXML
    public void onActionButtonLoad(ActionEvent actionEvent) throws IOException {
        fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(thisStage);
        SudokuBoard sudokuBoard;
        SudokuBoardDaoFactory factory = new StreamSudokuBoardFactory();
        Dao<SudokuBoard> sudokuBoardDaoFile;
        sudokuBoardDaoFile = factory.getFileDao(file.getAbsolutePath());
        sudokuBoard = sudokuBoardDaoFile.read();
        Locale.setDefault(new Locale(language));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.languages");
        LoadedController loaderController = new LoadedController(sudokuBoard);
        loaderController.showStage();
    }
}
