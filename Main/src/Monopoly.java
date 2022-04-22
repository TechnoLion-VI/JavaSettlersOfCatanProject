import java.util.ArrayList;
public class Monopoly extends DevelopmentCard{
    public Monopoly() { super("Monopoly"); }
    public boolean use(String rc) {
        //check if card can be played
        //take cards of type rc from all players and add it to player's hand
        Player[] p = GameState.getPlayers();
        ArrayList<ResourceCard> cards = new ArrayList<>();

        for (int i = p[0].size() - 1; i >= 0; i--) {
            if (p[0].get(i).getType().equals(rc))
                cards.add(p[0].remove(i));
        }

        for (int i = p[1].size() - 1; i >= 0; i--) {
            if (p[1].get(i).getType().equals(rc))
                cards.add(p[1].remove(i));
        }

        for (int i = p[2].size() - 1; i >= 0; i--) {
            if (p[2].get(i).getType().equals(rc))
                cards.add(p[2].remove(i));
        }

        for (int i = p[3].size() - 1; i >= 0; i--) {
            if (p[3].get(i).getType().equals(rc))
                cards.add(p[3].remove(i));
        }

        GameState.currentPlayer.addToHand(cards);
        return true;
    }
}
