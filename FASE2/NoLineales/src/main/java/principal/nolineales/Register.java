package principal.nolineales;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import principal.ArbolAVL.AVL;
import principal.ArbolAVL.NodoAVL;

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

    public void Registrar(ActionEvent actionEvent)  throws IOException{
        double dpi;
        if(txnombre.getText().isEmpty() || txpass.getText().isEmpty() || txdpi.getText().isEmpty()) {
            lbregistrado.setText("Faltan credenciales.");
        }else{
            AVL arbol = new AVL();
            dpi = Double.parseDouble(txdpi.getText().toString());
            arbol.insertar(10);
            arbol.insertar(13);
            arbol.insertar(15);

            arbol.insertar(14);
            arbol.insertar(16);
            arbol.insertar(8);
            arbol.insertar(1);
            arbol.insertar(12);
            arbol.insertar(9);
            arbol.insertar(2);

            System.out.println("INORDEN: ");
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
            }


            lbregistrado.setText("Usuario Registrado!");
        }
    }
}
