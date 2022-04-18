import java.util.ArrayList;

public class TradeManager {
    public static String p1offer, p2offer, p1receives, p2receives;
    public TradeManager(){

    }

    public boolean domesticTrade(Player p1, Player p2, ArrayList<ResourceCard> offer1, ArrayList<ResourceCard> offer2){
        p1offer="";p2offer="";p1receives="";p2receives="";
        for (int i=0; i<offer1.size(); i++) {   //checks players aren't trading the same type of resource
            for (int j=0; j<offer2.size(); j++) {
                if (offer1.get(i).equals(offer2.get(j)))
                    return false;
            }
        }
        ArrayList<ResourceCard> rc1 = p1.getResourceCards();
        ArrayList<ResourceCard> rc2 = p2.getResourceCards();
        for (int i=0; i<offer1.size(); i++) { //checks player 1 has the resource cards needed
            ResourceCard rc=offer1.get(i);
            if (!rc1.contains(rc))
                return false;
            rc1.remove(rc);//removes the card so it makes sure player has the amount needed in case they offer more than 1
            if (i!=offer1.size()-1) {
                p1offer+=rc.getType()+", ";
                p2receives+=rc.getType()+", ";
            }
            else {
                p1offer+="and "+rc.getType();
                p2receives+="and "+rc.getType();
            }
        }
        for (int i=0; i<offer2.size(); i++) {
            ResourceCard rc=offer2.get(i);
            if (!rc2.contains(rc))
                return false;
            rc2.remove(rc);
            if (i!=offer2.size()-1) {
                p2offer+=rc.getType()+", ";
                p1receives+=rc.getType()+", ";
            }
            else {
                p2offer+="and "+rc.getType();
                p1receives+="and "+rc.getType();
            }
        }
        rc1.addAll(offer2);
        rc2.addAll(offer1);
        p1.setResourceCards(rc1);
        p2.setResourceCards(rc2);
        return true;
    }

    public boolean maritimeTrade(ResourceCard offer, ResourceCard need){
        p1offer="";p2offer="";p1receives="";p2receives="";
        ArrayList<ResourceCard> rc=GameState.currentPlayer.getResourceCards();
        for (int i=0; i<4; i++) {    //checks player has 4 of what they're offering
            if (!rc.contains(offer))
                return false;
            rc.remove(offer);
        }
        rc.add(need);
        GameState.currentPlayer.setResourceCards(rc);
        p1offer+=offer.getType();
        p1receives+=need.getType();
        return true; //temporary
    }

    public boolean harborTrade(Edge e, ResourceCard offer, ResourceCard need){
        p1offer="";p2offer="";p1receives="";p2receives="";
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
        p1offer+=offer.getType();
        p1receives+=need.getType();
        return true; //temporary
    }

}
