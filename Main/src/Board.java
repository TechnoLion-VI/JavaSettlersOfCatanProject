import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

public class Board {
    private ArrayList<Integer> tokens = new ArrayList<>();
    private Intersection[] intersections;
    private Edge[] edges;
    private ArrayList<Tile> tilesList;
    private Tile[][] tiles;
    private BufferedImage brick, grain, lumber, ore, wool;

    //INITIALIZE THE FULL BOARD
    public Board() {
        tilesList=new ArrayList<>();
        tiles=new Tile[5][];
        intersections = new Intersection[54];
        edges = new Edge[72];
        try {
            brick=ImageIO.read(Board.class.getResource("/Images/brick tile.png"));
            grain=ImageIO.read(Board.class.getResource("/Images/grain tile.png"));
            lumber=ImageIO.read(Board.class.getResource("/Images/lumber tile.png"));
            ore=ImageIO.read(Board.class.getResource("/Images/ore tile.png"));
            wool=ImageIO.read(Board.class.getResource("/Images/wool tile.png"));
        }
        catch(Exception e) {
            System.out.println("Board (Tile) Images Error");
            return;
        }
        fillTiles();
        setAdjacentTiles();
        fillEdges();
        fillIntersections();
    }

    public void fillTiles() {
        tiles[0] = new Tile[3];
        tiles[1] = new Tile[4];
        tiles[2] = new Tile[5];
        tiles[3] = new Tile[4];
        tiles[4] = new Tile[3];

        // 1 desert, 3 ore, 3 brick, 4 wheat, 4 lumber, 4 wool
        ArrayList<Tile> tilesArray = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            tilesArray.add(new Tile(new ResourceCard("ore", ore)));
            tilesArray.add(new Tile(new ResourceCard("brick", brick)));
        }

        for (int i = 0; i < 4; i++) {
            tilesArray.add(new Tile(new ResourceCard("grain", grain)));
            tilesArray.add(new Tile(new ResourceCard("lumber", lumber)));
            tilesArray.add(new Tile(new ResourceCard("wool", wool)));
        }

        tilesArray.add(new Tile(new ResourceCard("desert", null)));

        Collections.shuffle(tilesArray);

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[y].length; y++) {
                tiles[x][y] = tilesArray.remove(0);
                tiles[x][y].setLocation(x, y);
            }
        }
    }

    // NW = 0, NE = 1, E = 2, SE = 3, SW = 4, W = 5
    public void setAdjacentTiles() {
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                Tile[] adjacentTiles = new Tile[6];

                switch(x) {
                    case 0: case 1: {
                        try {
                            if (tiles[x - 1][y - 1] != null) {
                                adjacentTiles[0] = tiles[x - 1][y - 1];
                            }

                            if (tiles[x - 1][y] != null) {
                                adjacentTiles[1] = tiles[x - 1][y];
                            }

                            if (tiles[x][y + 1] != null) {
                                adjacentTiles[2] = tiles[x][y + 1];
                            }

                            if (tiles[x + 1][y + 1] != null) {
                                adjacentTiles[3] = tiles[x + 1][y + 1];
                            }

                            if (tiles[x + 1][y] != null) {
                                adjacentTiles[4] = tiles[x + 1][y];
                            }

                            if (tiles[x][y - 1] != null) {
                                adjacentTiles[5] = tiles[x][y - 1];
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            continue;
                        }

                        break;
                    }

                    case 2: {
                        try {
                            if (tiles[x - 1][y - 1] != null) {
                                adjacentTiles[0] = tiles[x - 1][y - 1];
                            }

                            if (tiles[x - 1][y] != null) {
                                adjacentTiles[1] = tiles[x - 1][y];
                            }

                            if (tiles[x][y + 1] != null) {
                                adjacentTiles[2] = tiles[x][y + 1];
                            }

                            if (tiles[x + 1][y] != null) {
                                adjacentTiles[3] = tiles[x + 1][y];
                            }

                            if (tiles[x + 1][y - 1] != null) {
                                adjacentTiles[4] = tiles[x + 1][y - 1];
                            }

                            if (tiles[x][y - 1] != null) {
                                adjacentTiles[5] = tiles[x][y - 1];
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            continue;
                        }

                        break;
                    }

                    case 3: case 4: {
                        try {
                            if (tiles[x - 1][y] != null) {
                                adjacentTiles[0] = tiles[x - 1][y];
                            }

                            if (tiles[x - 1][x + 1] != null) {
                                adjacentTiles[1] = tiles[x - 1][y + 3];
                            }

                            if (tiles[x][y + 1] != null) {
                                adjacentTiles[2] = tiles[x][y + 1];
                            }

                            if (tiles[x + 1][y] != null) {
                                adjacentTiles[3] = tiles[x + 1][y];
                            }

                            if (tiles[x + 1][y - 1] != null) {
                                adjacentTiles[4] = tiles[x + 1][y - 1];
                            }

                            if (tiles[x][y - 1] != null) {
                                adjacentTiles[5] = tiles[x][y - 1];
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            continue;
                        }
                    }
                    break;
                }

                tiles[x][y].setAdjacentTiles(adjacentTiles);
            }
        }
    }

    public void fillEdges() {
        int numOfEdges = 0;

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                Edge[] adjacentEdges = new Edge[6];

                // NW Edge
                Tile tileNW = tiles[x][y].getAdjacentTile(0);
                if (tileNW != null) {
                    if (tileNW.getEdges()[3] != null) {
                        adjacentEdges[0] = tileNW.getEdges()[3];
                    } else {
                        numOfEdges++;
                        adjacentEdges[0] = new Edge(numOfEdges);
                    }
                }

                // N Edge
                Tile tileN = tiles[x][y].getAdjacentTile(1);
                if (tileN != null) {
                    if (tileN.getEdges()[4] != null) {
                        adjacentEdges[1] = tileN.getEdges()[4];
                    } else {
                        numOfEdges++;
                        adjacentEdges[1] = new Edge(numOfEdges);
                    }
                }

                // NE Edge
                Tile tileNE = tiles[x][y].getAdjacentTile(2);
                if (tileNE != null) {
                    if (tileNE.getEdges()[5] != null) {
                        adjacentEdges[2] = tileNE.getEdges()[5];
                    } else {
                        numOfEdges++;
                        adjacentEdges[2] = new Edge(numOfEdges);
                    }
                }

                // SE Edge
                Tile tileSE = tiles[x][y].getAdjacentTile(3);
                if (tileSE != null) {
                    if (tileSE.getEdges()[0] != null) {
                        adjacentEdges[3] = tileSE.getEdges()[0];
                    } else {
                        numOfEdges++;
                        adjacentEdges[3] = new Edge(numOfEdges);
                    }
                }

                // S Edge
                Tile tileS = tiles[x][y].getAdjacentTile(4);
                if (tileS != null) {
                    if (tileS.getEdges()[1] != null) {
                        adjacentEdges[4] = tileS.getEdges()[1];
                    } else {
                        numOfEdges++;
                        adjacentEdges[4] = new Edge(numOfEdges);
                    }
                }

                // SW Edge
                Tile tileSW = tiles[x][y].getAdjacentTile(5);
                if (tileSW != null) {
                    if (tileSW.getEdges()[2] != null) {
                        adjacentEdges[5] = tileSW.getEdges()[1];
                    } else {
                        numOfEdges++;
                        adjacentEdges[5] = new Edge(numOfEdges);
                    }
                }

            }
        }
    }

    public void fillIntersections() {
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                Tile tile = tiles[x][y];

                Tile adjacentTileOne = null;
                Tile adjacentTileTwo = null;

                for (int vertexOrientation = 0; vertexOrientation < 6; vertexOrientation++) {
                    switch (vertexOrientation) {
                        case 0: {
                            adjacentTileOne = tile.getAdjacentTile(0);
                            adjacentTileTwo = tile.getAdjacentTile(1);
                            break;
                        }
                        case 1: {
                            adjacentTileOne = tile.getAdjacentTile(1);
                            adjacentTileTwo = tile.getAdjacentTile(2);
                            break;
                        }
                        case 2: {
                            adjacentTileOne = tile.getAdjacentTile(2);
                            adjacentTileTwo = tile.getAdjacentTile(3);
                            break;
                        }
                        case 3: {
                            adjacentTileOne = tile.getAdjacentTile(3);
                            adjacentTileTwo = tile.getAdjacentTile(4);
                            break;
                        }
                        case 4: {
                            adjacentTileOne = tile.getAdjacentTile(4);
                            adjacentTileTwo = tile.getAdjacentTile(5);
                            break;
                        }
                        case 5: {
                            adjacentTileOne = tile.getAdjacentTile(5);
                            adjacentTileTwo = tile.getAdjacentTile(0);
                            break;
                        }
                    }

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
                }
            }
        }
    }

    public void giveResources(int numRolled){
        for(int r=0; r<tiles.length; r++){
            for (int c=0; c<tiles[r].length; c++)
            if(tiles[r][c].getAssignedNum() == numRolled){
                tiles[r][c].giveResource();
            }
        }
    }

    public static boolean buildRoad(Player p, Edge e){
        if(e.canPlace(p)) {
            p.decrementRoadsLeft();
            e.setOwner(p);
            return true;
        }
        return false;
    }

    public static boolean buildSettlement(Player p, Intersection i){
        if (i.canPlace()) {
            i.setOwner(p);
            i.setIsStlmt(true); //check Intersection class
            return true;    //also needs to increase player's vp
        }
        return false;
    }

    public static boolean buildCity(Intersection i){
        return i.setIsCity();
        //also needs to increase player's vp
    } //check Intersection class
}