import java.awt.*;

public class MainMenu {
    int x = 1835, y = 20, width = 50, height = 50, openX = Main.SCREEN_WIDTH/2 - 400, openY = 20, openWidth = 800, openHeight = 800;
    private boolean isOpen = true;

    public boolean getIsMenuOpen() { return isOpen; }

    public void changeMenu(boolean b) { isOpen = b; }

    public MainMenu() {}

    public void paintMainMenu(Graphics2D g2d){
        if (isOpen){
            g2d.setColor(Color.black);
            g2d.fillRect(0, 0, 1920, 1080);
            g2d.setColor(Color.white);

            g2d.setFont(new Font("TimesRoman", Font.BOLD, 100));
            g2d.drawString("NIGHT TERRORS TOGETHER", 225, 500);

            g2d.setFont(new Font("TimesRoman", Font.BOLD, 50));
            g2d.drawString("press enter to start", 400, 650);
        }
    }

}
