import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
public class Grain extends ResourceCard{
    public Grain() throws IOException {
        super("Grain", ImageIO.read(Grain.class.getResource("Images/Final Grain Resource Card.png")));
    }
}
