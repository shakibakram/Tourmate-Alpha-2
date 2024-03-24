package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RegController {
    @FXML
    private Button signUp;
    @FXML
    private Label errorMessage;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField username;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private CheckBox checkBox;
    @FXML
    private Hyperlink loginLink;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;

    @FXML
    private void loginPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Login_view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void signup(ActionEvent event) throws SQLException, ClassNotFoundException {

        if(firstName.getText().isBlank() == false && lastName.getText().isBlank() == false && email.getText().isBlank() == false && phone.getText().isBlank() == false && username.getText().isBlank() == false && passwordField.getText().isBlank() == false && confirmPasswordField.getText().isBlank() == false){
            // checks DB for valid credentials
            // failedLoginMessage.setText("Login attempt made."); // temporary placeholder
            validatesingup();

        } else{
            errorMessage.setText("Please, Fill-up the form");
        }

    }
    private void validatesingup(){
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDB = connectnow.getConnection();
        String registration = "INSERT INTO registration (`UserName`, `emailID`, `phone`, `Pass_word`, `first_name`, `last_name`,`confirm_pass`) VALUES ('" + username.getText() + "','" + email.getText() + "','" + phone.getText() + "','" + passwordField.getText() + "','" + firstName.getText() + "','" + lastName.getText() + "', '" + confirmPasswordField.getText() + "')";
        Statement statement = null;
        try {
            statement = connectDB.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.execute(registration);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("You Successfully added register. Now Go back to LogIn");
        alert.setTitle("Successful");
        alert.showAndWait();
    }

}


