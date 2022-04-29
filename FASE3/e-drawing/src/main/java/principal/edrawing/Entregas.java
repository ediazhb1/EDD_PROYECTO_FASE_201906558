package principal.edrawing;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import principal.tablahash.TablaHash;

import java.io.IOException;

public class Entregas {
    public Label lbname;
    public Button btcerrar;
    public ComboBox<String> cmbx;
    public ComboBox<String> cmbsucursal;
    String usuario ="";
    Stage stageLogin = null;
    Stage stageMasiva = null;

   Dijkstra dij = new Dijkstra();

    public void getStage(Stage stage, Stage stagem){
        stageLogin = stage;
        stageMasiva = stagem;
    }

    public void CerrarSesion() throws IOException {
        stageLogin.show();
        Stage myStage = (Stage) btcerrar.getScene().getWindow();
        myStage.hide();
        myStage.close();
    }

    public void jsonMensajero() {
    }

    public void Masiva() throws IOException {
        stageMasiva.show();
        Stage myStage = (Stage) btcerrar.getScene().getWindow();
        myStage.hide();
        myStage.close();
    }

    public void displayName(String text) {
        lbname.setText(text);
        usuario = text;
        for (int i = 0; i < TablaHash.tabla.length; i++) {
            if(TablaHash.tabla[i] != null) {
                cmbx.getItems().addAll(TablaHash.tabla[i].nombre);
            }
        }
        for (int i = 0; i < Cliente.jsonarra.size(); i++) {
            JSONObject jsonobj1 = (JSONObject) Cliente.jsonarra.get(i);
            cmbsucursal.getItems().addAll(jsonobj1.get("id").toString()+". "+jsonobj1.get("nombre").toString());
        }

    }


    public void Entregar(ActionEvent actionEvent) {
        for (int i = 0; i < Cliente.jsonarra.size(); i++) {
            for (int j = 0; j <Cliente.jsonarra.size(); j++) {
                //dij.cost[i][j] = in.nextInt();
            }
        }


        int contador = 0;
        String inicio ="";
        while (true){
            char firstCharacter = cmbsucursal.getValue().charAt(contador);
            if(firstCharacter == '.'){
                break;
            }else{
                inicio += firstCharacter;
                contador++;
            }
        }
        dij.calc(Cliente.jsonarra.size(),Integer.parseInt(inicio));

    }
}
