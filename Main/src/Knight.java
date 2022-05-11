import javax.imageio.ImageIO;
import javax.swing.*;
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
    //push commit
    public boolean use(){
        JOptionPane.showMessageDialog(null,"Please select where you'd like to move the robber.");
        MainPanel.action = "Robber";
        GameState.currentPlayer.addToPlayedKnightCards();
        GameState.checkLargestArmyPlayer();
        ActionLogPanel.robberK();
        return true;
    }
}
