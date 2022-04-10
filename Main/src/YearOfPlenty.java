public class YearOfPlenty extends DevelopmentCard {
    public YearOfPlenty() {
        super("YearOfPlenty");
    }
    public boolean use(Player p, ResourceCard type1, ResourceCard type2) {
        if (GameState.resourceDeck.getNumLeft(type1)<=0||GameState.resourceDeck.getNumLeft(type2)<=0)
            return false;
        //needs give() in resourceDeck to be coded first
    }
}
