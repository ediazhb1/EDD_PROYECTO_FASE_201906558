module principal.edrawing {
    requires javafx.controls;
    requires javafx.fxml;


    opens principal.edrawing to javafx.fxml;
    exports principal.edrawing;
}