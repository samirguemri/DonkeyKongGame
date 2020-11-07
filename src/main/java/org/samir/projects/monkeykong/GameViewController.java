package org.samir.projects.monkeykong;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameViewController {

    @FXML public Pane gameViewPane;
    private Group gameViewLayer;
    
    private GameEngine gameEngine;
    private int edgeSize;

    private AnimationTimer animationTimer = null;
    private Direction DIRECTION = Direction.DOWN;
    private boolean directionClicked = false;
    private boolean GAME_OVER = false;
    private Text gameOverText = null;
    private ImageView donkeyKong = null;
    private ImageView banana = null;

    public void initialize() {
        gameEngine = new GameEngine(SettingsViewController.COMPLEXITY);
        edgeSize = gameEngine.edgeSize;
        gameViewLayer = new Group();
        gameViewPane.getChildren().addAll(gameViewLayer);

        animationTimer = new AnimationTimer() {
            long lastTick = 0;
            @Override
            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    initGame();
                    return;
                }
                if (now - lastTick > 100000000) {
                    lastTick = now;
                    drawFrame();
                }
            }
        };
        animationTimer.start();
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

    private void drawFrame() {
        if (GAME_OVER) {
            gameViewLayer.setEffect(new GaussianBlur());
            gameOverText = new Text(100, 250,"GAME OVER");
            gameOverText.setFill(Color.RED);
            gameOverText.setFont(Font.font(null, FontWeight.BOLD, 50));
            gameOverText.setId("gameOverText");
            gameViewPane.getChildren().add(gameOverText);
            animationTimer.stop();
            return;
        }

        /* Move DonkeyKong */
        if (directionClicked) {
            int x = gameEngine.dkPosition.getX();
            int y = gameEngine.dkPosition.getY();
            switch (DIRECTION) {
                case UP:
                    gameEngine.dkPosition.setPosition(x,y-1);
                    removeDonkeyKong();
                    drawDonkeyKong(x,y-1);
                    break;
                case DOWN:
                    gameEngine.dkPosition.setPosition(x,y+1);
                    removeDonkeyKong();
                    drawDonkeyKong(x,y+1);
                    break;
                case LEFT:
                    gameEngine.dkPosition.setPosition(x-1,y);
                    removeDonkeyKong();
                    drawDonkeyKong(x-1,y);
                    break;
                case RIGHT:
                    gameEngine.dkPosition.setPosition(x+1,y);
                    removeDonkeyKong();
                    drawDonkeyKong(x+1,y);
                    break;
            }
            directionClicked = false;
        }
    }

    public void keyEventListener(Scene gameScene){
        gameScene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.UP) {
                directionClicked = true;
                DIRECTION = Direction.UP;
            }
            if (key.getCode() == KeyCode.LEFT) {
                directionClicked = true;
                DIRECTION = Direction.LEFT;
            }
            if (key.getCode() == KeyCode.DOWN) {
                directionClicked = true;
                DIRECTION = Direction.DOWN;
            }
            if (key.getCode() == KeyCode.RIGHT) {
                directionClicked = true;
                DIRECTION = Direction.RIGHT;
            }
        });

    }

    private void drawDonkeyKong(int xPosition, int yPosition) {
        Image image = new Image(DonkeyKongGameApp.class.getResourceAsStream("image_dkInitial.png"));
        donkeyKong = new ImageView(image);
        drawElement(donkeyKong,xPosition * edgeSize,yPosition * edgeSize);
    }

    private void drawWall(int xPosition, int yPosition) {
        Image image = new Image(DonkeyKongGameApp.class.getResourceAsStream("image_wall.png"));
        ImageView wall = new ImageView(image);
        drawElement(wall,xPosition * edgeSize,yPosition * edgeSize);
    }

    private void drawBanana(int xPosition, int yPosition) {
        Image image = new Image(DonkeyKongGameApp.class.getResourceAsStream("image_banana.png"));
        banana = new ImageView(image);
        drawElement(banana,xPosition * edgeSize,yPosition * edgeSize);
    }

    private void drawElement(ImageView imageView, int xPosition, int yPosition){
        imageView.setFitWidth(edgeSize);
        imageView.setFitHeight(edgeSize);
        imageView.setX(xPosition);
        imageView.setY(yPosition);
        gameViewLayer.getChildren().add(imageView);
    }

    private void removeDonkeyKong(){
        gameViewLayer.getChildren().remove(donkeyKong);
    }

    public void onClose() {
        DonkeyKongGameApp.primaryStage.show();
    }
}
