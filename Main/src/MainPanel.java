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
    private ArrayList<Integer> xCoords;  //for intersections
    private ArrayList<Integer> yCoords;
    private String playerIndStr = "PLAYER ONE";
    private BufferedImage playerIndicator, brick, ore, grain, lumber, wool, sword, trophy, resource, blueStlmt, blueCity, orangeStlmt, orangeCity, redStlmt, redCity, whiteStlmt, whiteCity ;
    private BufferedImage genericHarbor, brickHarbor, grainHarbor, lumberHarbor, oreHarbor, woolHarbor;
    private BufferedImage settlement, city, road;
    private BufferedImage one, two, three, four, five, six;
    private JButton endTurn, build, trade, rollDice;
    private JPanel devCardPanel;
    private JScrollPane devCards;
    private boolean devCardPlayed;
    //private Font playerTitleFont;
    public static int x, y;

    public MainPanel() {
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
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
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
        /* ACTION LOG STUFF */
        JTextArea log = new JTextArea(50, 50);
        log.setBackground(new Color(255, 220, 100));
        //change font later
        log.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        log.setEditable(false);
        log.setLineWrap(true);
        PrintStream printStream = new PrintStream(new ActionLogPanel(log));
        System.setOut(printStream);
        JScrollPane logPanel = new JScrollPane(log, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        logPanel.setBounds(1100, 10, 400, 200);
        add(logPanel);
        /* DEVELOPMENT CARDS PANEL */
        devCardPanel = new JPanel();
        devCardPanel.setBackground(new Color(255, 220, 100));
        devCards = new JScrollPane(devCardPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        devCards.setBounds(12, 385, 350, 100);
        add(devCards);
        /* END TURN, CLAIM WIN, BUILD, TRADE BUTTONS */
        trade = new JButton("Trade");
        trade.setBounds(15, 520, 100, 50);
        trade.setBackground(new Color(255, 200, 100));
        trade.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String[] options = new String[] {"Domestic", "Maritime"};
                int response = JOptionPane.showOptionDialog(null, "Choose what type of trade you wish to perform.", "Trade",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
                if(response == 0){
                    JPanel p = new JPanel();
                    p.setBounds(0, 0, 100, 100);
                    p.setLocation(0, 0);
                    int x = 1150;
                    int y = 350;
                    for(int i = 0; i < GameState.currentPlayer.getResourceCards().size(); i++){
                        JCheckBox c1 = new JCheckBox(GameState.currentPlayer.getResourceCards().get(i) + "");
                        c1.setBounds(x,y, 100,150);
                        p.add(c1);
                        if(y >= 600){
                            x = 1300;
                            y = 350;
                        }
                        else{
                            y += 60;
                        }
                    }
                    System.out.println(GameState.currentPlayer.toString() + " has requested to trade " + TradeManager.p1offer + ".");
                    add(p);

//                    JCheckBox brick = new JCheckBox("Brick");
//                    brick.setBounds(100,100, 150,150);
//                    JCheckBox grain = new JCheckBox("Grain");
//                    grain.setBounds(100,150, 150,150);
//                    JCheckBox lumber = new JCheckBox("Lumber");
//                    lumber.setBounds(100,150, 150,150);
//                    JCheckBox ore = new JCheckBox("Ore");
//                    ore.setBounds(100,150, 150,150);
//                    JCheckBox wool = new JCheckBox("Wool");
//                    wool.setBounds(100,150, 150,150);
//                    p.add(brick);
//                    p.add(grain);
//                    p.add(lumber);
//                    p.add(ore);
//                    p.add(wool);
                    //add(p);

                    p.setLocation(1150,300);
                    p.setSize(600,600);
                    p.setBackground(new Color(255, 220, 100));
                    p.setLayout(null);
                    p.setVisible(true);

                }
                if(response == 1){
                    //maritime
                    System.out.println(GameState.currentPlayer.toString() + " has performed a maritime trade at a harbor."); //NOT DONE; need to specify kind of harbor
                }
                repaint();
            }
    });

        build = new JButton("Build/Buy");
        build.setBounds(140, 520, 100, 50);
        build.setBackground(new Color(255, 200, 100));
        build.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                String[] options = new String[] {"Road", "Settlement", "City", "Development Card"};
                int response = JOptionPane.showOptionDialog(null, "Choose what you want to build/buy.", "Build/Buy",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                            null, options, options[0]);
                if (response == 0) {
                    if (GameState.currentPlayer.getResourceCards().contains("Brick") && GameState.currentPlayer.getResourceCards().contains("Lumber")) {
                        GameState.currentPlayer.getResourceCards().remove("Brick");
                        GameState.currentPlayer.getResourceCards().remove("Lumber");
                        //let them select where they want to place road, check if they can
                        Edge road = GameState.getEdge(x, y);
                        if (road != null && road.canPlace(GameState.currentPlayer)) road.setOwner(GameState.currentPlayer);
                        System.out.println(GameState.currentPlayer.toString() + " has built a road.");
                    } else {
                        System.out.println(GameState.currentPlayer.toString() + " was unable to build a road.");
                    }
                }
                if (response == 1) {
                    if (GameState.currentPlayer.getResourceCards().contains("Brick") && GameState.currentPlayer.getResourceCards().contains("Lumber") && GameState.currentPlayer.getResourceCards().contains("Wool") && GameState.currentPlayer.getResourceCards().contains("Grain")) {
                        GameState.currentPlayer.getResourceCards().remove("Brick");
                        GameState.currentPlayer.getResourceCards().remove("Lumber");
                        GameState.currentPlayer.getResourceCards().remove("Wool");
                        GameState.currentPlayer.getResourceCards().remove("Grain");
                        //let them select where they want to place settlement, check if they can
                        Intersection stlmt = GameState.getIntersection(x, y);
                        if (stlmt != null && stlmt.canPlace(GameState.currentPlayer)) stlmt.setOwner(GameState.currentPlayer);
                        System.out.println(GameState.currentPlayer.toString() + " has built a settlement.");
                    } else {
                        System.out.println(GameState.currentPlayer.toString() + " was unable to build a settlement.");
                    }
                }
                if (response == 2){
                    int ore = 0;
                    for (int i = 0; i < GameState.currentPlayer.getResourceCards().size(); i++){
                        ArrayList<ResourceCard> rc = GameState.currentPlayer.getResourceCards();
                        if(rc.get(i).getType().equals("Ore")){
                            ore++;
                        }
                    }
                    int grain = 0;
                    for (int i = 0; i < GameState.currentPlayer.getResourceCards().size(); i++){
                        ArrayList<ResourceCard> rc = GameState.currentPlayer.getResourceCards();
                        if (rc.get(i).getType().equals("Grain")){
                            grain++;
                        }
                    }
                    if (ore >= 3 && grain >= 2) {
                        GameState.currentPlayer.getResourceCards().remove("Ore");
                        GameState.currentPlayer.getResourceCards().remove("Ore");
                        GameState.currentPlayer.getResourceCards().remove("Ore");
                        GameState.currentPlayer.getResourceCards().remove("Grain");
                        GameState.currentPlayer.getResourceCards().remove("Grain");

                        Intersection city = GameState.getIntersection(x, y);
                        if (city != null && city.getOwner() == GameState.currentPlayer && city.isStlmt()) city.setIsCity();
                        System.out.println(GameState.currentPlayer.toString() + " has built a city.");
                    } else {
                        System.out.println(GameState.currentPlayer.toString() + " was unable to build a city.");
                    }
                    //let them select a settlement of theirs
                }
                if (response == 3){
                    if (GameState.currentPlayer.getResourceCards().contains("Wool") && GameState.currentPlayer.getResourceCards().contains("Ore") && GameState.currentPlayer.getResourceCards().contains("Grain")){
                        GameState.currentPlayer.getResourceCards().remove("Wool");
                        GameState.currentPlayer.getResourceCards().remove("Grain");
                        GameState.currentPlayer.getResourceCards().remove("Ore");
                        //take it from development card deck and display it visually
                        GameState.currentPlayer.addDev(DevelopmentCardDeck.draw());
                        devCardPanel.removeAll();
                        for (DevelopmentCard dc:GameState.currentPlayer.getDevCards()) {
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
                            devCards.revalidate();
                            revalidate();
                        }
                        System.out.println(GameState.currentPlayer.toString() + " has bought a development card.");
                    } else {
                        System.out.println(GameState.currentPlayer.toString() + " was unable to buy a development card.");
                    }
                }
            }
                //JOptionPane optionPane = new JOptionPane("Choose what you want to build/buy.", JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION); //not done
                //JDialog dialog = optionPane.createDialog("Dialog");
                //dialog.setVisible(true);
        });

        rollDice = new JButton("Roll Dice");
        rollDice.setBounds(970, 30, 100, 50);
        rollDice.setBackground(new Color(255, 200, 100));
        rollDice.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                GameState.rollDice();
            }});
        add(rollDice);

        endTurn = new JButton("End Turn");
        endTurn.setBounds(265, 520, 100, 50);
        endTurn.setBackground(new Color(255, 200, 100));
        endTurn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(GameState.currentPlayer.toString() + " has ended their turn.");
                for (int i = 0; i < 4; i++) {
                    if (GameState.currentPlayer == GameState.players[0]) {
                            GameState.currentPlayer = GameState.players[1];
                            break;
                    }
                    else if(GameState.currentPlayer == GameState.players[1]){
                        GameState.currentPlayer = GameState.players[2];
                        break;
                    }
                    else if(GameState.currentPlayer == GameState.players[2]){
                        GameState.currentPlayer = GameState.players[3];
                        break;
                    }
                    else if(GameState.currentPlayer == GameState.players[3]){
                        GameState.currentPlayer = GameState.players[0];
                        break;
                    }
                    else{
                        GameState.currentPlayer = GameState.players[0];
                        break;
                    }
                }
                repaint();
            }
        });

        add(endTurn);
        add(build);
        add(trade);
        //GameState.setUpPhase();
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
        g.setFont (playerTitleFont);
        g.drawString(GameState.currentPlayer.toString(), 20, 75);
        g.setFont(tradeFont);
        g.drawString(GameState.currentPlayer.toString() + " Stats", 13, 650);

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

        //g.setFont(myFontsmall);

        g.drawString("PUBLIC PLAYER STATS",5,145);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        g2.drawLine(7,152,380,152);
        g2.drawLine(7,152,7,300);
        //harbors
        ArrayList<BufferedImage> harbors = new ArrayList<BufferedImage>();
        for(int i = 0; i < 4; i++){
            harbors.add(genericHarbor);
        }
        harbors.add(oreHarbor);
        harbors.add(grainHarbor);
        harbors.add(lumberHarbor);
        harbors.add(woolHarbor);
        harbors.add(brickHarbor);
        Collections.shuffle(harbors);
        g.drawImage(harbors.get(0), 437, 313, 50, 50, null);
        g.drawImage(harbors.get(1), 538, 107, 50, 50, null);
        g.drawImage(harbors.get(2), 755, 110, 50, 50, null);
        g.drawImage(harbors.get(3), 905, 205, 50, 50, null);
        g.drawImage(harbors.get(4), 1010, 400, 50, 50, null);
        g.drawImage(harbors.get(5), 895, 610, 50, 50, null);
        g.drawImage(harbors.get(6), 540, 706, 50, 50, null);
        g.drawImage(harbors.get(7), 750, 700, 50, 50, null);
        g.drawImage(harbors.get(8), 450, 510, 50, 50, null);

        g.drawString("Blue: ",11,180);
        g.drawString("Orange: ",11,220);
        g.drawString("Red: ",11,260);
        g.drawString("White: ",11,300);
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

        g.drawString(GameState.players[0].getRoadsLeft() +"", 250, 180);
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
        g.drawString("DEVELOPMENT CARDS",5,373);
        g2.drawLine(7,380,360,380);
        g2.drawLine(7,380,7,480);
        g.drawRect(480,20, 230,60);
        Font diceRollFont = new Font("Serif", Font.BOLD, 20);
        g.setFont(diceRollFont);
        g.drawString("DICE ROLL TOTAL: " + GameState.diceNum, 490, 55);

        //drawing tiles and tokens
        g.setFont(new Font("Serif", Font.BOLD, 25));
        for (int i=0; i<3; i++) {
            g.drawImage(GameState.board.getTiles()[0][i].getImg(), 560+i*110, 142, 110, 145, null);
            if (!GameState.board.getTiles()[0][i].getIsDesert()) {
                if (GameState.board.getTiles()[0][i].getAssignedNum()==6 || GameState.board.getTiles()[0][i].getAssignedNum()==8)
                    g.setColor(Color.RED);
                g.drawString(""+GameState.board.getTiles()[0][i].getAssignedNum(), 607+i*112, 270);
                g.setColor(Color.WHITE);
            }
            GameState.board.getTiles()[0][i].setPixel(560+i*110, 142);

            g.drawImage(GameState.board.getTiles()[4][i].getImg(), 560+i*110, 578, 110, 145, null);
            if (!GameState.board.getTiles()[4][i].getIsDesert()) {
                if (GameState.board.getTiles()[4][i].getAssignedNum()==6 || GameState.board.getTiles()[4][i].getAssignedNum()==8)
                    g.setColor(Color.RED);
                g.drawString("" + GameState.board.getTiles()[4][i].getAssignedNum(), 607 + i * 112, 700);
                g.setColor(Color.WHITE);
            }
            GameState.board.getTiles()[4][i].setPixel(560+i*110, 578);
        }
        for (int i=0; i<4; i++) {
            g.drawImage(GameState.board.getTiles()[1][i].getImg(), 505+i*110, 251, 110, 145, null);
            if (!GameState.board.getTiles()[1][i].getIsDesert()) {
                if (GameState.board.getTiles()[1][i].getAssignedNum()==6 || GameState.board.getTiles()[1][i].getAssignedNum()==8)
                    g.setColor(Color.RED);
                g.drawString(""+GameState.board.getTiles()[1][i].getAssignedNum(), 550+i*112, 380);
                g.setColor(Color.WHITE);
            }
            GameState.board.getTiles()[1][i].setPixel(505+i*110, 251);

            g.drawImage(GameState.board.getTiles()[3][i].getImg(), 505+i*110, 469, 110, 145, null);
            if (!GameState.board.getTiles()[3][i].getIsDesert()) {
                if (GameState.board.getTiles()[3][i].getAssignedNum()==6 || GameState.board.getTiles()[3][i].getAssignedNum()==8)
                    g.setColor(Color.RED);
                g.drawString(""+GameState.board.getTiles()[3][i].getAssignedNum(), 550+i*112, 598);
                g.setColor(Color.WHITE);
            }
            GameState.board.getTiles()[3][i].setPixel(505+i*110, 469);
        }
        for (int i=0; i<5; i++) {
            g.drawImage(GameState.board.getTiles()[2][i].getImg(), 450+i*110, 360, 110, 145, null);
            if (!GameState.board.getTiles()[2][i].getIsDesert()) {
                if (GameState.board.getTiles()[2][i].getAssignedNum()==6 || GameState.board.getTiles()[2][i].getAssignedNum()==8)
                    g.setColor(Color.RED);
                g.drawString(""+GameState.board.getTiles()[2][i].getAssignedNum(), 490+i*112, 485);
                g.setColor(Color.WHITE);
            }
            GameState.board.getTiles()[2][i].setPixel(452+i*110, 360);
        }
        GameState.board.setTilesIntersectionsLocations();
        //drawing edges
        for (Edge e:GameState.board.getEdges()) {
            if (e.getOwner()!=null) {
                if (e.getOwner().getColor().equals("Blue"))
                    g.setColor(Color.BLUE);
                else if (e.getOwner().getColor().equals("Orange"))
                    g.setColor(Color.ORANGE);
                else if (e.getOwner().getColor().equals("White"))
                    g.setColor(Color.WHITE);
                else g.setColor(Color.RED);
                g.drawLine(e.getPoint1()[0], e.getPoint1()[1], e.getPoint2()[0], e.getPoint2()[1]);
            }
        }
        //drawing stlmts and cities
        for (Intersection i:GameState.board.getIntersections()) {
            if (i.getOwner()!=null) {
                g.drawImage(i.getImage(), i.getX()-10, i.getY()-10, 20, 20, null);
            }
        }
        //temporary section for checking intersection locations
//        int count=1;
//        for (Tile[] tiles: GameState.board.getTiles()) {
//            for (Tile tile:tiles) {
////                for (Intersection i:tile.getIntersections()) {
////                    g.fillRect(i.getLocation()[0], i.getLocation()[1], 10, 10);
////                }
//                Edge[] e=tile.getEdges();
//
//                g.setColor(Color.BLACK);
//                g.fillRect(tile.getIntersections()[0].getX(), tile.getIntersections()[0].getY(), 10, 10);
//                g.drawLine(e[0].getPoint1()[0], e[0].getPoint1()[1], e[0].getPoint2()[0], e[0].getPoint2()[1]);
//                g.setColor(Color.RED);
//                g.fillRect(tile.getIntersections()[1].getX(), tile.getIntersections()[1].getY(), 10, 10);
////                if (tile.getIntersections()[1]!=null) {
////                    //System.out.println("intersections 1 are not null "+count);
////                    count++;
////                }
////                System.out.println(e[1].getPoint1()[0]+", "+e[1].getPoint1()[1]+"; "+e[1].getPoint2()[0]+", "+e[1].getPoint2()[1]+"; "+count);
//                count++;
//                g.drawLine(e[1].getPoint1()[0], e[1].getPoint1()[1], e[1].getPoint2()[0], e[1].getPoint2()[1]);
//
//                g.setColor(Color.ORANGE);
//                g.fillRect(tile.getIntersections()[2].getX(), tile.getIntersections()[2].getY(), 10, 10);
//                g.drawLine(e[2].getPoint1()[0], e[2].getPoint1()[1], e[2].getPoint2()[0], e[2].getPoint2()[1]);
//
//                g.setColor(Color.YELLOW);
//                g.fillRect(tile.getIntersections()[3].getX(), tile.getIntersections()[3].getY(), 10, 10);
//                g.drawLine(e[3].getPoint1()[0], e[3].getPoint1()[1], e[3].getPoint2()[0], e[3].getPoint2()[1]);
//
//                g.setColor(Color.GREEN);
//                g.fillRect(tile.getIntersections()[4].getX(), tile.getIntersections()[4].getY(), 10, 10);
//                g.drawLine(e[4].getPoint1()[0], e[4].getPoint1()[1], e[4].getPoint2()[0], e[4].getPoint2()[1]);
//
//                g.setColor(Color.BLUE);
//                g.fillRect(tile.getIntersections()[5].getX(), tile.getIntersections()[5].getY(), 10, 10);
//                g.drawLine(e[5].getPoint1()[0], e[5].getPoint1()[1], e[5].getPoint2()[0], e[5].getPoint2()[1]);
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
