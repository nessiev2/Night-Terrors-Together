import java.awt.*;

public class DoDissection extends Task{
    int x = 400, y = 280;
    public DoDissection(boolean isTask){
        super(isTask);
    }
    public void paint(Graphics g2d) {
        g2d.setColor(Color.green);
        g2d.fillRect(x, y, 30, 50);
    }
}
