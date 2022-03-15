package principal.nolineales;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Cliente {
    
    public Button btCapa;
    public ComboBox<String> cmbx;
    public Label lbname;

    HelloApplication m = new HelloApplication();
    ListaCapas LC = new ListaCapas();
    Matriz mz = new Matriz();

    public void toLogin() throws IOException {
        m.changeStart();
    }

    public void RutaArchivo() throws IOException{
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
        } else  {
            System.out.println("Error, seleccione json");
        }

    }

    public void Analyzer(String path){
        String contcombo ="";
        int fila;
        int columna;
        try{
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(path));
            JSONArray jsonarra = (JSONArray) obj;

            for(int i = 0 ; i < jsonarra.size(); i++){
                JSONObject jsonobj1 = (JSONObject) jsonarra.get(i);
                contcombo =  jsonobj1.get("id_capa").toString() ;
                cmbx.getItems().addAll(contcombo);
                LC.insertar(contcombo);

                NodoListaCapas conexion = LC.BuscarCapa(contcombo);

                JSONArray pixeles = (JSONArray) jsonobj1.get("pixeles");
                for(int j = 0; j < pixeles.size(); j++){
                    JSONObject jsonobj2 = (JSONObject) pixeles.get(j);
                    fila = Integer.parseInt(jsonobj2.get("fila").toString());
                    columna =  Integer.parseInt(jsonobj2.get("columna").toString());
                    conexion.insertarNodo(columna ,fila,jsonobj2.get("color").toString());
                }
            }
            btCapa.setDisable(false);
        }catch (Exception e){
            System.out.println("Error en parsear json "+e);
        }
    }


    public void MostrarCapa() {
        NodoListaCapas conexion = LC.BuscarCapa(cmbx.getValue());
        conexion.mayorcol();
        conexion.mayorfila();
        conexion.graficar(cmbx.getValue());
        //conexion.imprimir();
    }

    public void toImages(ActionEvent actionEvent) {
    }

    public void toAlbum(ActionEvent actionEvent) {
    }
}
