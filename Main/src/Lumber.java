import javax.imageio.ImageIO;
import java.io.IOException;
public class Lumber extends ResourceCard{
    public Lumber() throws IOException {
        super("Lumber", ImageIO.read(Lumber.class.getResource("Images/Final Lumber Resource Card.png")));
    }
}
