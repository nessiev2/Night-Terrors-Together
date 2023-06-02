import java.awt.*;

public class Transition {
    private final static int width = 1920;
    private final static int height = 1080;

    public Transition() {}
    // start screen???
    // definitely end screen and the black screen to transition between rooms

    public void paintBlack(Graphics g2d) {
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, width, height);
    }

    public void paintStart(Graphics g2d) {
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.white);
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 200));
        g2d.drawString("MAIN MENU", width/2, height/2);
    }
}
