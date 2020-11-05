package org.samir.projects.monkeykong;

import java.io.IOException;
import java.util.Timer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainViewController {

    @FXML public VBox primaryView;
    @FXML public ImageView donkeyKongImageView;

    public static int GAME_WINDOW_WIDTH;
    public static int GAME_WINDOW_HEIGHT;

    @FXML
    public void initialize() {
        donkeyKongImageView.setTranslateX(10);
        donkeyKongImageView.setTranslateY(30);
    }

    public void settings(ActionEvent actionEvent) {
        DonkeyKongGameApp.primaryStage.hide();
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(DonkeyKongGameApp.class.getResource("settingsView.fxml"));
            Parent settingsLayer = fxmlLoader.load();
            SettingsViewController settingsViewController = fxmlLoader.<SettingsViewController>getController();

            Stage settingsStage = new Stage();
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
        GAME_WINDOW_WIDTH = SettingsViewController.X_EDGE_NUMBER * SettingsViewController.EDGE_SIZE;
        GAME_WINDOW_HEIGHT = SettingsViewController.Y_EDGE_NUMBER * SettingsViewController.EDGE_SIZE;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DonkeyKongGameApp.class.getResource("gameView.fxml"));
            Parent gameLayer = fxmlLoader.load();
            GameViewController gameViewController = fxmlLoader.<GameViewController>getController();

            Stage gameStage = new Stage();
            gameStage.setScene(new Scene(gameLayer,GAME_WINDOW_WIDTH,GAME_WINDOW_HEIGHT));
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
