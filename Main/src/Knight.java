import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Knight extends DevelopmentCard{
    public Knight() {
        super("Knight");
        BufferedImage card;
        try {
            card= ImageIO.read(Objects.requireNonNull(Knight.class.getResource("/Images/Knight.png")));
        }
        catch (Exception e) {
            System.out.println("Knight Image Error");
            return;
        }
        setImg(card);
    }
    public boolean use(){
        MainPanel.cancel.setEnabled(true);
        MainPanel.action = "Robber";
        GameState.currentPlayer.addToPlayedKnightCards();
        ActionLogPanel.robberK();
        return true;
    }
}
