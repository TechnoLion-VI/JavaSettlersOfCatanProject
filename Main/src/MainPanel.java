import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Objects;

public class MainPanel extends JPanel implements MouseListener {
    private ArrayList<Integer> xCoords;  //for intersections
    private ArrayList<Integer> yCoords;
    private GameState gameState;
    private String playerIndStr = "PLAYER ONE";
    private BufferedImage playerIndicator, brick, ore, grain, lumber, wool;
    private JButton endTurn, build;
    //private Font playerTitleFont;
    private int x, y;

    public MainPanel() {
        gameState = new GameState();
        try {
            playerIndicator = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/Player Indicator.png")));
            brick = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/Final Brick Resource Card.png")));
            ore = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/Final Ore Resource Card.png")));
            grain = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/Final Grain Resource Card.png")));
            lumber = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/Final Lumber Resource Card.png")));
            wool = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/Final Wool Resource Card.png")));
            //playerTitleFont = Font.createFont(0, Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Algerian Regular.ttf"))).deriveFont(24.0F);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //ge.registerFont(playerTitleFont);
        } catch (Exception e) {            e.printStackTrace();
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
        /* END TURN, CLAIM WIN, BUILD BUTTONS */
        build = new JButton("Build/Buy");
        build.setBounds(800, 730, 100, 50);
        build.setBackground(new Color(255, 200, 100));
        build.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                String[] options = new String[] {"Road", "Settlement", "City", "Development Card"};
                int response = JOptionPane.showOptionDialog(null, "Choose what you want to build/buy.", "Build/Buy",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                            null, options, options[0]);
                if(response == 0){
                    if(GameState.currentPlayer.getResourceCards().contains("Brick") && GameState.currentPlayer.getResourceCards().contains("Lumber")){
                        GameState.currentPlayer.getResourceCards().remove("Brick");
                        GameState.currentPlayer.getResourceCards().remove("Lumber");
                        //let them select where they want to place road, check if they can
                        System.out.println(GameState.currentPlayer.toString() + " has built a road.");
                    }
                if(response == 1){
                    if(GameState.currentPlayer.getResourceCards().contains("Brick") && GameState.currentPlayer.getResourceCards().contains("Lumber") && GameState.currentPlayer.getResourceCards().contains("Wool") && GameState.currentPlayer.getResourceCards().contains("Grain")){
                        GameState.currentPlayer.getResourceCards().remove("Brick");
                        GameState.currentPlayer.getResourceCards().remove("Lumber");
                        GameState.currentPlayer.getResourceCards().remove("Wool");
                        GameState.currentPlayer.getResourceCards().remove("Grain");
                        //let them select where they want to place settlement, check if they can
                        System.out.println(GameState.currentPlayer.toString() + " has built a settlement.");
                }
                if(response == 2){
                    int ore = 0;
                    for(int i = 0; i < GameState.currentPlayer.getResourceCards().size(); i++){
                        ArrayList<ResourceCard> rc = GameState.currentPlayer.getResourceCards();
                        if(rc.get(i).getType().equals("Ore")){
                            ore++;
                        }
                    }
                    if(ore >= 3) {
                        for (int i = 0; i < 3; i++) {
                            GameState.currentPlayer.getResourceCards().remove("Ore");
                        }
                    }
                    int grain = 0;
                    for(int i = 0; i < GameState.currentPlayer.getResourceCards().size(); i++){
                        ArrayList<ResourceCard> rc = GameState.currentPlayer.getResourceCards();
                        if(rc.get(i).getType().equals("Grain")){
                            grain++;
                        }
                    }
                    if(grain >= 2) {
                        for (int i = 0; i < 2; i++) {
                            GameState.currentPlayer.getResourceCards().remove("Grain");
                        }
                    }
                    //let them select a settlement of theirs
                    System.out.println(GameState.currentPlayer.toString() + " has built a city.");
                    }
                if(response == 3){
                    if(GameState.currentPlayer.getResourceCards().contains("Wool") && GameState.currentPlayer.getResourceCards().contains("Ore") && GameState.currentPlayer.getResourceCards().contains("Grain")){
                        GameState.currentPlayer.getResourceCards().remove("Wool");
                        GameState.currentPlayer.getResourceCards().remove("Grain");
                        GameState.currentPlayer.getResourceCards().remove("Ore");
                        //take it from development card deck and display it visually
                        System.out.println(GameState.currentPlayer.toString() + " has bought a development card.");
                    }
                }
                }

                }
                }
                //JOptionPane optionPane = new JOptionPane("Choose what you want to build/buy.", JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION); //not done
                //JDialog dialog = optionPane.createDialog("Dialog");
                //dialog.setVisible(true);
        });

        endTurn = new JButton("End Turn");
        endTurn.setBounds(550, 730, 100, 50);
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
        /*claimWin = new JButton("Claim Win");
        claimWin.setBounds(800, 730, 100, 50);
        claimWin.setBackground(new Color(255, 200, 100));
        claimWin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (GameState.currentPlayer.getSecretScore() >= 10) {
                    System.out.println(GameState.currentPlayer.toString() + " wins!");
                } else {
                    System.out.println(GameState.currentPlayer.toString() + " cannot claim their win yet.");
                }
            }
        });*/

        add(endTurn);
        //add(claimWin);
        add(build);
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
        g.drawString("DEVELOPMENT CARDS",5,373);
        g.drawRect(500,10,400,75);
        Font diceRollFont = new Font("Serif", Font.BOLD, 35);
        g.setFont(diceRollFont);
        g.drawString("DICE ROLL TOTAL: " + GameState.getDiceNum(), 510, 60);
        g.drawRect(915, 10, 75, 75);
        g.drawRect(1000, 10, 75, 75);
        for (int i=0; i<3; i++) {
            g.drawImage(GameState.board.getTiles()[0][i].getImg(), 560+i*110, 142, 110, 146, null);
            GameState.board.getTiles()[0][i].setPixel(560+i*110, 142);
            g.drawImage(GameState.board.getTiles()[4][i].getImg(), 560+i*110, 578, 110, 146, null);
            GameState.board.getTiles()[4][i].setPixel(560+i*110, 578);
        }
        for (int i=0; i<4; i++) {
            g.drawImage(GameState.board.getTiles()[1][i].getImg(), 505+i*110, 251, 110, 146, null);
            GameState.board.getTiles()[1][i].setPixel(505+i*110, 251);
            g.drawImage(GameState.board.getTiles()[3][i].getImg(), 505+i*110, 469, 110, 146, null);
            GameState.board.getTiles()[3][i].setPixel(505+i*110, 469);
        }
        for (int i=0; i<5; i++) {
            g.drawImage(GameState.board.getTiles()[2][i].getImg(), 450+i*110, 360, 110, 146, null);
            GameState.board.getTiles()[2][i].setPixel(450+i*110, 360);
        }
        GameState.board.setTilesIntersectionsLocations();
        //temporary section for checking intersection locations
        for (Tile[] tiles: GameState.board.getTiles()) {
            for (Tile tile:tiles) {
                for (Intersection i:tile.getIntersections()) {
                    g.fillRect(i.getLocation()[0], i.getLocation()[1], 10, 10);
                }
            }
        }
        g.drawString(GameState.currentPlayer.toString() + " Stats", 13, 650);
        g.setFont(victoryTitleFont);
        g.drawString(GameState.currentPlayer.getSecretScore() + "", 40, 700);
        g.drawString(GameState.currentPlayer.getPlayedKnightCards() + "", 100, 700);
        g.drawImage(brick, 125, 675, 40, 40, null);
        g.drawString(GameState.currentPlayer.getNumResources("Brick") + "", 160, 700);
        g.drawImage(ore, 0, 725, 40, 40, null);
        g.drawString(GameState.currentPlayer.getNumResources("Ore") + "", 40, 750);
        g.drawImage(grain, 60, 725, 40, 40, null);
        g.drawString(GameState.currentPlayer.getNumResources("Grain") + "", 100, 750);
        g.drawImage(lumber, 120, 725, 40, 40, null);
        g.drawString(GameState.currentPlayer.getNumResources("Lumber") + "", 160, 750);
        g.drawImage(wool, 180, 725, 40, 40, null);
        g.drawString(GameState.currentPlayer.getNumResources("Wool") + "", 220, 750);
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
