import java.util.ArrayList;
import java.util.HashMap;

public class GameState {
    public static ResourceDeck resourceDeck = new ResourceDeck();
    public static Player currentPlayer, largestArmyPlayer;
    public static Board board = new Board();
    public static int diceNum;
    private static boolean isTurn;
    private static Player[] players = new Player[]{new Player("Blue"), new Player("Green"), new Player("Red"), new Player("Yellow")};
    public static DevelopmentCardDeck devCardDeck = new DevelopmentCardDeck();
    private HashMap<Integer, ArrayList<Tile>> resourceDist;
    private int currentLargestArmySize;

    public GameState(){

    }

    public void getIntersection(int x, int y){

    }

    public void getEdge(int x, int y){

    }

    public void getTile(int x, int y){

    }

    public boolean claimWin(){
        if(currentPlayer.getSecretScore() >= 10){
            ActionLogPanel.winClaimed();
            return true;
        }
        return false;
    }

    public void rollDice(){
        int diceOne = (int)(Math.random() * 6) + 1;
        int diceTwo = (int)(Math.random() * 6) + 1;
        diceNum = diceOne + diceTwo;
        ActionLogPanel.rollDice();
    }
    public static int getDiceNum(){
        return diceNum;
    }

    public static Player[] getPlayers() { return players; }

    public boolean moveRobber(Player p, Tile t){ //p is the player you are stealing from
        if(diceNum == 7){
            for(int i = 0; i < players.length; i++){ //anyone with more than 7 resource cards discards half rounded down (chooses which ones) (not for knight card)
                ArrayList <ResourceCard> rc = players[i].getResourceCards();
                if(rc.size() > 7){
                    //goes in graphics
                }
            }
        }
        //no one gets resources
        //move robber to a new place
        if (t.getHasRobber()) return false; else t.setHasRobber(true);
        //disable hasRobber in old place
        //player who moves robber can steal one random card from a player of their choice (adjacent to new hex)
        if (p == null || p == GameState.currentPlayer) return true;
        GameState.currentPlayer.add(p.remove((int)(Math.random()*p.getResourceCards().size())));
        ActionLogPanel.robber();
        return true;
    }
    public void checkLargestArmyPlayer() {
        int playerindex=-1, size=3;
        for (int i=0; i<players.length; i++) {
            if (players[i].getPlayedKnightCards()>=size) {
                playerindex=i;
                size=players[i].getPlayedKnightCards();
            }
        }

        if (currentLargestArmySize<size && !largestArmyPlayer.equals(players[playerindex])) {
            largestArmyPlayer.setHasLargestArmy(false);
            players[playerindex].setHasLargestArmy(true);
            largestArmyPlayer=players[playerindex];
            currentLargestArmySize=size;
        }
        ActionLogPanel.largestArmy();
    }
}