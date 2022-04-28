import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
public class Ore extends ResourceCard{
    public Ore() throws IOException {
        super("Ore", ImageIO.read(Ore.class.getResource("Images/Final Ore Resource Card.png")));
    }
}
