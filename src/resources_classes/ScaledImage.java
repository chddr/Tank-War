package resources_classes;

import java.awt.*;

public class ScaledImage {

    public static final Color darkRed = new Color(172,17,21);
    public static Image create(String path, int width, int height){
        Image backgroundImage = Toolkit.getDefaultToolkit().createImage(path);
        Image scaledImage = backgroundImage.getScaledInstance(width,height,Image.SCALE_DEFAULT);
        return scaledImage;
    }
}
