module org.example.librarymanagementsystemlab {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens org.example.librarymanagementsystemlab to javafx.fxml;
    exports org.example.librarymanagementsystemlab;


    opens  org.example.librarymanagementsystemlab.controller to javafx.fxml;

    exports org.example.librarymanagementsystemlab.models;
    exports org.example.librarymanagementsystemlab.controller;




}