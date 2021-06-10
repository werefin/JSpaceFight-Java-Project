package pigdm.jspacefight.view;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (ClassNotFoundException | UnsupportedLookAndFeelException |
                InstantiationException | IllegalAccessException  e) {
            e.printStackTrace();
        }
        javax.swing.SwingUtilities.invokeLater(GameWindow :: new);
    }

}
