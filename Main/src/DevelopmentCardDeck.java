import java.util.ArrayList;

public class DevelopmentCardDeck {
    private ArrayList<DevelopmentCard> deck;

    public DevelopmentCardDeck() {
        deck = new ArrayList<>();
        for (int i = 0; i < 14; i++) deck.add(new Knight());
        for (int i = 0; i < 5; i++) deck.add(new VictoryPoint());
        for (int i = 0; i < 2; i++) {
            deck.add(new Monopoly());
            deck.add(new YearOfPlenty());
            deck.add(new RoadBuilding());
        }
    }
    public DevelopmentCard draw() {
        return deck.remove(0);
    }
}
