package org.samir.projects.monkeykong;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class DonkeyKongGameApp extends Application {

    public static Scene rootScene;
    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {

        System.out.println(Thread.currentThread().getName() + " is running");

        this.primaryStage = primaryStage;
        Font.loadFont(DonkeyKongGameApp.class.getResource("Gameplay.ttf").toExternalForm(),10);
        Parent parentLayer = loadFXML("mainView");
        rootScene = new Scene(parentLayer);
        primaryStage.setScene(rootScene);
        primaryStage.setTitle("The Donkey Kong Game");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    static void setRoot(String fxmlFile) throws IOException {
        rootScene.setRoot(loadFXML(fxmlFile));
    }

    private static Parent loadFXML(String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DonkeyKongGameApp.class.getResource(fxmlFile + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
            launch();
    }

}