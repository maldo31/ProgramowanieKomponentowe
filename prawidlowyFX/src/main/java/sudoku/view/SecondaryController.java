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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;


public class SecondaryController implements Initializable {
    private Stage thisStage;

    @FXML

    private GridPane sudokuBoardGrid;

    private PopOutWindow popOutWindow = new PopOutWindow();
    private SudokuBoard sudokuBoard = new SudokuBoard();
    private BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
    private Level difficultyLevel = new Level();


    private static String language;


    public TextArea textArea;
    private FileChooser fileChooser;
    private File file;

    public SecondaryController() {

        thisStage = new Stage();

        try {
            language=App.getLanguage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
            Locale.setDefault(new Locale(language));
            ResourceBundle bundle = ResourceBundle.getBundle("bundles.languages");
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
        solver.solve(sudokuBoard);
        try {
            difficultyLevel.chooseLevel(sudokuBoard, PrimaryController.getLevel());
        } catch (EmptyBoardException e) {
            e.printStackTrace();
        }
        fillGrid();
        textArea.appendText("Nieprawidłwy Układ");

    }

    public void showStage() {
        thisStage.showAndWait();
    }

    @FXML
    public void onActionButtonCheck(ActionEvent actionEvent) throws IOException {
        if (sudokuBoard.checkBoard() == true) {
            textArea.clear();
            textArea.appendText("Układ Prawidłowy");
        } else {
            textArea.clear();
            textArea.appendText("Układ Nieprawidłowy");
        }
    }

    @FXML
    public void onActionButtonSaveGame(ActionEvent actionEvent)  {
        fileChooser = new FileChooser();
        file = fileChooser.showSaveDialog(thisStage);
        SudokuBoardDaoFactory factory = new StreamSudokuBoardFactory();
        Dao<SudokuBoard> sudokuBoardDaoFile;
        sudokuBoardDaoFile = factory.getFileDao(file.getAbsolutePath());
        sudokuBoardDaoFile.write(sudokuBoard);

    }
}