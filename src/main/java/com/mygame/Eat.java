package com.mygame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Eat {
    private double x, y;
    private double width, height;
    private Color color;

    public Eat(double x, double y, double width, double height, Color color) {
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