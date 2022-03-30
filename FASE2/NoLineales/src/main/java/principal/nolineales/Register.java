package principal.nolineales;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import principal.ArbolBB.ABB;

import java.io.IOException;

public class Register {

    public Register() {
    }

    @FXML
    private TextField txnombre;
    @FXML
    private TextField txdpi;
    @FXML
    private TextField txpass;
    @FXML
    private Label lbregistrado;


    public void toLogin() throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeStart();
    }

    public void Registrar(ActionEvent actionEvent){
        double dpi;
        if(txnombre.getText().isEmpty() || txpass.getText().isEmpty() || txdpi.getText().isEmpty()) {
            lbregistrado.setText("Faltan credenciales.");
        }else{
            dpi = Double.parseDouble(txdpi.getText().toString());
            ABB arbol = new ABB();


            /*System.out.println("INORDEN: ");
            arbol.InOrden(arbol.obtRaiz());
            System.out.println("");
            System.out.println("POSTORDEN: ");
            arbol.PostOrden(arbol.obtRaiz());
            System.out.println("");
            System.out.println("PREORDEN: ");
            arbol.PreOrden(arbol.obtRaiz());
            System.out.println("");


            System.out.print("Busqueda del numero 2: ");
            NodoAVL x = arbol.IniciarBusqueda(2);
            System.out.println(x.getDato());

            System.out.print("Busqueda del numero 115: ");
            NodoAVL a = arbol.IniciarBusqueda(115);
            if(a != null){
                System.out.println(a.getDato());
            }*/


            lbregistrado.setText("Usuario Registrado!");
        }
    }
}
