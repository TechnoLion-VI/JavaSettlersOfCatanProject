import java.awt.image.BufferedImage;

public class Tile {
    private int assignedNum;
    private BufferedImage img;
    private int[] location = new int[2];
    private ResourceCard resource;

    public Tile(ResourceCard r, BufferedImage b, int[] coords) {
        resource=r;
        img=b;
        location=coords;
        assignedNum=0;
    }
    public int getAssignedNum(){
        return assignedNum;
    }
    public int[] getLocation(){
        return location;
    }
    public void setImg(BufferedImage b) {
        img=b;
    }
    public BufferedImage getImg() {
        return img;
    }
    public void giveResource() {

    }
    public ResourceCard getResource() {
        return resource;
    }
}
