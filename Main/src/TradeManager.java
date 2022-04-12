import java.util.ArrayList;

public class TradeManager {

    public TradeManager(){

    }

    public boolean domesticTrade(Player p1, Player p2, ArrayList<ResourceCard> offer1, ArrayList<ResourceCard> offer2){
        ArrayList<ResourceCard> rc1 = p1.getResourceCards();
        ArrayList<ResourceCard> rc2 = p2.getResourceCards();
        //needs to check types of resource cards in the offers
        for (ResourceCard rc: offer1) { //checks player 1 has the resource cards needed
            if (!rc1.contains(rc))
                return false;
            rc1.remove(rc); //removes the card so it makes sure player has the amount needed in case they offer more than 1
        }
        for (ResourceCard rc: offer2) {
            if (!rc2.contains(rc))
                return false;
            rc2.remove(rc);
        }
        return true; //temporary
    }

    public boolean maritimeTrade(ArrayList offer, ResourceCard need){
        return true; //temporary
    }

    public boolean harborTrade(Edge e, ResourceCard need){
        return true; //temporary
    }
}
