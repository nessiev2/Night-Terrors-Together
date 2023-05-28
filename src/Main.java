import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Main extends JPanel {
    final static int SCREEN_WIDTH = 1920, SCREEN_HEIGHT = 1080;
    boolean gameOver = false;
    int currentClassroom = 5;

    CountDown cd = new CountDown();

    Transition transition1 = new Transition();

    Door d1 = new Door(800, SCREEN_HEIGHT/4-200);
    Door d2 = new Door(0, SCREEN_HEIGHT/4-200);
    Player p1 = new Player1();
    Player p2 = new Player2();
    Teacher t = new Teacher();

    Physics phys = new Physics();
    Chemistry chem = new Chemistry();

    public void changeCurrentClassroom(int i) {
        currentClassroom = i;
    }

    public Main() {

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
//Passes the KeyEvent e to the ball instance
                p1.keyReleased(e, phys.arson1, phys.trashCans, phys.cb);
                p2.keyReleased(e, phys.arson1, phys.trashCans, phys.cb);
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
        p1.move(phys.desks);
        p2.move(phys.desks);
        t.move(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        cd.move();
        if (cd.getTime() <= 0) {
            gameOver = true;
        }

        //ARSON1 TESTING
        phys.arson1.doTask(phys.trashCans, p1, p2);
        //
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        switch(currentClassroom) {
            case 5:
                phys.paint(g, p1, p2, transition1);
                d1.paint(g2d);

                if (d1.containsPlayer(p1, p2)) {
                    System.out.println("physics to chem");
                    transition1.paint(g2d);
                    changeCurrentClassroom(6);
                }
                break;
            case 6:
                chem.paint(g, p1, p2, transition1);
                d2.paint(g2d);
                if (d2.containsPlayer(p1, p2)) {
                    System.out.println("chem to physics");
                    transition1.paint(g2d);
                    changeCurrentClassroom(5);
                }
                break;
        }

        p1.paint(g2d);
        p2.paint(g2d);
        t.paint(g2d);

        cd.paint(g2d);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Physics");

        Main c = new Main();
        frame.add(c);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        while (!c.gameOver)
        {
            c.move(); //Updates the coordinates
            c.repaint(); //Calls the paint method
            Thread.sleep(10); //Pauses for a moment
        }
    }
}