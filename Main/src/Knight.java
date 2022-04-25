import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Knight extends DevelopmentCard{
    public Knight() {
        super("Knight");
        BufferedImage card;
        try {
            card= ImageIO.read(Knight.class.getResource("/Images/Knight.png"));
        }
        catch (Exception e) {
            System.out.println("Knight Image Error");
            return;
        }
        setImg(card);
    }
    public boolean use(Player p){
        p.addToPlayedKnightCards();
        //make sure the card is not played on turn gotten or on the same turn as another development card
        //allow the player to choose where they want to move the robber (moveRobber method in GameState)
        //set object chosenHex to where they chose and make sure it's different from where the robber was at initially
        return true;
    }
}
