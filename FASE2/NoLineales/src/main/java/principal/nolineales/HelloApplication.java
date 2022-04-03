package principal.nolineales;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static Stage stg;
    public static Stage StageRegiste;
    public static Stage StageCliente;
    public static Stage StageImag;
    public static Stage StageReport;

    public static Scene scene;
    public static boolean cambioCapa = false;
    public static boolean cambioImag = false;
    public static boolean cambioReport= false;

    public static boolean cambioRegister = false;
    public static boolean cambioAdmin = false;

    @Override
    public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("[EDD]Fase2!");
            stage.setScene(scene);
            stage.getIcons().add(new Image(String.valueOf(getClass().getClassLoader().getResource("logo.png"))));
            stage.show();
            stg = stage;
    }


    public void changeStart() throws IOException {
        if(StageRegiste !=null){
            StageRegiste.hide();
            cambioRegister = false;
        }

        if(StageImag != null){
            StageImag.hide();
            cambioImag = false;
        }

        if(StageCliente != null){
            StageCliente.hide();
            cambioCapa = false;
        }

        if(StageReport != null) {
            StageReport.hide();
            cambioReport= false;
        }

        start(stg);
    }

    public void changeRegister() throws IOException {
        if (!cambioRegister) {
            stg.hide();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registro.fxml"));
            scene = new Scene(fxmlLoader.load(), 600, 450);
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

    public void changeCliente() throws IOException {
        if(!cambioCapa){
            stg.hide();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cliente.fxml"));
            scene = new Scene(fxmlLoader.load(), 935, 610);
            StageCliente = new Stage();
            StageCliente.setTitle("Cliente - Capas");
            StageCliente.setScene(scene);
            StageCliente.getIcons().add(new Image(String.valueOf(getClass().getClassLoader().getResource("logo.png"))));
            StageCliente.show();
            cambioCapa = true;
        }else{
            if(StageImag != null){
                StageImag.hide();
            }
            if(StageReport != null) {
                StageReport.hide();
            }

            StageCliente.show();
        }
    }


    public void changeAdmin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin.fxml"));
        scene = new Scene(fxmlLoader.load(), 900, 600);
        stg.setTitle("Administrador");
        stg.setScene(scene);
        stg.show();
    }

    public void changeImagen() throws IOException {
        if(!cambioImag){
            if(StageCliente != null) {
                StageCliente.hide();
            }
            if(StageReport != null) {
                StageReport.hide();
            }
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cliente_img.fxml"));
            scene = new Scene(fxmlLoader.load(), 935, 610);
            StageImag = new Stage();
            StageImag.setTitle("Cliente - Imagenes");
            StageImag.setScene(scene);
            StageImag.getIcons().add(new Image(String.valueOf(getClass().getClassLoader().getResource("logo.png"))));
            StageImag.show();
            cambioImag = true;
        }else{
            if(StageCliente != null) {
                StageCliente.hide();
            }
            if(StageReport != null) {
                StageReport.hide();
            }
            StageImag.show();
        }
    }

    public void changeReporte() throws IOException {
        if(!cambioReport){
            StageCliente.hide();
            if(StageImag != null){
                StageImag.hide();
            }
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cliente_report.fxml"));
            scene = new Scene(fxmlLoader.load(), 990, 750);
            StageReport = new Stage();
            StageReport.setTitle("Cliente - Reportes");
            StageReport.setScene(scene);
            StageReport.getIcons().add(new Image(String.valueOf(getClass().getClassLoader().getResource("logo.png"))));

            StageReport.show();
            cambioReport = true;
        }else{
            if(StageImag != null){
                StageImag.hide();
            }

            if(StageCliente != null){
                StageCliente.hide();
            }

            StageReport.show();
        }
    }


    public static void main(String[] args) {
        launch();
    }
}