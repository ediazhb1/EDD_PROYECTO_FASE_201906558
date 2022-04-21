package principal.nolineales;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import principal.ArbolAVL.AVL;
import principal.ArbolAVL.NodoAVL;
import principal.ArbolBB.ABB;
import principal.ArbolBB.Nodobb;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

public class reporte {
    public Button btnEstructura;
    public Label Titulo;
    public Label subtitulo;
    public Button regresar;
    public Button btMReportes;
    public Button btMEstru;
    public ComboBox combo;
    public static ABB x;
    public static AVL y;
    public ImageView imgest;
    public ProgressBar progressb;
    public static Thread th;
    public TextField txid;

    public Label top5;


    HelloApplication m = new HelloApplication();


    public void toAlbum(ActionEvent actionEvent) {
    }

    public void toImg() throws IOException {
        m.changeImagen();
    }

    public void toCapa() throws IOException {
        m.changeCliente();
    }

    public void toLogin() throws IOException {
        m.changeStart();
    }

    public void Estructuras(ActionEvent actionEvent) {
        btMEstru.setVisible(false);
        btMReportes.setVisible(false);
        btnEstructura.setVisible(true);
        Titulo.setVisible(true);
        Titulo.setText("Estado de Estructura");
        subtitulo.setVisible(true);
        regresar.setVisible(true);
        combo.setVisible(true);
        combo.getItems().addAll("AVL - IMAGENES");
        combo.getItems().addAll("ABB - CAPAS");
        combo.getItems().addAll("LISTA - ÁLBUM");
        combo.getItems().addAll("MATRIZ - CAPAS");
        combo.getItems().addAll("AVL Y ABB - IMG Y CAPAS");

    }

    public void Reportes(ActionEvent actionEvent) {
        btMEstru.setVisible(false);
        btMReportes.setVisible(false);
        regresar.setVisible(true);

    }


    public void toBacks(ActionEvent actionEvent) {
        btnEstructura.setVisible(false);
        btMEstru.setVisible(true);
        btMReportes.setVisible(true);
        regresar.setVisible(false);
        Titulo.setVisible(false);
        subtitulo.setVisible(false);
        combo.setVisible(false);
    }

    public void heredar(ABB harbol){
        x = harbol;
    }

    public void heredarimg(AVL harbol){
        y = harbol;
    }


    public void CrearEstructura(ActionEvent actionEvent) {
        if(combo.getValue().equals("AVL - IMAGENES")){
            if(y != null) {
                y.Iniciograph(true);
                GenerarGrafo(y.graph,"AVL");
                colocarImg("AVL");
            }else{
                System.out.println("Carge el archivo");
            }
        }else if(combo.getValue().equals("ABB - CAPAS")){
            if(x != null) {
                x.Iniciograph(true,"");
                GenerarGrafo(x.graph, "ABB");
                colocarImg("ABB");
            }else{
                System.out.println("Carge el archivo");
            }
        }else if(combo.getValue().equals("MATRIZ - CAPAS")){
            Nodobb conexion = x.IniciarBusquedabb(Integer.parseInt(txid.getText()));
            conexion.mayorcol();
            conexion.mayorfila();
            conexion.dot(txid.getText());
            GenerarGrafo(conexion.dotgraph, "MATRIZ");
            colocarImg("MATRIZ");
        }else if(combo.getValue().equals("AVL Y ABB - IMG Y CAPAS")){
            String unirdots = "";
            y.Iniciograph(false); //AVL
            unirdots = y.graph;
            NodoAVL conexion1 = y.IniciarBusqueda(Integer.parseInt(txid.getText()));//ABB
            conexion1.Iniciograph(false,txid.getText());
            unirdots += conexion1.graph;
            GenerarGrafo(unirdots, "AVL-ABB");
            colocarImg("AVL-ABB");

        }

    }

    public void GenerarGrafo(String data,String tipo){
        FileWriter fichero = null;
        PrintWriter pw = null;
        /*Crea un archivo con extesión .dot con el texto de la variable graph*/
        try{
            fichero = new FileWriter("Estructuras.dot");
            pw = new PrintWriter(fichero);
            pw.write(data);
            pw.close();
            fichero.close();
        }catch(Exception e){
            System.out.println("Error en generar dot de la capa");
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
            proceso = new ProcessBuilder("dot", "-Tpng", "-o",dir+"/"+"Estructura"+tipo+".png","Estructuras.dot");
            proceso.redirectErrorStream(true);
            proceso.start();
        }catch (Exception e){
            System.out.println("Error en generar png de la Estructura");
        }
    }

    public void colocarImg(String tipos){
        th = new Thread() {
            public void run() {
                for(int i = 1 ; i <= 12 ; i++){
                    try {
                        if(i == 8){
                            progressb.setProgress(0.69);
                        }else if(i == 4){
                            progressb.setProgress(0.32);
                        }else if(i == 2){
                            progressb.setProgress(0.16);
                        }else if(i == 6){
                            progressb.setProgress(0.48);
                        }else if(i == 1) {
                            progressb.setProgress(0.08);
                        }else if(i == 10){
                            progressb.setProgress(0.80);
                        }else if(i==12){
                            progressb.setProgress(1);
                        }

                        Thread.sleep(1000);
                    }catch (Exception e){
                        System.out.println("Error en el progress bar");
                    }
                }
                String dir = Paths.get("")
                        .toAbsolutePath()
                        .toString();
                File f = new File(dir+"/"+ "Estructura"+tipos+".png");
                if (f.exists()) {
                    System.out.println("Exists: " + f.exists());
                    Image image1 = new Image("file:" +dir+"/"+ "Estructura" + tipos + ".png");
                    imgest.setImage(image1);

                } else {
                    System.out.println("Does not Exists" + f.exists());
                }
            }
        };th.start();
    }


    public void clickcombo() {
        if(combo.getValue().equals("MATRIZ - CAPAS")){
            txid.setText("");
            txid.setVisible(true);
        }else if(combo.getValue().equals("AVL Y ABB - IMG Y CAPAS")){
            txid.setText("");
            txid.setVisible(true);
        }else{
            txid.setVisible(false);
        }
    }
}
