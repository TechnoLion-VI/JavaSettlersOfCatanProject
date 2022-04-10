import java.awt.image.BufferedImage;

public class ResourceCard {
    private String type;
    private BufferedImage img;
    public ResourceCard(String type, BufferedImage img) {
        this.type = type; this.img = img;
    }

    public String getType() {
        return type;
    }

    public BufferedImage getImg() {
        return img;
    }
}
