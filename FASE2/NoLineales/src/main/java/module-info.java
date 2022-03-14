module principal.nolineales {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens principal.nolineales to javafx.fxml;
    exports principal.nolineales;
}