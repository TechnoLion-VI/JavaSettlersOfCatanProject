import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Tile {
    private boolean isDesert, hasRobber;
    private int assignedNum;
    private ResourceCard resource;
    private BufferedImage img;
    private int[] location = new int[2];
    private ArrayList<Player> players;

    // NW = 0, N = 1, NE = 2, SE = 3, S = 4, SW = 5
    private Edge[] edges;
    private Intersection[] intersections;
    private Tile[] adjacentTiles;

    public Tile(ResourceCard r, BufferedImage b, int[] coords) {
        resource=r;
        img=b;
        boolean tileOneExists = adjacentTileOne != null;
        boolean tileTwoExists = adjacentTileTwo != null;

        boolean vertexExists = false;

        int tileOneVertexOrientation = -1;
        int tileTwoVertexOrientation = -1;

        switch (vertexOrientation) {
            case 0: {
                tileOneVertexOrientation = 2;
                tileTwoVertexOrientation = 3;
                break;
            }
            case 1: {
                tileOneVertexOrientation = 4;
                tileTwoVertexOrientation = 5;
                break;
            }
            case 2: {
                tileOneVertexOrientation = 0;
                tileTwoVertexOrientation = 1;
                break;
            }
            case 3: { // fix
                tileOneVertexOrientation = 2;
                tileTwoVertexOrientation = 3;
                break;
            }
            case 4: {
                tileOneVertexOrientation = 0;
                tileTwoVertexOrientation = 5;
                break;
            }
            case 5: {
                tileOneVertexOrientation = 1;
                tileTwoVertexOrientation = 3;
            }
        }

        if (tileOneExists) {
            Intersection temp = adjacentTileOne.getIntersections()[tileTwoVertexOrientation];

            if (temp != null) {
                intersections[vertexOrientation] = temp;

                if (tileTwoExists) {
                    adjacentTileTwo.setIntersection(temp, vertexOrientation);
                }

                vertexExists = true;
            }
        }

        if (!vertexExists) {
            Intersection intersection = new Intersection();
            intersections[vertexOrientation] = intersection;

            if (tileOneExists) {
                adjacentTileOne.setIntersection(intersection, tileOneVertexOrientation);
            }

            if (tileTwoExists) {
                adjacentTileTwo.setIntersection(intersection, tileTwoVertexOrientation);
            }
        }
        location=coords;
        assignedNum=0;

        edges = new Edge[6];
        intersections = new Intersection[6];
        adjacentTiles = new Tile[6];
    }
    public Tile(ResourceCard r) {
        this(r, null, null);
    }
    public int resourcesNeeded() {
        int count=0;
        for(int i = 0; i < players.size(); i++){
            Player p = players.get(i);
            for(int n = 0; n < intersections.length; n++) {
                if (intersections[n].getOwner() == p && intersections[n].isStlmt() == true) {
                    count++;
                }
                else if(intersections[n].getOwner() == p && intersections[n].isCity() == true) {
                    count+=2;
                }
            }
        }
        return count;
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
    public void giveResource(){
        for(int i = 0; i < players.size(); i++){
            Player p = players.get(i);
            ArrayList<ResourceCard> rc = p.getResourceCards();
            for(int n = 0; n < intersections.length; n++) {
                if (intersections[n].getOwner() == p && intersections[n].isStlmt() == true) {
                    rc.add(resource);
                }
                else if(intersections[n].getOwner() == p && intersections[n].isCity() == true) {
                    rc.add(resource);
                    rc.add(resource);
                }
            }
        }
    }

    public void addPlayer(Player p) {
        for (int i = 0; i < intersections.length; i++) {
            if (intersections[i].getOwner() == p) {
                players.add(p);
            }
        }
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
    public ResourceCard getResource() {
        return resource;
    }
    public boolean getIsDesert() {return isDesert;}
    public boolean getHasRobber() {return hasRobber;}
    public void setHasRobber(boolean hR) {hasRobber = hR;}
    public boolean canGive() {
        return !isDesert && !hasRobber;
    }
}
