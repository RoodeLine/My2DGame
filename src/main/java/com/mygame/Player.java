package com.mygame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import com.mygame.GameScene;

public class Player {
    private double x, y;
    private double width, height;
    private Color color;

    public Player(double x, double y, double width, double height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void update() {

    }

    public void render(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x, y, width, height);
    }

    public void moveLeft() {
        x -= GameScene.CELL_SIZE;
    }

    public void moveRight() {
        x += GameScene.CELL_SIZE;
    }

    public void moveUp() {
        y -= GameScene.CELL_SIZE;
    }

    public void moveDown() {
        y += GameScene.CELL_SIZE;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}