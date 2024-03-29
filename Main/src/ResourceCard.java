import java.awt.image.BufferedImage;

public class ResourceCard {
    private String type;
    private BufferedImage img;
    public ResourceCard(String type, BufferedImage img) {
        this.type = type;
        this.img = img;
    }
    public ResourceCard(){}
    //type getter
    public String getType() {
        return type;
    }
    //image getter
    public BufferedImage getImg() {
        return img;
    }
    //toString
    public String toString() {return getType();}
}
