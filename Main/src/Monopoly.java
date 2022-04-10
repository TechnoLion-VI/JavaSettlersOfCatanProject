public class Monopoly extends DevelopmentCard{
    private String type = "monopoly";
    public Monopoly(){
        super();
    }
    public boolean monopoly(Player p){
        boolean canPlay = p.isLegal;
        if(canPlay == true){
            //allow the player to choose which type of resource card they want and put it in resourceCard typeWanted
            ResourceCard typeWanted;
            if(player1.resourceCards)
            return true;
        }
        return false;
    }
}
