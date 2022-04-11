import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tile {
    private boolean canGive;
    private int assignedNum;
    private ResourceCard resource;
    private BufferedImage img;
    private Intersection[] intersections = new Intersection[6];
    private Edge[] edges = new Edge[6];
    private int[] location = new int[2];
    private ArrayList<Player> players;

    public Tile(int n, boolean isDesert, ResourceCard type, BufferedImage b){
        assignedNum=n;
        canGive=isDesert;
        resource=type;
        img=b;
        //figure out how to add the intersections, edges, location, and player arraylist
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

    public void addPlayer(Player p){
        for(int i = 0; i < intersections.length; i++){
            if(intersections[i].getOwner() == p){
                players.add(p);
            }
        }
    }

    public int[] getLocation(){
        return location;
    }

    public int getAssignedNum(){
        return assignedNum;
    }

}
