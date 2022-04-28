import javax.swing.JTextArea;
import java.io.OutputStream;

public class ActionLogPanel extends OutputStream{
    private JTextArea textArea;

    public ActionLogPanel(JTextArea textArea) {
        this.textArea = textArea;
    }

    public void write(int b) {
        // redirects data to the text area
        textArea.append(String.valueOf((char)b));
        // scrolls the text area to the end of data
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    public static void rollDice() {
        System.out.println(GameState.currentPlayer.toString() + " rolled a " + GameState.getDiceNum());
    }

    public static void trade(){
        System.out.println(GameState.currentPlayer.toString() + " traded " + TradeManager.p1offer);
    }

    public static void builtSettlement(){
        System.out.println(GameState.currentPlayer.toString() + " built a settlement."); //not done
    }

    public static void builtRoad(){
        System.out.println(GameState.currentPlayer.toString() + " built a road.");
    }

    public static void builtCity(){ System.out.println(GameState.currentPlayer.toString() + " built a city.");}

    public static void buy(){
        System.out.println(GameState.currentPlayer.toString() + " bought a development card.");
    }

    public static void largestArmy(){
        System.out.println(GameState.currentPlayer.toString() + " acquired the largest army card.");
    }

    public static void longestRoad(Player p){
        System.out.println(p.toString() + " acquired the longest road card.");
    }

    public static void robber(){
        System.out.println(GameState.currentPlayer.toString() + " rolled a seven and moved the robber.");
    }

    public static void winClaimed() {
        System.out.println(GameState.currentPlayer.toString() + " claimed win.");
    }

}
