package sudoku.view;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import exception.SceneLoadException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * JavaFX App
 */
public class App extends Application {
    private static Stage stage;
    private static Scene scene;
    private static String language = "en_US";
    private static Locale locale = new Locale("en","US");



    public static String getLanguage() {
        return language;
    }

    public static void setLocale(Locale locale) {
        App.locale = locale;
    }

    public static Locale getLocale() {
        return App.locale;
    }

    public static void setLanguage(String language) throws SceneLoadException {
        App.language = language;
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.languages");
        try {
            scene = new Scene(load_fxml("primary"), 640, 480);
        } catch (IOException e) {
            throw new SceneLoadException(bundle.getString("exception_scene_load"),e);
            //e.printStackTrace();
        }

        stage.setTitle(bundle.getString("application_board_title"));
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Locale.setDefault(new Locale(language));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.languages");
        scene = new Scene(load_fxml("primary"), 640, 480);
        stage.setTitle(bundle.getString("application_board_title"));
        stage.setScene(scene);
        this.stage = stage;
        stage.show();
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