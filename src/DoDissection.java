import java.awt.*;

public class DoDissection extends Task{
    private int x = 450, y = 600, w = 30, h = 50;
    private final static int RADIUS = 130;
    private boolean[] isComplete = {false, false, false, false, false, false};
    private boolean[] interacted = {false, false, false, false, false, false};
    public DoDissection(boolean isTask){
        super(isTask);
    }
    public boolean getIsClose(int i, Player p){
        if (i == 0){
            return isPlayerClose(x, y, p, p) ;
        }
        if (i == 1){
            return isPlayerClose(x, y + 100, p, p) ;
        }
        if (i == 2){
            return isPlayerClose(x, y + 2 * 100, p, p) ;
        }
        if (i == 3){
            return isPlayerClose(x - 270, y , p, p) ;
        }
        if (i == 4) {
            return isPlayerClose(x - 270, y + 100, p, p) ;
        }
        else {
            return isPlayerClose(x - 270, y + 2 * 100, p, p) ;
        }
    }
    public void changeIsClose(int i){
        if (i == 0){
            interacted[0] = true;
        }
        if (i == 1){
            interacted[1] = true;
        }
        if (i == 2){
            interacted[2] = true;
        }
        if (i == 3){
            interacted[3] = true;
        }
        if (i == 4) {
            interacted[4] = true;
        }
        else {
            interacted[5] = true;
        }
    }
    public void paint(Graphics g2d, Player p1, Player p2, SideMenu menu) {
        int i = 10;
        if (checkWin()){
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 25));
            g2d.setColor(Color.red);
            g2d.drawString("task complete!", 250, 500);
            taskFinished();
            menu.updateTaskCompletion(2);
        } else {
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 25));
            g2d.setColor(Color.red);
            g2d.drawString("interact with all frogs", 225, 475);
            g2d.drawString("to dissect them", 225, 475+25);
        }

        // drawing "glow"
        g2d.setColor(Color.yellow);

        if (isPlayerClose(x, y, p1, p2) && !interacted[0]) {
            g2d.fillRect(x - i, y - i, w + 2 * i, h + 2 * i);
        }
        if (isPlayerClose(x, y + 100, p1, p2) && !interacted[1]) {
            g2d.fillRect(x - i, y + 100 - i, w + 2 * i, h + 2 * i);
        }
        if (isPlayerClose(x, y + 2 * 100, p1, p2) && !interacted[2]) {
            g2d.fillRect(x - i, y + 2 * 100 - i, w + 2 * i, h + 2 * i);
        }

        if (isPlayerClose(x - 270, y, p1, p2) && !interacted[3]) {
            g2d.fillRect(x - 270 - i, y - i, w + 2 * i, h + 2 * i);
        }
        if (isPlayerClose(x - 270, y + 100, p1, p2) && !interacted[4]) {
            g2d.fillRect(x - 270 - i, y + 100 - i, w + 2 * i, h + 2 * i);
        }
        if (isPlayerClose(x - 270, y + 2 * 100, p1, p2) && !interacted[5]) {
            g2d.fillRect(x - 270 - i, y + 2 * 100 - i, w + 2 * i, h + 2 * i);
        }


        g2d.setColor(Color.green);
        // right column
        g2d.fillRect(x, y, w, h);
        g2d.fillRect(x, y + 100, w, h);
        g2d.fillRect(x, y + 2 * 100, w, h);

        // left column
        g2d.fillRect(x - 270, y, w, h);
        g2d.fillRect(x - 270, y + 100, w, h);
        g2d.fillRect(x - 270, y + 2 * 100, w, h);

            // changing state of interacted
            if (isPlayerClose(x, y, p1, p2) && interacted[0]){
                isComplete[0] = true;
            }
            if (isPlayerClose(x, y + 100, p1, p2) && interacted[1]){
                isComplete[1] = true;
            }
            if (isPlayerClose(x, y + 2 * 100, p1, p2) && interacted[2]){
                isComplete[2] = true;
            }

            if (isPlayerClose(x - 270, y, p1, p2) && interacted[3]){
                isComplete[3] = true;
            }
            if (isPlayerClose(x - 270, y + 100, p1, p2) && interacted[4]){
                isComplete[4] = true;
            }
            if (isPlayerClose(x - 270, y + 2 * 100, p1, p2) && interacted[5]){
                isComplete[5] = true;
            }

            g2d.setColor(Color.red);

            if (isComplete[0]){
                g2d.fillRect(x, y, w, h);
            }
            if (isComplete[1]){
                g2d.fillRect(x, y + 100, w, h);
            }
            if (isComplete[2]){
                g2d.fillRect(x, y + 2 * 100, w, h);
            }
            if (isComplete[3]){
                g2d.fillRect(x - 270, y, w, h);
            }
            if (isComplete[4]){
                g2d.fillRect(x - 270, y + 100, w, h);
            }
            if (isComplete[5]){
                g2d.fillRect(x - 270, y + 2 * 100, w, h);
            }

    }

    public boolean checkWin() {
        for (boolean x: isComplete){
            if (!x){
                return false;
            }
        }

        return true;
    }

    public boolean isPlayerClose(int x, int y, Player p1, Player p2) {
        int centerX = (2*x + w)/2, centerY = (2*y + h)/2;
        double dist1 = Math.sqrt(Math.pow(centerX-p1.getCenterX(), 2) + Math.pow(centerY-p1.getY(), 2));
        double dist2 = Math.sqrt(Math.pow(centerX-p2.getCenterX(), 2) + Math.pow(centerY-p2.getY(), 2));

        if ((dist1 <= RADIUS)|| (dist2 <= RADIUS)) {
            return true;
        } else {
            return false;
        }
    }
}
