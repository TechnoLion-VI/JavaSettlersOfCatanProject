import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class MainPanel extends JPanel implements MouseListener {
    private int[] xCoords;  //add values later
    private int[] yCoords;
    private JTextArea log;

    public MainPanel() throws IOException {

    }

    public void initComponents() {
        log = new JTextArea();
        PrintStream printStream = new PrintStream(new ActionLogPanel(log));
        System.setOut(printStream);
    }

    public void paint(Graphics g) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }

}
