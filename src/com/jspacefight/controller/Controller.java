package com.jspacefight.controller;

import java.awt.event.KeyEvent;
import com.jspacefight.interfaces.IController;
import com.jspacefight.model.Player;
import static com.jspacefight.controller.ShortcutKeyEvent.*;

public enum Controller implements IController {

    PLAYER_1 {
        @Override
        public void keyPressed(Player player, KeyEvent key) {
            int keyCode = key.getKeyCode();
            if (!player.isDead()) {
                switch (keyCode) {
                    case PLAYER_ONE_FIRE:
                        player.fire();
                        break;
                    case PLAYER_ONE_MOVE_UP:
                        player.setDy(-1);
                        break;
                    case PLAYER_ONE_MOVE_DOWN:
                        player.setDy(1);
                        break;
                    case PLAYER_ONE_MOVE_LEFT:
                        player.setDx(-1);
                        break;
                    case PLAYER_ONE_MOVE_RIGHT:
                        player.setDx(1);
                        break;
                }
            }
        }

        @Override
        public void keyReleased(Player player, KeyEvent key) {
            int keyCode = key.getKeyCode();
            if (!player.isDead()) {
                switch (keyCode) {
                    case PLAYER_ONE_MOVE_UP:
                        player.setDy(0);
                        break;
                    case PLAYER_ONE_MOVE_DOWN:
                        player.setDy(0);
                        break;
                    case PLAYER_ONE_MOVE_LEFT:
                        player.setDx(0);
                        break;
                    case PLAYER_ONE_MOVE_RIGHT:
                        player.setDx(0);
                        break;
                }
            }
        }
    },

    PLAYER_2 {
        @Override
        public void keyPressed(Player player, KeyEvent key) {
            int keyCode = key.getKeyCode();
            if (!player.isDead()) {
                switch (keyCode) {
                    case PLAYER_TWO_FIRE:
                        player.fire();
                        break;
                    case PLAYER_TWO_MOVE_UP:
                        player.setDy(-1);
                        break;
                    case PLAYER_TWO_MOVE_DOWN:
                        player.setDy(1);
                        break;
                    case PLAYER_TWO_MOVE_LEFT:
                        player.setDx(-1);
                        break;
                    case PLAYER_TWO_MOVE_RIGHT:
                        player.setDx(1);
                        break;
                }
            }
        }

        @Override
        public void keyReleased(Player player, KeyEvent key) {
            int keyCode = key.getKeyCode();
            if (!player.isDead()) {
                switch (keyCode) {
                    case PLAYER_TWO_MOVE_UP:
                        player.setDy(0);
                        break;
                    case PLAYER_TWO_MOVE_DOWN:
                        player.setDy(0);
                        break;
                    case PLAYER_TWO_MOVE_LEFT:
                        player.setDx(0);
                        break;
                    case PLAYER_TWO_MOVE_RIGHT:
                        player.setDx(0);
                        break;
                }
            }
        }
    }

}