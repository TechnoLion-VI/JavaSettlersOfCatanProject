public class Edge {
    //there will be 72 edges
    private Player owner;
    private Intersection[] intersections;
    int[] point1;
    int[] point2;

    public Edge(int x1, int y1, int x2, int y2, Intersection i1, Intersection i2) {
        owner = null;
        setIntersections(i1, i2);
        point1 = new int[]{x1, y1};
        point2 = new int[]{x2, y2};
    }

    public boolean canPlace(Player p) {
        if (owner != null) return false;
        for (Intersection i:intersections) {
            if (i.getOwner().equals(p)) return true;
            for (Edge e:i.getEdges()) {
                if (e.getOwner().equals(p)) return true;
            }
        }
        return false;
    }

    public boolean hasIntersection() {
        for (Intersection i:intersections) {
            if (i.getOwner() != null) return false;
        }
        return true;
    }

    public int[][] getLocation() {
        return new int[][]{point1, point2};
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
    }
    public void setLocation(int x1, int y1, int x2, int y2) {
        setPoint1(x1, y1); setPoint2(x2, y2);
    }

    public Player getOwner() {
        return owner;
    }
    public void setOwner(Player p) {
        owner = p;
    }

    public Intersection[] getIntersections() {
        return intersections;
    }
    public void setIntersections(Intersection[] i) {
        intersections = i;
    }
    public void setIntersections(Intersection i1, Intersection i2) {
        intersections = new Intersection[]{i1, i2};
    }
}
