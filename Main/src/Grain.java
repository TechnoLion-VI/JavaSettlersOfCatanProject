import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
public class Grain extends ResourceCard{
    public Grain() throws IOException {
        super("Grain", ImageIO.read(new File("Images/Final Grain Resource Card.png")));
    }
}
