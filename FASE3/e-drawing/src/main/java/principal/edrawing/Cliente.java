package principal.edrawing;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import principal.arbolb.BNode;
import principal.tablahash.TablaHash;

import java.io.*;
import java.nio.file.Paths;

public class Cliente {
    @FXML
    public Label lbname;
    @FXML
    public Label avisoLugares;
    @FXML
    public Label avisoRuta;
    @FXML
    public Label avisoMensaje;
    @FXML
    public ImageView adya;
    @FXML
    public static Thread th;
    @FXML
    public ImageView nodiri;
    @FXML
    public Button btcerrar;

    ListaAdyacente miLista;
    String ruta = "",usuario ="";
    HelloApplication m = new HelloApplication();
    public static TablaHash TH = new TablaHash();
    Stage stageLogin = null;
    public static JSONArray jsonarra;

    public void getStage(Stage stage){
        stageLogin = stage;
    }

    public void CerrarSesion() throws IOException {
        stageLogin.show();
        BNode validacion = m.arbol.BusquedaUsuario(usuario);
        validacion.perfil.hide();
        validacion.perfil.close();

    }

    public void displayName(String text) {
        lbname.setText(text);
        usuario = text;
    }

    public void Entregas() throws IOException {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("entrega.fxml"));
        Parent root1 = loader1.load();
        Entregas cliente1 = loader1.getController();
        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();
        Stage myStage = (Stage) btcerrar.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.getIcons().add(new Image(String.valueOf(getClass().getClassLoader().getResource("logo.png"))));
        stage1.setTitle("Entrega");
        stage1.show();
        cliente1.displayName(usuario);
        cliente1.getStage(stageLogin,myStage);
        stage1.setOnCloseRequest(e -> {
            try {
                cliente1.CerrarSesion();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        myStage.hide();
        myStage.close();

    }

    public void RutaArchivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cargar el archivo");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON", "*.json"),
                new FileChooser.ExtensionFilter("ALL FILES", "*.*")
        );

        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            ruta = file.getPath();
        } else {
            System.out.println("Error, seleccione json");
        }
    }



    public void GenerarGrafo(String data,String tipo){
        FileWriter fichero = null;
        PrintWriter pw = null;
        /*Crea un archivo con extesión .dot con el texto de la variable graph*/
        try{
            fichero = new FileWriter("Grafo.dot");
            pw = new PrintWriter(fichero);
            pw.write(data);
            pw.close();
            fichero.close();
        }catch(Exception e){
            System.out.println("Error en generar dot del grafo");
        }finally {
            if(pw!=null){
                pw.close();
            }
        }
        try{
            ProcessBuilder proceso;
            String dir = Paths.get("")
                    .toAbsolutePath()
                    .toString();
            proceso = new ProcessBuilder("dot", "-Tpng", "-o",dir+"/"+"Grafo "+tipo+".png","Grafo.dot");
            proceso.redirectErrorStream(true);
            proceso.start();
        }catch (Exception e){
            System.out.println("Error en generar png de la Estructura");
        }
    }

    public void ColocarImg(String tipo, ImageView image1){
        th = new Thread() {
            public void run() {
                for (int i = 1; i <= 3; i++) {
                    try {

                        Thread.sleep(1000);
                    } catch (Exception e) {
                        System.out.println("Error en el progress bar");
                    }
                }

        String dir = Paths.get("")
                .toAbsolutePath()
                .toString();
        File f = new File(dir+"/"+"Grafo "+tipo+".png");
        if (f.exists()) {
            System.out.println("Exists: " + f.exists());
            Image image = new Image("file:" +dir+"/"+"Grafo "+tipo+".png");
            image1.setImage(image);

        } else {
            System.out.println("Does not Exists" + f.exists());
        }}
        };th.start();
    }


    public void jsonLugares(ActionEvent actionEvent) {
        RutaArchivo();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(ruta));
            JSONObject jsonobj = (JSONObject) obj;
            jsonarra = (JSONArray) jsonobj.get("Lugares");
            avisoLugares.setTextFill(Color.GREEN);
            miLista=new ListaAdyacente(jsonarra.size()+1);
            for (int i = 0; i < jsonarra.size(); i++) {
                JSONObject jsonobj1 = (JSONObject) jsonarra.get(i);
                miLista.insert(jsonobj1.get("id").toString(), i+1);
            }

        }catch(Exception e) {
            System.out.println("Error en parsear json de Lugares" + e);
        }
    }

    public static JSONArray jsonarraru;
    public void jsonRutas(ActionEvent actionEvent) {
        RutaArchivo();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(ruta));
            JSONObject jsonobj = (JSONObject) obj;
            jsonarraru = (JSONArray) jsonobj.get("Grafo");
            avisoRuta.setTextFill(Color.GREEN);
            miLista.nograph = "strict graph G  \n" +
                    "{\n" +
                    "    layout =neato\n";
            for (int i = 0; i < jsonarraru.size(); i++) {
                JSONObject jsonobjr1 = (JSONObject) jsonarraru.get(i);
                miLista.conexion(Integer.parseInt(jsonobjr1.get("inicio").toString()),Integer.parseInt(jsonobjr1.get("final").toString()));
                miLista.nodirigido(jsonobjr1.get("inicio").toString(),jsonobjr1.get("final").toString(),jsonobjr1.get("peso").toString());
            }
            //miLista.matrizdijk();
            miLista.nograph += "}";
            miLista.graph();
            GenerarGrafo(miLista.graph, "Adyacente");
            ColocarImg("Adyacente",adya);
            GenerarGrafo(miLista.nograph, "NoDirigido");
            ColocarImg("NoDirigido",nodiri);
        }catch(Exception e) {
            System.out.println("Error en parsear json de Municipios" + e);
        }
    }

    public void jsonMensajero(ActionEvent actionEvent) {
        try {
            RutaArchivo();
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(ruta));
            JSONArray jsonarra = (JSONArray) obj;
            for (int i = 0; i < jsonarra.size(); i++) {
                JSONObject jsonobj1 = (JSONObject) jsonarra.get(i);
                TH.insertar(Long.parseLong(jsonobj1.get("dpi").toString()),jsonobj1.get("nombres").toString(),jsonobj1.get("apellidos").toString(),jsonobj1.get("tipo_licencia").toString(),jsonobj1.get("genero").toString(),null,jsonobj1.get("direccion").toString());
            }


            avisoMensaje.setTextFill(Color.GREEN);
        }catch(Exception e) {
            System.out.println("Error en parsear json de Mensajero" + e);
        }
    }



}
