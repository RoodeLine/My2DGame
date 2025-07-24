package com.mygame;

import javafx.stage.Stage;

import com.mygame.GameScene;

public class GameEngine {
    private Stage primaryStage;
    private GameScene gameScene;

    public GameEngine(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void startGame() {
        gameScene = new GameScene();
        primaryStage.setTitle("My 2D Game");
        primaryStage.setScene(gameScene.getScene());
        primaryStage.setResizable(false);
        primaryStage.show();

        gameScene.startGameLoop();
    }
}
