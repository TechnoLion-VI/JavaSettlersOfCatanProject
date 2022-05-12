import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

public class YearOfPlenty extends DevelopmentCard {
    public YearOfPlenty() {
        super("YearOfPlenty");
        BufferedImage card;
        try {
            card= ImageIO.read(YearOfPlenty.class.getResource("/Images/YearOfPlenty.png"));
        }
        catch (Exception e) {
            System.out.println("Year of Plenty Image Error");
            return;
        }
        setImg(card);
    }
    public boolean use() {
        String[] options = new String[]{"Brick", "Lumber", "Grain", "Ore", "Wool"};
        int response1 = JOptionPane.showOptionDialog(null, "Choose resource to obtain", "Year of Plenty", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        String type1 = options[response1];
        int response2 = JOptionPane.showOptionDialog(null, "Choose resource to obtain", "Year of Plenty", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        String type2 = options[response2];
        if (ResourceDeck.getNumLeft(type1)<=0||ResourceDeck.getNumLeft(type2)<=0)   //checks deck has enough
            return false;
        if (type1.equals(type2) && ResourceDeck.getNumLeft(type1)<=1)    //checks it has enough if both are same type
            return false;
        GameState.currentPlayer.add(ResourceDeck.draw(type1));
        GameState.currentPlayer.add(ResourceDeck.draw(type2));
        return true;
    }
}
