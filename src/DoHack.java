import java.awt.*;

public class DoHack extends Task{
    private int x = 890, y = 820, width = 120, height = 80;
    private boolean playerIsClose = false;
    private final int RADIUS = 65;

    public DoHack(boolean isTask){
        super(isTask);
    }

    public boolean isPlayerClose(Player p1, Player p2) {
        if ((p1.getCenterX() >= x - RADIUS && p1.getCenterX() <= x + width + RADIUS && y - p1.getY() - height <= RADIUS) || (p2.getCenterX() >= x - RADIUS && p2.getCenterX() <= x + width + RADIUS && y - p2.getY() - height <= RADIUS)) {
            playerIsClose = true;
            return true;
        }

        playerIsClose = false;
        return false;
    }

    public void paint(Graphics g2d, Player p1, Player p2, SideMenu menu) {
        int i = 10;
        this.isPlayerClose(p1, p2);

        if (!finished){
            if (playerIsClose && isTask) {
                g2d.setColor(Color.yellow);
                g2d.fillRect(x-i, y-i, width+2*i, height+2*i);
            }
            g2d.setColor(new Color(47, 47, 47));
            g2d.fillRect(x, y, width, height);

        } else {
            g2d.setColor(new Color(255, 0, 0));
            g2d.fillRect(x, y, width, height);
            menu.updateTaskCompletion(3);
        }
    }
}


