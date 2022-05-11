import javax.swing.*;
import java.io.IOException;

public class MainFrame extends JFrame {
    private static final int WIDTH=1600; //these should be changed later
    private static final int HEIGHT=900;

    public MainFrame(String title) throws IOException {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        add(new MainPanel());
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
