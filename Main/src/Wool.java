import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
public class Wool extends ResourceCard{
    public Wool() throws IOException {
        super("Wool", ImageIO.read(new File("Images/Final Wool Resource Card.png")));
    }
}
