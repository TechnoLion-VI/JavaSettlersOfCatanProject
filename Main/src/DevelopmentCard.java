import java.awt.image.BufferedImage;

public class DevelopmentCard {
    private String type;
    private BufferedImage img;
    public DevelopmentCard(String s, BufferedImage b) {
        type=s;
        img=b;
    }

    //type getter
    public String getType() {
        return type;
    }
    //to be overridden by children
    public boolean use(Player p) {
        return false;
    }
    //image getter
    public BufferedImage getImage() { return img; }
}
