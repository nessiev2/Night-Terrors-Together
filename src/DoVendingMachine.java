import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DoVendingMachine extends Task {
    private BufferedImage vendInteract, vendOpen, vendBusted;
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
        try {
            vendInteract = ImageIO.read(new File("res\\vendinteract.png"));
            vendOpen = ImageIO.read(new File("res\\vendopen.png"));
            vendBusted = ImageIO.read(new File("res\\vendbusted.png"));
        } catch (IOException e) { System.out.println("frog no image"); }
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
                g2d.drawImage(vendInteract, x-i, y-i, null);
            }
            // draws vending machine
            g2d.drawImage(vendOpen, x, y, null);
        } else {
            if (p1.croom == 2 && isTask) {
                g2d.drawImage(vendBusted, x, y, null);
//                g2d.setColor(new Color(255, 0, 0));
//                g2d.fillRect(x, y, width, height);
            }
        }
    }
}


