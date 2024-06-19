package lk.ijse.studentpaymentsystem.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class PaymentFormController {

    @FXML
    private JFXComboBox<?> cmbArrived;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colArrived;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableView<?> tblPayment;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtStudentAddName;

    @FXML
    private TextField txtStudentAddNic;

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnCopyOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnStudentClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnStudentSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void btnYesOnAction(ActionEvent event) {

    }

    @FXML
    void nameKeyRelaseAction(KeyEvent event) {

    }

    @FXML
    void nicKeyRelaseAction(KeyEvent event) {

    }

}
