import javax.swing.*;
import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException {
        MainFrame frame = new MainFrame("Settlers of Catan");
        JOptionPane.showMessageDialog(null,GameState.currentPlayer.toString()+", please build your first settlement and road.");
        //GameState.setUpPhase();
    }
}
