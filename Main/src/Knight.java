public class Knight extends DevelopmentCard{
    private String type = "Knight";
    public Knight(){
        super();
    }
    public boolean knightCard(Player p){
        boolean canPlay = isLegal(p);
        if(canPlay == true){
            return true;
        }
        else{
            return false;
        }
    }
}
