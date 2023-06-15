import java.awt.*;

public class DoStealTests extends Task{
    private int x = 700, y = 200, w = 50, h = 100, stack = 10;
    private final static int RADIUS = 100, THICKNESS = 10, COUNT = 10;
    private boolean isTaskComplete = false;
    public int getStack(){
        return stack;
    }
    public void decrementStack(Player p1, Player p2){
        if (isPlayerClose(x, y, p1, p2)){
            stack--;
        }
    }
    public DoStealTests(boolean isTask){
        super(isTask);
    }
    public void paint(Graphics g2d, Player p1, Player p2, SideMenu menu) {
        int i = 10;

        if (stack == 0){
            isTaskComplete = true;
            menu.updateTaskCompletion(7);
            taskFinished();
        }

        if (!isTaskComplete && isPlayerClose(x, y, p1, p2)){
            g2d.setColor(Color.yellow);
            g2d.fillRect(x - i, y + THICKNESS * (COUNT-stack) - i, w + 2*i, h - THICKNESS * (COUNT-stack) + 2*i);
        }

        g2d.setColor(Color.white);
        g2d.fillRect(x, y + THICKNESS * (COUNT-stack), w, h - THICKNESS * (COUNT-stack));
        g2d.setColor(Color.black);
        g2d.drawRect(x, y + THICKNESS * (COUNT-stack), w, h - THICKNESS * (COUNT-stack));
        for (int j = 0; j < stack; j++){
            g2d.drawLine(x, y + THICKNESS*(COUNT-j), x+w, y + THICKNESS*(COUNT-j));
        }
    }

    public boolean isPlayerClose(int x, int y, Player p1, Player p2) {
        int centerX = (2*x + w)/2, centerY = (2*y + h)/2;
        double dist1 = Math.sqrt(Math.pow(centerX-p1.getCenterX(), 2) + Math.pow(centerY-p1.getY(), 2));
        double dist2 = Math.sqrt(Math.pow(centerX-p2.getCenterX(), 2) + Math.pow(centerY-p2.getY(), 2));

        if ((dist1 <= RADIUS && p1.getY() < y)|| (dist2 <= RADIUS && p2.getY() < y)) {
            return true;
        } else {
            return false;
        }
    }
}
