import java.util.ArrayList;
public class Monopoly extends DevelopmentCard{
    public Monopoly() { super("Monopoly"); }
    public boolean use(String rc) {
        //check if card can be played
        //take cards of type rc from all players and add it to player's hand
        Player[] player = GameState.getPlayers();
        ArrayList<ResourceCard> cards = new ArrayList<>();

        if (GameState.currentPlayer == player[0]) {
            ArrayList<ResourceCard> p1 = player[0].getResourceCards();
            for (int i = p1.size() - 1; i >= 0; i--) {
                if (p1.get(i).getType().equals(rc))
                    cards.add(p1.remove(i));
            }
        }
        if (GameState.currentPlayer == player[1]) {
            ArrayList<ResourceCard> p2 = player[1].getResourceCards();
            for (int i = p2.size() - 1; i >= 0; i--) {
                if (p2.get(i).getType().equals(rc))
                    cards.add(p2.remove(i));
            }
        }
        if (GameState.currentPlayer == player[2]) {
            ArrayList<ResourceCard> p3 = player[2].getResourceCards();
            for (int i = p3.size() - 1; i >= 0; i--) {
                if (p3.get(i).getType().equals(rc))
                    cards.add(p3.remove(i));
            }
        }
        if (GameState.currentPlayer == player[3]) {
            ArrayList<ResourceCard> p4 = player[3].getResourceCards();
            for (int i = p4.size() - 1; i >= 0; i--) {
                if (p4.get(i).getType().equals(rc))
                    cards.add(p4.remove(i));
            }
        }
        GameState.currentPlayer.addToHand(cards);
        return true;
    }
}
