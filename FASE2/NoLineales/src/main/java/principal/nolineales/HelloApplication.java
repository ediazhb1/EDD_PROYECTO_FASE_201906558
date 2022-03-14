package principal.nolineales;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage stg;

    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("[EDD]Fase2!");
        stage.setScene(scene);
        stage.show();
    }

    public void changeStart() throws IOException {
        start(stg);
    }

    public void changeRegister() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registro.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 450);
        stg.setTitle("Registro");
        stg.setScene(scene);
        stg.show();
    }
    public void changeCliente() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cliente.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 560);
        stg.setTitle("Cliente");
        stg.setScene(scene);
        stg.show();
    }
    public static void main(String[] args) {
        launch();
    }
}