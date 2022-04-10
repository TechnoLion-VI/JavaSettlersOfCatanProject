import java.util.ArrayList;
public class ResourceDeck {
    private ArrayList<Brick> brickDeck;
    private ArrayList<Grain> grainDeck;
    private ArrayList<Lumber> lumberDeck;
    private ArrayList<Ore> oreDeck;
    private ArrayList<Wool> woolDeck;

    public ResourceDeck() {
        brickDeck = new ArrayList<>();
        grainDeck = new ArrayList<>();
        lumberDeck = new ArrayList<>();
        oreDeck = new ArrayList<>();
        woolDeck = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            brickDeck.add(new Brick());
            grainDeck.add(new Grain());
            lumberDeck.add(new Lumber());
            oreDeck.add(new Ore());
            woolDeck.add(new Wool());
        }
    }
    //adds ResourceCard to Player's hand
    public boolean give(String type, int num, Player p) {
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
    //same as above, just with the specified type being a ResourceCard instead of a String
    public int getNumLeft(ResourceCard rc) {
        return getNumLeft(rc.getType());
    }
}
