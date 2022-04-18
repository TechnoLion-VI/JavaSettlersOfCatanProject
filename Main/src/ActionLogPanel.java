import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ActionLogPanel {
    private ArrayList<String> actions;
    //private JPanel panel;

    public ActionLogPanel(){
        JFrame f = new JFrame("Action Log Panel");
        JPanel panel = new JPanel();
        panel.setBounds(40, 80, 200, 200); //not final, just a filler
        panel.setBackground(Color.white); //color can be changed
        f.add(panel);
        f.setSize(400, 400); //not final, just a filler
        f.setLayout(null);
        f.setVisible(true);
        /*panel.AutoScroll = false;
        panel.HorizontalScroll.Enabled = false;
        panel.HorizontalScroll.Visible = false;
        panel.HorizontalScroll.Maximum = 0;
        panel.AutoScroll = true;/*

    }

    public void addAction(Player p, String s){
        System.out.println(GameState.currentPlayer.toString() + " rolled a " + GameState.getDiceNum());

        /*
        roll dice
        trade
        build/buy
        use development card
        longest army and longest road
        robber (rolled a 7)
        claim win
         */
    }

    public ArrayList<String> getActions(){
        return actions;
    }

    public void paint(Graphics g){
        g.drawString(GameState.currentPlayer.toString() + " rolled a " + GameState.getDiceNum(), 10, 0); //if dice rolled
        g.drawString(GameState.currentPlayer.toString() + " traded " + TradeManager.p1offer, 10, 5);
        g.drawString(GameState.currentPlayer.toString() + " built a " , 10, 10);
        g.drawString(GameState.currentPlayer.toString() + "bought a development card.", 10, 15);
        g.drawString(GameState.currentPlayer.toString() + " acquired the longest army card.", 10, 20);
        g.drawString(GameState.currentPlayer.toString() + " acquired the longest road card.", 10, 25);
        g.drawString(GameState.currentPlayer.toString() + " rolled a seven.", 10, 30);
        g.drawString(GameState.currentPlayer.toString() + " claimed win.", 10, 35);
    }
}
