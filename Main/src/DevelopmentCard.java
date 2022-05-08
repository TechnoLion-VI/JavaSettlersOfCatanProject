import java.awt.image.BufferedImage;

public class DevelopmentCard {
    private String type;
    private BufferedImage img;
    public DevelopmentCard(String s) {
        type=s;
    }

    //to be overridden
    public boolean use() { return false; }
    //type getter
    public String getType() {
        return type;
    }
    //image getter
    public BufferedImage getImage() { return img; }
    //image setter
    public void setImg(BufferedImage b) {
        img=b;
    }
}
