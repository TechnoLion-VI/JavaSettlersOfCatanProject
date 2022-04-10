import java.awt.image.BufferedImage;

public class Intersection {
    private boolean isStlmt, isCity, isHarbor;
    private Player owner;
    private Edge[] edges=new Edge[3];
    private BufferedImage stlmt, city;
    private int[] location =new int[2];

    public Intersection(int x, int y) {
        location[0]=x;
        location[1]=y;
        //add the initializing of edges
    }
    public int[] getLocation() {
        return location;
    }
    public boolean isStlmt() {
        return isStlmt;
    }
    public boolean isCity() {
        return isCity;
    }
    public boolean isHarbor() {
        return isHarbor;
    }
    public Player getOwner() {
        return owner;
    }
    public Edge[] getEdges() {
        return edges;
    }
    public BufferedImage getImage() {
        if (isCity) {
            return city;
        }
        else if (isStlmt) {
            return stlmt;
        }
        else return null;
    }

    public boolean canPlace() {
        for (Edge e:edges) {
            if (e.hasIntersection())
                return false;
        }
        return true;
    }
    public void setOwner(Player p) {
        owner=p;
        stlmt=p.getStlmtImage();
        city=p.getCityImage();
    }
}
