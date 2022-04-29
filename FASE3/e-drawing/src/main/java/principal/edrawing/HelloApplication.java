package principal.edrawing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import principal.arbolb.BTree;

import java.io.IOException;

public class HelloApplication extends Application {
    public static BTree arbol = new BTree();

    public static boolean preCargar = false;

    public static Stage stageLogin = null;


    @Override
    public void start(Stage stage) throws IOException {
        stageLogin = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("[EDD]Fase3!");
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getClassLoader().getResource("logo.png"))));
        stage.show();

        if(!preCargar){
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("cliente.fxml"));
            Parent root1 = loader1.load();
            Cliente cliente1 = loader1.getController();
            Scene scene1 = new Scene(root1);
            Stage stage1 = new Stage();
            stage1.setScene(scene1);

            stage1.getIcons().add(new Image(String.valueOf(getClass().getClassLoader().getResource("logo.png"))));
            stage1.setTitle("Administrador - Masiva");
            cliente1.displayName("admin");
            cliente1.getStage(stage);
            stage1.setOnCloseRequest(e -> {
                try {
                    cliente1.CerrarSesion();
                    System.out.println("Se cerro");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            });
            //----------------------------------------------------------------------------------------------------------
            arbol.insertarB(0,"Administrador","admin","adm@adm.com","123","11223344","adm","ins",stage1);
            preCargar = true;
        }
    }


    public static void main(String[] args) {
        launch();
    }
}