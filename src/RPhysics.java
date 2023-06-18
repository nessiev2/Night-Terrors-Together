import java.awt.*;
import java.util.Random;

public class RPhysics {
    DoArson[] arson5 = new DoArson[3];
    DoArson arsont1, arsont2, arsont3;
    ATrashCan[] trashCans = new ATrashCan[3];
    AChalkBoard cb;
    AWall w1 = new AWall(0, 0);
    ADesk[] desks = new ADesk[6];

    boolean b1;

    public void initializePhysics(boolean b, boolean b1){
        Random r = new Random();
        this.b1 = b1;

        trashCans[0] = new ATrashCan(r.nextInt(1431) + 270,r.nextInt(91) + 290); // top wall
        trashCans[1] = new ATrashCan(r.nextInt(271),r.nextInt(221) + 660); // left wall
        trashCans[2] = new ATrashCan(r.nextInt(221) + 1420,r.nextInt(221) + 660); //right wall

        arsont1 = new DoArson(b1, trashCans[0]);
        arsont2 = new DoArson(b1, trashCans[1]);
        arsont3 = new DoArson(b1, trashCans[2]);

        arson5[0] = arsont1;
        arson5[1] = arsont2;
        arson5[2] = arsont3;

        cb = new AChalkBoard(300, 10, "PHYSICS", b);
    }

    public RPhysics() {
        int counter = 0;

        //generating desks loop
        for (int i = 400; i <= 1000; i += 300){         // x
            for (int j = 500; j <= 800; j += 300){      // y
                desks[counter] = new ADesk(i, j);
                counter++;
            }
        }

        initializePhysics(false, false);
    }

    public void paint(Graphics g, Player p1, Player p2, Transition transition1, SideMenu menu) {
        //super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        // IRENE IS MAKING A CLASSROOM DESK TRIAL
        for (ADesk d:desks) {
            d.paint(g2d);
        }

        for (ATrashCan tc: trashCans){
            tc.paint(g2d, p1, p2, b1);
        }

        //.out.println(arson5[0].getOnFire());

        for (DoArson arson: arson5){
            arson.paint(g2d, p1, p2, menu);
        }

        w1.paint(g2d); // wall

        cb.isPlayerClose(p1, p2);
        cb.paint(g2d, menu); // chalkboard

        // physics should have access to if chalkboard and trash can are
        // REAL tasks

        g2d.setFont(new Font("TimesRoman", Font.BOLD, 25));
        g2d.setColor(Color.white);
        g2d.drawString("this is the only room you're safe in.", 500, 50);

//        if (menu.isScribbleTask()){
////        g2d.drawString("which chalkboard are you destined to scribble on?", 500, 150);
////        g2d.drawString("find out by looking for the glowing chalkboard", 500, 150+25);
//        g2d.drawString("- look out for the glowing chalkboard", 500, 150+25);
//
//        }
//        if (menu.isArsonTask()){
////        g2d.drawString("which room's trash cans were you prophesied to draw on?", 500, 225);
////        g2d.drawString("find out by looking for the room with glowing trash cans", 500, 225+25);
//        g2d.drawString("- look out for the glowing trash cans", 500, 225);
//        }
    }
}
