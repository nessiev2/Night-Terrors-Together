import java.awt.*;

public class DoBadReaction extends Task{
    public DoBadReaction(boolean isTask){
        super(isTask);
    }
    public void paint(Graphics g2d) {
        g2d.setColor(new Color(1, 184, 252));

    }
}
