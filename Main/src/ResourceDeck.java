import java.util.ArrayList;
import java.io.IOException;
public class ResourceDeck {
//    public static ArrayList<Brick> brickDeck;
//    public static ArrayList<Grain> grainDeck;
//    public static ArrayList<Lumber> lumberDeck;
//    public static ArrayList<Ore> oreDeck;
//    public static ArrayList<Wool> woolDeck;
    public static ArrayList<ResourceCard> brickDeck, grainDeck, lumberDeck, oreDeck, woolDeck;

    public ResourceDeck() {
        brickDeck = new ArrayList<>();
        grainDeck = new ArrayList<>();
        lumberDeck = new ArrayList<>();
        oreDeck = new ArrayList<>();
        woolDeck = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            try {
                brickDeck.add(new Brick());
                grainDeck.add(new Grain());
                lumberDeck.add(new Lumber());
                oreDeck.add(new Ore());
                woolDeck.add(new Wool());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //adds ResourceCard to Player's hand
    public boolean remove(ResourceCard type, int num) {
        if (getNumLeft(type)<num)
            return false;

        return false; // to be coded
    }

    //get number of cards left in the deck of a specified type
    public int getNumLeft(String type) {
        return switch (type) {
            case "Brick" -> brickDeck.size();
            case "Grain" -> grainDeck.size();
            case "Lumber" -> lumberDeck.size();
            case "Ore" -> oreDeck.size();
            case "Wool" -> woolDeck.size();
            default -> -1;
        };
    }

    public static ArrayList<ResourceCard> getDeck(String s){
        return switch (s) {
            case "Brick" -> brickDeck;
            case "Grain" -> grainDeck;
            case "Lumber" -> lumberDeck;
            case "Ore" -> oreDeck;
            case "Wool" -> woolDeck;
            default -> null;
        }
    }
    //same as above, just with the specified type being a ResourceCard instead of a String
    public int getNumLeft(ResourceCard rc) {
        return getNumLeft(rc.getType());
    }
}
