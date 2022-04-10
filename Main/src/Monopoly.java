public class Monopoly extends DevelopmentCard{
    private String type;
    public Monopoly(String t){
        super();
        type = t;
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
