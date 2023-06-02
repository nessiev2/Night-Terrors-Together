import java.awt.*;

public class Menuu {
    int x = 1835, y = 20, width = 50, height = 50, openX = Main.SCREEN_WIDTH/2 - 400, openY = 20, openWidth = 800, openHeight = 800;
    boolean isOpen = false;

    public Menuu(){
    }

    public void paint (Graphics2D g2d){
        if (!isOpen){
            g2d.setColor(Color.white);
            g2d.fillRect(x, y, width, height);
        } else {

        }
    }
}
