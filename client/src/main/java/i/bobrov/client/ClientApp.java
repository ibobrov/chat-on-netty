package i.bobrov.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/client.fxml"));
        stage.setTitle("Chat on Netty");
        stage.setScene(new Scene(fxmlLoader.load(), 400, 400));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}