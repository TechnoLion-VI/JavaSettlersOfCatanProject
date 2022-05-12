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
        int response = JOptionPane.showOptionDialog(null, "Choose resource you want to monopolize.", "Monopoly", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        String rc = options[response];
        //take cards of type rc from all players and add it to player's hand
        ArrayList<ResourceCard> cards = new ArrayList<>();
        for (Player p:GameState.getPlayers()) {
            for (int i = 0; i < p.size(); i++) {
                if (p.get(i).getType().equals(rc)) {
                    cards.add(p.remove(i));
                }
            }
        }

        GameState.currentPlayer.addToHand(cards);
        return true;
    }
}
