import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class RoadBuilding extends DevelopmentCard{
    public RoadBuilding() {
        super("RoadBuilding");
        BufferedImage card;
        try {
            card= ImageIO.read(RoadBuilding.class.getResource("/Images/RoadBuilding.png"));
        }
        catch (Exception e) {
            System.out.println("Road Building Image Error");
            return;
        }
        setImg(card);
    }
//    public boolean use(Edge a, Edge b) {
//        return Board.buildRoad(GameState.currentPlayer, a) && Board.buildRoad(GameState.currentPlayer, b);
//    }
}
