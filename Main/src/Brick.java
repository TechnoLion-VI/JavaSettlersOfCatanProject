import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
public class Brick extends ResourceCard{
    public Brick() throws IOException {
        super("Brick", ImageIO.read(new File("Images/Final Brick Resource Card.png")));
    }
}
