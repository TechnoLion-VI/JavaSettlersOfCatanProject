import java.util.ArrayList;

public class DevelopmentCardDeck {
    private ArrayList<DevelopmentCard> deck=new ArrayList<>();

    public DevelopmentCardDeck() {

    }
    public DevelopmentCard draw() {
        return deck.remove(0);
    }
}
