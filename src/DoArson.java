import java.awt.*;

public class DoArson extends Task {

    private final int RADIUS = 200;
    private int counter = 0;
    private boolean[] isOnFire = {false, false, false};
    private boolean[] interacted = {false, false, false};
    ATrashCan[] trash;

    public void changeInteracted(int i){
        interacted[i] = true;
    }
    public DoArson(boolean isTask, ATrashCan[] trash){
        super(isTask);
        this.trash = trash;
    }

    public int doTask(Player p1, Player p2, SideMenu menu) {
        if (isTask) {
            for (ATrashCan t: trash){
                if (t.getIsOnFire()) {
                    counter++;
                }
            }

            if (counter >= 3){
                this.taskFinished();
                menu.updateTaskCompletion(0);
                return 1;
            }
        }

        return 0;
    }

    public void paint(Graphics g2d, Player p1, Player p2, SideMenu menu) {
        int i = 10;

        // draws the glow around the trash can
        for (int j = 0; j < 3; j++){
            if (!interacted[j]) {
                g2d.setColor(Color.yellow);
                g2d.fillOval(trash[j].getX()-2*i, trash[j].getY()-2*i, trash[j].getWidth()+4*i, trash[j].getHeight()+4*i);
            }

            if (isOnFire[j]) {
                g2d.setColor(Color.red);
                g2d.fillRect(trash[j].getX()+2*i, trash[j].getY()+2*i, trash[j].getWidth()-4*i, trash[j].getHeight()-4*i);
            }
        }
    }

    public boolean isPlayerClose(Player p1, Player p2, int c) {
        int centerX = (2*trash[c].getX() + trash[c].getWidth())/2, centerY = (2*trash[c].getY() + trash[c].getHeight())/2;
        double dist1 = Math.sqrt(Math.pow(centerX-p1.getCenterX(), 2) + Math.pow(centerY-p1.getCenterY(), 2));
        double dist2 = Math.sqrt(Math.pow(centerX-p2.getCenterX(), 2) + Math.pow(centerY-p2.getCenterY(), 2));

        if (dist1 <= RADIUS || dist2 <= RADIUS) {
            isOnFire[c] = true;
            return true;
        } else {
            isOnFire[c] = false;
            return false;
        }
    }
}
