package com.jspacefight.model;

import java.awt.Rectangle;

public class Enemy extends GameObject {
    private int x;
    private int y;
    private static final int VELOCITY = 2;

    public Enemy() {
    }

    public static int generatePosX() {
        return generatePosX(100);
    }

    public static int generatePosX(int value) {
        int generateX = 456 + (int) (Math.random() * 16 * value);
        return generateX;
    }

    public static int generatePosY() {
        int generateY = 10 + (int) (Math.random() * 320);
        return generateY;
    }

    public void move(int valueEnemy) {
        if (this.x < 0) {
            this.x = generatePosX(valueEnemy);
            this.y = generatePosY();
        }
        else {
            this.x -= VELOCITY;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, getWidth(), getHeight());
    }

}