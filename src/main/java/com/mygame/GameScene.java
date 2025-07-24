package com.mygame;

import java.util.HashSet;
import java.util.Set;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameScene {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public static final int CELL_SIZE = 20;

    private Pane root;
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;

    private Set<KeyCode> activeKeys = new HashSet<>();
    private AnimationTimer gameLoop;

    private Player player;
    private Eat eat;

    private int score = 0;
    private Text scoreText;

    private long startTime;
    private Text timerText;

    private boolean moved = false;

    public GameScene() {
        root = new Pane();
        scene = new Scene(root, WIDTH, HEIGHT);
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);

        setupInputHandlers();
        initGameObjects();

        scoreText = new Text(10, 20, "Score: 0");
        scoreText.setFont(Font.font(20));
        scoreText.setFill(Color.BLACK);
        root.getChildren().add(scoreText);

        timerText = new Text(10, 50, "Time: 0");
        timerText.setFont(Font.font(20));
        timerText.setFill(Color.BLACK);
        root.getChildren().add(timerText);

    }

    private void updateScoreText() {
        scoreText.setText("Score: " + score);
    }

    private void setupInputHandlers() {
        scene.setOnKeyPressed(event -> {
            activeKeys.add(event.getCode());
            moved = false;
        });

        scene.setOnKeyReleased(event -> activeKeys.remove(event.getCode()));
    }

    private void initGameObjects() {
        player = new Player(0, 0, CELL_SIZE, CELL_SIZE, Color.BLUE);
        eat = new Eat(randomCell(WIDTH), randomCell(HEIGHT), CELL_SIZE, CELL_SIZE, Color.RED);

    }

    public void startGameLoop() {
        startTime = System.nanoTime();
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
            }
        };
        gameLoop.start();
    }

    private void update() {
        handleInput();
        player.update();
        eat.update();
        scoreUpdate();
        updateTimer();
    }

    private int randomCell(int max) {
        Random r = new Random();
        return r.nextInt(max / CELL_SIZE) * CELL_SIZE;
    }

    private void updateTimer() {
        long now = System.nanoTime();
        long elapsedSeconds = (now - startTime) / 1_000_000_000;
        timerText.setText("Time: " + elapsedSeconds + "s");
    }

    private void scoreUpdate() {
        if (player.getX() == eat.getX() && player.getY() == eat.getY()) {
            score++;
            updateScoreText();
            eat = new Eat(randomCell(WIDTH), randomCell(HEIGHT), CELL_SIZE, CELL_SIZE, Color.RED);
        }
    }

    private void handleInput() {
        if (!moved) {
            if (activeKeys.contains(KeyCode.LEFT) && player.getX() >= GameScene.CELL_SIZE) {
                player.moveLeft();
                moved = true;
            }
            if (activeKeys.contains(KeyCode.RIGHT)
                    && player.getX() + player.getWidth() + GameScene.CELL_SIZE <= WIDTH) {
                player.moveRight();
                moved = true;
            }
            if (activeKeys.contains(KeyCode.UP) && player.getY() >= GameScene.CELL_SIZE) {
                player.moveUp();
                moved = true;
            }
            if (activeKeys.contains(KeyCode.DOWN)
                    && player.getY() + player.getHeight() + GameScene.CELL_SIZE <= HEIGHT) {
                player.moveDown();
                moved = true;
            }
        }
    }

    private void render() {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        player.render(gc);
        eat.render(gc);
    }

    public Scene getScene() {
        return scene;
    }
}
