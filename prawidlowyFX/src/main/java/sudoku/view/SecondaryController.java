package sudoku.view;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.model.*;
import sudoku.model.exception.SudokuIOException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ResourceBundle;


public class SecondaryController implements Initializable {
    private Stage thisStage;
    private static final Logger logger = LoggerFactory.getLogger(LoadedController.class);
    @FXML
    private GridPane sudokuBoardGrid;

    private PopOutWindow popOutWindow = new PopOutWindow();
    private SudokuBoard sudokuBoard = new SudokuBoard();
    private BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();

    private ResourceBundle bundle = ResourceBundle.getBundle("bundles.languages");

    public TextArea textArea;
    private FileChooser fileChooser;
    private File file;
    private ChoosedLevel choosedLevel;

    public SecondaryController() {

        thisStage = new Stage();

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
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
        StringConverter<Number> converter = new NumberStringConverter() {
            //Nadpisanie konwertera w celu kontroli wprowadzanych wartości
            @Override
            public Number fromString(String var1) {
                try {
                    //Kontrola czy wprowadzona wartość jest w zakresie 1-9
                    if (Integer.parseInt(var1) > 9 || Integer.parseInt(var1) < 1) {
                        popOutWindow.messageBox(bundle.getString("wrong_value"),
                                (bundle.getString("wrong_value_info")),
                                Alert.AlertType.ERROR);
                        var1 = "0";
                    }
                    if (var1 == null) {
                        return null;
                    } else {
                        var1 = var1.trim();
                        if (var1.length() < 1) {
                            return null;
                        } else {
                            NumberFormat var2 = this.getNumberFormat();
                            return var2.parse(var1);
                        }
                    }
                } catch (ParseException | NumberFormatException var3) {
                    popOutWindow.messageBox(bundle.getString("wrong_type"),
                            (bundle.getString("wrong_type_info")),
                            Alert.AlertType.ERROR);
                    throw new RuntimeException(var3);
                }
            }
        };
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
            switch (PrimaryController.getLevel()) {
                case "Easy": case "Łatwy": case "Fácil":  {
                    choosedLevel = ChoosedLevel.EASY;
                    break;
                }
                case "Medium": case "Średni": case "Medio": {
                    choosedLevel = ChoosedLevel.MEDIUM;
                    break;
                }
                case "Hard": case "Trudny": case "Difícil": {
                    choosedLevel = ChoosedLevel.HARD;
                    break;
                }
                default:
                    break;
            }
            choosedLevel.chooseLevel(sudokuBoard);
        } catch (EmptyBoardException e) {
            e.printStackTrace();
        }
            fillGrid();

        textArea.appendText(bundle.getString("arrangement_false"));

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

        try {
            fileChooser = new FileChooser();
            file = fileChooser.showSaveDialog(thisStage);
            SudokuBoardDaoFactory factory = new StreamSudokuBoardFactory();
            Dao<SudokuBoard> sudokuBoardDaoFile;
            sudokuBoardDaoFile = factory.getFileDao(file.getAbsolutePath());
            sudokuBoardDaoFile.write(sudokuBoard);
        } catch (SudokuIOException e) {
            logger.warn("Error saving file");
        }

    }
}