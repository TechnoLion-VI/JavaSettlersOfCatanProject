import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Monopoly extends DevelopmentCard{
    public Monopoly() {
        super("Monopoly");
        BufferedImage card;
        try {
            card=ImageIO.read(Monopoly.class.getResource("/Images/Monopoly.png"));
        }
        catch (Exception e) {
            System.out.println("Monopoly Image Error");
            return;
        }
        setImg(card);
    }
    public boolean use() {
        //check if card can be played
        String[] options = new String[]{"Brick", "Lumber", "Grain", "Ore", "Wool"};
        int response = JOptionPane.showOptionDialog(null, "Choose resource", "Monopoly", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        String rc = options[response];
        //take cards of type rc from all players and add it to player's hand
        Player[] p = GameState.getPlayers();
        ArrayList<ResourceCard> cards = new ArrayList<>();
        //put resource cards of type rc in cards ArrayList
        for (int i = p[0].size() - 1; i >= 0; i--) {
            if (p[0].get(i).getType().equals(rc))
                cards.add(p[0].remove(i));
        }

        for (int i = p[1].size() - 1; i >= 0; i--) {
            if (p[1].get(i).getType().equals(rc))
                cards.add(p[1].remove(i));
        }

        for (int i = p[2].size() - 1; i >= 0; i--) {
            if (p[2].get(i).getType().equals(rc))
                cards.add(p[2].remove(i));
        }

        for (int i = p[3].size() - 1; i >= 0; i--) {
            if (p[3].get(i).getType().equals(rc))
                cards.add(p[3].remove(i));
        }

        GameState.currentPlayer.addToHand(cards);
        return true;
    }
}
