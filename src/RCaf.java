import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

public class RCaf {
    private DoVendingMachine vendMachine1;
    private DoVendingMachine vendMachine2;

    private DoVendingMachine[] vendMachines = new DoVendingMachine[2];

    private DoArson[] arson2;
    private ATrashCan[] trashCans = {};
    private AChalkBoard cb;
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
        this.b1 = b1;
        this.b2 = b2;
        //arson2 = new DoArson(true, trashCans[0]);
        DoVendingMachine vendMachine1 = new DoVendingMachine(800, 600, b2);
        DoVendingMachine vendMachine2 = new DoVendingMachine(1000, 600, b2);
        vendMachines[0] = vendMachine1;
        vendMachines[1] = vendMachine2;
        cb = new AChalkBoard(500, 10, "CAFETERIA", b);
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

        g2d.setColor(new Color(160, 146, 146));
        for (ADesk d:desks) {
            if (d.getDeskType() == 0){
                d.paintDesk0(g2d);
            } else {
                d.paint(g2d);
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
                menu.updateTaskCompletion(8);
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
