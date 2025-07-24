package com.mygame;

import javafx.application.Application;
import javafx.stage.Stage;

import com.mygame.GameScene;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GameEngine gameEngine = new GameEngine(primaryStage);
        gameEngine.startGame();
    }
}
