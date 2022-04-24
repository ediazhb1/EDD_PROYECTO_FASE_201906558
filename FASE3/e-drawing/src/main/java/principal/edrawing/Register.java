package principal.edrawing;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import principal.arbolb.BNode;

import java.io.IOException;

public class Register {
    public TextField txnombre;
    public TextField txdpi;
    public PasswordField txpass;
    public TextField txusuario;
    public TextField txcorreo;
    public TextField txtel;
    public TextField txdire;
    public TextField txmuni;
    BNode validacion = null;
    HelloApplication m = new HelloApplication();

    public void Registrar() throws IOException {
        if(txusuario.getText().isEmpty() || txpass.getText().isEmpty() || txdpi.getText().isEmpty() || txnombre.getText().isEmpty()) {
            System.out.println("Faltan Credenciales");
        }else{
            validacion = m.arbol.BusquedaUsuario(txusuario.getText());
            if(validacion ==null){
                long dpi = Long.parseLong(txdpi.getText());
                m.arbol.insertarB(dpi,txnombre.getText(),txusuario.getText(),txcorreo.getText(),txpass.getText(), txtel.getText(), txdire.getText(), txmuni.getText());
                m.changeStart();
            }else{
                txusuario.setText("");
                System.out.println("Usuario no disponible");
            }

        }
    }

    public void toLogin() throws IOException {
        m.changeStart();
    }
}
