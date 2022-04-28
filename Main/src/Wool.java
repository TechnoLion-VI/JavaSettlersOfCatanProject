import javax.imageio.ImageIO;
import java.io.IOException;
public class Wool extends ResourceCard{
    public Wool() throws IOException {
        super("Wool", ImageIO.read(Wool.class.getResource("/Images/Final Wool Resource Card.png")));
    }
}
