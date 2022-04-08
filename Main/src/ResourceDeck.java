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

    public boolean give(int num, ResourceCard rc, Player p) {
        return false; //to be coded
    }

    public int getNumLeft(String type) {
        switch (type) {
            case "Brick" -> return brickDeck.size();
            case "Grain" -> return grainDeck.size();
            case "Lumber" -> return lumberDeck.size();
            case "Ore" -> return oreDeck.size();
            case "Wool" -> return woolDeck.size();
        }
        return -1;
    }
}
