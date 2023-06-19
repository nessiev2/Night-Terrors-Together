import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DoHack extends Task{
    private boolean ts = true;

    private BufferedImage monitor, monitorInteract, monitorBusted;
    private int x = 890, y = 820, width = 120, height = 80;
    private boolean playerIsClose = false;
    private final int RADIUS = 65;

    public DoHack(boolean isTask){
        super(isTask);
        ts = true;
        try {
            monitorInteract = ImageIO.read(new File("res\\hackmonitorI.png"));
            monitorBusted = ImageIO.read(new File("res\\hackmonitorB.png"));
            monitor = ImageIO.read(new File("res\\hackmonitor.png"));
        } catch (IOException e) { System.out.println("hack no image"); }
    }

    public boolean isPlayerClose(Player p1, Player p2) {
        if ((p1.getCenterX() >= x - RADIUS && p1.getCenterX() <= x + width + RADIUS && y - p1.getY() - height <= RADIUS) || (p2.getCenterX() >= x - RADIUS && p2.getCenterX() <= x + width + RADIUS && y - p2.getY() - height <= RADIUS)) {
            playerIsClose = true;
            return true;
        }
        playerIsClose = false;
        return false;
    }

    public void paint(Graphics g2d, Player p1, Player p2, SideMenu menu) throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        int i = 10;
        if (isTask){
            isPlayerClose(p1, p2);
        } else {
            playerIsClose = false;
        }

        if (!finished){
            if (playerIsClose && isTask) {// && isTask) {
                g2d.setFont(new Font("TimesRoman", Font.BOLD, 25));
                g2d.setColor(Color.black);
                g2d.drawString("hold interact to smash", x - 50, y + 120);
                g2d.drawString("this expensive computer", x - 50, y + 145);
                g2d.drawImage(monitorInteract, x-i, y-i, null);
            }
            g2d.drawImage(monitor, x, y, null);

        } else {
            if (isTask){
                g2d.drawImage(monitorBusted, x, y, null);
                if (ts) {
                    menu.updateTaskCompletion(3);
                    ts = false;
                }

                g2d.setFont(new Font("TimesRoman", Font.BOLD, 25));
                g2d.setColor(Color.black);
                g2d.drawString("task complete!", x - 50, y + 120);
            }
        }
    }
}


