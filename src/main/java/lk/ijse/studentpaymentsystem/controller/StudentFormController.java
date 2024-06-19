package lk.ijse.studentpaymentsystem.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentpaymentsystem.dao.StudentDAOImpl;
import lk.ijse.studentpaymentsystem.model.StudentDTO;
import lk.ijse.studentpaymentsystem.util.Regex;

import java.sql.SQLException;
import java.util.List;

public class StudentFormController {

    @FXML
    private JFXComboBox<String> cmbArrived;

    @FXML
    private TableColumn<?, ?> colArrived;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<StudentDTO> tblStudent;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;


    public void initialize() {
        setCellValueFactory();
        loadAllStudents();
//        getCurrentCustomerId();
        showSelectedUserDetails();
        setCmbArrived();
    }

    private void setCellValueFactory(){
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colArrived.setCellValueFactory(new PropertyValueFactory<>("arrived"));
    }

    private void setCmbArrived() {
        ObservableList<String> arrivedList = FXCollections.observableArrayList();
        cmbArrived.setValue("no");

        arrivedList.add("yes");
        arrivedList.add("no");

        cmbArrived.setItems(arrivedList);
    }

    private void showSelectedUserDetails() {
        StudentDTO selectedUser = tblStudent.getSelectionModel().getSelectedItem();
        tblStudent.setOnMouseClicked(event -> showSelectedUserDetails());
        if (selectedUser != null) {
            txtNic.setText(selectedUser.getNic());
            txtName.setText(selectedUser.getName());
            cmbArrived.setValue(selectedUser.getArrived());
        }
    }

    private void loadAllStudents() {
        ObservableList<StudentDTO> obList = FXCollections.observableArrayList();

        try {
            List<StudentDTO> studentDTOList = StudentDAOImpl.getAll();
            for (StudentDTO student : studentDTOList) {
                StudentDTO tm = new StudentDTO(
                        student.getNic(),
                        student.getName(),
                        student.getArrived()
                );
                obList.add(tm);
            }

            tblStudent.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void bntYesOnAction(ActionEvent event) {
        cmbArrived.setValue("yes");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtNic.setText(null);
        txtName.setText(null);
        cmbArrived.setValue("no");

        txtName.setStyle(null);
        txtNic.setStyle(null);
        tblStudent.refresh();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String nic = txtNic.getText();

        try {
            boolean isDeleted = StudentDAOImpl.delete(nic);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "student deleted!").show();
                clearFields();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String nic = txtNic.getText();
        String name = txtName.getText();
        String arrived = cmbArrived.getValue();

        StudentDTO student = new StudentDTO(nic, name, arrived);
        if (isValied()) {
            try {
                boolean isSaved = StudentDAOImpl.save(student);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "student saved!").show();
                    clearFields();
                    initialize();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            validationError();
        }
    }

    public boolean isValied(){
        boolean nameValid = Regex.setTextColor(lk.ijse.studentpaymentsystem.util.TextField.NAME, txtName);
        boolean nicValid = Regex.setTextColor(lk.ijse.studentpaymentsystem.util.TextField.NIC, txtNic);

        return nameValid && nicValid;
    }

    @FXML
    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException {
        String nic = txtNic.getText();

        if (isSearchNicValied()) {

            StudentDTO studentDTO = StudentDAOImpl.nicSearch(nic);
            if (studentDTO != null) {
                cmbArrived.setValue(studentDTO.getArrived());
                txtName.setText(studentDTO.getName());

            } else {
                new Alert(Alert.AlertType.INFORMATION, "student not found!").show();
            }
        } else {
            validationError();
        }
    }

    public boolean isSearchNicValied(){
        boolean nicValid = Regex.setTextColor(lk.ijse.studentpaymentsystem.util.TextField.NIC, txtNic);
        return nicValid;
    }
    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String name = txtName.getText();
        String nic = txtNic.getText();
        String arrived = cmbArrived.getValue();

        if (isValied()) {
            StudentDTO studentDTO = new StudentDTO(nic, name, arrived);

            try {
                boolean isUpdated = StudentDAOImpl.update(studentDTO);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "student updated!").show();
                    clearFields();
                    initialize();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            validationError();
        }
    }

    void validationError() {
        // Show error message if validation fails
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText("Validation Failed");
        alert.setContentText("Please fill in all fields correctly.");
        alert.showAndWait();
    }

    public void nameKeyRelaseOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.studentpaymentsystem.util.TextField.NAME, txtName);
    }

    public void nicKeyRelaseOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.studentpaymentsystem.util.TextField.NIC, txtNic);
    }
}
