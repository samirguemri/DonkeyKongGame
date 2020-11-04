package org.samir.projects.monkeykong;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class GameViewController {

    @FXML public Pane gameViewPane;
    private Canvas gameViewLayer;
    private GraphicsContext graphicsContext;
    
    private GameEngine gameEngine;
    private int edgeSize;

    public GameViewController(){
        gameViewLayer  = new Canvas(SettingsViewController.WINDOW_WIDTH,SettingsViewController.WINDOW_HEIGHT);
        graphicsContext = gameViewLayer.getGraphicsContext2D();
        gameEngine = new GameEngine(50);
        edgeSize = gameEngine.edgeSize;
    }

    public void initialize(){
        gameViewPane.getChildren().addAll(gameViewLayer);
        initGame();
    }

    private void initGame() {
        for (int i = 0; i < gameEngine.xEdgeNumber; i++) {
            for (int j = 0; j < gameEngine.yEdgeNumber; j++){
                switch (gameEngine.DKBoard[i][j]) {
                    case GameEngine.DONKEY_KONG: drawDonkeyKong(j,i);
                        break;
                    case GameEngine.WALL : drawWall(j,i);
                        break;
                    case GameEngine.BANANA : drawBanana(j,i);
                        break;
                }
            }
        }
    }

    private void drawBanana(int xPosition, int yPosition) {
        Image image = new Image(DonkeyKongGameApp.class.getResourceAsStream("image_banana.png"));
        drawElement(graphicsContext, image,xPosition * edgeSize,yPosition * edgeSize);
    }

    private void drawWall(int xPosition, int yPosition) {
        Image image = new Image(DonkeyKongGameApp.class.getResourceAsStream("image_wall.png"));
        drawElement(graphicsContext, image,xPosition * edgeSize,yPosition * edgeSize);
    }

    private void drawDonkeyKong(int xPosition, int yPosition) {
        Image image = new Image(DonkeyKongGameApp.class.getResourceAsStream("image_dkInitial.png"));
        drawElement(graphicsContext, image,xPosition * edgeSize,yPosition * edgeSize);
    }

    private void drawElement(GraphicsContext graphicsContext, Image image, int xPosition, int yPosition){
        graphicsContext.drawImage(image,xPosition,yPosition,gameEngine.edgeSize,gameEngine.edgeSize);
    }

    public void onClose() {
        DonkeyKongGameApp.primaryStage.show();
    }
}
