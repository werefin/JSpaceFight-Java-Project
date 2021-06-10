package com.jspacefight.model;

import java.awt.Rectangle;
import com.jspacefight.utils.ProxyImage;

public class Bullet extends GameObject {
    private int x;
    private int y;
    private static ProxyImage imageProxy;
    private static final int WIDTH = 500;
    private static final int VELOCITY = 3;

    public Bullet() {
        if (imageProxy == null) {
            imageProxy = new ProxyImage("com/jspacefight/images/bullet.png");
        }
        this.setImage(imageProxy.loadImage().getImage());
        this.setHeight(getImage().getHeight(null));
        this.setWidth(getImage().getWidth(null));
        this.setVisible(true);
    }

    public void move() {
        this.x += VELOCITY;
        if (this.x > WIDTH) {
            setVisible(false);
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle (x, y, getWidth(), getHeight());
    }

}
