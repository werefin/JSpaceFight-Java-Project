package com.jspacefight.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import com.jspacefight.controller.Controller;
import com.jspacefight.model.Bullet;
import com.jspacefight.model.Enemy;
import com.jspacefight.model.Player;
import com.jspacefight.utils.ProxyImage;

public class GamePanel extends JPanel implements ActionListener {
    private int playerChoice;
    private final Image background;
    private Image image;
    private final Player player;
    private final Timer timer;
    private final Player playerOne;
    private final Player playerTwo;

    private boolean boolP2 = false;
    private boolean playing;
    private boolean begin;
    private boolean isWon;

    private List<Enemy> enemies;

    public GamePanel() {
        this.player = new Player();

        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new KeyBoardAdapter());

        ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("com/jspacefight/images/background.png")));
        this.background = backgroundImage.getImage();

        playerOne = (Player) player.clone();
        playerOne.setX(100);
        playerOne.setY(100);
        playerOne.setController(Controller.PLAYER_1);

        playerTwo = (Player) player.clone();
        playerTwo.setX(100);
        playerTwo.setY(200);
        playerTwo.setController(Controller.PLAYER_2);

        playing = false;
        isWon = false;
        begin = true;

        initEnemy();

        timer = new Timer(5, this);
        timer.start();
    }

    public void checkPlayer() {
        try {
            playerChoice = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            null,
                            "Type 1 to Singleplayer\n" + "Type 2 to Multiplayer\n",
                            "Game Mode", 1));
            if (playerChoice == 2) {
                boolP2 = true;
            }
        }
        catch (NumberFormatException nfe) {
            System.out.println("Error: " + nfe);
            System.exit(0);
        }
    }

    public JMenuBar createMenu() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu help = new JMenu("Help");
        JMenuItem htp = new JMenuItem("How to Play");
        htp.addActionListener((e) -> {
            JOptionPane.showMessageDialog(
                    null,
                    "Player 1:\n"
                            + "Fire - Space\n"
                            + "Up - W\n"
                            + "Down - S\n"
                            + "Left - A\n"
                            + "Right - D\n"
                            + "\n"
                            + "Player 2:\n"
                            + "Fire - Shift\n"
                            + "Up - UP ARROW\n"
                            + "Down - DOWN ARROW\n"
                            + "Left - LEFT ARROW\n"
                            + "Right - RIGHT ARROW\n",
                    "How to Play", JOptionPane.INFORMATION_MESSAGE);
        });
        help.add(htp);
        jMenuBar.add(help);

        return jMenuBar;
    }

    private void initEnemy() {
        enemies = new ArrayList<>();
        Enemy enemy = new Enemy();

        ProxyImage enemyOneImage = new ProxyImage("com/jspacefight/images/enemy_1.gif");
        ProxyImage enemyTwoImage = new ProxyImage("com/jspacefight/images/enemy_2.gif");

        for (int i = 0; i < 100; i++) {
            Enemy clone = (Enemy) enemy.clone();
            clone.setX(Enemy.generatePosX());
            clone.setY(Enemy.generatePosY());
            if (i % 3 == 0) {
                clone.setImage(enemyTwoImage.loadImage().getImage());
            }
            else {
                clone.setImage(enemyOneImage.loadImage().getImage());
            }
            clone.setHeight(clone.getImage().getHeight(null));
            clone.setWidth(clone.getImage().getWidth(null));
            clone.setVisible(true);
            enemies.add(clone);
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawImage(background, 0, 0, null);

        if (playing) {
            if (playerOne.isDead() == false) {
                graphics2D.drawImage(playerOne.getImage(), playerOne.getX(), playerOne.getY(), this);
            }
            if (boolP2 == true) {
                if (playerTwo.isDead() == false) {
                    ImageIcon shipTwoImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("com/jspacefight/images/ship_2.gif")));
                    playerTwo.setImage(shipTwoImage.getImage());
                    graphics2D.drawImage(playerTwo.getImage(), playerTwo.getX(), playerTwo.getY(), this);
                }
            }
            List<Bullet> bulletsP1 = playerOne.getBullets();
            List<Bullet> bulletsP2 = playerTwo.getBullets();

            for (int i = 0; i < bulletsP1.size(); i++) {
                Bullet bulletP1 = (Bullet) bulletsP1.get(i);
                graphics2D.drawImage(bulletP1.getImage(), bulletP1.getX(), bulletP1.getY(), this);
            }
            for (int i = 0; i < bulletsP2.size(); i++) {
                Bullet bulletP2 = (Bullet) bulletsP2.get(i);
                graphics2D.drawImage(bulletP2.getImage(), bulletP2.getX(), bulletP2.getY(), this);
            }
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                graphics2D.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
            }
            graphics2D.setColor(Color.WHITE);
            graphics2D.drawString("Enemies: " + enemies.size(), 5, 15);
        }
        else if (isWon) {
            ImageIcon gameWonImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("com/jspacefight/images/game_won.jpg")));
            graphics2D.drawImage(gameWonImage.getImage(), 0, 0, null);
        }
        else if (begin) {
            ImageIcon mainMenuImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("com/jspacefight/images/main_menu.jpg")));
            image = mainMenuImage.getImage();
            graphics2D.drawImage(image, 0, 0, null);
        }
        else {
            ImageIcon gameOverImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("com/jspacefight/images/game_over.jpg")));
            graphics2D.drawImage(gameOverImage.getImage(), 0, 0, null);
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        try {
            Thread.sleep(5);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (enemies.isEmpty()) {
            playing = false;
            isWon = true;
        }
        List<Bullet> bullets1 = playerOne.getBullets();
        List<Bullet> bullets2 = playerTwo.getBullets();

        for (int i = 0; i < bullets1.size(); i++) {
            Bullet bulletP1 = (Bullet) bullets1.get(i);
            if (bulletP1.isVisible()) {
                bulletP1.move();
            }
            else {
                bullets1.remove(i);
            }
        }
        for (int i = 0; i < bullets2.size(); i++) {
            Bullet bulletP2 = (Bullet) bullets2.get(i);
            if (bulletP2.isVisible()) {
                bulletP2.move();
            }
            else {
                bullets2.remove(i);
            }
        }
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if (enemy.isVisible()) {
                enemy.move(enemies.size());
            }
            else {
                enemies.remove(i);
            }
        }
        playerOne.move();
        playerTwo.move();
        findCollisions();

        if (boolP2 == true) {
            if (playerOne.isDead() && playerTwo.isDead()) {
                playing = false;
            }
        }
        repaint();
    }

    public void findCollisions() {
        Rectangle p1Bounds = playerOne.getBounds();
        Rectangle p2Bounds = playerTwo.getBounds();
        Rectangle rectEnemy;
        Rectangle rectBullet;

        for (int i = 0; i < enemies.size(); i++) {
            Enemy tempEnemy = enemies.get(i);
            rectEnemy = tempEnemy.getBounds();
            if (p1Bounds.intersects(rectEnemy)) {
                playerOne.setVisible(false);
                playerOne.setDead(true);
                if (boolP2 == false) {
                    playing = false;
                }
            }
            if (p2Bounds.intersects(rectEnemy)) {
                playerTwo.setVisible(false);
                playerTwo.setDead(true);
            }
            if (playerOne.isDead() == false && playerTwo.isDead() == false) {
                if (p1Bounds.intersects(p2Bounds)) {
                    playerOne.setDx(0);
                    playerOne.setDy(0);
                }
                if (p2Bounds.intersects(p1Bounds)) {
                    playerTwo.setDx(0);
                    playerTwo.setDy(0);
                }
            }
        }
        List<Bullet> bulletsOne = playerOne.getBullets();
        List<Bullet> bulletsTwo = playerTwo.getBullets();

        for (int i = 0; i < bulletsOne.size(); i++) {
            Bullet tempBullet = bulletsOne.get(i);
            rectBullet = tempBullet.getBounds();

            for (int j = 0; j < enemies.size(); j++) {
                Enemy tempEnemy = enemies.get(j);
                rectEnemy = tempEnemy.getBounds();

                if (rectBullet.intersects(rectEnemy)) {
                    tempEnemy.setVisible(false);
                    tempBullet.setVisible(false);
                }
                if (rectBullet.intersects(p2Bounds)) {
                    tempBullet.setVisible(false);
                }
            }
        }
        for (int i = 0; i < bulletsTwo.size(); i++) {
            Bullet tempBullet = bulletsTwo.get(i);
            rectBullet = tempBullet.getBounds();
            for (int j = 0; j < enemies.size(); j++) {
                Enemy tempEnemy = enemies.get(j);
                rectEnemy = tempEnemy.getBounds();
                if (rectBullet.intersects(rectEnemy)) {
                    tempEnemy.setVisible(false);
                    tempBullet.setVisible(false);
                }
                if (rectBullet.intersects(p1Bounds)) {
                    tempBullet.setVisible(false);
                }
            }
        }
    }

    private class KeyBoardAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (playing == false) {
                    playing = true;
                    playerOne.setDead(false);
                    playerTwo.setDead(false);
                    isWon = false;

                    if (begin == true) {
                        begin = false;
                    }
                    playerOne.setX(100);
                    playerOne.setY(100);
                    playerTwo.setX(100);
                    playerTwo.setY(200);

                    initEnemy();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                playing = false;
            }
            playerOne.getController().keyPressed(playerOne, e);
            if (boolP2) {
                playerTwo.getController().keyPressed(playerTwo, e);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            playerOne.getController().keyReleased(playerOne, e);
            if (boolP2) {
                playerTwo.getController().keyReleased(playerTwo, e);
            }
        }

    } // end class KeyBoardAdapter

} // end class GamePanel