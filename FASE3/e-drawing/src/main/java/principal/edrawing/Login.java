package principal.edrawing;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    public Button btnreg;
    public Button btningre;

    BNode validacion = null;

    HelloApplication m = new HelloApplication();

    public void toRegister() throws IOException {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("registro.fxml"));
        Parent root1 = loader1.load();
        Register cliente1 = loader1.getController();
        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();

        stage1.setScene(scene1);
        stage1.getIcons().add(new Image(String.valueOf(getClass().getClassLoader().getResource("logo.png"))));
        stage1.setTitle("Registro");
        stage1.show();

        stage1.setOnCloseRequest(e -> {
            try {
                cliente1.closeWindows();
                stage1.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        lbwrong.setText("");
        Stage myStage = (Stage) this.btnreg.getScene().getWindow();
        myStage.close();
    }

    public void actLog() throws IOException {
        if(txuser.getText().isEmpty() && txpass.getText().isEmpty()) {
            lbwrong.setText("Ingrese sus credenciales.");
        }else{
            validacion = HelloApplication.arbol.iniciarBusquedas(txuser.getText(),txpass.getText());
            if(validacion != null) {
                lbwrong.setText("");

                validacion.perfil.show();
                Stage myStage = (Stage) this.btningre.getScene().getWindow();
                myStage.hide();
                myStage.close();
            }else{
                lbwrong.setText("Usuario o Contraseña Incorrecto!");
            }
        }
        txpass.setText("");
    }

}
