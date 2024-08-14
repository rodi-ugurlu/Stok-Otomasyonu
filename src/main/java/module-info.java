module com.example.stoktakip {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.prefs;


    opens com.example.stoktakip to javafx.fxml;
    exports com.example.stoktakip;
}