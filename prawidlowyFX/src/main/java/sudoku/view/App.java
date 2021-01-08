package sudoku.view;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static String language = "pl_PL";

    public static String getLanguage() {
        return language;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Locale.setDefault(new Locale(language));

        ResourceBundle bundle = ResourceBundle.getBundle("bundles.languages");
        scene = new Scene(load_fxml("primary"), 640, 480);
        stage.setTitle(bundle.getString("application_board_title"));
        stage.setScene(scene);
        stage.show();
    }

    private void chooseLanguage(){

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(load_fxml(fxml));
    }

    private static Parent load_fxml(String fxml) throws IOException {
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