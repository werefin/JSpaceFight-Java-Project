package com.jspacefight.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameWindow extends JFrame {
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 420;

    public GameWindow() {
        super("GameWindow");
        JFrame jFrame = new JFrame("JSpaceFight");
        GamePanel gamePanel = new GamePanel();
        gamePanel.checkPlayer();
        jFrame.add(gamePanel);
        jFrame.setJMenuBar(gamePanel.createMenu());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        try {
            URL url = getClass().getClassLoader().getResource("com/jspacefight/images/game_icon.png");
            assert url != null;
            BufferedImage image = ImageIO.read(url);
            jFrame.setIconImage(image);
        }
        catch (IOException ioe) {
            System.out.println("ImageError: " + ioe);
        }
        jFrame.setVisible(true);
    }

}