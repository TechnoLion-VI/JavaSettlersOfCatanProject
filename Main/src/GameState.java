import java.util.ArrayList;
import java.util.HashMap;

public class GameState {
    public static ResourceDeck resourceDeck = new ResourceDeck();
    public static Player currentPlayer;
    private static Board board = new Board();
    public static int diceNum;
    private static boolean isTurn;
    private static Player[] players = new Player[]{new Player("Blue"), new Player("Green"), new Player("Red"), new Player("Yellow")};
    public static DevelopmentCardDeck devCardDeck = new DevelopmentCardDeck();
    private HashMap<Integer, ArrayList<Tile>> resourceDist;

    public GameState(){

    }

    public void getIntersection(int x, int y){

    }

    public void getEdge(int x, int y){

    }

    public void getTile(int x, int y){

    }

    public void rollDice(){
        int dice1=(int)(Math.random()*6)+1; //these two lines aren't necessary unless we want to show the dice
        int dice2=(int)(Math.random()*6)+1;
        diceNum=dice1+dice2;
    }

    public static int getDiceNum(){
        return diceNum;
    }

    public static Player[] getPlayers() { return players; }

    public boolean moveRobber(Player p, Tile t){
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
        //player who moves robber can steal one random card from a player of their choice (adjacent to new hex)
        if (p == null || p == GameState.currentPlayer) return true;
        GameState.currentPlayer.add(p.remove((int)(Math.random()*p.getResourceCards().size())));
        return true;
    }
}