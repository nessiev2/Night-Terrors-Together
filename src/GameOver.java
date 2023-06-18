import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameOver {
    private int x = 1835, y = 20, width = 50, height = 50, openX = NTT.SCREEN_WIDTH/2 - 400, openY = 20, openWidth = 800, openHeight = 800;
    private BufferedImage gameOverScreen;
    private BufferedImage youWinScreen;

    public GameOver() {
        try {
            gameOverScreen = ImageIO.read(new File("res\\gameover.png"));
            youWinScreen = ImageIO.read(new File("res\\youWinScreen.png"));
        } catch (IOException e) { System.out.println("gameover no img"); }
    }

    public void paintGameOverYouWin(Graphics2D g2d) {
        g2d.drawImage(youWinScreen, 0, 0, NTT.SCREEN_WIDTH, NTT.SCREEN_HEIGHT, null);
//        g2d.setColor(Color.pink);
//        g2d.setFont(new Font("TimesRoman", Font.BOLD, 300));
//        g2d.drawString("U WIN", 500, 500);
    }

    public void paintGameOver(Graphics2D g2d){
        g2d.drawImage(gameOverScreen, 0, 0, NTT.SCREEN_WIDTH, NTT.SCREEN_HEIGHT, null);
    }

}
