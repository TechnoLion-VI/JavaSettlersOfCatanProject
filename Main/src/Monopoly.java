public class Monopoly extends DevelopmentCard{
    private String type = "monopoly";
    public Monopoly(){
        super();
    }
    public boolean monopoly(Player p){
        boolean canPlay = isLegal(p);
        if(canPlay == true){
            return true;
        }
        else{
            return false;
        }
    }
}
