import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class RCaf {
    private boolean ts = true;

    private BufferedImage cafdesk1, cafdesk2;
    private DoVendingMachine vendMachine1;
    private DoVendingMachine vendMachine2;
    private DoVendingMachine[] vendMachines = new DoVendingMachine[2];
    private DoArson[] arson2;
    private ATrashCan[] trashCans = {};
    private AChalkBoard cb;
    private boolean[] tables = new boolean[5];
    private ADesk[] desks = {new ADesk(300, 300+25, 400, 150, true), new ADesk(800, 300+25, 400, 150, true), new ADesk(1300, 300+25, 400, 150, true), new ADesk(300, 600+25, 400, 150, true), new ADesk(1300, 600+25, 400, 150, true)};
    private AWall w1 = new AWall(0, 0);
    private boolean b1;
    private boolean b2;
    public ADesk[] getDesks(){
        return desks;
    }
    public DoVendingMachine[] getVendMachines(){
        return vendMachines;
    }
    public DoArson[] getArson(){
        return arson2;
    }
    public ATrashCan[] getTrashCans(){
        return trashCans;
    }
    public AChalkBoard getCb(){
        return cb;
    }
    public void initializeCaf(boolean b, boolean b1, boolean b2){
        ts = true;
        Random r = new Random();

        this.b1 = b1;
        this.b2 = b2;
        //arson2 = new DoArson(true, trashCans[0]);
        DoVendingMachine vendMachine1 = new DoVendingMachine(800, 600, b2);
        DoVendingMachine vendMachine2 = new DoVendingMachine(1000, 600, b2);
        vendMachines[0] = vendMachine1;
        vendMachines[1] = vendMachine2;
        cb = new AChalkBoard(500, 10, "CAFETERIA", b);
        try {
            cafdesk1 = ImageIO.read(new File("res\\cafdesk1.png"));
            cafdesk2 = ImageIO.read(new File("res\\cafdesk2.png"));
        } catch (IOException e) { System.out.println("frog no image"); }
        for (int i = 0; i < 5; i++){
            tables[i] = r.nextBoolean();
        }
        for (boolean x: tables){
            System.out.println(x);
        }
    }
    public RCaf() {
        initializeCaf(false, false, false);
    }

    public void paint(Graphics g, Player p1, Player p2, Transition transition1, SideMenu menu) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        //super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        for (ATrashCan tc: trashCans){
            tc.paint(g2d, p1, p2, b1);
        }

        g2d.setColor(new Color(160, 146, 146, 0));
        for (ADesk d:desks) {
            d.paint(g2d);
        }
        int tmpCount = 0;
        for (int j = 300; j <= 1300; j += 500){      // x
            for (int k = 325; k <= 625; k += 300){        // y
                if (j == 800 && k == 625){
                } else {
                    if (tables[tmpCount]){
                        g2d.drawImage(cafdesk1, j, k, null);
                    } else {
                        g2d.drawImage(cafdesk2, j, k, null);
                    }
                    tmpCount++;
                }
            }
        }

        w1.paint(g2d); // wall
        for (DoVendingMachine VM:vendMachines) {
            VM.paint(g2d, p1, p2);
            if (VM.getVendComplete() && b2) {
                VM.taskFinished();
                //menu.updateTaskCompletion(8);
            }
        }

        if (b2) {
            DoVendingMachine VM = vendMachines[0];
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 25));
            g2d.setColor(Color.black);
            if (vendMachines[0].getVendComplete() && vendMachines[1].getVendComplete()) {
                if (ts) {
                    menu.updateTaskCompletion(8);
                    ts = false;
                }
                g2d.drawString("task complete!", VM.getX(), VM.getY() - 25);
            } else {
                g2d.drawString("interact to smash", VM.getX(), VM.getY() - 50);
                g2d.drawString("these vending machines", VM.getX(), VM.getY() - 25);
            }
        }

        cb.isPlayerClose(p1, p2);
        cb.paint(g2d, menu); // chalkboard
    }


}
