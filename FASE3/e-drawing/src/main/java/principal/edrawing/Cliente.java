package principal.edrawing;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import principal.arbolb.BNode;

import java.io.IOException;

public class Cliente {
    @FXML
    public Label lbname;
    HelloApplication m = new HelloApplication();
    public void toLogin() throws IOException {
        m.changeStart();
    }


    public void Masivas() {
    }

    public void displayName(BNode text) {
        lbname.setText(text.nombre);
    }
}
