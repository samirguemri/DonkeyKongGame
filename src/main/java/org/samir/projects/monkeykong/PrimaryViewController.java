package org.samir.projects.monkeykong;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PrimaryViewController {

    @FXML
    public ImageView donkeyKongImageView;

    @FXML
    private void switchToSecondary() throws IOException {
        DonkeyKongGameApp.setRoot("secondary");
    }

    @FXML
    public void initialize() {
        donkeyKongImageView.setTranslateY(30);
    }

    public void settings(ActionEvent actionEvent) {
        try {
            Stage settingsStage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(DonkeyKongGameApp.class.getResource("settingsView.fxml"));
            Parent settingsLayer = (Parent) fxmlLoader.load();

            settingsStage.setScene(new Scene(settingsLayer));
            settingsStage.setTitle("Settings");
            settingsStage.setResizable(false);
            settingsStage.initModality(Modality.APPLICATION_MODAL);
            settingsStage.initStyle(StageStyle.DECORATED);
            settingsStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startGame(ActionEvent actionEvent) {
    }
}
