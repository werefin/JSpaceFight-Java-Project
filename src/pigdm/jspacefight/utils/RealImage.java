package pigdm.jspacefight.utils;

import javax.swing.ImageIcon;
import java.util.Objects;
import pigdm.jspacefight.interfaces.IImage;

public class RealImage implements IImage {
    private final String path;
    private ImageIcon imageIcon;

    public RealImage(String path) {
        this.path = path;
    }

    @Override
    public ImageIcon loadImage() {
        if (imageIcon == null) {
            imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(path)));
        }
        return imageIcon;
    }

}