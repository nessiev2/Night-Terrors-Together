import java.awt.*;

public class DoVendingMachine extends Task{
    private int x, y, width = 150, height = 200;
    private boolean playerIsClose;
    private final int RADIUS = 65;
    private boolean isThisVendComplete;

    public int getX() { return x; }
    public int getY() { return y; }

    public void initializeVM(){
        playerIsClose = false;
        isThisVendComplete = false;
    }

    public DoVendingMachine(int x, int y, boolean isTask) {
        super(isTask);
        this.x = x;
        this.y = y;
        initializeVM();
    }

    public void finishVending() {
        isThisVendComplete = true;
    }

    public boolean getVendComplete() {
        return isThisVendComplete;
    }

    public boolean isPlayerClose(Player p1, Player p2) {
        if ((p1.getCenterX() >= x - RADIUS && p1.getCenterX() <= x + width + RADIUS && y - p1.getY() - height <= RADIUS) || (p2.getCenterX() >= x - RADIUS && p2.getCenterX() <= x + width + RADIUS && y - p2.getY() - height <= RADIUS)) {
            playerIsClose = true;
            return true;
        }
        playerIsClose = false;
        return false;
    }

    public void paint(Graphics g2d, Player p1, Player p2) {
        int i = 10;
        isPlayerClose(p1, p2);

        if (!finished){
            if (playerIsClose && isTask) {
                // lights up yellow
                g2d.setColor(Color.yellow);
                g2d.fillRect(x-i, y-i, width+2*i, height+2*i);
            }
            // draws vending machine
            g2d.setColor(new Color(47, 47, 47));
            g2d.fillRect(x, y, width, height);
        } else {
            if (p1.croom == 2 && isTask) {
                g2d.setColor(new Color(255, 0, 0));
                g2d.fillRect(x, y, width, height);
            }
        }
    }
}


