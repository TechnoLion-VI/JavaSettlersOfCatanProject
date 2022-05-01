import java.awt.image.BufferedImage;

public class Intersection {
    private boolean isStlmt, isCity, isHarbor;
    private Player owner;
    private Edge[] edges=new Edge[3];
    private BufferedImage stlmt, city;
    private int[] location;

    public Intersection(int x, int y) {
        location = new int[]{x, y};
        //add the initializing of edges
    }

    public Intersection() {
        this(-1, -1);
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
    //for initial building phase before settlements can only be placed if connected to their own road
    public boolean canPlaceInitial() {
        for (Edge e:edges) if (e.hasBuildings()) return false;
        return true;
    }
    public boolean canPlace() {
        if (!canPlaceInitial()) return false;
        for (Edge e:edges) if (e.getOwner() == owner) return true;
        return false;
    }
    public void setOwner(Player p) {
        owner=p;
        stlmt=p.getStlmtImage();
        city=p.getCityImage();
    }

    public void setIsStlmt(boolean s) {
        isStlmt = s;
        owner.buildStlmt();
    }

    public boolean setIsCity() {
        if (owner == null) return false;
        isCity = true;
        owner.buildCity();
        setIsStlmt(false);
        return true;
    }
}