import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.font.GlyphMetrics;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.awt.MouseInfo;
import java.awt.Point;


public class MainPanel extends JPanel implements MouseListener {
    ArrayList<BufferedImage> harbors;
    private String playerIndStr = "PLAYER ONE";
    private BufferedImage playerIndicator, brick, ore, grain, lumber, wool, sword, trophy, resource, blueStlmt, blueCity, orangeStlmt, orangeCity, redStlmt, redCity, whiteStlmt, whiteCity;
    private BufferedImage genericHarbor, brickHarbor, grainHarbor, lumberHarbor, oreHarbor, woolHarbor;
    private BufferedImage settlement, city, road;
    private BufferedImage one, two, three, four, five, six, HelpButton;
    private JButton endTurn, build, trade, rollDice, playDevcard,helppls;
    private JPanel devCardPanel;
    private JScrollPane devCards;
    private boolean devCardPlayed;
    private ResourceCard brickResource=new ResourceCard(), grainResource=new ResourceCard(), lumberResource=new ResourceCard(), oreResource=new ResourceCard(), woolResource=new ResourceCard();
    //private Font playerTitleFont;
    public static int x, y;
    private Color blue, orange, white, red;
    public static int state = 0;
    public static String action="";
    public static int brickLoc, lumberLoc, oreLoc, grainLoc, woolLoc;
    //public HelpFrame frame3;

    public MainPanel() {
        blue = new Color(68, 115, 196);
        orange = new Color(237, 125, 49);
        white = new Color(255, 255, 255);
        red = new Color(192, 0, 0);
        try {
            playerIndicator = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/Player Indicator.png")));
            brick = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/Final Brick Resource Card.png")));
            ore = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/Final Ore Resource Card.png")));
            grain = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/Final Grain Resource Card.png")));
            lumber = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/Final Lumber Resource Card.png")));
            wool = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/Final Wool Resource Card.png")));
            sword = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/sword.png")));
            trophy = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/trophy.png")));
            resource = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/parchment.png")));
            blueStlmt = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/Blue Settlement.png")));
            orangeStlmt = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/Orange Settlement.png")));
            redStlmt = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/Red Settlement.png")));
            whiteStlmt = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/White Settlement.png")));
            blueCity = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/Blue City.png")));
            orangeCity = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/Orange City.png")));
            redCity = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/Red City.png")));
            whiteCity = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/White City.png")));
            genericHarbor = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/3to1.png")));
            brickHarbor = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/bricks2to1.png")));
            grainHarbor = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/wheat2to1.png")));
            lumberHarbor = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/wood2to1.png")));
            oreHarbor = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/ore2to1.png")));
            woolHarbor = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/sheep2to1.png")));
            settlement = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/settlement.png")));
            city = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/city2.png")));
            road = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/road.png")));
            one = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/one.jpg")));
            two = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/two.jpg")));
            three = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/three.jpg")));
            four = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/four.jpg")));
            five = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/five.jpg")));
            six = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/six.jpg")));
            //  HelpButton = ImageIO.read(Objects.requireNonNull(MainPanel.class.getResource("/Images/help-button_adobespark.png")));
            brickResource=new Brick();
            grainResource=new Grain();
            lumberResource=new Lumber();
            oreResource=new Ore();
            woolResource=new Wool();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLayout(null);
        devCardPlayed = false;
        initComponents();
        addMouseListener(this);
        //GameState.rollDice();
    }


    public void initComponents() {
        //init components
        JPanel p = new JPanel();
        JTextArea log = new JTextArea(50, 50);
        JScrollPane logPanel = new JScrollPane(log, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        devCardPanel = new JPanel();
        devCards = new JScrollPane(devCardPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        trade = new JButton("Trade");
        build = new JButton("Build/Buy");
        rollDice = new JButton("Roll Dice");
        endTurn = new JButton("End Turn");
        playDevcard = new JButton("Play Development Card");
        //helppls = new JButton("Help");

        //harbors
        harbors = new ArrayList<BufferedImage>();
        for (int i = 0; i < 4; i++) {
            harbors.add(genericHarbor);
        }
        harbors.add(oreHarbor);
        harbors.add(grainHarbor);
        harbors.add(lumberHarbor);
        harbors.add(woolHarbor);
        harbors.add(brickHarbor);
        Collections.shuffle(harbors);
        //components
//      JPanel p = new JPanel();
        p.setLocation(1140, 300);
        p.setSize(300, 450);
        p.setBackground(new Color(255, 220, 100));
        p.setLayout(null);
        p.setVisible(true);
        add(p);
        /* ACTION LOG STUFF */
//      JTextArea log = new JTextArea(50, 50);
        log.setBackground(new Color(255, 220, 100));
        //change font later
        log.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        log.setEditable(false);
        log.setLineWrap(true);
        PrintStream printStream = new PrintStream(new ActionLogPanel(log));
        System.setOut(printStream);
//        JScrollPane logPanel = new JScrollPane(log, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        logPanel.setBounds(1100, 10, 400, 200);
        add(logPanel);
        /* DEVELOPMENT CARDS PANEL */
        devCardPanel.setBackground(new Color(255, 220, 100));
        devCards.setBounds(12, 385, 350, 100);
        add(devCards);
        /* END TURN, CLAIM WIN, BUILD, TRADE BUTTONS */
        trade.setBounds(15, 520, 100, 50);
        trade.setBackground(new Color(255, 200, 100));
        trade.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String[] options = new String[]{"Domestic", "Maritime"};
                int response = JOptionPane.showOptionDialog(null, "Choose what type of trade you wish to perform.", "Trade",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
                if (response == 0) {
                    JLabel tradeMessage = new JLabel("Please select what you wish to trade with.");
                    tradeMessage.setFont(new Font("Serif", 1, 15));
                    tradeMessage.setBounds(0, -30, 400, 80);
                    p.add(tradeMessage);

                    JLabel ore = new JLabel("Ore");
                    ore.setFont(new Font("Serif", 1, 15));
                    ore.setBounds(10, 50, 30, 30);
                    ore.requestFocus();
                    ore.setVisible(true);
                    JTextField oreText = new JTextField(10);
                    oreText.setBounds(70, 55, 30, 25);
                    oreText.setBackground(new Color(255, 220, 100));
                    p.add(oreText);
                    p.add(ore);

                    JLabel grain = new JLabel("Grain");
                    grain.setFont(new Font("Serif", 1, 15));
                    grain.setBounds(10, 90, 60, 30);
                    grain.requestFocus();
                    grain.setVisible(true);
                    JTextField grainText = new JTextField(10);
                    grainText.setBounds(70, 95, 30, 25);
                    grainText.setBackground(new Color(255, 220, 100));
                    p.add(grainText);
                    p.add(grain);

                    JLabel lumber = new JLabel("Lumber");
                    lumber.setFont(new Font("Serif", 1, 15));
                    lumber.setBounds(10, 130, 60, 30);
                    lumber.requestFocus();
                    lumber.setVisible(true);
                    JTextField lumberText = new JTextField(10);
                    lumberText.setBounds(70, 135, 30, 25);
                    lumberText.setBackground(new Color(255, 220, 100));
                    p.add(lumberText);
                    p.add(lumber);

                    JLabel brick = new JLabel("Brick");
                    brick.setFont(new Font("Serif", 1, 15));
                    brick.setBounds(130, 50, 60, 30);
                    brick.requestFocus();
                    brick.setVisible(true);
                    JTextField brickText = new JTextField(10);
                    brickText.setEditable(true);
                    brickText.setBounds(190, 55, 30, 25);
                    brickText.setBackground(new Color(255, 220, 100));
                    p.add(brickText);
                    p.add(brick);

                    JLabel wool = new JLabel("Wool");
                    wool.setFont(new Font("Serif", 1, 15));
                    wool.setBounds(130, 90, 60, 30);
                    wool.requestFocus();
                    wool.setVisible(true);
                    JTextField woolText = new JTextField(10);
                    woolText.setBounds(190, 95, 30, 25);
                    woolText.setBackground(new Color(255, 220, 100));
                    p.add(woolText);
                    p.add(wool);

                    JButton done = new JButton("Done");
                    done.setFont(new Font("Serif", 1, 15));
                    done.setBackground(new Color(255, 220, 100));
                    done.setBounds(115, 400, 75, 25);
                    done.addActionListener(new ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            ArrayList<String> trade = new ArrayList<String>();
                            if (oreText.isValid()) {
                                String text = oreText.getText();
                                int numOre = Integer.parseInt(text);
                                int oreLeft = 0;
                                for (int i = 0; i < GameState.currentPlayer.getResourceCards().size(); i++) {
                                    if (GameState.currentPlayer.getResourceCards().get(i).getType().equals("Ore")) {
                                        oreLeft++;
                                    }
                                }
                                if (numOre <= oreLeft) {
                                    System.out.println(GameState.currentPlayer.toString() + " has requested to trade " + text + " ore.");
                                    for(int i = 0; i < numOre; i++){
                                        trade.add("ore");
                                    }
                                    //p.repaint();
                                } else {
                                    while (numOre > oreLeft) {
                                        JOptionPane.showMessageDialog(null, GameState.currentPlayer.toString() + " does not have enough ore to carry out this trade.");
                                        String n = JOptionPane.showInputDialog("How many ore does " + GameState.currentPlayer.toString() + " wish to trade with? (max of " + oreLeft + ")");
                                        numOre = Integer.parseInt(n);
                                        tradeMessage.setText("Please select what you wish to trade with.");
                                    }
                                }
                            }

                            if (grainText.isValid()) {
                                String text = grainText.getText();
                                int numGrain = Integer.parseInt(text);
                                int grainLeft = 0;
                                for (int i = 0; i < GameState.currentPlayer.getResourceCards().size(); i++) {
                                    if (GameState.currentPlayer.getResourceCards().get(i).getType().equals("Grain")) {
                                        grainLeft++;
                                    }
                                }
                                if (numGrain <= grainLeft) {
                                    System.out.println(GameState.currentPlayer.toString() + " has requested to trade " + text + " grain.");
                                    for(int i = 0; i < numGrain; i++){
                                        trade.add("grain");
                                    }
                                    //p.repaint();
                                } else {
                                    while (numGrain > grainLeft) {
                                        JOptionPane.showMessageDialog(null, GameState.currentPlayer.toString() + " does not have enough grain to carry out this trade.");
                                        String n = JOptionPane.showInputDialog("How many grain does " + GameState.currentPlayer.toString() + " wish to trade with? (max of " + grainLeft + ")");
                                        numGrain = Integer.parseInt(n);
                                        tradeMessage.setText("Please select what you wish to trade with.");
                                    }
                                }
                            }

                            if (brickText.isValid()) {
                                String text = brickText.getText();
                                int numBrick = Integer.parseInt(text);
                                int brickLeft = 0;
                                for (int i = 0; i < GameState.currentPlayer.getResourceCards().size(); i++) {
                                    if (GameState.currentPlayer.getResourceCards().get(i).getType().equals("Brick")) {
                                        brickLeft++;
                                    }
                                }
                                if (numBrick <= brickLeft) {
                                    System.out.println(GameState.currentPlayer.toString() + " has requested to trade " + text + " brick.");
                                    for(int i = 0; i < numBrick; i++){
                                        trade.add("brick");
                                    }
                                    //p.repaint();
                                } else {
                                    //JOptionPane.showMessageDialog(null, GameState.currentPlayer.toString() + " does not have enough brick to carry out this trade.");
                                    while (numBrick > brickLeft) {
                                        JOptionPane.showMessageDialog(null, GameState.currentPlayer.toString() + " does not have enough brick to carry out this trade.");
                                        String n = JOptionPane.showInputDialog("How many brick does " + GameState.currentPlayer.toString() + " wish to trade with? (max of " + brickLeft + ")");
                                        numBrick = Integer.parseInt(n);
                                        tradeMessage.setText("Please select what you wish to trade with.");
                                    }
                                }
                            }

                            if (lumberText.isValid()) {
                                String text = lumberText.getText();
                                int numLumber = Integer.parseInt(text);
                                int lumberLeft = 0;
                                for (int i = 0; i < GameState.currentPlayer.getResourceCards().size(); i++) {
                                    if (GameState.currentPlayer.getResourceCards().get(i).getType().equals("Lumber")) {
                                        lumberLeft++;
                                    }
                                }
                                if (numLumber <= lumberLeft) {
                                    System.out.println(GameState.currentPlayer.toString() + " has requested to trade " + text + " lumber.");
                                    for(int i = 0; i < numLumber; i++){
                                        trade.add("lumber");
                                    }
                                    //p.repaint();
                                } else {
                                    while (numLumber > lumberLeft) {
                                        JOptionPane.showMessageDialog(null, GameState.currentPlayer.toString() + " does not have enough lumber to carry out this trade.");
                                        String n = JOptionPane.showInputDialog("How many lumber does " + GameState.currentPlayer.toString() + " wish to trade with? (max of " + lumberLeft + ")");
                                        numLumber = Integer.parseInt(n);
                                        tradeMessage.setText("Please select what you wish to trade with.");
                                    }
                                }
                            }


                            if (woolText.isValid()) {
                                String text = woolText.getText();
                                int numWool = Integer.parseInt(text);
                                int woolLeft = 0;
                                for (int i = 0; i < GameState.currentPlayer.getResourceCards().size(); i++) {
                                    if (GameState.currentPlayer.getResourceCards().get(i).getType().equals("Wool")) {
                                        woolLeft++;
                                    }
                                }
                                if (numWool <= woolLeft) {
                                    System.out.println(GameState.currentPlayer.toString() + " has requested to trade " + text + " wool.");
                                    for(int i = 0; i < numWool; i++){
                                        trade.add("wool");
                                    }
                                    //p.repaint();
                                } else {
                                    while (numWool > woolLeft) {
                                        JOptionPane.showMessageDialog(null, GameState.currentPlayer.toString() + " does not have enough wool to carry out this trade.");
                                        String n = JOptionPane.showInputDialog("How many wool does " + GameState.currentPlayer.toString() + " wish to trade with? (max of " + woolLeft + ")");
                                        numWool = Integer.parseInt(n);
                                        tradeMessage.setText("Please select what you wish to trade with.");
                                    }
                                }
                            }

                            p.repaint();

                            tradeMessage.setText("");
                            JLabel tradeMessageTwo = new JLabel("");
                            tradeMessageTwo.setFont(new Font("Serif", 1, 15));
                            tradeMessageTwo.setBounds(0, -20, 400, 80);
                            tradeMessageTwo.setText("<html>Please type in how many of each resource<br>you wish to trade for.");
                            p.add(tradeMessageTwo);

                            JLabel oreTwo = new JLabel("Ore");
                            oreTwo.setFont(new Font("Serif", 1, 15));
                            oreTwo.setBounds(10, 50, 30, 30);
                            oreTwo.requestFocus();
                            oreTwo.setVisible(true);
                            JTextField oreTextTwo = new JTextField(10);
                            oreTextTwo.setBounds(70, 55, 30, 25);
                            oreTextTwo.setBackground(new Color(255, 220, 100));
                            oreTextTwo.setVisible(true);
                            String oText = oreTextTwo.getText();
                            int numO = Integer.parseInt(oText);
                            if(!trade.contains("ore")) {
                                System.out.println("BRO");
                                System.out.println(GameState.currentPlayer.toString() + " has requested to trade for " + numO + " ore.");
                            }
                            else{
                                while(trade.contains("ore")){
                                    JOptionPane.showInputDialog("You cannot trade the same resource.");
                                }
                            }
                            p.add(oreTextTwo);
                            p.add(oreTwo);

                            JLabel grainTwo = new JLabel("Grain");
                            grainTwo.setFont(new Font("Serif", 1, 15));
                            grainTwo.setBounds(10, 90, 30, 30);
                            grainTwo.requestFocus();
                            grainTwo.setVisible(true);
                            JTextField grainTextTwo = new JTextField(10);
                            grainTextTwo.setBounds(70, 95, 30, 25);
                            grainTextTwo.setBackground(new Color(255, 220, 100));
                            String gText = grainTextTwo.getText();
                            int numG = Integer.parseInt(gText);
                            System.out.println(GameState.currentPlayer.toString() + " has requested to trade " + numG + " grain.");
                            p.add(grainTextTwo);
                            p.add(grain);

                            JLabel lumberTwo = new JLabel("Lumber");
                            lumberTwo.setFont(new Font("Serif", 1, 15));
                            lumberTwo.setBounds(10, 130, 30, 30);
                            lumberTwo.requestFocus();
                            lumberTwo.setVisible(true);
                            JTextField lumberTextTwo = new JTextField(10);
                            lumberTextTwo.setBounds(70, 135, 30, 25);
                            lumberTextTwo.setBackground(new Color(255, 220, 100));
                            String lText = lumberTextTwo.getText();
                            int numL = Integer.parseInt(lText);
                            System.out.println(GameState.currentPlayer.toString() + " has requested to trade " + numL + " lumber.");
                            p.add(lumberTextTwo);
                            p.add(lumber);

                            JLabel brickTwo = new JLabel("Brick");
                            brickTwo.setFont(new Font("Serif", 1, 15));
                            brickTwo.setBounds(10, 170, 30, 30);
                            brickTwo.requestFocus();
                            brickTwo.setVisible(true);
                            JTextField brickTextTwo = new JTextField(10);
                            brickTextTwo.setBounds(70, 175, 30, 25);
                            brickTextTwo.setBackground(new Color(255, 220, 100));
                            String bText = brickTextTwo.getText();
                            int numB = Integer.parseInt(bText);
                            System.out.println(GameState.currentPlayer.toString() + " has requested to trade " + numB + " brick.");
                            p.add(brickTextTwo);
                            p.add(brick);

                            JLabel woolTwo = new JLabel("Wool");
                            woolTwo.setFont(new Font("Serif", 1, 15));
                            woolTwo.setBounds(10, 210, 30, 30);
                            woolTwo.requestFocus();
                            woolTwo.setVisible(true);
                            JTextField woolTextTwo = new JTextField(10);
                            woolTextTwo.setBounds(70, 215, 30, 25);
                            woolTextTwo.setBackground(new Color(255, 220, 100));
                            String wText = woolTextTwo.getText();
                            int numW = Integer.parseInt(wText);
                            System.out.println(GameState.currentPlayer.toString() + " has requested to trade " + numW + " wool.");
                            p.add(woolTextTwo);
                            p.add(wool);

                            p.repaint();
                            if(GameState.currentPlayer.toString().equals("Blue Player")){
                                JLabel white = new JLabel("White Player");
                                white.setFont(new Font("Serif", 1, 15));
                                white.setBounds(10, 50, 30, 30);
                                white.requestFocus();
                                white.setVisible(true);
                                JTextField whiteTwo = new JTextField(10);
                                whiteTwo.setBackground(new Color(255, 220, 100));
                                String whiteText = whiteTwo.getText();
                                if(whiteText.equals("A")){
                                    System.out.println(GameState.currentPlayer.toString() + " has traded with White Player.");
                                }
                                else{
                                    System.out.println("White Player has declined " + GameState.currentPlayer.toString() + "'s trade.");
                                }
                            }
                        }

                        //make sure they are not trading the same thing
                        //allow each player to accept or decline
                        //display whether player accepted or declined on action log
                        //give cards
                    });
                    p.add(done);
                }
                if (response == 1) {
                    //maritime
                    System.out.println(GameState.currentPlayer.toString() + " has performed a maritime trade at a harbor."); //NOT DONE; need to specify kind of harbor
                }
                repaint();
            }
        });

//      build = new JButton("Build/Buy");
        build.setBounds(140, 520, 100, 50);
        build.setBackground(new Color(255, 200, 100));
        build.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String[] options = new String[]{"Road", "Settlement", "City", "Development Card"};
                int response = JOptionPane.showOptionDialog(null, "Choose what you want to build/buy.", "Build/Buy",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
                if (response == 0) {    //road
                    boolean containsBrick=false, containsLumber=false;
                    brickLoc=-1; lumberLoc=-1;
                    for (int i=0; i<GameState.currentPlayer.getResourceCards().size(); i++) {
                        ResourceCard rc=GameState.currentPlayer.getResourceCards().get(i);
                        //System.out.print(rc.getType() + " ");
                        if (rc.getType().equals("Brick")) {
                            containsBrick = true;
                            brickLoc=i;
                        }
                        if (rc.getType().equals("Lumber")) {
                            containsLumber = true;
                            lumberLoc=i;
                        }
                    }
//                    System.out.println();
                    if (containsBrick && containsLumber) {

                        //let them select where they want to place road, check if they can
//                        Edge road = GameState.getEdge(x, y);
//                        if (road != null && road.canPlace(GameState.currentPlayer))
//                            road.setOwner(GameState.currentPlayer);
//                        System.out.println(GameState.currentPlayer.toString() + " has built a road.");
                        action="Road";
                        trade.setEnabled(false);
                    }
                    else {
                        System.out.println(GameState.currentPlayer.toString() + " was unable to build a road.");
                    }
                }
                if (response == 1) {    //settlement
                    boolean containsBrick=false, containsLumber=false, containsWool=false, containsGrain=false;
                    brickLoc=-1; lumberLoc=-1; woolLoc=-1; grainLoc=-1;
                    for (int i=0; i<GameState.currentPlayer.getResourceCards().size(); i++) {
                        ResourceCard rc=GameState.currentPlayer.getResourceCards().get(i);
                        //System.out.print(rc.getType() + " ");
                        if (rc.getType().equals("Brick")) {
                            containsBrick = true;
                            brickLoc=i;
                        }
                        if (rc.getType().equals("Lumber")) {
                            containsLumber = true;
                            lumberLoc=i;
                        }
                        if (rc.getType().equals("Wool")) {
                            containsWool = true;
                            woolLoc=i;
                        }
                        if (rc.getType().equals("Grain")) {
                            containsGrain = true;
                            grainLoc=i;
                        }
                    }
                    if (containsBrick && containsLumber && containsWool && containsGrain) {
//                        //let them select where they want to place settlement, check if they can
//                        Intersection stlmt = GameState.getIntersection(x, y);
//                        if (stlmt != null && stlmt.canPlace(GameState.currentPlayer))
//                            stlmt.setOwner(GameState.currentPlayer);
//                        System.out.println(GameState.currentPlayer.toString() + " has built a settlement.");
                        action="Settlement";
                        trade.setEnabled(false);
                    } else {
                        System.out.println(GameState.currentPlayer.toString() + " was unable to build a settlement.");
                    }
                }
                if (response == 2) {    //city
                    int ore = 0, grain = 0;
                    for (int i = 0; i < GameState.currentPlayer.getResourceCards().size(); i++) {
                        ArrayList<ResourceCard> rc = GameState.currentPlayer.getResourceCards();
                        if (rc.get(i).getType().equals("Ore")) {
                            ore++;
                        }
                        if (rc.get(i).getType().equals("Grain")) {
                            grain++;
                        }
                    }
                    if (ore >= 3 && grain >= 2) {
                        action="City";
                        trade.setEnabled(false);
                    } else {
                        System.out.println(GameState.currentPlayer.toString() + " was unable to build a city.");
                    }
                    //let them select a settlement of theirs
                }
                if (response == 3) {    //devcard
                    boolean containsOre=false, containsWool=false, containsGrain=false;
                    for (int i=0; i<GameState.currentPlayer.getResourceCards().size(); i++) {
                        ResourceCard rc=GameState.currentPlayer.getResourceCards().get(i);
                        //System.out.print(rc.getType() + " ");
                        if (rc.getType().equals("Ore")) {
                            containsOre = true;
                        }
                        if (rc.getType().equals("Wool")) {
                            containsWool = true;
                        }
                        if (rc.getType().equals("Grain")) {
                            containsGrain = true;
                        }
                    }
                    //take it from development card deck and display it visually
                    if (containsGrain && containsOre && containsWool && !DevelopmentCardDeck.deck.isEmpty()) {
                        GameState.currentPlayer.remove("Grain");
                        GameState.currentPlayer.remove("Ore");
                        GameState.currentPlayer.remove("Wool");
                        ResourceDeck.add("Ore");
                        ResourceDeck.add("Grain");
                        ResourceDeck.add("Wool");
                        GameState.currentPlayer.addDev(DevelopmentCardDeck.draw());
                        devCardPanel.removeAll();
                        for (DevelopmentCard dc : GameState.currentPlayer.getDevCards()) {
                            int x = 0;
                            int y = 0;
                            JButton b = new JButton(new ImageIcon(resize(dc.getImage(), 25, 75)));
                            b.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    if (!devCardPlayed) {
                                        // use to be implemented
                                        devCardPlayed = true;
                                        GameState.currentPlayer.removeDev(dc);
                                        devCardPanel.remove(b);
                                        devCardPanel.revalidate();
                                    }
                                }
                            });
                            devCardPanel.add(b);
                            b.setBounds(x, y, 25, 75);
                            x+= 20;
                            devCards.revalidate();
                            revalidate();
                            repaint();
                        }
                        System.out.println(GameState.currentPlayer.toString() + " has bought a development card.");
                        trade.setEnabled(false);
                    } else {
                        System.out.println(GameState.currentPlayer.toString() + " was unable to buy a development card.");
                    }
                }
            }
        });

        endTurn.setEnabled(false);
        rollDice.setBounds(920, 30, 100, 50);
        rollDice.setBackground(new Color(255, 200, 100));
        rollDice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameState.rollDice();
                if (GameState.diceNum!=7) {
                    GameState.board.giveResources(GameState.getDiceNum());
                }
                else {
                    for (Player p:GameState.getPlayers()) {
                        if (p.size() > 7) {
                            int disc = p.size() / 2;
                            while (disc < p.size()) {
                                int response = JOptionPane.showOptionDialog(null, "Choose card to discard", p.toString() + " Discard Phase", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, p.getResourceCards().toArray(), p.getResourceCards().toArray()[0]);
                                p.remove(response);
                                repaint();
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(null,"Please select where you'd like to move the robber.");
                    state = 20;
                    ActionLogPanel.robber7();
                }
                endTurn.setEnabled(true);
                trade.setEnabled(true);
                build.setEnabled(true);
                rollDice.setEnabled(false);
                repaint();
            }
        });
        add(rollDice);

//      endTurn = new JButton("End Turn");
        endTurn.setBounds(265, 520, 100, 50);
        endTurn.setBackground(new Color(255, 200, 100));
        endTurn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(GameState.currentPlayer.toString() + " has ended their turn.");
                for (int i = 0; i < 4; i++) {
                    if (GameState.currentPlayer == GameState.players[i]) {
                        GameState.currentPlayer = GameState.players[(i + 1) % 4];
                        break;
                    }
                }
                rollDice.setEnabled(true);
                trade.setEnabled(false);
                build.setEnabled(false);
                endTurn.setEnabled(false);
                repaint();
            }
        });

//        helppls.setBounds(320, 625, 75, 50);
//        try {
//            Image HelpButton2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Images/help-button_adobespark.png")));
//            helppls.setIcon(new ImageIcon(HelpButton2));
//        } catch (Exception ex) {
//            System.out.println("All Images Loaded/Implemented Successfully, u alr know");
//        }
//        helppls.setBackground(new Color(255, 200, 100));
//        helppls.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                //frame3 = new HelpFrame("Help");
//            }
//        });
//        add(helppls);
        add(endTurn);
        add(build);
        add(trade);

    }

    public void paintComponent(Graphics g) {
        g.setColor(new Color(255, 230, 150));
        g.fillRect(0, 0, 1600, 900);
        g.drawImage(playerIndicator, -10, -15, 500, 139, null);
        g.setColor(Color.black);
        Font playerTitleFont = new Font("Serif", Font.BOLD, 50);
        Font tradeFont = new Font("Serif", Font.BOLD, 40);
        g.setFont(tradeFont);
        g.drawString("Trade Panel", 1190, 285);
        g.setFont(playerTitleFont);
        g.drawString(GameState.currentPlayer.toString(), 20, 75);
        g.setFont(tradeFont);
        g.drawString(GameState.currentPlayer.toString() + " Stats", 13, 650);

        g.drawImage(HelpButton,1575,25,40,40,null);


        Font victoryTitleFont = new Font("Serif", Font.BOLD, 20);
        g.setFont(victoryTitleFont);

        g.drawImage(trophy, 0, 675, 40, 40, null);
        g.drawString(GameState.currentPlayer.getSecretScore() + "", 40, 700);

        g.drawImage(sword, 60, 675, 40, 40, null);
        g.drawString(GameState.currentPlayer.getPlayedKnightCards() + "", 100, 700);

        g.drawImage(road, 120, 675, 40, 40, null);
        g.drawString(GameState.currentPlayer.getRoadsLeft() + "", 160, 700);

        g.drawImage(settlement, 195, 675, 40, 40, null);
        g.drawString(GameState.currentPlayer.getStlmtsLeft() + "", 240, 700);

        g.drawImage(city, 260, 665, 50, 50, null);
        g.drawString(GameState.currentPlayer.getCitiesLeft() + "", 310, 700);

        g.drawImage(ore, 0, 725, 40, 40, null);
        g.drawString(GameState.currentPlayer.getNumResources("Ore") + "", 40, 750);

        g.drawImage(grain, 60, 725, 40, 40, null);
        g.drawString(GameState.currentPlayer.getNumResources("Grain") + "", 100, 750);

        g.drawImage(lumber, 120, 725, 40, 40, null);
        g.drawString(GameState.currentPlayer.getNumResources("Lumber") + "", 160, 750);

        g.drawImage(wool, 180, 725, 40, 40, null);
        g.drawString(GameState.currentPlayer.getNumResources("Wool") + "", 220, 750);

        g.drawImage(brick, 240, 725, 40, 40, null);
        g.drawString(GameState.currentPlayer.getNumResources("Brick") + "", 280, 750);

        g.drawString("PUBLIC PLAYER STATS", 5, 145);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        g2.drawLine(7, 152, 380, 152);
        g2.drawLine(7, 152, 7, 300);
        //harbors

        g.drawImage(harbors.get(0), 437, 313, 50, 50, null);
        g.drawImage(harbors.get(1), 538, 107, 50, 50, null);
        g.drawImage(harbors.get(2), 755, 110, 50, 50, null);
        g.drawImage(harbors.get(3), 905, 205, 50, 50, null);
        g.drawImage(harbors.get(4), 1010, 400, 50, 50, null);
        g.drawImage(harbors.get(5), 895, 610, 50, 50, null);
        g.drawImage(harbors.get(6), 540, 706, 50, 50, null);
        g.drawImage(harbors.get(7), 750, 700, 50, 50, null);
        g.drawImage(harbors.get(8), 450, 510, 50, 50, null);

        g.drawString("Blue: ", 11, 180);
        g.drawString("Orange: ", 11, 220);
        g.drawString("Red: ", 11, 260);
        g.drawString("White: ", 11, 300);
        g.drawString(GameState.players[0].getPublicScore() + "", 130, 180);
        g.drawImage(trophy, 90, 158, 30, 30, null);
        g.drawString(GameState.players[1].getPublicScore() + "", 130, 220);
        g.drawImage(trophy, 90, 198, 30, 30, null);
        g.drawString(GameState.players[2].getPublicScore() + "", 130, 260);
        g.drawImage(trophy, 90, 238, 30, 30, null);
        g.drawString(GameState.players[3].getPublicScore() + "", 130, 300);
        g.drawImage(trophy, 90, 278, 30, 30, null);

        g.drawString(GameState.players[0].getResourceCards().size() + "", 190, 180);
        g.drawImage(resource, 150, 158, 30, 30, null);
        g.drawString(GameState.players[1].getResourceCards().size() + "", 190, 220);
        g.drawImage(resource, 150, 198, 30, 30, null);
        g.drawString(GameState.players[2].getResourceCards().size() + "", 190, 260);
        g.drawImage(resource, 150, 238, 30, 30, null);
        g.drawString(GameState.players[3].getResourceCards().size() + "", 190, 300);
        g.drawImage(resource, 150, 278, 30, 30, null);

        g.drawString(GameState.players[0].getRoadsLeft() + "", 250, 180);
        g.drawImage(road, 210, 158, 30, 30, null);
        g.drawString(GameState.players[1].getRoadsLeft() + "", 250, 220);
        g.drawImage(road, 210, 198, 30, 30, null);
        g.drawString(GameState.players[2].getRoadsLeft() + "", 250, 260);
        g.drawImage(road, 210, 238, 30, 30, null);
        g.drawString(GameState.players[3].getRoadsLeft() + "", 250, 300);
        g.drawImage(road, 210, 278, 30, 30, null);

        g.drawString(GameState.players[0].getStlmtsLeft() + "", 315, 180);
        g.drawImage(settlement, 280, 158, 30, 30, null);
        g.drawString(GameState.players[1].getStlmtsLeft() + "", 315, 220);
        g.drawImage(settlement, 280, 198, 30, 30, null);
        g.drawString(GameState.players[2].getStlmtsLeft() + "", 315, 260);
        g.drawImage(settlement, 280, 238, 30, 30, null);
        g.drawString(GameState.players[3].getStlmtsLeft() + "", 315, 300);
        g.drawImage(settlement, 280, 278, 30, 30, null);

        g.drawString(GameState.players[0].getCitiesLeft() + "", 375, 180);
        g.drawImage(city, 340, 158, 30, 30, null);
        g.drawString(GameState.players[1].getCitiesLeft() + "", 375, 220);
        g.drawImage(city, 340, 198, 30, 30, null);
        g.drawString(GameState.players[2].getCitiesLeft() + "", 375, 260);
        g.drawImage(city, 340, 238, 30, 30, null);
        g.drawString(GameState.players[3].getCitiesLeft() + "", 375, 300);
        g.drawImage(city, 340, 278, 30, 30, null);

        g.setFont(victoryTitleFont);
        g.drawString("DEVELOPMENT CARDS", 5, 373);
        g2.drawLine(7, 380, 360, 380);
        g2.drawLine(7, 380, 7, 480);
        g.drawRect(480, 20, 230, 60);
        Font diceRollFont = new Font("Serif", Font.BOLD, 20);
        g.setFont(diceRollFont);
        g.drawString("DICE ROLL TOTAL: " + GameState.diceNum, 490, 55);
        if (GameState.diceOne == 1) {
            g.drawImage(one, 740, 20, 60, 60, null);
        }
        if (GameState.diceOne == 2) {
            g.drawImage(two, 740, 20, 60, 60, null);
        }
        if (GameState.diceOne == 3) {
            g.drawImage(three, 740, 20, 60, 60, null);
        }
        if (GameState.diceOne == 4) {
            g.drawImage(four, 740, 20, 60, 60, null);
        }
        if (GameState.diceOne == 5) {
            g.drawImage(five, 740, 20, 60, 60, null);
        }
        if (GameState.diceOne == 6) {
            g.drawImage(six, 740, 20, 60, 60, null);
        }

        if (GameState.diceTwo == 1) {
            g.drawImage(one, 830, 20, 60, 60, null);
        }
        if (GameState.diceTwo == 2) {
            g.drawImage(two, 830, 20, 60, 60, null);
        }
        if (GameState.diceTwo == 3) {
            g.drawImage(three, 830, 20, 60, 60, null);
        }
        if (GameState.diceTwo == 4) {
            g.drawImage(four, 830, 20, 60, 60, null);
        }
        if (GameState.diceTwo == 5) {
            g.drawImage(five, 830, 20, 60, 60, null);
        }
        if (GameState.diceTwo == 6) {
            g.drawImage(six, 830, 20, 60, 60, null);
        }


        //drawing tiles and tokens
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 25));
        for (int i = 0; i < 3; i++) {
            g.drawImage(GameState.board.getTiles()[0][i].getImg(), 560 + i * 110, 142, 110, 145, null);
            if (!GameState.board.getTiles()[0][i].getIsDesert()) {
                if (GameState.board.getTiles()[0][i].getAssignedNum() == 6 || GameState.board.getTiles()[0][i].getAssignedNum() == 8)
                    g.setColor(Color.RED);
                g.drawString("" + GameState.board.getTiles()[0][i].getAssignedNum(), 607 + i * 112, 270);
                g.setColor(Color.WHITE);
            }
            GameState.board.getTiles()[0][i].setPixel(560 + i * 110, 142);

            g.drawImage(GameState.board.getTiles()[4][i].getImg(), 560 + i * 110, 578, 110, 145, null);
            if (!GameState.board.getTiles()[4][i].getIsDesert()) {
                if (GameState.board.getTiles()[4][i].getAssignedNum() == 6 || GameState.board.getTiles()[4][i].getAssignedNum() == 8)
                    g.setColor(Color.RED);
                g.drawString("" + GameState.board.getTiles()[4][i].getAssignedNum(), 607 + i * 112, 700);
                g.setColor(Color.WHITE);
            }
            GameState.board.getTiles()[4][i].setPixel(560 + i * 110, 578);
        }
        for (int i = 0; i < 4; i++) {
            g.drawImage(GameState.board.getTiles()[1][i].getImg(), 505 + i * 110, 251, 110, 145, null);
            if (!GameState.board.getTiles()[1][i].getIsDesert()) {
                if (GameState.board.getTiles()[1][i].getAssignedNum() == 6 || GameState.board.getTiles()[1][i].getAssignedNum() == 8)
                    g.setColor(Color.RED);
                g.drawString("" + GameState.board.getTiles()[1][i].getAssignedNum(), 550 + i * 112, 380);
                g.setColor(Color.WHITE);
            }
            GameState.board.getTiles()[1][i].setPixel(505 + i * 110, 251);

            g.drawImage(GameState.board.getTiles()[3][i].getImg(), 505 + i * 110, 469, 110, 145, null);
            if (!GameState.board.getTiles()[3][i].getIsDesert()) {
                if (GameState.board.getTiles()[3][i].getAssignedNum() == 6 || GameState.board.getTiles()[3][i].getAssignedNum() == 8)
                    g.setColor(Color.RED);
                g.drawString("" + GameState.board.getTiles()[3][i].getAssignedNum(), 550 + i * 112, 598);
                g.setColor(Color.WHITE);
            }
            GameState.board.getTiles()[3][i].setPixel(505 + i * 110, 469);
        }
        for (int i = 0; i < 5; i++) {
            g.drawImage(GameState.board.getTiles()[2][i].getImg(), 450 + i * 110, 360, 110, 145, null);
            if (!GameState.board.getTiles()[2][i].getIsDesert()) {
                if (GameState.board.getTiles()[2][i].getAssignedNum() == 6 || GameState.board.getTiles()[2][i].getAssignedNum() == 8)
                    g.setColor(Color.RED);
                g.drawString("" + GameState.board.getTiles()[2][i].getAssignedNum(), 490 + i * 112, 485);
                g.setColor(Color.WHITE);
            }
            GameState.board.getTiles()[2][i].setPixel(452 + i * 110, 360);
        }
        GameState.board.setTilesIntersectionsLocations();
        GameState.board.fillEdges();
        //drawing edges
        for (Edge e : GameState.board.getEdges()) {
            //e.setOwner(GameState.currentPlayer);
            if (e.getOwner() != null) {
                if (e.getOwner().getColor().equals("Blue")) {
                    g.setColor(blue);
//                    System.out.println("color has been set");
                } else if (e.getOwner().getColor().equals("Orange")) {
                    g.setColor(orange);
//                    System.out.println("color has been set");
                } else if (e.getOwner().getColor().equals("White")) {
                    g.setColor(white);
//                    System.out.println("color has been set");
                } else {
                    g.setColor(red);
//                    System.out.println("color has been set");
                }
                g.drawLine(e.getPoint1()[0], e.getPoint1()[1], e.getPoint2()[0], e.getPoint2()[1]);
            }
            //System.out.println(e.getPoint1()[0]+" "+e.getPoint1()[1]+"; "+e.getPoint2()[0]+" "+e.getPoint2()[1]);
        }
        //drawing stlmts and cities
        for (Intersection i : GameState.board.getIntersections()) {
            if (i.getOwner() != null) {
                g.drawImage(i.getImage(), i.getX() - 10, i.getY() - 10, 20, 20, null);
            }
        }
        //drawing Robber
        g.setColor(Color.GRAY);
        for (Tile[] tiles:GameState.board.getTiles()) {
            for (Tile t:tiles) {
                if (t.getHasRobber()) g.fillOval(t.getXPixel()+35, t.getYPixel()+53, 40, 40);
            }
        }
//        ArrayList<BufferedImage> harbors = new ArrayList<BufferedImage>();
//        for(int i = 0; i < 4; i++){
//            harbors.add(genericHarbor);
//        }
//        harbors.add(oreHarbor);
//        harbors.add(grainHarbor);
//        harbors.add(lumberHarbor);
//        harbors.add(woolHarbor);
//        harbors.add(brickHarbor);
//        Collections.shuffle(harbors);
//        g.drawImage(harbors.get(0), 437, 313, 50, 50, null);
//        g.drawImage(harbors.get(1), 538, 107, 50, 50, null);
//        g.drawImage(harbors.get(2), 755, 110, 50, 50, null);
//        g.drawImage(harbors.get(3), 905, 205, 50, 50, null);
//        g.drawImage(harbors.get(4), 1010, 400, 50, 50, null);
//        g.drawImage(harbors.get(5), 895, 610, 50, 50, null);
//        g.drawImage(harbors.get(6), 540, 706, 50, 50, null);
//        g.drawImage(harbors.get(7), 750, 700, 50, 50, null);
//        g.drawImage(harbors.get(8), 450, 510, 50, 50, null);

        //temporary section for checking intersection locations
//        int count=1;
//        for (Tile[] tiles: GameState.board.getTiles()) {
//            for (Tile tile:tiles) {
////                for (Intersection i:tile.getIntersections()) {
////                    g.fillRect(i.getLocation()[0], i.getLocation()[1], 10, 10);
////                }
////                Edge[] e=tile.getEdges();
//
//                g.setColor(Color.BLACK);
//                g.fillRect(tile.getIntersections()[0].getX(), tile.getIntersections()[0].getY(), 10, 10);
//                Edge[] e=tile.getIntersections()[0].getEdges();
//                g.drawLine(e[0].getPoint1()[0], e[0].getPoint1()[1], e[0].getPoint2()[0], e[0].getPoint2()[1]);
//                g.drawLine(e[1].getPoint1()[0], e[1].getPoint1()[1], e[1].getPoint2()[0], e[1].getPoint2()[1]);
//                g.drawLine(e[2].getPoint1()[0], e[2].getPoint1()[1], e[2].getPoint2()[0], e[2].getPoint2()[1]);
////                g.setColor(Color.RED);
////                g.fillRect(tile.getIntersections()[1].getX(), tile.getIntersections()[1].getY(), 10, 10);
//////                if (tile.getIntersections()[1]!=null) {
//////                    //System.out.println("intersections 1 are not null "+count);
//////                    count++;
//////                }
//////                System.out.println(e[1].getPoint1()[0]+", "+e[1].getPoint1()[1]+"; "+e[1].getPoint2()[0]+", "+e[1].getPoint2()[1]+"; "+count);
////                count++;
////                g.drawLine(e[1].getPoint1()[0], e[1].getPoint1()[1], e[1].getPoint2()[0], e[1].getPoint2()[1]);
////
////                g.setColor(Color.ORANGE);
////                g.fillRect(tile.getIntersections()[2].getX(), tile.getIntersections()[2].getY(), 10, 10);
////                g.drawLine(e[2].getPoint1()[0], e[2].getPoint1()[1], e[2].getPoint2()[0], e[2].getPoint2()[1]);
////
////                g.setColor(Color.YELLOW);
////                g.fillRect(tile.getIntersections()[3].getX(), tile.getIntersections()[3].getY(), 10, 10);
////                g.drawLine(e[3].getPoint1()[0], e[3].getPoint1()[1], e[3].getPoint2()[0], e[3].getPoint2()[1]);
////
////                g.setColor(Color.GREEN);
////                g.fillRect(tile.getIntersections()[4].getX(), tile.getIntersections()[4].getY(), 10, 10);
////                g.drawLine(e[4].getPoint1()[0], e[4].getPoint1()[1], e[4].getPoint2()[0], e[4].getPoint2()[1]);
////
////                g.setColor(Color.BLUE);
////                g.fillRect(tile.getIntersections()[5].getX(), tile.getIntersections()[5].getY(), 10, 10);
////                g.drawLine(e[5].getPoint1()[0], e[5].getPoint1()[1], e[5].getPoint2()[0], e[5].getPoint2()[1]);
//            }
//        }
    }


    public BufferedImage resize(BufferedImage img, int w, int h) {
        BufferedImage resizedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(img, 0, 0, w, h, null);
        g.dispose();

        return resizedImage;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();
//        if (x>=450 && x<=1000 && y>=140  && y<=720) {
        switch (state) {
            case 0: {
                GameState.initBuildSettlement();
                repaint();
                break;
            }
            case 1: {
                GameState.buildRoad();
                repaint();
                break;
            }
            case 2: {
                GameState.initBuildSettlement();
                repaint();
                break;
            }
            case 3: {
                GameState.buildRoad();
                repaint();
                break;
            }
            case 4: {
                GameState.initBuildSettlement();
                repaint();
                break;
            }
            case 5: {
                GameState.buildRoad();
                repaint();
                break;
            }
            case 6: {
                GameState.initBuildSettlement();
                repaint();
                break;
            }
            case 7: {
                GameState.buildRoad();
                repaint();
                break;
            }
            case 8: {
                GameState.initBuildSettlement();
                repaint();
                break;
            }
            case 9: {
                GameState.buildRoad();
                repaint();
                break;
            }
            case 10: {
                GameState.initBuildSettlement();
                repaint();
                break;
            }
            case 11: {
                GameState.buildRoad();
                repaint();
                break;
            }
            case 12: {
                GameState.initBuildSettlement();
                repaint();
                break;
            }
            case 13: {
                GameState.buildRoad();
                repaint();
                break;
            }
            case 14: {
                GameState.initBuildSettlement();
                repaint();
                break;
            }
            case 15: {
                GameState.buildRoad();
                repaint();
                break;
            }
            case 20: {
                GameState.moveRobber();
                repaint();
                break;
            }
        }
        switch(action) {
            case "Road": {
                GameState.buildRoad();
                repaint();
                break;
            }
            case "Settlement": {
                GameState.buildSettlement();
                repaint();
                break;
            }
            case "City": {
                GameState.buildCity();
                repaint();
                break;
            }
        }
        //}
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