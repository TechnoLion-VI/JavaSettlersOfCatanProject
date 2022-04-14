import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ActionLogPanel {
    private ArrayList<String> actions;
    //private JPanel panel;

    public ActionLogPanel(){
        JFrame f = new JFrame("frame");
        JPanel panel = new JPanel();
        panel.setBounds(40, 80, 200, 200); //not final, just a filler
        panel.setBackground(Color.white); //color can be changed


    }

    public void addAction(Player p, String s){
        System.out.println(GameState.currentPlayer.toString() + " rolled a " + GameState.getDiceNum());

        /*
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

    }
}
