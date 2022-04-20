public class Monopoly extends DevelopmentCard{
    public Monopoly() { super("Monopoly"); }
    public boolean use(String rc) {
        //check if card can be played
        //take cards of type rc from all players and add it to player's hand
        return true;
    }
}
