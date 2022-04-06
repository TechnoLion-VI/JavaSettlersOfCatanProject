import javax.swing.*;

public class MainFrame extends JFrame {
    private static final int WIDTH=1600;
    private static final int HEIGHT=900;
    public MainFrame(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        add(new MainPanel());
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
