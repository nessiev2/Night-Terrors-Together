import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Main extends JPanel {
    final static int SCREEN_WIDTH = 1920, SCREEN_HEIGHT = 1080;

    Transition transition1 = new Transition();
    //ARSON TASK TESTING
    Arson arson1 = new Arson(true);
    TrashCan[] trashCans = {new TrashCan(1000, 400), new TrashCan(1300, 400), new TrashCan(1000, 600)};
    //
    Player p1 = new Player1();
    Player p2 = new Player2();
    Teacher t = new Teacher();

    Wall w1 = new Wall(0, 0);
    Desk[] desks = {new Desk(200, 300), new Desk(600, 300), new Desk(200, 700), new Desk(600, 700)};

    //TrashCan tc1 = new TrashCan(200, 500);
    Door d1 = new Door(0, w1.getWallHeight()-200);

    public Main() {

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
//Passes the KeyEvent e to the ball instance
                p1.keyReleased(e, arson1, trashCans);
                p2.keyReleased(e, arson1, trashCans);
            }
            @Override
            public void keyPressed(KeyEvent e) {
//Passes the KeyEvent e to the ball instance
                p1.keyPressed(e);
                p2.keyPressed(e);
            }
        });
        setFocusable(true);
    }

    private void move() {
        p1.move(desks);
        p2.move(desks);
        t.move(p1.getX(), p1.getY(), p2.getX(), p2.getY());

        //ARSON1 TESTING
        arson1.doTask(trashCans, p1, p2);
        //
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        // IRENE IS MAKING A CLASSROOM DESK TRIAL
        for (Desk d:desks) {
            d.paint(g2d);
        }

        for (TrashCan tc: trashCans){
            tc.isPlayerClose(p1, p2);
            tc.paint(g2d);
        }
        //tc1.paint(g2d);
        // END

        //JOYI WALL
        w1.paint(g2d);
        //testing door
        d1.paint(g2d);
        // mmmm

        g2d.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.BOLD, 50));
        g2d.drawString("PHYSICS", 500, 200);

        p1.paint(g2d);
        p2.paint(g2d);
        t.paint(g2d);

        if (d1.containsPlayer(p1, p2)) {// || d1.containsPlayer(p2)) {
            System.out.println("transition black screen worked");
            transition1.paint(g2d);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("UI Testing");

        Main c = new Main();
        frame.add(c);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true)
        {
            c.move(); //Updates the coordinates
            c.repaint(); //Calls the paint method
            Thread.sleep(10); //Pauses for a moment
        }
    }
}