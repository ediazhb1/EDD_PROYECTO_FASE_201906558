package principal.nolineales;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;

public class Cliente_Img {
    public Button btnPRL;
    public Button btPAI;
    public Button btPCapa;
    public Button regresar;
    public RadioButton RadioIn;
    public RadioButton RadioPre;
    public RadioButton RadioPos;
    public Label Titulo;
    public Label numero;
    public TextField txboxnum;
    public Label Recorrido;
    public Label Recorrido2;
    public Label contRecorrido;
    public Button btnCrear;
    HelloApplication m = new HelloApplication();

    public void toCapa() throws IOException {
        m.changeCliente();
    }

    public void toAlbum(ActionEvent actionEvent) {
    }

    public void toReport(ActionEvent actionEvent) {
    }

    public void toLogin() throws IOException {
        m.changeStart();
    }


    public void PRL() {
        btnPRL.setVisible(false);
        btPAI.setVisible(false);
        btPCapa.setVisible(false);
        regresar.setVisible(true);
        RadioIn.setVisible(true);
        RadioPos.setVisible(true);
        RadioPre.setVisible(true);
        Titulo.setVisible(true);
        Titulo.setText("Por Recorrido Limitado");
        numero.setVisible(true);
        txboxnum.setVisible(true);
        Recorrido.setVisible(true);
        Recorrido2.setVisible(true);
        contRecorrido.setVisible(true);
        btnCrear.setVisible(true);

        ToggleGroup group = new ToggleGroup();
        RadioIn.setToggleGroup(group);
        RadioIn.setSelected(true);
        RadioPre.setToggleGroup(group);
        RadioPos.setToggleGroup(group);


    }

    public void PAI(ActionEvent actionEvent) {
        btnPRL.setVisible(false);
        btPAI.setVisible(false);
        btPCapa.setVisible(false);
        regresar.setVisible(true);
        RadioIn.setVisible(false);
        RadioPos.setVisible(false);
        RadioPre.setVisible(false);
        Titulo.setVisible(true);
        Titulo.setText("Por Árbol de Imágenes");
        numero.setVisible(true);
        txboxnum.setVisible(true);
        Recorrido.setVisible(false);
        Recorrido2.setVisible(false);
        contRecorrido.setVisible(false);
        btnCrear.setVisible(true);

    }

    public void PCapa(ActionEvent actionEvent) {
        btnPRL.setVisible(false);
        btPAI.setVisible(false);
        btPCapa.setVisible(false);
        regresar.setVisible(true);
        RadioIn.setVisible(false);
        RadioPos.setVisible(false);
        RadioPre.setVisible(false);
        Titulo.setVisible(true);
        Titulo.setText("Por Capa");
        numero.setVisible(true);
        txboxnum.setVisible(true);
        Recorrido.setVisible(false);
        Recorrido2.setVisible(false);
        contRecorrido.setVisible(false);
        btnCrear.setVisible(true);


    }

    public void toBacks() {
        btnPRL.setVisible(true);
        btPAI.setVisible(true);
        btPCapa.setVisible(true);
        regresar.setVisible(false);
        RadioIn.setVisible(false);
        RadioPos.setVisible(false);
        RadioPre.setVisible(false);
        Titulo.setVisible(false);
        numero.setVisible(false);
        txboxnum.setVisible(false);
        Recorrido.setVisible(false);
        Recorrido2.setVisible(false);
        contRecorrido.setVisible(false);
        btnCrear.setVisible(false);


    }

    public void CrearImg(ActionEvent actionEvent) {
    }
}
