import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
public class Lumber extends ResourceCard{
    public Lumber() throws IOException {
        super("Lumber", ImageIO.read(new File("Images/Final Lumber Resource Card.png")));
    }
}
