import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DevelopmentCardDeck {
    private static ArrayList<DevelopmentCard> deck;

    static {
        deck = new ArrayList<>();
        for (int i = 0; i < 14; i++) deck.add(new Knight());    //adding 14 knight cards

        for (int i = 0; i < 2; i++) {   //adding 2 of each dev card
            deck.add(new Monopoly());
            deck.add(new YearOfPlenty());
            deck.add(new RoadBuilding());
        }

        ArrayList<BufferedImage> images=new ArrayList<>();  //creating and adding one of each vp
        try {
            images.add(ImageIO.read(DevelopmentCardDeck.class.getResource("/Images/ChapelVP.png")));
            images.add(ImageIO.read(DevelopmentCardDeck.class.getResource("/Images/GovernorsHouseVP.png")));
            images.add(ImageIO.read(DevelopmentCardDeck.class.getResource("/Images/LibraryVP.png")));
            images.add(ImageIO.read(DevelopmentCardDeck.class.getResource("/Images/MarketVP.png")));
            images.add(ImageIO.read(DevelopmentCardDeck.class.getResource("/Images/UniversityVP.png")));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for (BufferedImage b:images) {
            DevelopmentCard dc=new VictoryPoint();
            dc.setImg(b);
            deck.add(dc);
        }

        Collections.shuffle(deck);
    }
    public static DevelopmentCard draw() {
        return deck.remove(0);
    }
}
