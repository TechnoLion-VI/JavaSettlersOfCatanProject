import java.util.ArrayList;

public class TradeManager {

    public TradeManager(){

    }

    public boolean domesticTrade(Player p1, Player p2, ArrayList<ResourceCard> offer1, ArrayList<ResourceCard> offer2){
        for (int i=0; i<offer1.size(); i++) {   //checks players aren't trading the same type of resource
            for (int j=0; j<offer2.size(); j++) {
                if (offer1.get(i).equals(offer2.get(j)))
                    return false;
            }
        }
        ArrayList<ResourceCard> rc1 = p1.getResourceCards();
        ArrayList<ResourceCard> rc2 = p2.getResourceCards();
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
        rc1.addAll(offer2);
        rc2.addAll(offer1);
        p1.setResourceCards(rc1);
        p2.setResourceCards(rc2);
        return true;
    }

    public boolean maritimeTrade(ArrayList<ResourceCard> offer, ResourceCard need){
        if (offer.size()<4) //checks player is trading 4
            return false;
        ArrayList<ResourceCard> rc=GameState.currentPlayer.getResourceCards();
        for (ResourceCard r:offer) {    //checks player has 4 of that type
            if (!rc.contains(r))
                return false;
            rc.remove(r);
        }

        return true; //temporary
    }

    public boolean harborTrade(Edge e, ResourceCard need){
        return true; //temporary
    }
}
