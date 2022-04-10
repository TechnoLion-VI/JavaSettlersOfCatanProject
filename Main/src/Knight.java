public class Knight extends DevelopmentCard{
    private String type = "Knight";
    public Knight(){
        super();
    }
    public boolean knightCard(Player p){
        boolean canPlay = isLegal(p);
        if(canPlay == true){
            p.playedKnightCards++;
            //allow the player to choose where they want to move the robber
            //set object chosenHex to where they chose and make sure it's different from where the robber was at initially
            return true;
        }
        else{
            return false;
        }
    }
}
