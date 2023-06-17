import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DoArson extends Task {
    private Image img = null;
    private final int RADIUS = 200;
    private boolean isOnFire;
    ATrashCan trash;
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
        } catch (IOException e) { System.out.println("arson no image"); }
    }

    public void paint(Graphics g2d, Player p1, Player p2, SideMenu menu) {
        int i = 10;

        // draws the glow around the trash can
        for (int j = 0; j < 3; j++){
            g2d.setColor(Color.red);

            if (isOnFire && isTask) {
                //g2d.fillRect(trash.getX()+2*i, trash.getY()+2*i, trash.getWidth()-4*i, trash.getHeight()-4*i);
                g2d.drawImage(img, trash.getX()+3*i/2, trash.getY()+i, trash.getWidth()-2*i, trash.getHeight()-2*i, null);
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

    //    public void doTask(Player p1, Player p2, SideMenu menu) {
//        if (isTask) {
////            this.taskFinished();
////            menu.updateTaskCompletion(0);
//        }
//    }
}
