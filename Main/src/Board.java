import java.util.ArrayList;

public class Board {
    private ArrayList<Integer> tokens = new ArrayList<>();
    private Intersection[] intersections;
    private Edge[] edges;
    private ArrayList<Tile> tilesList;
    private Tile[][] tiles;

    //INITIALIZE THE FULL BOARD
    public Board() {
        tilesList=new ArrayList<>();
        tiles=new Tile[7][7];
        intersections = new Intersection[54];
        edges = new Edge[72];
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
            return true;
        }
        return false;
    }

    public static boolean buildCity(Intersection i){
        return i.setIsCity();
    } //check Intersection class




}