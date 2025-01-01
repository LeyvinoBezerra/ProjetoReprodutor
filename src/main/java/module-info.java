module com.projetoreprodutor.projetoreprodutor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires jdk.jfr;


    opens com.projetoreprodutor.projetoreprodutor to javafx.fxml;
    exports com.projetoreprodutor.projetoreprodutor;
}