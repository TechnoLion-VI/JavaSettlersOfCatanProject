public class YearOfPlenty extends DevelopmentCard {
    public YearOfPlenty() {
        super("YearOfPlenty");
    }
    public boolean use(ResourceCard type1, ResourceCard type2) {
        if (GameState.resourceDeck.getNumLeft(type1)<=0||GameState.resourceDeck.getNumLeft(type2)<=0)   //checks deck has enough
            return false;
        if (type1==type2 && GameState.resourceDeck.getNumLeft(type1)<=1)    //checks it has enough if both are same type
            return false;
        GameState.currentPlayer.getResourceCards().add(type1);
        GameState.currentPlayer.getResourceCards().add(type2);
        return true;
    }
}
