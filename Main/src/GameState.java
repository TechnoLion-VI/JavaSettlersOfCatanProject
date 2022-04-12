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

    public int getDiceNum(){
        return diceNum;
    }

    public void moveRobber(){

    }
}
