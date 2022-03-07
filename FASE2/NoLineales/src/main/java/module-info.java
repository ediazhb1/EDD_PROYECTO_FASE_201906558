module principal.nolineales {
    requires javafx.controls;
    requires javafx.fxml;


    opens principal.nolineales to javafx.fxml;
    exports principal.nolineales;
}