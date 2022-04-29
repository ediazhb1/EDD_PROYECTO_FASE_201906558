module principal.edrawing {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens principal.edrawing to javafx.fxml;
    exports principal.edrawing;
}