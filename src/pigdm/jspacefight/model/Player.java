package pigdm.jspacefight.model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import pigdm.jspacefight.controller.Controller;
import pigdm.jspacefight.utils.ProxyImage;

public class Player extends GameObject {
    private int x, y;
    private int dx, dy;
    private boolean dead;
    private static ProxyImage imageProxy;
    private Bullet bullet;
    private Controller controller;
    private List<Bullet> bullets;

    public Player() {
        if (imageProxy == null)
            imageProxy = new ProxyImage("pigdm/jspacefight/images/ship_1.gif");
        this.setImage(imageProxy.loadImage().getImage());
        this.setHeight(getImage().getHeight(null));
        this.setWidth(getImage().getWidth(null));

        bullets = new ArrayList<Bullet>();
        bullet = new Bullet();
    }

    public void move() {
        x += dx * 2;
        y += dy * 2;
        collisionsPanel();
    }

    public void collisionsPanel() {
        if (this.x < 1) {
            x = 1;
        }
        if (this.x > 462) {
            x = 462;
        }
        if (this.y < 1) {
            y = 1;
        }
        if (this.y > 340) {
            y = 340;
        }
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void fire() {
        Bullet fireBullet = (Bullet) bullet.clone();
        fireBullet.setX(x + getWidth());
        fireBullet.setY(y + getHeight() / 2);
        this.bullets.add(fireBullet);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, getWidth(), getHeight());
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller ctrl) {
        this.controller = ctrl;
    }

}