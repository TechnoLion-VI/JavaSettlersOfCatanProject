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
    public boolean use(){
        GameState.currentPlayer.addToPlayedKnightCards();
        /* allow the player to choose where they want to move the robber (moveRobber method in GameState) */
        return true;
    }
}
