package principal.nolineales;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LogIn {
    HelloApplication m = new HelloApplication();

    public LogIn() {
    }

    @FXML
    private Label lbwrong;
    @FXML
    private TextField txuser;
    @FXML
    private PasswordField txpass;


    public void actLog(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {

        if(txuser.getText().toString().equals("admin") && txpass.getText().toString().equals("123")) {
            lbwrong.setText("admin!");
            m.changeAdmin();
        }else if(txuser.getText().toString().equals("cliente") && txpass.getText().toString().equals("123")) {
            lbwrong.setText("cliente!");
            m.changeCliente();
        }else if(txuser.getText().isEmpty() && txpass.getText().isEmpty()) {
            lbwrong.setText("Ingrese sus credenciales.");
        } else {
            lbwrong.setText("Usuario o Contraseña Incorrecto!");
        }
    }

    public void toRegister(ActionEvent event) throws IOException {
        m.changeRegister();
    }

}
