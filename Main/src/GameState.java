import java.util.ArrayList;
import java.util.HashMap;

public class GameState {
    public static ResourceDeck resourceDeck;
    public static Player currentPlayer;
    private Board board;
    public static int diceNum;
    private Player[] player;
    public static DevelopmentCardDeck devCardDeck;
    private HashMap<Integer, ArrayList<Tile>> resourceDist;

    public GameState(){
        resourceDeck = new ResourceDeck();
        board = new Board();
        player = new Player[4];
        devCardDeck = new DevelopmentCardDeck();
        player[0] = new Player("Blue");
        player[1] = new Player("Green");
        player[2] = new Player("Red");
        player[3] = new Player("Yellow");
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

    public void moveRobber(){

    }
}