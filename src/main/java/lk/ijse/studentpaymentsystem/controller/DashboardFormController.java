package lk.ijse.studentpaymentsystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardFormController {

    public JFXButton btnDashboard;
    @FXML
    private VBox vBoxButton;

    @FXML
    private Label date;

    @FXML
    private Label time;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private Button btnStudent;

    @FXML
    private Button btnPayment;

    @FXML
    private Button btnUser;

    public void initialize() {
        initializeDateTime();
        initializeButtonStyles();
    }

    @FXML
    private void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        // Load the login form
        Parent loginForm = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(loginForm);
        Stage stage = (Stage) rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
    }

    private void initializeDateTime() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    LocalDateTime now = LocalDateTime.now();
                    date.setText(now.format(dateFormatter));
                    time.setText(now.format(timeFormatter));
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void initializeButtonStyles() {
        // Set initial styles if needed
        btnStudent.setStyle("");
        btnPayment.setStyle("");
        btnUser.setStyle("");
    }

    private void resetButtonStyles() {
        btnDashboard.setStyle("");
        btnStudent.setStyle("");
        btnPayment.setStyle("");
        btnUser.setStyle("");
    }

    public void btnStudentOnAction(ActionEvent actionEvent) throws IOException {
        resetButtonStyles();
        btnStudent.setStyle("-fx-background-color: #020202;");

        FXMLLoader customerLoader = new FXMLLoader(getClass().getResource("/view/student_form.fxml"));
        Parent customerRoot = customerLoader.load();
        rootNode.getChildren().clear();
        rootNode.getChildren().add(customerRoot);

        Stage stage = (Stage) rootNode.getScene().getWindow();
        stage.setTitle("Student Manage");
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) throws IOException {
        resetButtonStyles();
        btnPayment.setStyle("-fx-background-color: #020202;");

        FXMLLoader customerLoader = new FXMLLoader(getClass().getResource("/view/payment_form.fxml"));
        Parent customerRoot = customerLoader.load();
        rootNode.getChildren().clear();
        rootNode.getChildren().add(customerRoot);

        Stage stage = (Stage) rootNode.getScene().getWindow();
        stage.setTitle("Payment Manage");

    }

    public void btnUserOnAction(ActionEvent actionEvent) throws IOException {
        resetButtonStyles();
        btnUser.setStyle("-fx-background-color: #020202;");

        FXMLLoader customerLoader = new FXMLLoader(getClass().getResource("/view/user_form.fxml"));
        Parent customerRoot = customerLoader.load();
        rootNode.getChildren().clear();
        rootNode.getChildren().add(customerRoot);

        Stage stage = (Stage) rootNode.getScene().getWindow();
        stage.setTitle("User Manage");
    }

    public void btnDashboardOnAction(ActionEvent actionEvent) throws IOException {
        resetButtonStyles();
        btnDashboard.setStyle("-fx-background-color: #020202;");

        FXMLLoader customerLoader = new FXMLLoader(getClass().getResource("/view/mainDashboard_form.fxml"));
        Parent customerRoot = customerLoader.load();
        rootNode.getChildren().clear();
        rootNode.getChildren().add(customerRoot);

        Stage stage = (Stage) rootNode.getScene().getWindow();
        stage.setTitle("Dashboard");
    }
}
