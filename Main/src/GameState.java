import com.sun.tools.javac.Main;

import javax.swing.*;
import java.util.ArrayList;

public class GameState {
    public static Player currentPlayer, largestArmyPlayer;
    public static Board board = new Board();
    public static int diceNum, diceOne, diceTwo;
    public static Player[] players = new Player[]{new Player("Blue"), new Player("Orange"), new Player("Red"), new Player("White")};
    private static int currentLargestArmySize;

    static {
        currentPlayer=players[0];   //temporary
    }

    public static Intersection getIntersection(int x, int y){
        double minDist = Double.MAX_VALUE;
        double dist;
        Intersection min = null;
        for (Tile[] tiles:board.getTiles()) {
            for (Tile tile:tiles) {
                for (Intersection i:tile.getIntersections()) {
                    dist = Math.sqrt(Math.pow(x - i.getX(), 2) + Math.pow(y - i.getY(), 2));
                    if (dist < minDist) {
                        minDist = dist;
                        min = i;
                    }
                }
            }
        }
        return min;
    }

    public static Edge getEdge(int x, int y){
        double minDist = Double.MAX_VALUE;
        double dist;
        Edge min = null;
        for (Edge e : board.getEdges()) {
            dist = Math.sqrt(Math.pow(Math.abs(x - e.getMidpoint()[0]), 2) + Math.pow(Math.abs(y - e.getMidpoint()[1]), 2));
            if (dist < minDist) {
                minDist = dist;
                min = e;
            }
        }
        return min;
    }

    public static Tile getTile(int x, int y){
        double minDist = Double.MAX_VALUE;
        double dist;
        Tile min = null;
        for (Tile[] tiles:board.getTiles()) {
            for (Tile tile:tiles) {
                int centerX=tile.getXPixel()+55;
                int centerY=tile.getYPixel()+73;
                dist = Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
                if (dist < minDist) {
                    minDist = dist;
                    min = tile;
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
        diceOne = (int)(Math.random() * 6) + 1;
        diceTwo = (int)(Math.random() * 6) + 1;
        diceNum = diceOne + diceTwo;
        ActionLogPanel.rollDice();
    }
    public static int getDiceNum(){
        return diceNum;
    }

    public static Player[] getPlayers() { return players; }

    public static void moveRobber(){ //p is the player you are stealing from
        Tile t = getTile(MainPanel.x, MainPanel.y);
        //move robber to a new place
        if (t.getHasRobber()) return; else t.setHasRobber(true);
        for (Tile[] tiles: board.getTiles()) {
            for (Tile tile:tiles) {
                if (tile == t) continue;
                if (tile.getHasRobber()) tile.setHasRobber(false);
            }
        }
        ArrayList<Player> options = new ArrayList<>();
        for (Intersection i:t.getIntersections()) {
            if (i.getOwner() != null && i.getOwner() != currentPlayer && !options.contains(i.getOwner())) options.add(i.getOwner()); //keep player duplicates, trust me
        }
        if (options.isEmpty()) {
            MainPanel.action = "";
            return;
        }
        int response = JOptionPane.showOptionDialog(null, "Choose player", "Robber Phase", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options.toArray(), options.toArray()[0]);
        Player p = options.get(response);
        //player who moves robber can steal one random card from a player of their choice (adjacent to new hex)
        currentPlayer.add(p.remove((int)(Math.random()*p.size())));
        MainPanel.action = "";
    }

    public static void checkLargestArmyPlayer() {
        int playerindex=-1, size=3;
        for (int i=0; i<players.length; i++) {
            if (players[i].getPlayedKnightCards()>=size) {
                playerindex=i;
                size=players[i].getPlayedKnightCards();
            }
        }
        //push comment
        if (currentLargestArmySize<size && largestArmyPlayer != null && !largestArmyPlayer.equals(players[playerindex])) {
            largestArmyPlayer.setHasLargestArmy(false);
            players[playerindex].setHasLargestArmy(true);
            largestArmyPlayer=players[playerindex];
            currentLargestArmySize=size;
            ActionLogPanel.largestArmy(players[playerindex]);
        }
    }

    public static void setUpPhase() {
        for (int i=0; i<4; i++) {
            currentPlayer=players[i];
            Intersection stlmt=getIntersection(MainPanel.x, MainPanel.y);
            stlmt.setOwner(currentPlayer);
            stlmt.setIsStlmt(true);
        }

    }
    public static void initBuildSettlement() {
        Intersection i=getIntersection(MainPanel.x, MainPanel.y);
        if (i.canPlaceInitial()) {
            i.setOwner(currentPlayer);
            i.setIsStlmt(true);
            ActionLogPanel.builtSettlement();
            if (MainPanel.state==8) {
                initGiveResource(i);
            }
            if (MainPanel.state==10) {
                initGiveResource(i);
            }
            if (MainPanel.state==12) {
                initGiveResource(i);
            }
            if (MainPanel.state==14) {
                initGiveResource(i);
            }
            MainPanel.state++;
        }
        else System.out.println(currentPlayer.toString() + " was unable to build a settlement.");
    }
    public static void initGiveResource(Intersection i) {
        ArrayList<Tile> connectedTiles=new ArrayList<>();
        for (Tile[] tiles:board.getTiles()) {
            for (Tile t:tiles) {
                for (Intersection tileInt:t.getIntersections()) {
                    if (tileInt.equals(i)) {
                        connectedTiles.add(t);
                    }
                }
            }
        }
        for (Tile t:connectedTiles) {
            if (t.getResource()!=null) {
                currentPlayer.add(t.getResource());
                ResourceDeck.draw(t.getResource());
            }
        }
    }
    public static void buildRoad() {
        Edge e=getEdge(MainPanel.x, MainPanel.y);
        if (e.canPlace(currentPlayer)) {
            e.setOwner(currentPlayer);
            currentPlayer.decrementRoadsLeft();
            ActionLogPanel.builtRoad();
            MainPanel.state++;
            if (MainPanel.state==2 ) {
                currentPlayer = players[1];
                JOptionPane.showMessageDialog(null, currentPlayer.toString() + ", please build your first settlement and road by clicking on the respective locations.");
            }
            if (MainPanel.state==4 ) {
                currentPlayer = players[2];
                JOptionPane.showMessageDialog(null, currentPlayer.toString() + ", please build your first settlement and road by clicking on the respective locations.");
            }
            if (MainPanel.state==6 ) {
                currentPlayer = players[3];
                JOptionPane.showMessageDialog(null, currentPlayer.toString() + ", please build your first settlement and road by clicking on the respective locations.");
            }
            if (MainPanel.state==8 ) {
                currentPlayer = players[3];
                JOptionPane.showMessageDialog(null, currentPlayer.toString() + ", please build your second settlement and road by clicking on the respective locations.");
            }
            if (MainPanel.state==10 ) {
                currentPlayer = players[2];
                JOptionPane.showMessageDialog(null, currentPlayer.toString() + ", please build your second settlement and road by clicking on the respective locations.");
            }
            if (MainPanel.state==12 ) {
                currentPlayer = players[1];
                JOptionPane.showMessageDialog(null, currentPlayer.toString() + ", please build your second settlement and road by clicking on the respective locations.");
            }
            if (MainPanel.state==14) {
                currentPlayer = players[0];
                JOptionPane.showMessageDialog(null, currentPlayer.toString() + ", please build your second settlement and road by clicking on the respective locations.");
            }
            if (MainPanel.action.equals("RoadBuilding")) MainPanel.action = "Road";

            MainPanel.action="";
        }
        else System.out.println(currentPlayer.toString() + " was unable to build a road.");
    }
    public static void buildSettlement() {
        Intersection i=getIntersection(MainPanel.x, MainPanel.y);
        if (i.canPlace(currentPlayer)) {
            i.setOwner(currentPlayer);
            i.setIsStlmt(true);
            ActionLogPanel.builtSettlement();
            MainPanel.action="";
        }
        else System.out.println(currentPlayer.toString() + " was unable to build a settlement.");
    }
    public static void buildCity() {
        Intersection i=getIntersection(MainPanel.x, MainPanel.y);
        if (i!=null && i.getOwner()!=null && i.getOwner().equals(currentPlayer) && i.isStlmt()) {
            i.setIsCity();
            ActionLogPanel.builtCity();
            MainPanel.action="";
        }
        else System.out.println(currentPlayer.toString() + " was unable to build a city.");
    }

}
