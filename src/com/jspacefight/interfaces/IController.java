package com.jspacefight.interfaces;

import java.awt.event.KeyEvent;
import com.jspacefight.model.Player;

public interface IController {

    void keyPressed(Player player, KeyEvent key);
    void keyReleased(Player player, KeyEvent key);

}