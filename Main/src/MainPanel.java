import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Objects;

public class MainPanel extends JPanel implements MouseListener {
    private int[] xCoords;  //add values later
    private int[] yCoords;
//  private Board board;
    private JTextArea log;
    private String playerIndStr = "PLAYER ONE";
    private BufferedImage playerIndicator;


    public MainPanel() throws IOException {
        //board = new Board();

        try {
            playerIndicator = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("Images/playerIndicator.png")));
        } catch (Exception e) {
            System.out.println("Exception error");
        }

    }



    public void initComponents() {
        log = new JTextArea();
        PrintStream printStream = new PrintStream(new ActionLogPanel(log));
        System.setOut(printStream);
        this.add(new JScrollPane(log)); //to be edited later
    }

    public void paint(Graphics g) {
        g.setColor(new Color(255, 230, 153));
        g.fillRect(0, 0, 1600, 900);
        g.drawImage(playerIndicator, -10, -15, 454, 164, null);
        Font myFont = new Font ("Algerian Regular", 1, 50);
        Font myFontsmall = new Font ("Algerian Regular", 1, 30);
        g.setColor(Color.black);
        g.setFont (myFont);
        g.drawString("PLAYER ONE", 50, 85);
        g.setFont(myFontsmall);
        g.drawString("PUBLIC VICTORY POINTS",15,175);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        g2.drawLine(15,180,390,180);
        g2.drawLine(15,180,15,335);
        g.drawString("Player One: ",25,215);
        g2.drawLine(15,380,525,380);
        g2.drawLine(15,380,15,425);
        g.drawString("PLAYED DEVELOPEMENT CARDS",15,375);
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
