import java.util.*;

public class Board {
    private ArrayList<Integer> tokens = new ArrayList<>();
    private Intersection[] intersections;
    private Edge[] edges;
    private Tile[] tiles;

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

    public void buildRoad(Player p, Edge e){
        if(e.canPlace(p)) {
            p.decrementRoadsLeft();
            e.setOwner(p);
        }
    }

    public void buildSettlement(Player p, Intersection i){
        if (i.canPlace()) {
            p.decrementStlmtsLeft();
            i.setOwner(p);
            i.setStlmt(true);
        }
    }

    public void buildCity(Intersection i){
        i.setCity();
    }




}
