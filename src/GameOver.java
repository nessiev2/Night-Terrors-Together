import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameOver {
    int x = 1835, y = 20, width = 50, height = 50, openX = Main.SCREEN_WIDTH/2 - 400, openY = 20, openWidth = 800, openHeight = 800;
    private BufferedImage img;

    public GameOver() {
        try {
            img = ImageIO.read(new File("res\\gameover.png"));
        } catch (IOException e) { System.out.println("gameover no img"); }
    }

    public void paintGameOver(Graphics2D g2d){
        g2d.drawImage(img, 0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT, null);

    }

}
