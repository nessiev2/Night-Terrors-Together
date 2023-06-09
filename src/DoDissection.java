import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DoDissection extends Task {
    private boolean ts = true;

    private BufferedImage alive, dead, froginteract;
    private int x = 450, y = 600, w = 30, h = 50;
    private final static int RADIUS = 130;
    private boolean[] isComplete = {false, false, false, false, false, false};
    private boolean[] interacted = {false, false, false, false, false, false};
    public DoDissection(boolean isTask){
        super(isTask);
        ts = true;
        try {
            alive = ImageIO.read(new File("res\\frogalive.png"));
            dead = ImageIO.read(new File("res\\frogdead.png"));
            froginteract = ImageIO.read(new File("res\\froginteract.png"));
        } catch (IOException e) { System.out.println("frog no image"); }
    }
    public boolean getIsClose(int i, Player p){
        if (i == 0){
            return isPlayerClose(x, y, p, p) ;
        }
        if (i == 1){
            return isPlayerClose(x, y + 100, p, p) ;
        }
        if (i == 2){
            return isPlayerClose(x, y + 2 * 100, p, p) ;
        }
        if (i == 3){
            return isPlayerClose(x - 270, y , p, p) ;
        }
        if (i == 4) {
            return isPlayerClose(x - 270, y + 100, p, p) ;
        }
        else {
            return isPlayerClose(x - 270, y + 2 * 100, p, p) ;
        }
    }
    public void changeIsClose(int i){
        if (i == 0){
            interacted[0] = true;
        }
        if (i == 1){
            interacted[1] = true;
        }
        if (i == 2){
            interacted[2] = true;
        }
        if (i == 3){
            interacted[3] = true;
        }
        if (i == 4) {
            interacted[4] = true;
        }
        else {
            interacted[5] = true;
        }
    }
    public void paint(Graphics g2d, Player p1, Player p2, SideMenu menu) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        int i = 10;
        if (checkWin()){
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 25));
            g2d.setColor(Color.black);
            g2d.drawString("task complete!", 250, 500 + 500);
            taskFinished();
            if (ts) {
                menu.updateTaskCompletion(2);
                ts = false;
            }
        }
        // drawing "glow"
        g2d.setColor(Color.yellow);

        if (isTask){
            if (isPlayerClose(x, y, p1, p2) && !interacted[0]) {
                g2d.drawImage(froginteract, x-2*i, y-2*i, null);
//                g2d.fillRect(x - i, y - i, w + 2 * i, h + 2 * i);
            }

            if (isPlayerClose(x, y + 100, p1, p2) && !interacted[1]) {
                g2d.drawImage(froginteract, x-2*i, y+100-2*i, null);
//                g2d.fillRect(x - i, y + 100 - i, w + 2 * i, h + 2 * i);
            }

            if (isPlayerClose(x, y + 2 * 100, p1, p2) && !interacted[2]) {
                g2d.drawImage(froginteract, x-2*i, y+2*100-2*i, null);
//                g2d.fillRect(x - i, y + 2 * 100 - i, w + 2 * i, h + 2 * i);
            }

            if (isPlayerClose(x - 270, y, p1, p2) && !interacted[3]) {
                g2d.drawImage(froginteract, x-270-2*i, y-2*i, null);
//                g2d.fillRect(x - 270 - i, y - i, w + 2 * i, h + 2 * i);
            }

            if (isPlayerClose(x - 270, y + 100, p1, p2) && !interacted[4]) {
                g2d.drawImage(froginteract, x-270-2*i, y+100-2*i, null);
//                g2d.fillRect(x - 270 - i, y + 100 - i, w + 2 * i, h + 2 * i);
            }

            if (isPlayerClose(x - 270, y + 2 * 100, p1, p2) && !interacted[5]) {
                g2d.drawImage(froginteract, x-270-2*i, y+2*100-2*i, null);
//                g2d.fillRect(x - 270 - i, y + 2 * 100 - i, w + 2 * i, h + 2 * i);
            }
        }

        g2d.drawImage(alive, x-i, y-i, null);
        g2d.drawImage(alive, x-i, y+100-i, null);
        g2d.drawImage(alive, x-i, y+2*100-i, null);
        g2d.drawImage(alive, x-270-i, y-i, null);
        g2d.drawImage(alive, x-270-i, y+100-i, null);
        g2d.drawImage(alive, x-270-i, y+2*100-i, null);

        if (isPlayerClose(x, y, p1, p2) || isPlayerClose(x, y + 100, p1, p2) && isPlayerClose(x, y + 2 * 100, p1, p2) || isPlayerClose(x - 270, y, p1, p2) || isPlayerClose(x - 270, y + 100, p1, p2) || isPlayerClose(x - 270, y + 2 * 100, p1, p2)){
            if (isTask && !checkWin()){
                instructions(g2d);
            }
        }

        // changing state of interacted
        if (isTask){
            if (isPlayerClose(x, y, p1, p2) && interacted[0]){
                isComplete[0] = true;
            }
            if (isPlayerClose(x, y + 100, p1, p2) && interacted[1]){
                isComplete[1] = true;
            }
            if (isPlayerClose(x, y + 2 * 100, p1, p2) && interacted[2]){
                isComplete[2] = true;
            }
            if (isPlayerClose(x - 270, y, p1, p2) && interacted[3]){
                isComplete[3] = true;
            }
            if (isPlayerClose(x - 270, y + 100, p1, p2) && interacted[4]){
                isComplete[4] = true;
            }
            if (isPlayerClose(x - 270, y + 2 * 100, p1, p2) && interacted[5]){
                isComplete[5] = true;
            }

            g2d.setColor(Color.red);

            if (isComplete[0]){
                g2d.drawImage(dead, x-i, y-i, null);
            }
            if (isComplete[1]){
                g2d.drawImage(dead, x-i, y+100-i, null);
            }
            if (isComplete[2]){
                g2d.drawImage(dead, x-i, y+2*100-i, null);
            }
            if (isComplete[3]){
                g2d.drawImage(dead, x-270-i, y-i, null);
            }
            if (isComplete[4]){
                g2d.drawImage(dead, x-270-i, y+100-i, null);
            }
            if (isComplete[5]){
                g2d.drawImage(dead, x-270-i, y+2*100-i, null);
            }
        }

    }

    public boolean checkWin() {
        for (boolean x: isComplete){
            if (!x){
                return false;
            }
        }
        return true;
    }

    public boolean isPlayerClose(int x, int y, Player p1, Player p2) {
        int centerX = (2*x + w)/2, centerY = (2*y + h)/2;
        double dist1 = Math.sqrt(Math.pow(centerX-p1.getCenterX(), 2) + Math.pow(centerY-p1.getY(), 2));
        double dist2 = Math.sqrt(Math.pow(centerX-p2.getCenterX(), 2) + Math.pow(centerY-p2.getY(), 2));

        if ((dist1 <= RADIUS)|| (dist2 <= RADIUS)) {
            return true;
        } else {
            return false;
        }
    }

    public void instructions(Graphics g2d){
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 25));
        g2d.setColor(Color.black);
        g2d.drawString("interact with all frogs", 225, 475 + 500);
        g2d.drawString("to dissect them", 225, 475+25+500);
    }
}
