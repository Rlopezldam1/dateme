module com.example.dateme {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.gluonhq.charm.glisten;
    requires java.sql;


    opens com.example.dateme to javafx.fxml, com.gluonhq.charm.glisten;
    exports com.example.dateme;
}