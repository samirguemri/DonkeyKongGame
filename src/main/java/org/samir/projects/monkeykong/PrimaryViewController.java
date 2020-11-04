package org.samir.projects.monkeykong;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PrimaryViewController {

    @FXML public VBox primaryView;
    @FXML public ImageView donkeyKongImageView;

    @FXML
    public void initialize() {
        donkeyKongImageView.setTranslateX(10);
        donkeyKongImageView.setTranslateY(30);
    }

    public void settings(ActionEvent actionEvent) {
        DonkeyKongGameApp.primaryStage.hide();
        try {
            Stage settingsStage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(DonkeyKongGameApp.class.getResource("settingsView.fxml"));
            Parent settingsLayer = fxmlLoader.load();
            SettingsViewController settingsViewController = fxmlLoader.<SettingsViewController>getController();

            settingsStage.setScene(new Scene(settingsLayer));
            settingsStage.setTitle("Settings");
            settingsStage.setResizable(false);
            settingsStage.initModality(Modality.APPLICATION_MODAL);
            settingsStage.initStyle(StageStyle.DECORATED);
            settingsStage.setOnCloseRequest(e -> settingsViewController.onClose());
            settingsStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startGame(ActionEvent actionEvent) {
        DonkeyKongGameApp.primaryStage.hide();
        try {
            Stage gameStage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(DonkeyKongGameApp.class.getResource("gameView.fxml"));
            Parent gameLayer = fxmlLoader.load();
            GameViewController gameViewController = fxmlLoader.<GameViewController>getController();

            gameStage.setScene(new Scene(gameLayer,SettingsViewController.WINDOW_WIDTH,SettingsViewController.WINDOW_HEIGHT));
            gameStage.setTitle("Donkey Kong");
            gameStage.setResizable(false);
            gameStage.initModality(Modality.APPLICATION_MODAL);
            gameStage.initStyle(StageStyle.DECORATED);
            gameStage.setOnCloseRequest(e -> gameViewController.onClose());
            gameStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exitGame(ActionEvent actionEvent) {
        Platform.exit();
    }
}
