public class VictoryPoint extends DevelopmentCard {
    public VictoryPoint() {
        super("VictoryPoint");
    }
    public boolean use(Player p) {
        p.addToVictoryCards();
        return true;
    }
}
