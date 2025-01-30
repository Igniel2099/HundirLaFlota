package org.example.hundirlaflota.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.hundirlaflota.Window.UploadWindow;

import java.io.IOException;

public class LoginController {

    private Stage primaryStage;

    @FXML
    private TextField userField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button btnEntrar;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public TextField getUserField() {
        return userField;
    }

    public void setUserField(TextField userField) {
        this.userField = userField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(TextField passwordField) {
        this.passwordField = passwordField;
    }

    public Button getBtnEntrar() {
        return btnEntrar;
    }

    public void setBtnEntrar(Button btnEntrar) {
        this.btnEntrar = btnEntrar;
    }

    @FXML
    private void handleButtonClick(ActionEvent event) {
        if(getUserField().getText().equals("") || getPasswordField().getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Campos Vacios no puede estar vacios");
            alert.showAndWait();
        } else if (getUserField().getText().equals(getPasswordField().getText())) {
            UploadWindow uploadWindow = new UploadWindow();
            try{
                uploadWindow.start(getPrimaryStage(), getUserField().getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        {

        }
    }

}
