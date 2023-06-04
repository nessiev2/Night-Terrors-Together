import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu {
    int x = 1835, y = 20, width = 50, height = 50, openX = Main.SCREEN_WIDTH/2 - 400, openY = 20, openWidth = 800, openHeight = 800;
    private boolean isOpen = true;
    private BufferedImage img;

    public boolean getIsMenuOpen() { return isOpen; }

    public void changeMenu(boolean b) { isOpen = b; }

    public MainMenu() {
        try {
            img = ImageIO.read(new File("res\\start-menu.png"));
        } catch (IOException e) { System.out.println("main menu no img"); }
    }

    public void paintMainMenu(Graphics2D g2d){
        if (isOpen){
            g2d.drawImage(img, 0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT, null);
        }
    }

}
