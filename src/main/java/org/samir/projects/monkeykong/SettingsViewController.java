package org.samir.projects.monkeykong;

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
    @FXML public Slider complexity;
    @FXML public Label complexityLabel;

    @FXML public GridPane settingsViewPane;

    public static int X_EDGE_NUMBER = 10;
    public static int Y_EDGE_NUMBER = 10;
    public static int COMPLEXITY = 20;
    public static int EDGE_SIZE = 50;

    @FXML public void initialize() {
        xEdgeNumber.valueProperty().addListener((ov, oldValue, newValue) -> xLabel.setText(new DecimalFormat("#").format(newValue)));
        yEdgeNumber.valueProperty().addListener((ov, oldValue, newValue) -> yLabel.setText(new DecimalFormat("#").format(newValue)));
        cornerSize.valueProperty().addListener((ov, oldValue, newValue) -> cornerSizeLabel.setText(new DecimalFormat("#").format(newValue)));
        complexity.valueProperty().addListener((ov,oldValue,newValue) -> complexityLabel.setText(new DecimalFormat("#").format(newValue)));
        extractValues();
    }

    private void extractValues(){
        X_EDGE_NUMBER = (int) xEdgeNumber.getValue();
        Y_EDGE_NUMBER = (int) yEdgeNumber.getValue();
        COMPLEXITY = (int) complexity.getValue();
        EDGE_SIZE = (int) cornerSize.getValue();
    }

    public void onClose() {
        DonkeyKongGameApp.primaryStage.show();
    }

    public void submitSettings(ActionEvent actionEvent) {
        extractValues();
        settingsViewPane.getScene().getWindow().hide();
        DonkeyKongGameApp.primaryStage.show();
    }
}