package sudoku.view;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import sudoku.model.*;
import sudoku.model.exception.SudokuFileException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoadedController implements Initializable {
    private Stage thisStage;

    @FXML

    private GridPane sudokuBoardGrid;
    private SudokuBoard sudokuBoard;

    private PopOutWindow popOutWindow = new PopOutWindow();
    private BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();

    ResourceBundle bundle = ResourceBundle.getBundle("bundles.languages");

    private static String language = App.getLanguage();


    public TextArea textArea;
    private FileChooser fileChooser;
    private File file;

    public LoadedController(SudokuBoard sudokuBoard) {

        thisStage = new Stage();
        this.sudokuBoard = sudokuBoard;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
            Locale.setDefault(new Locale(language));
            loader.setResources(bundle);
            loader.setController(this);


            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle(bundle.getString("application_title"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillGrid() {
        StringConverter<Number> converter = new NumberStringConverter();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                textField.setMinSize(50, 50);
                textField.setFont(Font.font(18));
                Bindings.bindBidirectional(textField.textProperty(),
                        sudokuBoard.getProperty(i,j),converter);
                if (sudokuBoard.get(i, j) != 0) {
                    textField.setDisable(true);
                    textField.setText(String.valueOf(sudokuBoard.get(i, j)));
                }
                sudokuBoardGrid.add(textField, i, j);

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillGrid();
        textArea.appendText(bundle.getString("game_check"));

    }

    public void showStage() {
        thisStage.showAndWait();
    }

    @FXML
    public void onActionButtonCheck(ActionEvent actionEvent) throws IOException {
        if (sudokuBoard.checkBoard() == true) {
            textArea.clear();
            textArea.appendText(bundle.getString("arrangement_true"));
        } else {
            textArea.clear();
            textArea.appendText(bundle.getString("arrangement_false"));
        }
    }

    @FXML
    public void onActionButtonSaveGame(ActionEvent actionEvent)  {
        fileChooser = new FileChooser();
        file = fileChooser.showSaveDialog(thisStage);
        SudokuBoardDaoFactory factory = new StreamSudokuBoardFactory();
        Dao<SudokuBoard> sudokuBoardDaoFile;
        sudokuBoardDaoFile = factory.getFileDao(file.getAbsolutePath());
        try {
            sudokuBoardDaoFile.write(sudokuBoard);
        } catch (SudokuFileException e) {
            e.printStackTrace();
        }


    }
}