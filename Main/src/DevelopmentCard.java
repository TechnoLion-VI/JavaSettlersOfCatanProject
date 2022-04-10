import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class DevelopmentCard {
    private String type;
    private BufferedImage img;
    public DevelopmentCard(String s, BufferedImage b) {
        type=s;
        img=b;

    }
    public String getType() {
        return type;
    }
    public BufferedImage getImage() { return img; }
}
