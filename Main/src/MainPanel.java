import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Objects;

public class MainPanel extends JPanel implements MouseListener {
    private int[] xCoords;  //add values later
    private int[] yCoords;
    private GameState gameState;
    private JTextArea log;
    private String playerIndStr = "PLAYER ONE";
    private BufferedImage playerIndicator;
    private String vicpoint1,vicpoint2,vicpoint3,vicpoint4;
    private Font playerTitleFont;

    public MainPanel() {
        gameState = new GameState();
        try {
            playerIndicator = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("Images/Screenshot_2022-04-26_185231-removebg-preview.png")));
            Font playerTitleFont = Font.createFont(0, Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Algerian Regular.ttf"))).deriveFont(24.0F);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(playerTitleFont);
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
        g.drawImage(playerIndicator, -10, -15, 385, 139, null);
        g.setColor(Color.black);
        g.setFont (playerTitleFont);
        g.drawString("PLAYER ONE", 30, 75);
        //g.setFont(myFontsmall);
        g.drawString("PUBLIC VICTORY POINTS",5,145);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        g2.drawLine(7,152,319,152);
        g2.drawLine(7,152,7,255);
        g.drawString("Player One: " + GameState.players[0].getPublicScore(),11,176);
        g.drawString("Player Two: " + GameState.players[1].getPublicScore(),11,200);
        g.drawString("Player Three: " + GameState.players[2].getPublicScore(),11,224);
        g.drawString("Player Four: " + GameState.players[3].getPublicScore(),11,248);
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
