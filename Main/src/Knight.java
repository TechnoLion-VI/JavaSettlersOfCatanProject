public class Knight extends DevelopmentCard{
    private String type = "Knight";
    public Knight(){
        super("knight");
    }
    public void knightCard(Player p){
            p.addToPlayedKnightCards();
            //allow the player to choose where they want to move the robber
            //set object chosenHex to where they chose and make sure it's different from where the robber was at initially
    }
}
