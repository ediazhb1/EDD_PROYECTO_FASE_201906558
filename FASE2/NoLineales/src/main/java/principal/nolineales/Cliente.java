package principal.nolineales;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Cliente {
    HelloApplication m = new HelloApplication();



    Matriz mz = new Matriz();

    public void toLogin() throws IOException {
        m.changeStart();
    }

    public void RutaArchivo() throws IOException{
        //mz.insertarNodo(0,0,"#ffffff");
        //mz.imprimir();
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
        try{
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(path));
            JSONArray jsonarra = (JSONArray) obj;
            //System.out.println(jsonarra);


            for(int i = 0 ; i < jsonarra.size(); i++){
                JSONObject jsonobj1 = (JSONObject) jsonarra.get(i);
                System.out.println("ID_CAPA: "+jsonobj1.get("id_capa") + " PIXELES: " +jsonobj1.get("pixeles"));
            }

        }catch (Exception e){
            System.out.println("Error en parsear json "+e);
        }
    }

}
