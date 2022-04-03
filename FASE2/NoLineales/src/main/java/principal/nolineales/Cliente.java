package principal.nolineales;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import principal.ArbolBB.ABB;
import principal.ArbolBB.Nodobb;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class Cliente {

    public Button btCapa;
    public ComboBox<String> cmbx;
    public Label lbname;
    public ImageView imgcapa;
    public ProgressBar progressb;
    public static Thread th;
    public Label aviso;

    HelloApplication m = new HelloApplication();
    ABB arbol = new ABB();
    Cliente_Img imgs = new Cliente_Img();
    reporte repor = new reporte();

    public void toLogin() throws IOException {
        m.changeStart();
    }

    public void RutaArchivo() throws IOException {
        cmbx.getItems().clear();
        String ruta = "";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cargar el archivo");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON", "*.json"),
                new FileChooser.ExtensionFilter("ALL FILES", "*.*")
        );

        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            ruta = file.getPath();
            Analyzer(ruta);
        } else {
            System.out.println("Error, seleccione json");
        }
    }
    public static String contcombo = "";
    public void Analyzer(String path) {
        int fila;
        int columna;
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(path));
            JSONArray jsonarra = (JSONArray) obj;

            for (int i = 0; i < jsonarra.size(); i++) {
                JSONObject jsonobj1 = (JSONObject) jsonarra.get(i);
                contcombo = jsonobj1.get("id_capa").toString();
                cmbx.getItems().addAll(contcombo);
                arbol.insertarbb(Integer.parseInt(contcombo));//insertando capas en el abb

                Nodobb conexion = arbol.IniciarBusquedabb(Integer.parseInt(contcombo));
                JSONArray pixeles = (JSONArray) jsonobj1.get("pixeles");
                for (int j = 0; j < pixeles.size(); j++) {
                    JSONObject jsonobj2 = (JSONObject) pixeles.get(j);
                    fila = Integer.parseInt(jsonobj2.get("fila").toString());
                    columna = Integer.parseInt(jsonobj2.get("columna").toString());
                    conexion.insertarNodo(columna, fila, jsonobj2.get("color").toString());
                }
            }

            imgs.heredar(arbol);
            repor.heredar(arbol);

            aviso.setText("Archivo Cargado!");
            aviso.setTextFill(Color.GREEN);
            btCapa.setDisable(false);
        } catch (Exception e) {
            System.out.println("Error en parsear json de capas" + e);
        }
    }

    public void MostrarCapa() {
        Nodobb conexion = arbol.IniciarBusquedabb(Integer.parseInt(cmbx.getValue())); // Busca la capa seleccionada en el combo en el arbol abb
        conexion.mayorcol();
        conexion.mayorfila();
        conexion.graficar(cmbx.getValue());//Grafica los pixeles que tiene la capa seleccionada
        //conexion.imprimir();

        th = new Thread() {
            public void run() {
                for(int i = 1 ; i <= 10 ; i++){
                    try {
                        if(i == 8){
                            progressb.setProgress(1);
                        }else if(i == 4){
                            progressb.setProgress(0.5);
                        }else if(i == 2){
                            progressb.setProgress(0.25);
                        }else if(i == 6){
                            progressb.setProgress(0.75);
                        }else if(i == 1) {
                            progressb.setProgress(0.12);
                        }

                        Thread.sleep(1000);
                    }catch (Exception e){
                        System.out.println("Error en el progress bar");
                    }
                }
                String dir = Paths.get("")
                        .toAbsolutePath()
                        .toString();
                System.out.println(dir);
                File f = new File(dir+"/"+"Capa"+cmbx.getValue()+".png");
                if (f.exists()) {
                    System.out.println("Exists: " + f.exists());
                    Image image1 = new Image("file:" +dir+"/"+"Capa"+cmbx.getValue()+".png");
                    imgcapa.setImage(image1);

                } else {
                    System.out.println("Does not Exists" + f.exists());
                }
            }
        };th.start();

    }

    public void toImages() throws IOException {
        m.changeImagen();
    }

    public void toAlbum(ActionEvent actionEvent) {
    }

    public void toReport() throws IOException {
        m.changeReporte();
    }
}
