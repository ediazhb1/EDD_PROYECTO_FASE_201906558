package principal.nolineales;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import principal.ArbolAVL.AVL;
import principal.ArbolAVL.NodoAVL;
import principal.ArbolBB.ABB;
import principal.ArbolBB.Nodobb;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Cliente_Img {
    public Button btnPRL;
    public Button btPAI;
    public Button btPCapa;
    public Button regresar;
    public RadioButton RadioIn;
    public RadioButton RadioPre;
    public RadioButton RadioPos;
    public Label Titulo;
    public Label numero;
    public TextField txboxnum;
    public Label Recorrido;
    public Label Recorrido2;
    public Label contRecorrido;
    public Button btnCrearPRL;
    public Button btnCrearPAI;
    public Button btnCrearPC;
    public ProgressBar progressb;
    public ImageView imgcapa;
    public static Thread th;
    public static ABB x;
    public Label lbCarga;
    public Button btEliminar;
    public Button btnBorrar;
    String num = "";

    HelloApplication m = new HelloApplication();
    AVL arbolavl = new AVL();
    Matriz imagenes = new Matriz();

    public void toCapa() throws IOException {
        m.changeCliente();
    }

    public void toAlbum(ActionEvent actionEvent) {
        arbolavl.iniciarpre();
    }

    public void toReport(ActionEvent actionEvent) {
    }

    public void toLogin() throws IOException {
        m.changeStart();
    }


    public void PRL() {
        btEliminar.setVisible(false);
        btnPRL.setVisible(false);
        btPAI.setVisible(false);
        btPCapa.setVisible(false);
        regresar.setVisible(true);
        RadioIn.setVisible(true);
        RadioPos.setVisible(true);
        RadioPre.setVisible(true);
        Titulo.setVisible(true);
        Titulo.setText("Por Recorrido Limitado");
        numero.setVisible(true);
        txboxnum.setVisible(true);
        Recorrido.setVisible(true);
        Recorrido2.setVisible(true);
        txboxnum.setPromptText("1");
        contRecorrido.setText("Sin Crear Imagen");
        contRecorrido.setVisible(true);
        btnCrearPRL.setVisible(true);
        ToggleGroup group = new ToggleGroup();
        RadioIn.setToggleGroup(group);
        RadioIn.setSelected(true);
        RadioPre.setToggleGroup(group);
        RadioPos.setToggleGroup(group);
        btnCrearPRL.setVisible(true);
        btnCrearPAI.setVisible(false);
        btnCrearPC.setVisible(false);

    }

    public void PAI(ActionEvent actionEvent) {
        btEliminar.setVisible(false);
        btnPRL.setVisible(false);
        btPAI.setVisible(false);
        btPCapa.setVisible(false);
        regresar.setVisible(true);
        RadioIn.setVisible(false);
        RadioPos.setVisible(false);
        RadioPre.setVisible(false);
        Titulo.setVisible(true);
        Titulo.setText("Por Árbol de Imágenes");
        numero.setVisible(true);
        numero.setText("ID de la Imagen:");
        txboxnum.setVisible(true);
        txboxnum.setPromptText("1");
        Recorrido.setVisible(false);
        Recorrido2.setVisible(false);
        contRecorrido.setVisible(false);
        btnCrearPAI.setVisible(true);
        btnCrearPRL.setVisible(false);
        btnCrearPC.setVisible(false);
    }

    public void PCapa(ActionEvent actionEvent) {
        btnPRL.setVisible(false);
        btPAI.setVisible(false);
        btPCapa.setVisible(false);
        btEliminar.setVisible(false);
        regresar.setVisible(true);
        RadioIn.setVisible(false);
        RadioPos.setVisible(false);
        RadioPre.setVisible(false);
        Titulo.setVisible(true);
        Titulo.setText("Por Capa");
        numero.setVisible(true);
        numero.setText("ID de la Capas:");
        txboxnum.setVisible(true);
        txboxnum.setPromptText("1,2,3");
        Recorrido.setVisible(false);
        Recorrido2.setVisible(false);
        contRecorrido.setVisible(false);

        btnCrearPC.setVisible(true);
        btnCrearPAI.setVisible(false);
        btnCrearPRL.setVisible(false);

    }

    public void Eliminar(ActionEvent actionEvent) {
        btnBorrar.setVisible(true);
        btnPRL.setVisible(false);
        btEliminar.setVisible(false);
        btPAI.setVisible(false);
        btPCapa.setVisible(false);
        regresar.setVisible(true);
        Titulo.setVisible(true);
        Titulo.setText("Eliminar Imagen");
        numero.setVisible(true);
        numero.setText("ID de la Capas:");
        txboxnum.setVisible(true);
        txboxnum.setPromptText("1");

        btnCrearPC.setVisible(false);
        btnCrearPAI.setVisible(false);
        btnCrearPRL.setVisible(false);
    }

    public void toBacks() {
        btnBorrar.setVisible(false);
        btnPRL.setVisible(true);
        btPAI.setVisible(true);
        btPCapa.setVisible(true);
        btEliminar.setVisible(true);
        regresar.setVisible(false);
        RadioIn.setVisible(false);
        RadioPos.setVisible(false);
        RadioPre.setVisible(false);
        Titulo.setVisible(false);
        numero.setVisible(false);
        txboxnum.setVisible(false);
        Recorrido.setVisible(false);
        Recorrido2.setVisible(false);
        contRecorrido.setVisible(false);
        btnCrearPC.setVisible(false);
        btnCrearPAI.setVisible(false);
        btnCrearPRL.setVisible(false);
    }


    public void heredar(ABB harbol){
         x = harbol;
    }


    public void CargarArchivo(ActionEvent actionEvent) {
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


    public void reiniciarimg(){
        try{
            imagenes.exterminar();
        }catch (Exception e){

        }
    }



    public void progres(){ //Cuando imagen ya fue graficada con graphviz, se inicia una progress bar para insertar la imagen en la UI
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
                        System.out.println("Error en el progress bar");//Dar mas tiempo de carga
                    }
                }
                File f = new File("src/main/resources/Capa"+ num +".png");
                if (f.exists()) {
                    System.out.println("Exists: " + f.exists());
                    Image image1 = new Image("file:" + "src/main/resources/Capa" + num + ".png");
                    imgcapa.setImage(image1);

                } else {
                    System.out.println("Does not Exists" + f.exists());
                }
            }
        };th.start();
    }

    private void Analyzer(String path) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(path));
            JSONArray jsonarra = (JSONArray) obj;

            for (int i = 0; i < jsonarra.size(); i++) {
                JSONObject jsonobj1 = (JSONObject) jsonarra.get(i);
                arbolavl.insertar(Integer.parseInt(jsonobj1.get("id").toString()));

                NodoAVL conexion = arbolavl.IniciarBusqueda(Integer.parseInt(jsonobj1.get("id").toString()));

                JSONArray capas = (JSONArray) jsonobj1.get("capas");
                for (int j = 0; j < capas.size(); j++) {
                    conexion.insertarbb(Integer.parseInt(capas.get(j).toString()));
                }
            }
            btnCrearPRL.setDisable(false);
            btnCrearPAI.setDisable(false);
            btnCrearPC.setDisable(false);
            lbCarga.setText("Archivo Cargado!");
            lbCarga.setTextFill(Color.GREEN);

        } catch (Exception e) {
            System.out.println("Error en parsear json de imagenes" + e);
        }


    }

    public void CrearImgPAI(ActionEvent actionEvent) {
        reiniciarimg();
        num = "";
        String contxbox;
        contxbox = txboxnum.getText();

        try{
            NodoAVL conexion = arbolavl.IniciarBusqueda(Integer.parseInt(contxbox));
            conexion.InicioAmplitud();
            String[] ids1 = conexion.contAmplitud.split(",");
            for(int i = 0; i < ids1.length;i++) {
                num += ids1[i] + " ";
                Nodobb conexion3 = x.IniciarBusquedabb(Integer.parseInt(ids1[i]));
                nuevaImagen(conexion3);
            }
        }catch (Exception e){
            System.out.println("ID no encontrado");
        }
        imagenes.mayorcol();
        imagenes.mayorfila();
        imagenes.graficar(num);
        progres();
    }

    public void CrearImgPC() {
        reiniciarimg();
        num = "";
        String contxbox;
        contxbox = txboxnum.getText();
        String[] ids1 = contxbox.split(",");

        for(int i = 0; i < ids1.length;i++){
            num += ids1[i] + " ";
            Nodobb conexion3 = x.IniciarBusquedabb(Integer.parseInt(ids1[i]));
            nuevaImagen(conexion3);
        }

        imagenes.mayorcol();
        imagenes.mayorfila();
        imagenes.graficar(num);
        progres();

    }

    public void CrearImgPRL(ActionEvent actionEvent) {
        reiniciarimg();
        num = "";
        int contxbox;
        contxbox = Integer.parseInt(txboxnum.getText());

        if(RadioIn.isSelected()){
            System.out.println("Inorden");
            x.InicioInOrden();
            String[] capasin = x.contIn.split(",");

            for (int i = 0; i < contxbox; i++) {
                num += capasin[i] +" ";
                Nodobb conexion3 = x.IniciarBusquedabb(Integer.parseInt(capasin[i]));
                nuevaImagen(conexion3);
            }

        }else if(RadioPos.isSelected()){
            System.out.println("Postorden");
            x.InicioPostOrden();
            String[] capaspost = x.contPost.split(",");
            for (int i = 0; i < contxbox; i++) {
                num += capaspost[i] +" ";
                Nodobb conexion3 = x.IniciarBusquedabb(Integer.parseInt(capaspost[i]));
                nuevaImagen(conexion3);

            }

        }else if(RadioPre.isSelected()) {
            System.out.println("Preorden");
            x.InicioPreOrden();
            String[] capaspre = x.contPre.split(",");
            for (int i = 0; i < contxbox; i++) {
                num += capaspre[i] +" ";
                Nodobb conexion3 = x.IniciarBusquedabb(Integer.parseInt(capaspre[i]));
                nuevaImagen(conexion3);
            }
        }
        contRecorrido.setText(num);
        imagenes.mayorcol();
        imagenes.mayorfila();
        imagenes.graficar(num);
        progres();
    }

    public void nuevaImagen(Nodobb conexion3){
        if (conexion3 != null) {
            Nodo aux = conexion3.root;

            while (aux != null) {
                Nodo aux2 = aux;
                while (aux2 != null) {
                    imagenes.insertarNodo(aux2.x, aux2.y, aux2.color);
                    aux2 = aux2.siguiente;
                }
                aux = aux.abajo;
            }
        }
    }


    public void borrarimg(ActionEvent actionEvent) {
    }
}
