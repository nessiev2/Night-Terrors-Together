import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class RCompSci {
    private boolean ts = true;
    private BufferedImage vertDesk, horzDesk1, horzDesk2, smallMonitorL,smallMonitorR, smallMonitorH;
    private DoArson[] arson8 = new DoArson[3];
    private DoArson arsont1, arsont2, arsont3;
    private DoHack doHack8;
    private ATrashCan[] trashCans = new ATrashCan[3];
    private AChalkBoard cb;
    private AWall w1 = new AWall(0, 0);
    private ADesk[] desks = {new ADesk(400, 500, 200, 100, true), new ADesk(700, 500, 200, 100, true), new ADesk(1000, 500, 200, 100,true), new ADesk(1300, 500, 200, 100, true), new ADesk(400, 500, 100, 300, true), new ADesk(1400, 500, 100, 300, true), new ADesk(750, 800, 400, 200, true)};
    private ADesk[] decorComputers = {new ADesk(410, 650, 50, 80, true), new ADesk(500, 510, 80, 50, true), new ADesk(760, 510, 80, 50, true), new ADesk(1060, 510, 80, 50, true), new ADesk(1320, 510, 80, 50, true), new ADesk(1440, 650, 50, 80, true)};
    boolean b1;

    public void initializeCompSci(boolean b, boolean b1, boolean b2){
        Random r = new Random();
        this.b1 = b1;

        trashCans[0] = new ATrashCan(r.nextInt(1431) + 270,r.nextInt(91) + 290); // top wall
        trashCans[1] = new ATrashCan(r.nextInt(271),r.nextInt(221) + 660); // left wall
        trashCans[2] = new ATrashCan(r.nextInt(100) + 1700,r.nextInt(221) + 660); //right wall

        arsont1 = new DoArson(b1, trashCans[0]);
        arsont2 = new DoArson(b1, trashCans[1]);
        arsont3 = new DoArson(b1, trashCans[2]);

        arson8[0] = arsont1;
        arson8[1] = arsont2;
        arson8[2] = arsont3;

        doHack8 = new DoHack(b2);
        cb = new AChalkBoard(300, 10, "COMPUTER SCIENCE :P", b);
    }
    public ADesk[] getDesks(){
        return desks;
    }
    public DoHack getDoHack8(){
        return doHack8;
    }
    public DoArson[] getArson(){
        return arson8;
    }
    public ATrashCan[] getTrashCans(){
        return trashCans;
    }
    public AChalkBoard getCb(){
        return cb;
    }

    public RCompSci() {
        initializeCompSci(false, false,false);
        try {
            vertDesk = ImageIO.read(new File("res\\compscivert.png"));
            horzDesk1 = ImageIO.read(new File("res\\compscihorz1.png"));
            horzDesk2 = ImageIO.read(new File("res\\compscihorz2.png"));
            smallMonitorL = ImageIO.read(new File("res\\CSFL.png"));
            smallMonitorR = ImageIO.read(new File("res\\CSFR.png"));
            smallMonitorH = ImageIO.read(new File("res\\csdecor.png"));
        } catch (IOException e) { System.out.println("desks no image"); }

    }

    public void paint(Graphics g, Player p1, Player p2, Transition transition1, SideMenu menu) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        //super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        for (ADesk d:desks) {
            g2d.setColor(new Color(1,1,1, 0));
            d.paint(g2d);
        }
        g2d.drawImage(horzDesk1, 400, 500, null);
        g2d.drawImage(horzDesk1, 700, 500, null);
        g2d.drawImage(horzDesk1, 1000, 500, null);
        g2d.drawImage(horzDesk1, 1300, 500, null);
        g2d.drawImage(vertDesk, 400, 500, null);
        g2d.drawImage(vertDesk, 1400, 500, null);
        g2d.drawImage(horzDesk2, 750, 800, null);

        for (int j = 500; j <= 1320; j+= 260){
            if (j == 500 || j == 760){
                g2d.drawImage(smallMonitorH, j, 510, null);
            } else {
                g2d.drawImage(smallMonitorH, j + 40, 510, null);
            }
        }
        g2d.drawImage(smallMonitorR, 1440 , 650, null);
        g2d.drawImage(smallMonitorL, 410, 650, null);


        for (ATrashCan tc: trashCans){
            tc.paint(g2d, p1, p2, b1);
        }

        for (DoArson arson: arson8){
            arson.paint(g2d, p1, p2, menu);
        }

        if (arsont1.getIsFin() && arsont2.getIsFin() && arsont3.getIsFin()){
            menu.updateTaskCompletion(0);
            ts = false;
        }

        w1.paint(g2d); // wall

        cb.isPlayerClose(p1, p2);
        cb.paint(g2d, menu); // chalkboard

        doHack8.paint(g2d, p1, p2, menu);
    }


}
