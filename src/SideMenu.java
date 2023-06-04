import java.awt.*;

public class SideMenu {
    int x = 1835, y = 20, width = 50, height = 50, openX = Main.SCREEN_WIDTH/2 - 400, openY = 20, openWidth = 800, openHeight = 800;
    boolean isOpen = false;

    public boolean getIsOpen(){
        return isOpen;
    }

    public void updateIsOpen(boolean isOpen){
        this.isOpen = isOpen;
    }

    public void paint (Graphics2D g2d){
        g2d.setColor(Color.white);
        if (!isOpen){
            g2d.fillRect(x, y, width, height);
        } else {
            g2d.fillRect(openX, openY, openWidth, openHeight);
            g2d.setColor(Color.black);
            g2d.drawString("TASKS:", openX + openWidth - 500, openY + 70);
        }
    }
}
