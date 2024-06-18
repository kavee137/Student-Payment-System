module lk.ijse.studentpaymentsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;

    opens lk.ijse.studentpaymentsystem.controller to javafx.fxml;

    exports lk.ijse.studentpaymentsystem;
}
