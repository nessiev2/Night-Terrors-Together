import java.awt.*;

public class DoBadReaction extends Task{
    private int x = 500, y = 515, xSpacing = 350, ySpacing = 300, w = 30, h = 50;
    private int doneX1, doneY1, doneX2, doneY2;
    private int stainX1, stainY1, stainX2, stainY2;
    private static final int RADIUS = 170;
    private boolean green, pink, cyan, yellow, closeGreen, closePink, closeCyan, closeYellow, flag = false;
    public DoBadReaction(boolean isTask){
        super(isTask);
    }
    public void changeGreen(boolean z){
        green = z;
    }
    public void changePink(boolean z){
        pink = z;
    }
    public void changeCyan(boolean z){
        cyan = z;
    }
    public void changeYellow(boolean z){
        yellow = z;
    }
    public boolean getCloseGreen(){
        return closeGreen;
    }
    public boolean getClosePink(){
        return closePink;
    }
    public boolean getCloseCyan(){
        return closeCyan;
    }
    public boolean getCloseYellow(){
        return closeYellow;
    }

    public void doTask(Player p1, Player p2){
        closeGreen = isPlayerClose(x, y, p1, p2);
        closePink = isPlayerClose(x + xSpacing, y, p1, p2);
        closeCyan = isPlayerClose(x + xSpacing, y + ySpacing, p1, p2);
        closeYellow = isPlayerClose(x, y + ySpacing, p1, p2);

        if (green && pink && cyan && yellow){
            doneX1 = p1.getX();
            doneY1 = p1.getY();
            doneX2 = p2.getX();
            doneY2 = p2.getY();
            flag = true;
            taskFinished();
        }
        if (!flag){
            stainX1 = p1.getX();
            stainY1 = p1.getY();
            stainX2 = p2.getX();
            stainY2 = p2.getY();
        }
    }
    public void paint(Graphics g2d) {
        int i = 10;

        g2d.setColor(Color.yellow);
        if (closeGreen && !green){
            g2d.fillOval(x-i, y-i, w+2*i, h+2*i);
        }
        if (closePink && !pink){
            g2d.fillOval(x + xSpacing - i, y-i, w+2*i, h+2*i);
        }
        if (closeCyan && !cyan){
            g2d.fillOval(x  + xSpacing -i, y + ySpacing -i, w+2*i, h+2*i);
        }
        if (closeYellow && !yellow){
            g2d.fillOval(x-i, y + ySpacing-i, w+2*i, h+2*i);
        }

        g2d.setColor(Color.white);
        g2d.fillOval(x, y, w, h);
        g2d.fillOval(x + xSpacing, y, w, h);
        g2d.fillOval(x + xSpacing, y + ySpacing, w, h);
        g2d.fillOval(x, y + ySpacing, w, h);

        if (!green){
            g2d.setColor(new Color(5, 255, 0));
            g2d.fillOval(x, y, w, h);
        }

        if (!pink){
            g2d.setColor(new Color(255, 0, 204));
            g2d.fillOval(x + xSpacing, y, w, h);
        }

        if (!cyan){
            g2d.setColor(new Color(0, 255, 241));
            g2d.fillOval(x + xSpacing, y + ySpacing, w, h);
        }

        if (!yellow){
            g2d.setColor(new Color(255, 138, 0));
            g2d.fillOval(x, y + ySpacing, w, h);
        }

        g2d.setColor(Color.black);
        g2d.drawOval(x, y, w, h);
        g2d.drawOval(x + xSpacing, y, w, h);
        g2d.drawOval(x + xSpacing, y + ySpacing, w, h);
        g2d.drawOval(x, y + ySpacing, w, h);

        if (getFinished()){
            g2d.setColor(new Color(255, 0, 0));
            g2d.fillRect(doneX1, doneY1, 100, 100);
            g2d.fillRect(doneX2, doneY2, 100, 100);

            g2d.setColor(new Color(0, 0, 0, 195));
            g2d.fillRect(stainX1, stainY1, 100, 100);
            g2d.fillRect(stainX2, stainY2, 100, 100);

            taskFinished();
            //menu.updateTaskCompletion(n);
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
