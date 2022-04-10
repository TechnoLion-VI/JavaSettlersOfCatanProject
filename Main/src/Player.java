import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {
    private Color myColor;
    private int secretScore, publicScore, victoryCards, numStlmtsBuilt, playedKnightCards, roadLength, stlmtsLeft, citiesLeft, roadsLeft;
    private ArrayList<ResourceCard> resourceCards=new ArrayList<>();
    private ArrayList<DevelopmentCard> devCards=new ArrayList<>();
    private boolean hasLargestArmy, hasLongestRoad, hasPlayedDevCard;
    private BufferedImage stlmt, city;

    public Player(Color color, BufferedImage s, BufferedImage c) {
        myColor=color;
        stlmtsLeft=5;
        citiesLeft=4;
        roadsLeft=15;
        stlmt=s;
        city=c;
    }

    //accessor methods
    public Color getColor() {
        return myColor;
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
    public int getRoadLength() {
        return roadLength;
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
    public void addToPlayedKnightCards() {
        playedKnightCards++;
    }
}
