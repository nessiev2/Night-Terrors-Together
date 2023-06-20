import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DoArson extends Task {
    private int count = 0;
    private Image gif = null;
    private boolean isFin = false;
    private Image img;
    private final int RADIUS = 200;
    private boolean isOnFire;
    ATrashCan trash;
    public boolean getIsFin(){
        return isFin;
    }
    public boolean getOnFire(){
        return isOnFire;
    }
    public void setOnFire(){
        isOnFire = true;
    }
    public DoArson(boolean isTask, ATrashCan trash){
        super(isTask);
        this.trash = trash;
        try {
            img = ImageIO.read(new File("res\\arson.png"));
            gif = Toolkit.getDefaultToolkit().createImage("res\\firegif.gif");
        } catch (IOException e) { System.out.println("arson no image"); }
    }

    public void paint(Graphics g2d, Player p1, Player p2, SideMenu menu) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        int i = 10;

        // draws the glow around the trash can
        for (int j = 0; j < 3; j++){
            g2d.setColor(Color.red);

            if (isOnFire && isTask) {
                isFin = true;
                g2d.drawImage(gif, trash.getX()+2*i, trash.getY()+i, trash.getWidth()-2*i, trash.getHeight()-2*i, null);
            }
        }
    }

    public boolean isPlayerClose(Player p) {
        int centerX = (2*trash.getX() + trash.getWidth())/2, centerY = (2*trash.getY() + trash.getHeight())/2;
        double dist1 = Math.sqrt(Math.pow(centerX-p.getCenterX(), 2) + Math.pow(centerY-p.getCenterY(), 2));

        if (dist1 <= RADIUS) {
            return true;
        } else {
            return false;
        }
    }
}
