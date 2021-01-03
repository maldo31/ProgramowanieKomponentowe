package sudoku.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import sudoku.model.SudokuBoard;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static String language = "pl_PL";
//    ComboBox<String> languageComboBox;

    @Override
    public void start(Stage stage) throws IOException {

//        languageComboBox = new ComboBox<>();
//        languageComboBox.getItems().addAll(
//            "Polish",
//            "English",
//            "Spanish"
//        );

        Locale.setDefault(new Locale(language));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.languages");


        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setTitle(bundle.getString("application_title"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        Locale.setDefault(new Locale(language));
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.languages");
        fxmlLoader.setResources(bundle);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}