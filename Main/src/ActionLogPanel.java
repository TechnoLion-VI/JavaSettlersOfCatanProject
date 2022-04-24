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

    /*public void paint(Graphics g){
        g.drawString(GameState.currentPlayer.toString() + " rolled a " + GameState.getDiceNum(), 10, 0); //if dice rolled
        g.drawString(GameState.currentPlayer.toString() + " traded " + TradeManager.p1offer, 10, 5);
        g.drawString(GameState.currentPlayer.toString() + " built a " , 10, 10); //how do we check if they built a city or stlmnt
        g.drawString(GameState.currentPlayer.toString() + " bought a development card.", 10, 15);
        g.drawString(GameState.currentPlayer.toString() + " acquired the longest army card.", 10, 20);
        g.drawString(GameState.currentPlayer.toString() + " acquired the longest road card.", 10, 25);
        g.drawString(GameState.currentPlayer.toString() + " rolled a seven and moved the robber.", 10, 30);
        g.drawString(GameState.currentPlayer.toString() + " claimed win.", 10, 35);
    } */

}
