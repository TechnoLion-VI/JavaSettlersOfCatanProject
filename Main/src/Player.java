import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Player {
    private String myColor;
    private int secretScore, publicScore, victoryCards, numStlmtsBuilt, playedKnightCards, roadLength, stlmtsLeft, citiesLeft, roadsLeft;
    private ArrayList<ResourceCard> resourceCards=new ArrayList<>();
    private ArrayList<DevelopmentCard> devCards=new ArrayList<>();
    private boolean hasLargestArmy, hasLongestRoad, hasPlayedDevCard;
    private BufferedImage stlmt, city;
    public static boolean isTurn;

    public Player(String color) {
        myColor=color; //player color
        stlmtsLeft=5; //remaining settlements that this player can build
        citiesLeft=4; //remaining cities that this player can build
        roadsLeft=15; //remaining roads that this player can build
        try {
            stlmt = ImageIO.read(Player.class.getResource("/Images/" + myColor + " Settlement.png")); //settlement image
            city = ImageIO.read(Player.class.getResource("/Images/" + myColor + " City.png")); //city image
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //accessor methods
    public int getNumStlmtsBuilt() {return numStlmtsBuilt;}
    public String getColor() {
        return myColor;
    }
    public String toString(){
        return getColor() + " Player";
    }
    public int getPublicScore() {
        publicScore=numStlmtsBuilt;
        if (hasLargestArmy) {
            publicScore+=2;
        }
        if (hasLongestRoad) {
            publicScore+=2;
        }
        return publicScore;
    }
    public int getSecretScore() {
        secretScore=publicScore+victoryCards;
        return secretScore;
    }
    public ArrayList<ResourceCard> getResourceCards() {
        return resourceCards;
    }
    public ArrayList<DevelopmentCard> getDevCards() {
        return devCards;
    }
    public int getPlayedKnightCards() {
        return playedKnightCards;
    }
    public int getStlmtsLeft() {
        return stlmtsLeft;
    }
    public int getCitiesLeft() {
        return citiesLeft;
    }
    public int getRoadsLeft() {
        return roadsLeft;
    }
    public boolean getHasLargestArmy() {
        return hasLargestArmy;
    }
    public boolean getHasLongestRoad() {
        return hasLongestRoad;
    }
    public boolean canPlayDevCard() { return hasPlayedDevCard; }
    public BufferedImage getStlmtImage() {
        return stlmt;
    }
    public BufferedImage getCityImage() {
        return city;
    }

    //modifier methods
    public void addToVictoryCards() { //maybe this should take a devcard as a parameter
        victoryCards++;
    }
    public void buildStlmt() {
        numStlmtsBuilt++;
        stlmtsLeft--;
    }
    public void buildCity() {
        numStlmtsBuilt++;
        stlmtsLeft++;
        citiesLeft--;
    }
    public void setHasPlayedDevCard(boolean b) {
        hasPlayedDevCard=b;
    }
    public void setHasLargestArmy(boolean b) {
        hasLargestArmy=b;
    }
    public void setHasLongestRoad(boolean b) {
        hasLongestRoad = b;
    }
    public void addToPlayedKnightCards() {
        playedKnightCards++;
    }
    public void decrementRoadsLeft() {roadsLeft--;}
    public void addToRoadLength() {
        roadLength++;
    }
    public void setResourceCards(ArrayList<ResourceCard> newCards) {
        resourceCards=newCards;
    }
    //ArrayList methods
    public void add(ResourceCard rc) {resourceCards.add(rc);}
    public void addToHand(ArrayList<ResourceCard> rc) {resourceCards.addAll(rc);}
    public ResourceCard remove(int index) {return resourceCards.remove(index);}
    public ResourceCard remove(ResourceCard rc) {
        if (resourceCards.remove(rc)) return rc;
        return null;
    }
    public void remove(String type) {
        for (int i=0; i<resourceCards.size(); i++) {
            ResourceCard rc=resourceCards.get(i);
            if (rc.getType().equals(type)) {
                resourceCards.remove(i);
                break;
            }
        }
    }
    public ResourceCard get(int index) {return resourceCards.get(index);}
    public int size() {return resourceCards.size();}
    public void addDev(DevelopmentCard dc) {devCards.add(dc);}
    public DevelopmentCard removeDev(int index) {return devCards.remove(index);}
    public DevelopmentCard removeDev(DevelopmentCard dc) {
        if (devCards.remove(dc)) return dc;
        return null;
    }
    public DevelopmentCard getDev (int index) {return devCards.get(index);}

    //solved Longest Road issues
    public void checkRoadLength() {
        roadLength = 0;
        for (Edge e:Board.getEdges()) {
            roadLength = Math.max(roadLength, Board.checkRoadLength(this, e));
        }
    }
    public int getNumResources(String resource){
        int numResources = 0;
        for(int i = 0; i < resourceCards.size(); i++) {
            if (resourceCards.get(i).getType().equals(resource)) {
                numResources++;
            }
        }
        return numResources;
    }
    public int getRoadLength() { return roadLength; }
}