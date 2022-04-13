import java.util.*;

public class Board {
    private ArrayList<Integer> tokens = new ArrayList<>();
    private Intersection[] intersections;
    private Edge[] edges;
    private Tile[] tiles;

    //INITIALIZE THE FULL BOARD
    public Board() {
        intersections = new Intersection[54];
        edges = new Edge[72];
        tiles = new Tile[19];
    }

    public void giveResources(int numRolled){
        for(int i = 0; i < tiles.length; i++){
            if(tiles[i].getAssignedNum() == numRolled){
                tiles[i].giveResource();
            }
        }
    }

    public boolean buildRoad(Player p, Edge e){
        if(e.canPlace(p)) {
            p.decrementRoadsLeft();
            e.setOwner(p);
            return true;
        }
        return false;
    }

    public boolean buildSettlement(Player p, Intersection i){
        if (i.canPlace()) {
            p.buildStlmt();
            i.setOwner(p);
            i.setIsStlmt(true);
            return true;
        }
        return false;
    }

    public boolean buildCity(Intersection i){
        return i.setIsCity();
    }




}
