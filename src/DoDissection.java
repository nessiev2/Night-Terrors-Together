import java.awt.*;

public class DoDissection extends Task{
    private int x = 450, y = 600;
    private boolean[] isComplete = {false, false, false, false, false};
    public DoDissection(boolean isTask){
        super(isTask);
    }
    public void paint(Graphics g2d) {
        g2d.setColor(Color.green);

        // right column
        g2d.fillRect(x, y, 30, 50);
        g2d.fillRect(x, y + 100, 30, 50);
        g2d.fillRect(x, y + 2 * 100, 30, 50);

        // left column
        g2d.fillRect(x - 270, y, 30, 50);
        g2d.fillRect(x - 270, y + 100, 30, 50);
        g2d.fillRect(x - 270, y + 2 * 100, 30, 50);
    }

}
