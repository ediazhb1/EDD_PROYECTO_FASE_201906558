package principal.edrawing;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
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

    public Button btnregi;
    public Button btcerrar;

    BNode validacion = null;
    HelloApplication m = new HelloApplication();

    public void Registrar(ActionEvent event) throws IOException {
        if(txusuario.getText().isEmpty() || txpass.getText().isEmpty() || txdpi.getText().isEmpty() || txnombre.getText().isEmpty()) {
            System.out.println("Faltan Credenciales");
        }else{
            validacion = m.arbol.BusquedaUsuario(txusuario.getText());
            if(validacion ==null){// Verifica si el usuario no esta ya registrado
                long dpi = Long.parseLong(txdpi.getText());
                //------------------------------Stage de Cargas Masivas----------------------------------------------------
                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("cliente.fxml"));
                Parent root1 = loader1.load();
                Cliente cliente1 = loader1.getController();
                Scene scene1 = new Scene(root1);
                Stage stage1 = new Stage();
                stage1.setScene(scene1);

                stage1.getIcons().add(new Image(String.valueOf(getClass().getClassLoader().getResource("logo.png"))));
                stage1.setTitle("Cliente - Masiva");
                cliente1.displayName(txusuario.getText());
                cliente1.getStage(HelloApplication.stageLogin);
                stage1.setOnCloseRequest(e -> {
                    try {
                        cliente1.CerrarSesion();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println("Se cerro");

                });
                HelloApplication.arbol.insertarB(dpi,txnombre.getText(),txusuario.getText(),txcorreo.getText(),txpass.getText(), txtel.getText(), txdire.getText(), txmuni.getText(),stage1);
                closeWindows();
            }else{
                txusuario.setText("");
                System.out.println("Usuario no disponible");
            }

        }
    }

    public void closeWindows() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));

        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();

        Stage myStage = (Stage) btcerrar.getScene().getWindow();
        myStage.hide();
        myStage.close();
    }

    public void toLogin() throws IOException {
        closeWindows();
    }
}
