import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DoBadReaction extends Task{
    private int x = 500, y = 515, xSpacing = 350, ySpacing = 300, w = 30, h = 50, ticks = 0;
    private int doneX1, doneY1, doneX2, doneY2;
    private int stainX1, stainY1, stainX2, stainY2;
    private static final int RADIUS = 170;
    private BufferedImage img0, img1, img2, img3, img4, imghehe;
    private boolean green, pink, cyan, yellow, closeGreen, closePink, closeCyan, closeYellow, flag = false;
    public DoBadReaction(boolean isTask){
        super(isTask);
        try {
            img0 = ImageIO.read(new File("res\\flask.png"));
            img1 = ImageIO.read(new File("res\\flaskgreen.png"));
            img2 = ImageIO.read(new File("res\\flaskpink.png"));
            img3 = ImageIO.read(new File("res\\flaskcyan.png"));
            img4 = ImageIO.read(new File("res\\flaskyellow.png"));
            imghehe = ImageIO.read(new File("res\\flaskinteract.png"));
        } catch (IOException e) { System.out.println("stuff not loading :C"); }
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
        if (isTask) {
            closeGreen = isPlayerClose(x, y, p1, p2);
            closePink = isPlayerClose(x + xSpacing, y, p1, p2);
            closeCyan = isPlayerClose(x + xSpacing, y + ySpacing, p1, p2);
            closeYellow = isPlayerClose(x, y + ySpacing, p1, p2);

            if (green && pink && cyan && yellow) {
                doneX1 = p1.getX();
                doneY1 = p1.getY();
                doneX2 = p2.getX();
                doneY2 = p2.getY();
                flag = true;
                taskFinished();
            }
            if (!flag) {
                stainX1 = p1.getX();
                stainY1 = p1.getY();
                stainX2 = p2.getX();
                stainY2 = p2.getY();
            }
        }
    }

    public void paint(Graphics g2d, SideMenu menu) {
        int i = 15, j = 25;

        g2d.setColor(Color.yellow);
        if (closeGreen && !green){
            g2d.drawImage(imghehe, x-i, y-j, null);
//            g2d.fillOval(x-i, y-i, w+2*i, h+2*i);
        }
        if (closePink && !pink){
            g2d.drawImage(imghehe, x + xSpacing -i, y-j, null);
//            g2d.fillOval(x + xSpacing - i, y-i, w+2*i, h+2*i);
        }
        if (closeCyan && !cyan){
            g2d.drawImage(imghehe, x + xSpacing - i, y + ySpacing - j, null);
//            g2d.fillOval(x  + xSpacing -i, y + ySpacing -i, w+2*i, h+2*i);
        }
        if (closeYellow && !yellow){
            g2d.drawImage(imghehe, x-i, y + ySpacing - j, null);
//            g2d.fillOval(x-i, y + ySpacing-i, w+2*i, h+2*i);
        }

        g2d.drawImage(img0, x, y, null);
        g2d.drawImage(img0, x + xSpacing, y, null);
        g2d.drawImage(img0, x + xSpacing, y + ySpacing, null);
        g2d.drawImage(img0, x, y + ySpacing, null);

//        g2d.fillOval(x, y, w, h);
//        g2d.fillOval(x + xSpacing, y, w, h);
//        g2d.fillOval(x + xSpacing, y + ySpacing, w, h);
//        g2d.fillOval(x, y + ySpacing, w, h);

        if (!green){
            g2d.drawImage(img1, x, y, null);
//          g2d.setColor(new Color(5, 255, 0));
//          g2d.fillOval(x, y, w, h);
        }

        if (!pink){
            g2d.drawImage(img2, x + xSpacing, y, null);
//            g2d.setColor(new Color(255, 0, 204));
//            g2d.fillOval(x + xSpacing, y, w, h);
        }

        if (!cyan){
            g2d.drawImage(img3, x + xSpacing, y + ySpacing, null);
//            g2d.setColor(new Color(0, 255, 241));
//            g2d.fillOval(x + xSpacing, y + ySpacing, w, h);
        }

        if (!yellow){
            g2d.drawImage(img4, x, y + ySpacing, null);
//            g2d.setColor(new Color(255, 138, 0));
//            g2d.fillOval(x, y + ySpacing, w, h);
        }

        if (getFinished()){
            ticks++;
            if (ticks <= 120){
                g2d.setColor(new Color(255, 0, 0));
                g2d.fillRect(doneX1, doneY1, 100, 100);
                g2d.fillRect(doneX2, doneY2, 100, 100);
            }

            g2d.setColor(new Color(0, 0, 0, 195));
            g2d.fillRect(stainX1, stainY1, 100, 100);
            g2d.fillRect(stainX2, stainY2, 100, 100);

            g2d.setFont(new Font("TimesRoman", Font.BOLD, 25));
            g2d.setColor(Color.red);
            g2d.drawString("task complete!", 625, 700);
            taskFinished();
            menu.updateTaskCompletion(1);
        } else {
            if (isTask) {
                g2d.setFont(new Font("TimesRoman", Font.BOLD, 25));
                g2d.setColor(Color.red);
                g2d.drawString("interact with all chemicals", 500, 700);
                g2d.drawString("to create an explosion", 500, 700 + 25);
            }
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
