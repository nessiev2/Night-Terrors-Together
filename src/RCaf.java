import java.awt.*;

public class RCaf {
    DoVendingMachine vendMachine1;
    DoVendingMachine vendMachine2;

    DoVendingMachine[] vendMachines = new DoVendingMachine[2];

    DoArson[] arson2;
    ATrashCan[] trashCans = {};
    AChalkBoard cb;
    ADesk[] desks = {new ADesk(300, 300+25, 400, 150, true), new ADesk(800, 300+25, 400, 150, true), new ADesk(1300, 300+25, 400, 150, true), new ADesk(300, 600+25, 400, 150, true), new ADesk(1300, 600+25, 400, 150, true)};
    AWall w1 = new AWall(0, 0);
    public void initializeCaf(boolean b, boolean b1){
        //arson2 = new DoArson(true, trashCans[0]);
        DoVendingMachine vendMachine1 = new DoVendingMachine(800, 600, true);
        DoVendingMachine vendMachine2 = new DoVendingMachine(1000, 600, true);
        vendMachines[0] = vendMachine1;
        vendMachines[1] = vendMachine2;
        cb = new AChalkBoard(500, 10, "CAFETERIA", b);
    }
    public RCaf() {
        initializeCaf(false, false);
    }

    public void paint(Graphics g, Player p1, Player p2, Transition transition1, SideMenu menu) {
        //super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        for (ATrashCan tc: trashCans){
            tc.paint(g2d, p1, p2);
        }

        g2d.setColor(new Color(160, 146, 146));
        for (ADesk d:desks) {
            d.paint(g2d);
        }

        w1.paint(g2d); // wall
        for (DoVendingMachine VM:vendMachines) {
            VM.paint(g2d, p1, p2);
            if (VM.getVendComplete()) {
                VM.taskFinished();
                menu.updateTaskCompletion(8);
            }
        }

        if (vendMachines[0].getVendComplete() && vendMachines[1].getVendComplete() ) {
            menu.updateTaskCompletion(5);
        }


        cb.isPlayerClose(p1, p2);
        cb.paint(g2d, menu); // chalkboard
    }


}
