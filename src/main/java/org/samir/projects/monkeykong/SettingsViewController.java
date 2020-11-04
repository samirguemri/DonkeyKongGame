package org.samir.projects.monkeykong;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;

import java.text.DecimalFormat;

public class SettingsViewController {

    @FXML public Slider xEdgeNumber;
    @FXML public Label xLabel;
    @FXML public Slider yEdgeNumber;
    @FXML public Label yLabel;
    @FXML public Slider cornerSize;
    @FXML public Label cornerSizeLabel;
    @FXML public GridPane settingsViewPane;

    public static int X_EDGE_NUMBER = 10;
    public static int Y_EDGE_NUMBER = 10;
    public static int EDGE_SIZE = 50;

    public static int WINDOW_WIDTH = X_EDGE_NUMBER * EDGE_SIZE;
    public static int WINDOW_HEIGHT = Y_EDGE_NUMBER * EDGE_SIZE;

    @FXML
    public void initialize() {
        xEdgeNumber.valueProperty().addListener((ov, oldValue, newValue) -> xLabel.setText(new DecimalFormat("#").format(newValue)));
        yEdgeNumber.valueProperty().addListener((ov, oldValue, newValue) -> yLabel.setText(new DecimalFormat("#").format(newValue)));
        cornerSize.valueProperty().addListener((ov, oldValue, newValue) -> cornerSizeLabel.setText(new DecimalFormat("#").format(newValue)));

        getValues();
    }

    private void getValues(){
        X_EDGE_NUMBER = (int) xEdgeNumber.getValue();
        Y_EDGE_NUMBER = (int) yEdgeNumber.getValue();
        EDGE_SIZE = (int) cornerSize.getValue();
    }

    public void onClose() {
        DonkeyKongGameApp.primaryStage.show();
    }

    public void submitSettings(ActionEvent actionEvent) {
        getValues();
        settingsViewPane.getScene().getWindow().hide();
        DonkeyKongGameApp.primaryStage.show();
    }
}