import java.awt.image.BufferedImage;

public class Tile {
    private int assignedNum;
    private BufferedImage img;
    private int[] location = new int[2];
    private ResourceCard resource;

    // NW = 0, N = 1, NE = 2, SE = 3, S = 4, SW = 5
    private Edge[] edges;
    private Intersection[] intersections;
    private Tile[] adjacentTiles;

    public Tile(ResourceCard r, BufferedImage b, int[] coords) {
        resource=r;
        img=b;
        location=coords;
        assignedNum=0;

        edges = new Edge[6];
        intersections = new Intersection[6];
        adjacentTiles = new Tile[6];
    }

    public Tile(ResourceCard r) {
        this(r, null, null);
    }

    public void setLocation(int x, int y) {
        this.location = new int[]{x, y};
    }

    public void setAdjacentTiles(Tile[] tiles) {
        adjacentTiles = tiles;
    }

    public Tile getAdjacentTile(int direction) {
        return adjacentTiles[direction];
    }

    public Edge[] getEdges() {
        return edges;
    }

    public void setEdge(Edge edge, int direction) {
        edges[direction] = edge;
    }

    public void setIntersection(Intersection intersection, int orientation) {
        intersections[orientation] = intersection;
    }

    public Intersection[] getIntersections() {
        return intersections;
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
