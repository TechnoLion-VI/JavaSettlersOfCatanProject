import java.util.*;

public class Board {
    private ArrayList<Integer> tokens = new ArrayList<>();
    private Intersection[] intersections = new Intersection[54];
    private Edge[] edges = new Edge[72];
    private Tile[] tiles = new Tile[19];


    public void giveResources(int numRolled){
        for(int i = 0; i < tiles.length; i++){
            if(tiles[i].getAssignedNum() == numRolled){
                tiles[i].giveResource();
            }
        }
    }

    public void buildRoad(Player p, Edge e){
        if(e.canPlace(p)) {
            int r = p.getRoadsLeft();
            r--;
            ArrayList<ResourceCard> rc = p.getResourceCards();
        }
    }

    public void buildSettlement(Player p, Intersection i){

    }

    public void buildCity(Intersection i){

    }




}
