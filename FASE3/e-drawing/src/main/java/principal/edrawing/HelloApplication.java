package principal.edrawing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import principal.arbolb.BTree;

import java.io.IOException;

public class HelloApplication extends Application {
    public static BTree arbol = new BTree();

    public static Stage stg;
    public static Stage StageRegiste;
    public static Stage StageCliente;
    public static Scene scene;

    public static boolean cambioRegister = false,preCargar = false;

    @Override
    public void start(Stage stage) throws IOException {
        if(!preCargar){
            arbol.insertarB(0,"Administrador","admin","adm@adm.com","123","11223344","adm","ins");
            arbol.insertarB(1,"Cliente","cliente","clie@clie.com","123","44332211","clie","ente");
            preCargar = true;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("[EDD]Fase3!");
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getClassLoader().getResource("logo.png"))));
        stage.show();
        stg = stage;
    }

    public void changeRegister() throws IOException {
        if(!cambioRegister){
            stg.hide();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registro.fxml"));
            scene = new Scene(fxmlLoader.load(), 660, 540);
            StageRegiste = new Stage();
            StageRegiste.setTitle("Registro");
            StageRegiste.setScene(scene);
            StageRegiste.getIcons().add(new Image(String.valueOf(getClass().getClassLoader().getResource("logo.png"))));
            StageRegiste.show();
            cambioRegister = true;
        } else {
            stg.hide();
            StageRegiste.show();
        }
    }

    public void changeStart() throws IOException {
        if(StageRegiste !=null){
            StageRegiste.hide();
            cambioRegister = false;
        }

        start(stg);
    }

    public static void main(String[] args) {
        launch();
    }
}