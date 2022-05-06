import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GameState {
    public static ResourceDeck resourceDeck = new ResourceDeck();
    public static Player currentPlayer, largestArmyPlayer;
    public static Board board = new Board();
    public static int diceNum;
    public static Player[] players = new Player[]{new Player("Blue"), new Player("Orange"), new Player("Red"), new Player("White")};
    private HashMap<Integer, ArrayList<Tile>> resourceDist;
    private int currentLargestArmySize;

    static {
        currentPlayer=players[0];   //temporary
    }

    public static Intersection getIntersection(int x, int y){
        double minDist = Double.MAX_VALUE;
        double dist;
        int minX=Integer.MAX_VALUE, minY=Integer.MAX_VALUE;
        Intersection min = null;
//        for (Tile[] tiles:GameState.board.getTiles()) {
//            for (Tile tile:tiles) {
//                for (Intersection i:tile.getIntersections()) {
//                    dist = Math.sqrt(Math.pow(x - i.getX(), 2) + Math.pow(y - i.getY(), 2));
//                    if (dist < minDist) {
//                        minDist = dist;
//                        min = i;
//                    }
//                }
//            }
//        }
        for (Intersection i:board.getIntersections()) {
            if (Math.abs(i.getX()-x)<minX && Math.abs(i.getY()-y)<minY) {
                minX=Math.abs(i.getX()-x);
                minY=Math.abs(i.getY()-y);
                min=i;
            }
        }
        return min;
    }

    public static Edge getEdge(int x, int y){
        double minDist = Double.MAX_VALUE;
        double dist;
        int minX=Integer.MAX_VALUE, minY=Integer.MAX_VALUE;
        Edge min = null;
        for (Edge e:board.getEdges()) {
            if (Math.abs(e.getMidpoint()[0]-x)<minX && Math.abs(e.getMidpoint()[1]-y)<minY) {
                minX=Math.abs(e.getMidpoint()[0]-x);
                minY=Math.abs(e.getMidpoint()[1]-y);
                min=e;
            }
        }
        return min;
    }

    public static Tile getTile(int x, int y){
        double minDist = Double.MAX_VALUE;
        double dist;
        int minX=Integer.MAX_VALUE, minY=Integer.MAX_VALUE;
        Tile min = null;
        for (Tile[] tiles:GameState.board.getTiles()) {
            for (Tile tile:tiles) {
//                dist = Math.sqrt(Math.pow(x - tile.getX(), 2) + Math.pow(y - tile.getY(), 2));
//                if (dist < minDist) {
//                    minDist = dist;
//                    min = tile;
//                }
                int centerX=tile.getX()+55;
                int centerY=tile.getY()+73;
                if (Math.abs(centerX-x)<minX && Math.abs(centerY-y)<minY) {
                    minX=Math.abs(centerX-x);
                    minY=Math.abs(centerY-y);
                    min=tile;
                }
            }
        }
        return min;
    }

    public boolean claimWin(){
        if(currentPlayer.getSecretScore() >= 10){
            ActionLogPanel.winClaimed();
            return true;
        }
        return false;
    }

    public static void rollDice(){
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
    public static void setUpPhase() {
        for (int i=0; i<4; i++) {
            currentPlayer=players[i];
            JOptionPane.showMessageDialog(null, currentPlayer+", please build your first settlement and road (done in that order)");
            Intersection stlmt=getIntersection(MainPanel.x, MainPanel.y);
            stlmt.setOwner(currentPlayer);
            stlmt.setIsStlmt(true);
        }
    }
}