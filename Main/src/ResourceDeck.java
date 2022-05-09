import java.util.ArrayList;
import java.io.IOException;
public class ResourceDeck {
//    public static ArrayList<Brick> brickDeck;
//    public static ArrayList<Grain> grainDeck;
//    public static ArrayList<Lumber> lumberDeck;
//    public static ArrayList<Ore> oreDeck;
//    public static ArrayList<Wool> woolDeck;
    public static ArrayList<ResourceCard> brickDeck, grainDeck, lumberDeck, oreDeck, woolDeck;

    static {
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
    //need to code resourcesNeeded in Board Class
    public static boolean canDraw(String type, int resourcesNeeded) {
        if (getNumLeft(type)<resourcesNeeded)
            return false;
        return true;
    }

    //draws card from indicated deck
    public static ResourceCard draw(String type) {
        return switch (type) {
            case "Brick" -> brickDeck.remove(0);
            case "Grain" -> grainDeck.remove(0);
            case "Lumber" -> lumberDeck.remove(0);
            case "Ore" -> oreDeck.remove(0);
            case "Wool" -> woolDeck.remove(0);
            default -> null;
        };
    }

    public static void add(String type) {
        try {
            switch (type) {
                case "Brick" -> brickDeck.add(new Brick());
                case "Grain" -> grainDeck.add(new Grain());
                case "Lumber" -> lumberDeck.add(new Lumber());
                case "Ore" -> oreDeck.add(new Ore());
                case "Wool" -> woolDeck.add(new Wool());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    //get number of cards left in the deck of a specified type
    public static int getNumLeft(String type) {
        return switch (type) {
            case "Brick" -> brickDeck.size();
            case "Grain" -> grainDeck.size();
            case "Lumber" -> lumberDeck.size();
            case "Ore" -> oreDeck.size();
            case "Wool" -> woolDeck.size();
            default -> -1;
        };
    }

    public static ArrayList<ResourceCard> getDeck(String type){
        return switch (type) {
            case "Brick" -> brickDeck;
            case "Grain" -> grainDeck;
            case "Lumber" -> lumberDeck;
            case "Ore" -> oreDeck;
            case "Wool" -> woolDeck;
            default -> null;
        };
    }
    //same as above, just with the specified type being a ResourceCard instead of a String; overloaded getDeck
    public static ResourceCard draw(ResourceCard rc) {return draw(rc.getType());}
    public static int getNumLeft(ResourceCard rc) {
        return getNumLeft(rc.getType());
    }
    public static ArrayList<ResourceCard> getDeck(ResourceCard rc) { return getDeck(rc.getType());}
}
