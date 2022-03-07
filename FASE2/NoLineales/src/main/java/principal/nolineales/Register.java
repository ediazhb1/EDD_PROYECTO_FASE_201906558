package principal.nolineales;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
        if(txnombre.getText().isEmpty() || txpass.getText().isEmpty() || txdpi.getText().isEmpty()) {
            lbregistrado.setText("Faltan credenciales.");
        }else{
            lbregistrado.setText("Usuario Registrado!");
        }
    }
}
