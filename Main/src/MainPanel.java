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
    ArrayList<BufferedImage> harbors;
    private String playerIndStr = "PLAYER ONE";
    private BufferedImage playerIndicator, brick, ore, grain, lumber, wool, sword, trophy, resource, blueStlmt, blueCity, orangeStlmt, orangeCity, redStlmt, redCity, whiteStlmt, whiteCity;
    private BufferedImage genericHarbor, brickHarbor, grainHarbor, lumberHarbor, oreHarbor, woolHarbor;
    private BufferedImage settlement, city, road;
    private BufferedImage one, two, three, four, five, six;
    private JButton endTurn, build, trade, rollDice, playDevcard;
    private JPanel devCardPanel;
    private JScrollPane devCards;
    private boolean devCardPlayed;
    //private Font playerTitleFont;
    public static int x, y;
    private Color blue, orange, white, red;
    public static int state = 0;

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
//      devCardPanel = new JPanel();
        devCardPanel.setBackground(new Color(255, 220, 100));
//      devCards = new JScrollPane(devCardPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
                    p.repaint();

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
                    p.repaint();

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
                    p.repaint();

                    JLabel brick = new JLabel("Brick");
                    brick.setFont(new Font("Serif", 1, 15));
                    brick.setBounds(10, 170, 60, 30);
                    brick.requestFocus();
                    brick.setVisible(true);
                    JTextField brickText = new JTextField(10);
                    brickText.setBounds(70, 175, 30, 25);
                    brickText.setBackground(new Color(255, 220, 100));
                    p.add(brickText);
                    p.add(brick);
                    p.repaint();

                    JLabel wool = new JLabel("Wool");
                    wool.setFont(new Font("Serif", 1, 15));
                    wool.setBounds(10, 210, 60, 30);
                    wool.requestFocus();
                    wool.setVisible(true);
                    JTextField woolText = new JTextField(10);
                    woolText.setBounds(70, 215, 30, 25);
                    woolText.setBackground(new Color(255, 220, 100));
                    p.add(woolText);
                    p.add(wool);
                    p.repaint();
                    System.out.println(GameState.currentPlayer.toString() + " has requested to trade " + TradeManager.p1offer + ".");
                    JButton done = new JButton("Done");
                    done.setFont(new Font("Serif", 1, 15));
                    done.setBackground(new Color(255, 220, 100));
                    done.setBounds(115, 400, 75, 25);
                    done.addActionListener(new ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            if(oreText.isValid()){
                                String text = oreText.getText();
                                int numOre = Integer.parseInt(text);
                                if(numOre <= ResourceDeck.getNumLeft("Ore")) {
                                    System.out.println(GameState.currentPlayer.toString() + " has requested to trade " + text + " ores.");
                                }
                            }
                            tradeMessage.setText("");


                            JLabel tradeMessageTwo = new JLabel("");
                            tradeMessageTwo.setFont(new Font("Serif", 1, 15));
                            tradeMessageTwo.setBounds(0, -20, 400, 80);
                            tradeMessageTwo.setText("<html>Please type in how many of each resource<br>you wish to trade for.");
                            p.add(tradeMessageTwo);
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
                            p.repaint();

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
                            p.repaint();

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
                            p.repaint();

                            JLabel brick = new JLabel("Brick");
                            brick.setFont(new Font("Serif", 1, 15));
                            brick.setBounds(10, 170, 60, 30);
                            brick.requestFocus();
                            brick.setVisible(true);
                            JTextField brickText = new JTextField(10);
                            brickText.setBounds(70, 175, 30, 25);
                            brickText.setBackground(new Color(255, 220, 100));
                            p.add(brickText);
                            p.add(brick);
                            p.repaint();

                            JLabel wool = new JLabel("Wool");
                            wool.setFont(new Font("Serif", 1, 15));
                            wool.setBounds(10, 210, 60, 30);
                            wool.requestFocus();
                            wool.setVisible(true);
                            JTextField woolText = new JTextField(10);
                            woolText.setBounds(70, 215, 30, 25);
                            woolText.setBackground(new Color(255, 220, 100));
                            p.add(woolText);
                            p.add(wool);
                            p.repaint();
                        }

                        //check if input is not a number
                        //make sure they are not trading the same thing
                        //show what they requested on action log
                        //allow each player to accept or decline
                        //display whether player accepted or declined on action log
                        //give cards
                    });
                    p.add(done);
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
                if (response == 0) {
                    if (GameState.currentPlayer.getResourceCards().contains("Brick") && GameState.currentPlayer.getResourceCards().contains("Lumber")) {
                        GameState.currentPlayer.getResourceCards().remove("Brick");
                        GameState.currentPlayer.getResourceCards().remove("Lumber");
                        //let them select where they want to place road, check if they can
                        Edge road = GameState.getEdge(x, y);
                        if (road != null && road.canPlace(GameState.currentPlayer))
                            road.setOwner(GameState.currentPlayer);
                        System.out.println(GameState.currentPlayer.toString() + " has built a road.");
                        trade.setEnabled(false);
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
                        if (stlmt != null && stlmt.canPlace(GameState.currentPlayer))
                            stlmt.setOwner(GameState.currentPlayer);
                        System.out.println(GameState.currentPlayer.toString() + " has built a settlement.");
                        trade.setEnabled(false);
                    } else {
                        System.out.println(GameState.currentPlayer.toString() + " was unable to build a settlement.");
                    }
                }
                if (response == 2) {
                    int ore = 0;
                    for (int i = 0; i < GameState.currentPlayer.getResourceCards().size(); i++) {
                        ArrayList<ResourceCard> rc = GameState.currentPlayer.getResourceCards();
                        if (rc.get(i).getType().equals("Ore")) {
                            ore++;
                        }
                    }
                    int grain = 0;
                    for (int i = 0; i < GameState.currentPlayer.getResourceCards().size(); i++) {
                        ArrayList<ResourceCard> rc = GameState.currentPlayer.getResourceCards();
                        if (rc.get(i).getType().equals("Grain")) {
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
                        if (city != null && city.getOwner() == GameState.currentPlayer && city.isStlmt())
                            city.setIsCity();
                        System.out.println(GameState.currentPlayer.toString() + " has built a city.");
                        trade.setEnabled(false);
                    } else {
                        System.out.println(GameState.currentPlayer.toString() + " was unable to build a city.");
                    }
                    //let them select a settlement of theirs
                }
                if (response == 3) {
                    if (GameState.currentPlayer.getResourceCards().contains("Wool") && GameState.currentPlayer.getResourceCards().contains("Ore") && GameState.currentPlayer.getResourceCards().contains("Grain")) {
                        GameState.currentPlayer.getResourceCards().remove("Wool");
                        GameState.currentPlayer.getResourceCards().remove("Grain");
                        GameState.currentPlayer.getResourceCards().remove("Ore");
                        //take it from development card deck and display it visually
                        GameState.currentPlayer.addDev(DevelopmentCardDeck.draw());
                        devCardPanel.removeAll();
                        for (DevelopmentCard dc : GameState.currentPlayer.getDevCards()) {
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
                        trade.setEnabled(false);
                    } else {
                        System.out.println(GameState.currentPlayer.toString() + " was unable to buy a development card.");
                    }
                }
            }
            //JOptionPane optionPane = new JOptionPane("Choose what you want to build/buy.", JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION); //not done
            //JDialog dialog = optionPane.createDialog("Dialog");
            //dialog.setVisible(true);
        });

//      rollDice = new JButton("Roll Dice");
        endTurn.setEnabled(false);
        rollDice.setBounds(920, 30, 100, 50);
        rollDice.setBackground(new Color(255, 200, 100));
        rollDice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameState.rollDice();
                GameState.board.giveResources(GameState.getDiceNum());
                endTurn.setEnabled(true);
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
                trade.setEnabled(true);
                endTurn.setEnabled(false);
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
        g.setFont(playerTitleFont);
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