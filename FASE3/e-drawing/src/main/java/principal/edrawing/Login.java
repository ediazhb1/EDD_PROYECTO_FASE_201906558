package principal.edrawing;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import principal.arbolb.BNode;

import java.io.IOException;

public class Login {
    public TextField txuser;
    public PasswordField txpass;
    public Label lbwrong;
    public static Scene scene;

    BNode validacion = null;

    HelloApplication m = new HelloApplication();

    public void toRegister() throws IOException {
        m.changeRegister();
    }

    public void actLog(ActionEvent event) throws IOException {
        if(txuser.getText().isEmpty() && txpass.getText().isEmpty()) {
            lbwrong.setText("Ingrese sus credenciales.");
        }else{
            validacion = m.arbol.iniciarBusquedas(txuser.getText(),txpass.getText());
            if(validacion != null) {
                lbwrong.setText("Ingresando...");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("cliente.fxml"));
                Parent root = loader.load();
                Cliente cliente = loader.getController();
                cliente.displayName(validacion);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.getIcons().add(new Image(String.valueOf(getClass().getClassLoader().getResource("logo.png"))));
                stage.setTitle("Cliente");
                stage.show();
                lbwrong.setText("");
            }else{
                lbwrong.setText("Usuario o Contraseña Incorrecto!");
            }
        }
        txpass.setText("");
    }

}
