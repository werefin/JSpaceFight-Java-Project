package pigdm.jspacefight.interfaces;

import java.awt.event.KeyEvent;
import pigdm.jspacefight.model.Player;

public interface IController {

    void keyPressed(Player player, KeyEvent key);
    void keyReleased(Player player, KeyEvent key);

}