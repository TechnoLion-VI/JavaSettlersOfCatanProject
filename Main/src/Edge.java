public class Edge {
    //there will be 72 edges
    private Player owner;
    private Intersection[] intersections;
    int[] point1;
    int[] point2;

    public Edge(int x1, int y1, int x2, int y2, Intersection a, Intersection b) {
        owner = null;
        intersections = new Intersection[]{a, b};
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

    public Player getOwner() {
        return owner;
    }

    public Intersection[] getIntersections() {
        return intersections;
    }
}
