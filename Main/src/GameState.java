import java.util.ArrayList;
import java.util.HashMap;

public class GameState {
    public static ResourceDeck resourceDeck=new ResourceDeck();
    public static Player currentPlayer;
    private Board board;
    private int diceNum;
    private Player player1, player2, player3, player4;
    public static DevelopmentCardDeck devCardDeck;
    private HashMap<Integer, ArrayList<Tile>> resourceDist;

    public GameState(){

    }

    public void domesticTrade(Player p1, Player p2, ArrayList offer1, ArrayList offer2){

    }

    public void maritimeTrade(ArrayList offer, ResourceCard need){

    }

    public void harborTrade(Edge e, ResourceCard need){

    }

    public void getIntersection(int x, int y){

    }

    public void getEdge(int x, int y){

    }

    public void getTile(int x, int y){

    }

    public void rollDice(){

    }

    public int getDiceNum(){

    }

    public void moveRobber(){

    }
}
