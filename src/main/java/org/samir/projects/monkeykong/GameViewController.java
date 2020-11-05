package org.samir.projects.monkeykong;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameViewController {

    @FXML public Pane gameViewPane;
    private Canvas gameViewLayer;
    private GraphicsContext graphicsContext;
    
    private GameEngine gameEngine;
    private int edgeSize;

    private static AnimationTimer animationTimer = null;
    public static boolean GAME_OVER = true;
    private static Text gameOverText = null;

    public GameViewController(){
        gameViewLayer  = new Canvas(MainViewController.GAME_WINDOW_WIDTH,MainViewController.GAME_WINDOW_HEIGHT);
        graphicsContext = gameViewLayer.getGraphicsContext2D();
        gameEngine = new GameEngine(SettingsViewController.COMPLEXITY);
        edgeSize = gameEngine.edgeSize;
    }

    public void initialize() {
        gameViewPane.getChildren().addAll(gameViewLayer);
        initGame();
        animationTimer = new AnimationTimer() {
            long lastTick = 0;

            @Override
            public void handle(long now) {
                drawFrame();
            }
        };
        animationTimer.start();
    }

    private void drawFrame() {
        if (GAME_OVER) {
            gameOverText = new Text(100, 250,"GAME OVER");
            gameOverText.setFill(Color.RED);
            gameOverText.setFont(new Font("", 50));
            gameOverText.setId("gameOverText");
            //gameViewPane.getChildren().add(gameOverText);
            animationTimer.stop();
            return;
        }
    }

    private void initGame() {
        for (int i = 0; i < gameEngine.xEdgeNumber; i++) {
            for (int j = 0; j < gameEngine.yEdgeNumber; j++){
                switch (gameEngine.DKBoard[i][j]) {
                    case GameEngine.DONKEY_KONG: drawDonkeyKong(i,j);
                        break;
                    case GameEngine.WALL : drawWall(i,j);
                        break;
                    case GameEngine.BANANA : drawBanana(i,j);
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
