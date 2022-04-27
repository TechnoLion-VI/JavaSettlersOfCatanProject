import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Player {
    private String myColor;
    private int secretScore, publicScore, victoryCards, numStlmtsBuilt, playedKnightCards, roadLength, stlmtsLeft, citiesLeft, roadsLeft;
    private ArrayList<ResourceCard> resourceCards=new ArrayList<>();
    private ArrayList<DevelopmentCard> devCards=new ArrayList<>();
    private boolean hasLargestArmy, hasLongestRoad, hasPlayedDevCard;
    private BufferedImage stlmt, city;

    public Player(String color) {
        myColor=color; //player color
        stlmtsLeft=5; //remaining settlements that this player can build
        citiesLeft=4; //remaining cities that this player can build
        roadsLeft=15; //remaining roads that this player can build
        try {
            stlmt = ImageIO.read(new File("Images/Final " + myColor + " Settlement.PNG")); //settlement image
            city = ImageIO.read(new File("Images/Final " + myColor + " City.PNG")); //city image
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //accessor methods
    public String getColor() {
        return myColor;
    }
    public String toString(Player p){
        return p.getColor() + " Player";
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
    public ResourceCard get(int index) {return resourceCards.get(index);}
    public int size() {return resourceCards.size();}

    public void checkRoadLength() {
        int maxLen = 0;
        for (Edge e:Board.getEdges()) {
            maxLen = Math.max(maxLen, Board.checkRoadLength(this, e));
        }
        roadLength = maxLen;
    }
    public int getRoadLength() { return roadLength; }
}