package principal.edrawing;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import principal.Merkles.HashNode;
import principal.Merkles.Merkle;
import principal.arbolb.BNode;
import principal.matrices.Matriz;
import principal.matrices.Nodo;
import principal.tablahash.NodoHash;
import principal.tablahash.TablaHash;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Entregas {
    public Label lbname;
    public Button btcerrar;
    public ComboBox<String> cmbx;
    public ComboBox<String> cmbsucursal;
    String usuario ="";
    Stage stageLogin = null;
    Stage stageMasiva = null;
    Matriz mat = new Matriz();
    Dijkstra dij = new Dijkstra();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

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
            mat.insertarNodo(i+1,i+1,0);
        }

        for (int i = 0; i < Cliente.jsonarraru.size(); i++) {
            JSONObject jsonobjr1 = (JSONObject) Cliente.jsonarraru.get(i);
            mat.insertarNodo(Integer.parseInt(jsonobjr1.get("inicio").toString()),Integer.parseInt(jsonobjr1.get("final").toString()),Integer.parseInt(jsonobjr1.get("peso").toString()));
        }

        for (int i = 1; i <= Cliente.jsonarra.size(); i++) {
            for (int j = 1; j <=Cliente.jsonarra.size(); j++) {
                Nodo vacio = mat.buscarpixel(i,j);
                if(vacio == null){
                    mat.insertarNodo(i,j,99);
                }
            }
        }
    }



    public void Entregar(ActionEvent actionEvent) throws NoSuchAlgorithmException {
        for (int i = 1; i <= Cliente.jsonarra.size(); i++) {
            for (int j = 1; j <=Cliente.jsonarra.size(); j++) {
                Nodo ingresar = mat.buscarpixel(i,j);
                Dijkstra.cost[i][j] = ingresar.peso;
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
        //dij.calc(Cliente.jsonarra.size(),Integer.parseInt(inicio));

        /*System.out.println("The ruta mas corta de \t"+cmbsucursal.getValue()+" to all other vertices are : \n");
        for(int i=1;i<=Cliente.jsonarra.size();i++){
            if(i!=Integer.parseInt(inicio)) {
                System.out.println("origen :" + Integer.parseInt(inicio) + "\t destination :" + i + "\t MinCost is :" + dij.distance[i] + "\t");
            }
        }*/
        BNode validacion = HelloApplication.arbol.BusquedaUsuario(usuario);
        NodoHash validacion2 = Cliente.TH.buscarMensajero(cmbx.getValue());

        String OE = cmbsucursal.getValue()+" , "+dtf.format(LocalDateTime.now())+" , "+inicio+" , "+validacion.toString()+" , "+validacion2.toString();
        System.out.println(OE);

        ArrayList<String> dataBlocks = new ArrayList<>();
        dataBlocks.add(OE);
        HashNode root = Merkle.generateTree(dataBlocks);
        Merkle.printLevelOrderTraversal(root);


    }
}
