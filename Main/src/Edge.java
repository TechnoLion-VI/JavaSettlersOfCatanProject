public class Edge {
    //there will be 72 edges
    private Player owner;
    private Intersection[] intersections;
    int[] point1;
    int[] point2;
    int[] midpoint=new int[2];
    private boolean isHarbor;
    private ResourceCard type;
    private int id;

    public Edge(Intersection i1, Intersection i2, boolean harbor) {
        owner = null;
        setIntersections(i1, i2);
        setLocation(i1.getLocation(), i2.getLocation());
        isHarbor=harbor;
    }

    public Edge(int id) {
        this.id = id;
    }
    public Edge(){}

    //determines if the player can place a road on this edge
    public boolean canPlace(Player p) {
        if (owner != null || p.getRoadsLeft() <= 0) return false;

        for (Intersection i:intersections) {
            if (i.getOwner()!=null && i.getOwner().equals(p))
                return true;
            for (int k=0; k<i.getEdges().length; k++) {
                Edge e=i.getEdges()[k];
                if (e!=null && e.getOwner()!=null && e.getOwner().equals(p)) return true;
            }
        }
        return false;
    }
    //returns true if one of the intersections are owned
    public boolean hasBuildings() {
        for (Intersection i:intersections) {
            if (i.getOwner() != null) return true;
        }
        return false;
    }
    //getter and setter for endpoints and location
    public int[][] getEndpoints() {
        return new int[][]{point1, point2};
    }
    public int[] getMidpoint() {
        return midpoint;
    }
    public int[] getPoint1() {
        return point1;
    }
    public int[] getPoint2() {
        return point2;
    }
    public void setPoint1(int[] p) {
        point1 = p;
    }
    public void setPoint1(int x1, int y1) {
        point1 = new int[]{x1, y1};
    }
    public void setPoint2(int[] p) {
        point2 = p;
    }
    public void setPoint2(int x2, int y2) {
        point2 = new int[]{x2, y2};
    }
    public void setLocation(int[] p1, int[] p2) {
        setPoint1(p1); setPoint2(p2);
        midpoint[0]=(p1[0]+p2[0])/2;
        midpoint[1]=(p1[1]+p2[1])/2;
    }
//    public void setLocation(int x1, int y1, int x2, int y2) {
//        setPoint1(x1, y1); setPoint2(x2, y2);
//    }
    //getter and setter for owner variable
    public Player getOwner() {
        return owner;
    }
    public void setOwner(Player p) {
        owner = p;
    }
    //getter and setter for intersections array
    public Intersection[] getIntersections() {
        return intersections;
    }
    public void setIntersections(Intersection[] i) {
        intersections = i;
    }
    public void setIntersections(Intersection i1, Intersection i2) {
        intersections = new Intersection[]{i1, i2};
        point1=i1.getLocation();
        point2=i2.getLocation();
        midpoint[0]=(point1[0]+point2[0])/2;
        midpoint[1]=(point1[1]+point2[1])/2;
    }
    //accessor for isHarbor
    public boolean isHarbor() {
        return isHarbor;
    }
    //get and set type
    public ResourceCard getType() {
        return type;
    }
    public void setType(ResourceCard rc) {
        type=rc;
    }
}