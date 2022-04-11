import java.util.ArrayList;

public class TradeManager {

    public TradeManager(){

    }

    public boolean domesticTrade(Player p1, Player p2, ArrayList offer1, ArrayList offer2){
        ArrayList<ResourceCard> rc1 = p1.getResourceCards();
        ArrayList<ResourceCard> rc2 = p2.getResourceCards();
        return true; //temporary
    }

    public boolean maritimeTrade(ArrayList offer, ResourceCard need){
        return true; //temporary
    }

    public boolean harborTrade(Edge e, ResourceCard need){
        return true; //temporary
    }
}
