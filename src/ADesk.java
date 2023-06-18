import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ADesk extends Thing {
    private Image normdesk;
    private int width;
    private int height;
    private int deskType;
    boolean diffColour = false;
    public int getDeskType(){
        return deskType;
    }

    public ADesk(int x, int y) {
        super(x, y, 200, 150);
        width = 200;
        height = 150;
        try {
            normdesk = ImageIO.read(new File("res\\desknorm.png"));
        } catch (IOException e) { System.out.println("desk no image"); }
        deskType = 0;
    }

    public ADesk(int x, int y, int w, int h) {
        super(x, y, w, h);
        width = w;
        height = h;
        deskType = 1;
    }
    public ADesk(int x, int y, int w, int h, boolean diffColour) {
        super(x, y, w, h);
        width = w;
        height = h;
        this.diffColour = diffColour;
        deskType = 2;
    }

    public void paint(Graphics g2d) {
        if (!diffColour){
            g2d.setColor(new Color(150, 75, 0));
        }
        g2d.fillRect(getX(), getY(), width, height);
    }
    public void paintDesk0(Graphics g2d) {
        g2d.drawImage(normdesk, getX(), getY(), null);
    }
}
