package xruisu.project.equipmentforge;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/Forge"));

        stage.getIcons().addAll(
                new Image(getClass().getResource("/images/anvilhammer256.png").toExternalForm()),
                new Image(getClass().getResource("/images/anvilhammer32.png").toExternalForm()),
                new Image(getClass().getResource("/images/anvilhammer48.png").toExternalForm()),
                new Image(getClass().getResource("/images/anvilhammer16.png").toExternalForm()));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}