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

    public boolean maritimeTrade(ResourceCard offer, ResourceCard need){
        ArrayList<ResourceCard> rc=GameState.currentPlayer.getResourceCards();
        for (int i=0; i<4; i++) {    //checks player has 4 of what they're offering
            if (!rc.contains(offer))
                return false;
            rc.remove(offer);
        }
        rc.add(need);
        GameState.currentPlayer.setResourceCards(rc);
        return true; //temporary
    }

    public boolean harborTrade(Edge e, ResourceCard offer, ResourceCard need){
        if (!e.isHarbor())  //checks it's a harbor
            return false;
//        if (!e.hasBuildings())   //checks it has settlements at either end
//            return false; //not necessary bc of next condition?
        if (e.getIntersections()[0].getOwner()!=GameState.currentPlayer && e.getIntersections()[1].getOwner()!=GameState.currentPlayer) //checks current player owns of the intersections
            return false;
        ArrayList<ResourceCard> playerCards=GameState.currentPlayer.getResourceCards();
        if (e.getType()!=null) {    //if it's true then it means the harbor is a 2:1
            ResourceCard rc=e.getType();
            if (rc!=offer)
                return false;
            for (int i=0; i<2; i++) {   //checks player has 2 of that type
                if (!playerCards.contains(offer))
                    return false;
                playerCards.remove(offer);
            }
            playerCards.add(need);
        }
        else {
            for (int i=0; i<3; i++) {
                if (!playerCards.contains(offer))
                    return false;
                playerCards.remove(offer);
            }
            playerCards.add(need);
        }
        GameState.currentPlayer.setResourceCards(playerCards);
        return true; //temporary
    }
}
