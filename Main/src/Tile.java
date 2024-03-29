import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Tile {
    private boolean isDesert, hasRobber;
    private int assignedNum;
    private ResourceCard resource;
    private BufferedImage img;
    private int[] location = new int[2];
    private int xPixel, yPixel;

    // NW = 0, NE = 1, E = 2, SE = 3, SW = 4, W = 5
    private Edge[] edges;
    private Intersection[] intersections=new Intersection[6];
    private Tile[] adjacentTiles;
    private Tile adjacentTileOne, adjacentTileTwo;

    public Tile(BufferedImage b) {
        img = b;
//        adjacentTileOne = adjacentTiles[0];
//        adjacentTileTwo = adjacentTiles[1];
        assignedNum = 0;
        edges = new Edge[6];
        for (int i=0; i<6; i++) {
//            Intersection temp=new Intersection();
//            intersections[i]=temp;
            Edge e=new Edge();
            edges[i]=e;
        }

        adjacentTiles = new Tile[6];
    }
    public void setAdjacentVertex(int vertexOrientation) {

//        adjacentTileOne = adjacentTiles[0];
//        adjacentTileTwo = adjacentTiles[1];
//        boolean tileOneExists = adjacentTileOne != null;
//        boolean tileTwoExists = adjacentTileTwo != null;
//
//        boolean vertexExists = false;
//
//        int tileOneVertexOrientation = -1;
//        int tileTwoVertexOrientation = -1;
//
//        switch (vertexOrientation) {
//            case 0: {
//                tileOneVertexOrientation = 2;
//                tileTwoVertexOrientation = 3;
//                break;
//            }
//            case 1: {
//                tileOneVertexOrientation = 4;
//                tileTwoVertexOrientation = 5;
//                break;
//            }
//            case 2: {
//                tileOneVertexOrientation = 0;
//                tileTwoVertexOrientation = 1;
//                break;
//            }
//            case 3: { // fix
//                tileOneVertexOrientation = 2;
//                tileTwoVertexOrientation = 3;
//                break;
//            }
//            case 4: {
//                tileOneVertexOrientation = 0;
//                tileTwoVertexOrientation = 5;
//                break;
//            }
//            case 5: {
//                tileOneVertexOrientation = 1;
//                tileTwoVertexOrientation = 3;
//            }
//        }
//
//        if (tileOneExists) {
//            Intersection temp = adjacentTileOne.getIntersections()[tileTwoVertexOrientation];
//
//            if (temp != null) {
//                intersections[vertexOrientation] = temp;
//
//                if (tileTwoExists) {
//                    adjacentTileTwo.setIntersection(temp, vertexOrientation);
//                }
//
//                vertexExists = true;
//            }
//        }
//
//        if (!vertexExists) {
//            Intersection intersection = new Intersection();
//            intersections[vertexOrientation] = intersection;
//
//            if (tileOneExists) {
//                adjacentTileOne.setIntersection(intersection, tileOneVertexOrientation);
//            }
//
//            if (tileTwoExists) {
//                adjacentTileTwo.setIntersection(intersection, tileTwoVertexOrientation);
//            }
//        }
    }
    public void setResource(ResourceCard rc) {
        resource=rc;
    }

    public void setPixel(int x, int y) {
        xPixel=x;
        yPixel=y;
//        intersections[5].setLocation(xPixel, yPixel+36);
//        intersections[0].setLocation(xPixel+55, yPixel);
//        intersections[1].setLocation(xPixel+110, yPixel+36);
//        intersections[2].setLocation(xPixel+110, yPixel+109);
//        intersections[3].setLocation(xPixel+55, yPixel+145);
//        intersections[4].setLocation(xPixel, yPixel+109);
    }
    public int getXPixel() {
        return xPixel;
    }
    public int getYPixel() {
        return yPixel;
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
    public void setEdges(Edge[] e) {
        edges=e;
    }
    public void setIntersection(Intersection intersection, int orientation) {
        intersections[orientation] = intersection;
    }
    public void setIntersections(Intersection i0, Intersection i1, Intersection i2, Intersection i3, Intersection i4, Intersection i5) {
        intersections[0]=i0;
        intersections[1]=i1;
        intersections[2]=i2;
        intersections[3]=i3;
        intersections[4]=i4;
        intersections[5]=i5;
    }
    public Intersection[] getIntersections() {
        return intersections;
    }
    public void setAssignedNum(int num) {assignedNum=num;}
    public int getAssignedNum(){return assignedNum;}
    public int[] getLocation(){return location;}
    public int getX() {return location[0];}
    public int getY() {return location[1];}
    public void setImg(BufferedImage b) {img=b;}
    public BufferedImage getImg() {return img;}
    public ResourceCard getResource() {return resource;}
    public void setIsDesert(boolean b) {isDesert=b;}
    public boolean getIsDesert() {return isDesert;}
    public boolean getHasRobber() {return hasRobber;}
    public void setHasRobber(boolean hR) {hasRobber = hR;}
    public boolean canGive() {return !isDesert && !hasRobber;}

    public int resourcesNeeded() {
        int cnt=0;
        ArrayList<Player> players = new ArrayList<Player>();
        for (Intersection i:intersections) {
            if (i.getOwner() != null) players.add(i.getOwner()); //keep player duplicates, trust me
        }

        for(Player p:players){
            for(Intersection i:intersections) {
                if (i.getOwner() == p && i.isStlmt() == true) {
                    cnt++;
                }
                else if(i.getOwner() == p && i.isCity() == true) {
                    cnt+=2;
                }
            }
        }
        return cnt;
    }

    public void giveResource() {
        ArrayList<Player> players = new ArrayList<Player>();
        for (Intersection i:intersections) {
            if (i.getOwner() != null) players.add(i.getOwner()); //keep player duplicates, trust me
        }

        for (Player p:players) {
            for (Intersection i:intersections) {
                if (i.getOwner() == p && i.isStlmt() == true) {
                    p.add(ResourceDeck.draw(resource));
                } else if (i.getOwner() == p && i.isCity() == true) {
                    p.add(ResourceDeck.draw(resource));
                    p.add(ResourceDeck.draw(resource));
                }
            }
        }
    }

}
