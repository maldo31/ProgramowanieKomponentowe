package sudoku.view;

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

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class PrimaryController {

    private Stage thisStage;

    private static String level;
    @FXML
    private ComboBox comboBoxSystemDifficult;
    private FileChooser fileChooser;
    private File file;
    private PopOutWindow popOutWindow = new PopOutWindow();
    private static String language = App.getLanguage();

    public static String getLevel() {
        return level;
    }

    @FXML
    public void onActionButtonStartGame(ActionEvent actionEvent) throws IOException {
        Locale.setDefault(new Locale(language));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.languages");
        try {
            this.level = comboBoxSystemDifficult.getSelectionModel().getSelectedItem().toString();
           SecondaryController secondaryController = new SecondaryController();
           secondaryController.showStage();
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

    @FXML
    public void onActionButtonLoad(ActionEvent actionEvent) throws IOException {
        SudokuBoard sudokuBoard;
        fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(thisStage);
        SudokuBoardDaoFactory factory = new StreamSudokuBoardFactory();
        Dao<SudokuBoard> sudokuBoardDaoFile;
        sudokuBoardDaoFile = factory.getFileDao(file.getAbsolutePath());
        sudokuBoard=sudokuBoardDaoFile.read();
        Locale.setDefault(new Locale(language));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.languages");
        LoadedController loaderController = new LoadedController(sudokuBoard);
        loaderController.showStage();


    }
}
