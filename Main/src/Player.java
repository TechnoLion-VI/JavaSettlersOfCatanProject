import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {
    private Color myColor;
    private int secretScore, publicScore, victoryCards, numStlmtsBuilt, playedKnightCards, roadLength, stlmtsLeft, citiesLeft, roadsLeft;
    private ArrayList<ResourceCard> resourceCards=new ArrayList<>();
    private ArrayList<DevelopmentCard> devCards=new ArrayList<>();
    private boolean hasLargestArmy, hasLongestRoad, hasPlayedDevCard;

    public Player(Color c) {
        myColor=c;
        stlmtsLeft=5;
        citiesLeft=4;
        roadsLeft=15;
    }

    //accessor methods
    public Color getColor() {
        return myColor;
    }
    public int getPublicScore() {
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

    //modifier methods
    public void addToVictoryCards() { //maybe this should take a devcard as a parameter
        victoryCards++;
    }
    public void addToStlmts() {
        numStlmtsBuilt++;
        publicScore++;
    }
    public void addToPlayedKnightCards() {
        playedKnightCards++;
    }
}