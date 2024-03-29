import javax.imageio.ImageIO;
import javax.naming.spi.ResolveResult;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Board {
    private ArrayList<Integer> tokens = new ArrayList<>();
    private static Intersection[] intersections;
    private static ArrayList<Edge> edges;
    private ArrayList<Tile> tilesList;
    private Tile[][] tiles;
    private BufferedImage brick, grain, lumber, ore, wool, desert;
    private int[] robberLoc=new int[2];

    //INITIALIZE THE FULL BOARD
    public Board() {
        tilesList=new ArrayList<>();
        tiles=new Tile[5][];
        intersections = new Intersection[54];
        for (int i=0; i< intersections.length; i++) {
            intersections[i]=new Intersection();
        }
        edges = new ArrayList<>();
        try {
            brick=ImageIO.read(Board.class.getResource("/Images/brick tile.png"));
            grain=ImageIO.read(Board.class.getResource("/Images/grain tile.png"));
            lumber=ImageIO.read(Board.class.getResource("/Images/lumber tile.png"));
            ore=ImageIO.read(Board.class.getResource("/Images/ore tile.png"));
            wool=ImageIO.read(Board.class.getResource("/Images/wool tile.png"));
            desert=ImageIO.read(Board.class.getResource("/Images/desert tile.png"));
        }
        catch(Exception e) {
            System.out.println("Reading in Tile Images Error");
            return;
        }
        fillTiles();
        setAdjacentTiles();
        fillIntersections();
        assignTileNums();
        //fillEdges();
    }

    public void fillTiles() {
        tiles[0] = new Tile[3];
        tiles[1] = new Tile[4];
        tiles[2] = new Tile[5];
        tiles[3] = new Tile[4];
        tiles[4] = new Tile[3];

        // 1 desert, 3 ore, 3 brick, 4 wheat, 4 lumber, 4 wool
        ArrayList<Tile> tilesArray = new ArrayList<>();
        ResourceCard brickResource=new ResourceCard(), grainResource=new ResourceCard(), lumberResource=new ResourceCard(), oreResource=new ResourceCard(), woolResource=new ResourceCard();
        try {
            brickResource=new Brick();
            grainResource=new Grain();
            lumberResource=new Lumber();
            oreResource=new Ore();
            woolResource=new Wool();
        }
        catch(Exception e) {
            System.out.println("creating resource cards for tiles error");
        }
        for (int i = 0; i < 3; i++) {
            Tile brickTile=new Tile(brick);
            brickTile.setResource(brickResource);
            Tile oreTile=new Tile(ore);
            oreTile.setResource(oreResource);
            tilesArray.add(brickTile);
            tilesArray.add(oreTile);
        }

        for (int i = 0; i < 4; i++) {
            Tile grainTile=new Tile(grain);
            grainTile.setResource(grainResource);
            Tile lumberTile=new Tile(lumber);
            lumberTile.setResource(lumberResource);
            Tile woolTile=new Tile(wool);
            woolTile.setResource(woolResource);
            tilesArray.add(grainTile);
            tilesArray.add(lumberTile);
            tilesArray.add(woolTile);
        }
        Tile desertTile=new Tile(desert);
        desertTile.setIsDesert(true);
        desertTile.setHasRobber(true);
        tilesArray.add(desertTile);
        Collections.shuffle(tilesArray);

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                Tile temp=tilesArray.remove(0);
                tiles[x][y] = temp;
                tiles[x][y].setLocation(x, y);
                if (temp.getIsDesert()) {
                    robberLoc[0]=x;
                    robberLoc[1]=y;
                }
            }
        }
    }

    // NW = 0, NE = 1, E = 2, SE = 3, SW = 4, W = 5
    public void setAdjacentTiles() {
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                Tile[] adjacentTiles = new Tile[6];

                switch(x) {
                    case 0: {
                        adjacentTiles[0]=null;
                        adjacentTiles[1]=null;
                        adjacentTiles[3] = tiles[x+1][y+1];
                        adjacentTiles[4] = tiles[x+1][y];
                        if (y==0) {
                            adjacentTiles[5]=null;
                        }
                        else {
                            adjacentTiles[5] = tiles[x][y-1];
                        }
                        if (y==2) {
                            adjacentTiles[2] = null;
                        }
                        else {
                            adjacentTiles[2] = tiles[x][y+1];
                        }
                        break;
                    }
                    case 1: {
                        adjacentTiles[3]=tiles[x+1][y+1];
                        adjacentTiles[4]=tiles[x+1][y];
                        if (y==0) {
                            adjacentTiles[0] = null;
                            adjacentTiles[5]=null;
                        }
                        else {
                            adjacentTiles[0]=tiles[x-1][y-1];
                            adjacentTiles[5]=tiles[x][y-1];
                        }
                        if (y==3) {
                            adjacentTiles[1] = null;
                            adjacentTiles[2]=null;
                        }
                        else {
                            adjacentTiles[1]=tiles[x-1][y];
                            adjacentTiles[2]=tiles[x][y+1];
                        }
                        break;
                    }
                    case 2: {
                        if (y==0) {
                            adjacentTiles[0]=null;
                            adjacentTiles[4]=null;
                            adjacentTiles[5]=null;
                        }
                        else {
                            adjacentTiles[0]=tiles[x-1][y-1];
                            adjacentTiles[4]=tiles[x+1][y-1];
                            adjacentTiles[5]=tiles[x][y-1];
                        }
                        if (y==4) {
                            adjacentTiles[1]=null;
                            adjacentTiles[2]=null;
                            adjacentTiles[3]=null;
                        }
                        else {
                            adjacentTiles[1] = tiles[x - 1][y];
                            adjacentTiles[2] = tiles[x][y + 1];
                            adjacentTiles[3] = tiles[x + 1][y];
                        }
                        break;
                    }

                    case 3: {
                        adjacentTiles[0]=tiles[x-1][y];
                        adjacentTiles[1] = tiles[x - 1][y+1];
                        if (y==0) {
                            adjacentTiles[4]=null;
                            adjacentTiles[5]=null;
                        }
                        else {
                            adjacentTiles[4]=tiles[x+1][y-1];
                            adjacentTiles[5]=tiles[x][y-1];
                        }
                        if (y==3) {
                            adjacentTiles[2]=null;
                            adjacentTiles[3]=null;
                        }
                        else {
                            adjacentTiles[2] = tiles[x][y + 1];
                            adjacentTiles[3] = tiles[x + 1][y];
                        }
                        break;
                    }
                    case 4: {
                        adjacentTiles[0]=tiles[x-1][y];
                        adjacentTiles[1] = tiles[x - 1][y+1];
                        adjacentTiles[3]=null;
                        adjacentTiles[4]=null;
                        if (y==0) {
                            adjacentTiles[5]=null;
                        }
                        else {
                            adjacentTiles[5]=tiles[x][y-1];
                        }
                        if (y==2) {
                            adjacentTiles[2]=null;
                        }
                        else {
                            adjacentTiles[2] = tiles[x][y + 1];
                        }
                        break;
                    }
                }

                tiles[x][y].setAdjacentTiles(adjacentTiles);
            }
        }
    }

    public void fillEdges() {
        int numOfEdges = 0;

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                Edge[] adjacentEdges = tiles[x][y].getEdges();
                // NW Edge
                Tile tileNW = tiles[x][y].getAdjacentTile(0);
                if (tileNW != null) {
                    tileNW.getEdges()[3]=adjacentEdges[0];
                }

                // NE Edge
                Tile tileN = tiles[x][y].getAdjacentTile(1);
                if (tileN != null) {
                    tileN.getEdges()[4]=adjacentEdges[1];
                }

                // E Edge
                Tile tileNE = tiles[x][y].getAdjacentTile(2);
                if (tileNE != null) {
                    tileNE.getEdges()[5]=adjacentEdges[2];
                }

                // SE Edge
                Tile tileSE = tiles[x][y].getAdjacentTile(3);
                if (tileSE != null) {
                    tileSE.getEdges()[0]=adjacentEdges[3];
                }

                // SW Edge
                Tile tileS = tiles[x][y].getAdjacentTile(4);
                if (tileS != null) {
                    tileS.getEdges()[1]=adjacentEdges[4];
                }

                // W Edge
                Tile tileSW = tiles[x][y].getAdjacentTile(5);
                if (tileSW != null) {
                    tileSW.getEdges()[2]=adjacentEdges[5];
                }

                adjacentEdges[0].setIntersections(tiles[x][y].getIntersections()[5], tiles[x][y].getIntersections()[0]);
                tiles[x][y].getIntersections()[5].addEdge(adjacentEdges[0]); tiles[x][y].getIntersections()[0].addEdge(adjacentEdges[0]);
                adjacentEdges[1].setIntersections(tiles[x][y].getIntersections()[0], tiles[x][y].getIntersections()[1]);
                tiles[x][y].getIntersections()[0].addEdge(adjacentEdges[1]); tiles[x][y].getIntersections()[1].addEdge(adjacentEdges[1]);
                adjacentEdges[2].setIntersections(tiles[x][y].getIntersections()[1], tiles[x][y].getIntersections()[2]);
                tiles[x][y].getIntersections()[1].addEdge(adjacentEdges[2]); tiles[x][y].getIntersections()[2].addEdge(adjacentEdges[2]);
                adjacentEdges[3].setIntersections(tiles[x][y].getIntersections()[2], tiles[x][y].getIntersections()[3]);
                tiles[x][y].getIntersections()[2].addEdge(adjacentEdges[3]); tiles[x][y].getIntersections()[3].addEdge(adjacentEdges[3]);
                adjacentEdges[4].setIntersections(tiles[x][y].getIntersections()[3], tiles[x][y].getIntersections()[4]);
                tiles[x][y].getIntersections()[3].addEdge(adjacentEdges[4]); tiles[x][y].getIntersections()[4].addEdge(adjacentEdges[4]);
                adjacentEdges[5].setIntersections(tiles[x][y].getIntersections()[4], tiles[x][y].getIntersections()[5]);
                tiles[x][y].getIntersections()[4].addEdge(adjacentEdges[5]); tiles[x][y].getIntersections()[5].addEdge(adjacentEdges[5]);
                tiles[x][y].setEdges(adjacentEdges);
                for (Edge e:adjacentEdges) {
                    if (!edges.contains(e)) {
                        edges.add(e);
                        //System.out.println(e.getMidpoint()[0]+" "+e.getMidpoint()[1]);
                    }
                }
            }
        }
    }

    public void fillIntersections() {
        //hard coding the board (hopefully it works)
        tiles[0][0].setIntersections(intersections[0], intersections[4], intersections[8], intersections[12], intersections[7], intersections[3]);
        tiles[0][1].setIntersections(intersections[1], intersections[5], intersections[9], intersections[13], intersections[8], intersections[4]);
        tiles[0][2].setIntersections(intersections[2], intersections[6], intersections[10], intersections[14], intersections[9], intersections[5]);
        tiles[1][0].setIntersections(intersections[7], intersections[12], intersections[17], intersections[22], intersections[16], intersections[11]);
        tiles[1][1].setIntersections(intersections[8], intersections[13], intersections[18], intersections[23], intersections[17], intersections[12]);
        tiles[1][2].setIntersections(intersections[9], intersections[14], intersections[19], intersections[24], intersections[18], intersections[13]);
        tiles[1][3].setIntersections(intersections[10], intersections[15], intersections[20], intersections[25], intersections[19], intersections[14]);
        tiles[2][0].setIntersections(intersections[16], intersections[22], intersections[28], intersections[33], intersections[27], intersections[21]);
        tiles[2][1].setIntersections(intersections[17], intersections[23], intersections[29], intersections[34], intersections[28], intersections[22]);
        tiles[2][2].setIntersections(intersections[18], intersections[24], intersections[30], intersections[35], intersections[29], intersections[23]);
        tiles[2][3].setIntersections(intersections[19], intersections[25], intersections[31], intersections[36], intersections[30], intersections[24]);
        tiles[2][4].setIntersections(intersections[20], intersections[26], intersections[32], intersections[37], intersections[31], intersections[25]);
        tiles[3][0].setIntersections(intersections[28], intersections[34], intersections[39], intersections[43], intersections[38], intersections[33]);
        tiles[3][1].setIntersections(intersections[29], intersections[35], intersections[40], intersections[44], intersections[39], intersections[34]);
        tiles[3][2].setIntersections(intersections[30], intersections[36], intersections[41], intersections[45], intersections[40], intersections[35]);
        tiles[3][3].setIntersections(intersections[31], intersections[37], intersections[42], intersections[46], intersections[41], intersections[36]);
        tiles[4][0].setIntersections(intersections[39], intersections[44], intersections[48], intersections[51], intersections[47], intersections[43]);
        tiles[4][1].setIntersections(intersections[40], intersections[45], intersections[49], intersections[52], intersections[48], intersections[44]);
        tiles[4][2].setIntersections(intersections[41], intersections[46], intersections[50], intersections[53], intersections[49], intersections[45]);
    }

    public void setTilesIntersectionsLocations() {
        for (Tile[] tileRow: tiles) {
            for (Tile tile: tileRow) {
                int tilex=tile.getXPixel();
                int tiley=tile.getYPixel();
                tile.getIntersections()[5].setLocation(tilex, tiley+36);
                tile.getIntersections()[0].setLocation(tilex+55, tiley);
                tile.getIntersections()[1].setLocation(tilex+110, tiley+36);
                tile.getIntersections()[2].setLocation(tilex+110, tiley+109);
                tile.getIntersections()[3].setLocation(tilex+55, tiley+145);
                tile.getIntersections()[4].setLocation(tilex, tiley+109);
            }
        }
    }

    public void assignTileNums() {
        ArrayList<Integer> numsList=new ArrayList<>();
        //adds nums in a way where numsList.add() isn't repeated x19
        int[] tempArray={5, 2, 6, 3, 8, 10, 9, 12, 11, 4, 8, 10, 9, 4, 5, 6, 3, 11};
        for (int i=0; i< tempArray.length; i++) {
            numsList.add(tempArray[i]);
        }
        //setting values in a CCW spiral
        for (int x=0; x<tiles.length; x++) {
            if (!tiles[x][0].getIsDesert()) {
                tiles[x][0].setAssignedNum(numsList.remove(0));
            }
        }
        for (int y=1; y<tiles[4].length; y++) {
            if (!tiles[4][y].getIsDesert()) {
                tiles[4][y].setAssignedNum(numsList.remove(0));
            }
        }
        for (int x=3; x>-1; x--) {
            if (!tiles[x][tiles[x].length-1].getIsDesert()) {
                tiles[x][tiles[x].length-1].setAssignedNum(numsList.remove(0));
            }
        }
        for (int x=0; x<4; x++) {
            if (!tiles[x][1].getIsDesert()) {
                tiles[x][1].setAssignedNum(numsList.remove(0));
            }
        }
        for (int x=3; x>0; x--) {
            if (!tiles[x][tiles[x].length-2].getIsDesert()) {
                tiles[x][tiles[x].length-2].setAssignedNum(numsList.remove(0));
            }
        }
        if (!tiles[2][2].getIsDesert())
            tiles[2][2].setAssignedNum(numsList.remove(0));
    }


    public void giveResources(int numRolled) {
        for (Tile[] tt:tiles) {
            for (Tile t:tt) {
                if (t.canGive() && t.getAssignedNum() == numRolled && t.getResource() != null && ResourceDeck.canDraw(t.getResource().getType(), t.resourcesNeeded())) {
                    t.giveResource();
                    ResourceDeck.draw(t.getResource());
                }
            }
        }
    }

    public static ArrayList<Edge> getEdges() { return edges; }

    public static Intersection[] getIntersections() { return intersections; }

    public static void setLongestRoad() {
        Player max = null;
        int maxLen = 0;
        for (Player p:GameState.getPlayers()) {
            p.checkRoadLength();
            p.setHasLongestRoad(false);
            if (p.getRoadLength() > maxLen) {
                max = p; maxLen = max.getRoadLength();
            }
        }
        if (max == null) return; // sanity check
        if (maxLen == GameState.currentPlayer.getRoadLength()) {
            if (maxLen >= 5) {
                max.setHasLongestRoad(true);
                ActionLogPanel.longestRoad(max);
            }
        } else {
            for (Player p:GameState.getPlayers()) {
                if (p != max && p.getRoadLength() == maxLen) return;
            }
            if (maxLen >= 5) {
                max.setHasLongestRoad(true);
                ActionLogPanel.longestRoad(max);
            }
        }
    }

    public static boolean buildRoad(Edge e){
        if(e.canPlace(GameState.currentPlayer)) {
            GameState.currentPlayer.decrementRoadsLeft();
            e.setOwner(GameState.currentPlayer);
            setLongestRoad();
            ActionLogPanel.builtRoad();
            return true;
        }
        return false;
    }
    //Please double-check my recursion
    public static int checkRoadLength(Player p, Edge e) {
        if (p == null || e.getOwner() != p) return 0;
        Intersection[] ints = e.getIntersections();
        return 1 + checkRoadLength(ints[0], e, p) + checkRoadLength(ints[1], e, p);
    }
    private static int checkRoadLength(Intersection i, Edge e, Player p) {
        if (i.getOwner() != null && i.getOwner() != p) return 0;
        Edge[] edges = i.getEdges();
        if (edges[0] == e) return Math.max(checkRoadLength(edges[1], i, p), checkRoadLength(edges[2], i, p));
        else if (edges[1] == e) return Math.max(checkRoadLength(edges[0], i, p), checkRoadLength(edges[2], i, p));
        else if (edges[2] == e) return Math.max(checkRoadLength(edges[0], i, p), checkRoadLength(edges[1], i, p));
        return 0; //sanity check
    }
    private static int checkRoadLength(Edge e, Intersection i, Player p) {
        if (e.getOwner() != p) return 0;
        Intersection[] ints = e.getIntersections();
        if (ints[0] == i) return 1 + checkRoadLength(ints[1], e, p);
        return 1 + checkRoadLength(ints[0], e, p);
    }

    public static boolean buildSettlement(Player p, Intersection i){
        if (i.canPlace(p)) {
            i.setOwner(p);
            i.setIsStlmt(true); //check Intersection class
            setLongestRoad();
            ActionLogPanel.builtSettlement();
            p.buildStlmt();
            return true;    //also needs to increase player's vp
        }
        return false;
    }

    public static boolean buildCity(Intersection i){
        ActionLogPanel.builtCity();
        i.setIsCity();
        //also needs to increase player's vp
        i.getOwner().buildCity();
        if (i.isCity())
            return true;
        return false;
    } //check Intersection class

    public Tile[][] getTiles() {
        return tiles;
    }
    public void setRobberLoc(Tile t) {
        robberLoc[0]=t.getLocation()[0];
        robberLoc[1]=t.getLocation()[1];
    }
}
