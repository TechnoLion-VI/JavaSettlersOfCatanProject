import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LandTile extends Tile {
    private boolean isDesert, hasRobber;
    private Edge[] edges = new Edge[6];
    private ArrayList<Player> players;
    private Intersection[] intersections;

    public LandTile(boolean isDesert, ResourceCard type){
        //assignedNum=n;
        super(type, /*insert image here, insert coordinates here*/);
        intersections=new Intersection[6];
        this.isDesert=isDesert;
        hasRobber = isDesert;
        try {
            super.setImg(ImageIO.read(new File("Images/Final " + type.getType() + " Tile.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //figure out how to add the intersections, edges, location, and player arraylist
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
    public void giveResource(){
        ResourceCard resource=super.getResource();
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

    public boolean getIsDesert() {return isDesert;}
    public boolean getHasRobber() {return hasRobber;}
    public void setHasRobber(boolean hR) {hasRobber = hR;}
    public boolean canGive() {
        return !isDesert && !hasRobber;
    }
}