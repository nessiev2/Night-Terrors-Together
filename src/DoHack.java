import java.awt.*;

public class DoHack extends Task{
    public DoHack(boolean isTask){
        super(isTask);
    }

    public void paint(Graphics g2d) {
        g2d.setColor(new Color(47, 47, 47));
        g2d.fillRect(900, 820, 50, 30);
    }
}
