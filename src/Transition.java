import java.awt.*;

public class Transition {
    private final static int width = 1920;
    private final static int height = 1080;

    public Transition() {}
    // start screen???
    // definitely end screen and the black screen to transition between rooms

    public void paint(Graphics g2d) {
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, width, height);
    }
}