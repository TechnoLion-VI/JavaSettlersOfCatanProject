import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.io.PrintStream;
import java.util.Objects;

public class MainPanel extends JPanel implements MouseListener {
    private int[] xCoords;  //add values later
    private int[] yCoords;
    private GameState gameState;
    private String playerIndStr = "PLAYER ONE";
    private BufferedImage playerIndicator;
    private JButton endTurn, claimWin;
    //private Font playerTitleFont;
    private int x, y;

    public MainPanel() {
        gameState = new GameState();
        try {
            playerIndicator = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("Images/Player Indicator.png")));
            //playerTitleFont = Font.createFont(0, Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Algerian Regular.ttf"))).deriveFont(24.0F);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //ge.registerFont(playerTitleFont);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLayout(null);
        initComponents();
        addMouseListener(this);
        gameState.rollDice();
    }



    public void initComponents() {
        /* ACTION LOG STUFF */
        JTextArea log = new JTextArea(50, 50);
        //exact color from mockup
        log.setBackground(new Color(255, 220, 100));
        //change font later
        log.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        log.setEditable(false);
        log.setLineWrap(true);
        PrintStream printStream = new PrintStream(new ActionLogPanel(log));
        System.setOut(printStream);
        JScrollPane logPanel = new JScrollPane(log, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        logPanel.setBounds(1100, 10, 400, 200);
        this.add(logPanel);
        /* END TURN AND CLAIM WIN BUTTONS */
        endTurn = new JButton("End Turn");
        endTurn.setBounds(650, 650, 100, 100);
        endTurn.setBackground(new Color(255, 200, 100));
        endTurn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(GameState.currentPlayer.toString() + " has ended their turn.");
                for (int i = 0; i < 4; i++) {
                    if (GameState.currentPlayer == GameState.getPlayers()[i]) {
                        GameState.currentPlayer = GameState.getPlayers()[(i + 1) % 4];
                        break;
                    }
                }
                gameState.rollDice();
                repaint();
            }
        });
        claimWin = new JButton("Claim Win");
        claimWin.setBounds(850, 650, 100, 100);
        claimWin.setBackground(new Color(255, 200, 100));
        claimWin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (GameState.currentPlayer.getSecretScore() >= 10) {
                    System.out.println(GameState.currentPlayer.toString() + " wins!");
                } else {
                    System.out.println(GameState.currentPlayer.toString() + " cannot claim their win yet.");
                }
            }
        });
        add(endTurn);
        add(claimWin);
    }

    public void paintComponent(Graphics g) {
        g.setColor(new Color(255, 230, 150));
        g.fillRect(0, 0, 1600, 900);
        g.drawImage(playerIndicator, -10, -15, 500, 139, null);
        g.setColor(Color.black);
        Font playerTitleFont = new Font("Serif", Font.BOLD, 50);
        g.setFont (playerTitleFont);
        g.drawString(GameState.currentPlayer.toString(), 20, 75);
        //g.setFont(myFontsmall);
        Font victoryTitleFont = new Font("Serif", Font.BOLD, 20);
        g.setFont(victoryTitleFont);
        g.drawString("PUBLIC VICTORY POINTS",5,145);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        g2.drawLine(7,152,319,152);
        g2.drawLine(7,152,7,255);
        Font victoryPointFont = new Font("Serif", Font.BOLD, 15);
        g.drawString("Player Blue: " + GameState.players[0].getPublicScore(),11,176);
        g.drawString("Player Orange: " + GameState.players[1].getPublicScore(),11,200);
        g.drawString("Player Red: " + GameState.players[2].getPublicScore(),11,224);
        g.drawString("Player White: " + GameState.players[3].getPublicScore(),11,248);
        g2.drawLine(7,380,319,380);
        g2.drawLine(7,380,7,425);
        g.setFont(victoryTitleFont);
        g.drawString("PLAYED DEVELOPMENT CARDS",5,373);
        g.drawRect(500,10,400,75);
        Font diceRollFont = new Font("Serif", Font.BOLD, 35);
        g.setFont(diceRollFont);
        g.drawString("DICE ROLL TOTAL: " + GameState.getDiceNum(), 510, 60);
        for (int i=0; i<3; i++) {
            g.drawImage(GameState.board.getTiles()[0][i].getImg(), 560+i*110, 142, 110, 146, null);
            g.drawImage(GameState.board.getTiles()[4][i].getImg(), 560+i*110, 578, 110, 146, null);
        }
        for (int i=0; i<4; i++) {
            g.drawImage(GameState.board.getTiles()[1][i].getImg(), 505+i*110, 251, 110, 146, null);
            g.drawImage(GameState.board.getTiles()[3][i].getImg(), 505+i*110, 469, 110, 146, null);
        }
        for (int i=0; i<5; i++) {
            g.drawImage(GameState.board.getTiles()[2][i].getImg(), 450+i*110, 360, 110, 146, null);
        }
        g.drawString(GameState.currentPlayer.toString() + " Stats", 13, 650);
        g.setFont(victoryTitleFont);
        g.drawString(GameState.currentPlayer.getSecretScore() + "", 40, 680);
        g.drawString(GameState.currentPlayer.getPlayedKnightCards() + "", 80, 680);
        g.drawString(GameState.currentPlayer.getNumResources("Brick") + "", 120, 680);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        x=e.getX();
        y=e.getY();
        System.out.println("("+x+", "+y+")");
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
