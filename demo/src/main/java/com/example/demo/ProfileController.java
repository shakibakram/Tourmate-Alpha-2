package com.example.demo;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    private static Button profilePreview;
    @FXML
    private static Button editProfile;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;

    @FXML
    protected void viewProfile(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Profile_view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void editProfile(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Profile_edit.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private Circle profileImageBorder;
    @FXML
    private Text name; // concatenate FirstName and LastName from DB
    @FXML
    private Text username;
    @FXML
    private Text email;
    @FXML
    private ImageView dashboardIcon;
    @FXML
    private ImageView menu;
    @FXML
    private AnchorPane p1;
    @FXML
    private AnchorPane p2;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image profileImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/avatar.png")));
        profileImageBorder.setFill(new ImagePattern(profileImage));

        p1.setVisible(false);

        FadeTransition ft1 = new FadeTransition(Duration.seconds(0.5), p1);
        ft1.setFromValue(1);
        ft1.setToValue(0);
        ft1.play();

        TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), p2);
        tt1.setByX(-600);
        tt1.play();

        menu.setOnMouseClicked(event -> {
            p1.setVisible(true);

            FadeTransition ft2 = new FadeTransition(Duration.seconds(0.5), p1);
            ft2.setFromValue(0);
            ft2.setToValue(0.15);
            ft2.play();

            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.5), p2);
            tt2.setByX(+600);
            tt2.play();
        });

        p1.setOnMouseClicked(event -> {
            FadeTransition ft3 = new FadeTransition(Duration.seconds(0.5), p1);
            ft3.setFromValue(0.15);
            ft3.setToValue(0);
            ft3.play();

            ft3.setOnFinished(event1 -> {
                p1.setVisible(false);
            });

            TranslateTransition tt3 = new TranslateTransition(Duration.seconds(0.5), p2);
            tt3.setByX(-600);
            tt3.play();
        });
    }
}
