module lk.ijse.studentpaymentsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires java.datatransfer;
    requires com.jfoenix;
    requires jasperreports;
    requires spring.web;


    opens lk.ijse.studentpaymentsystem to javafx.fxml;
    exports lk.ijse.studentpaymentsystem;
    exports lk.ijse.studentpaymentsystem.controller;
    opens lk.ijse.studentpaymentsystem.controller to javafx.fxml;
    opens lk.ijse.studentpaymentsystem.model to javafx.base;

}