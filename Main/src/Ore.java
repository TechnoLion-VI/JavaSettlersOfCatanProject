import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
public class Ore extends ResourceCard{
    public Ore() throws IOException {
        super("Ore", ImageIO.read(new File("Images/Final Ore Resource Card.png")));
    }
}
