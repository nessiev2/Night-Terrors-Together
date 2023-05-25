import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Main extends JPanel {
    final static int screenWidth = 1920, screenHeight = 1080;
    Player p1 = new Player1();
    Player p2 = new Player2();
    Teacher t = new Teacher();

    Desk d1 = new Desk(500, 500);
    Wall w1 = new Wall(0, 0);
    TrashCan tc1 = new TrashCan(200, 500);

    public Main() {

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
//Passes the KeyEvent e to the ball instance
                p1.keyReleased(e);
                p2.keyReleased(e);
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
        p1.move(d1);
        p2.move(d1);
        t.move(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        // IRENE IS MAKING A CLASSROOM DESK TRIAL
        d1.paint(g2d);
        tc1.paint(g2d);
        // END

        //JOYI WALL
        w1.paint(g2d);
        //TESTING END

        p1.paint(g2d);
        p2.paint(g2d);
        t.paint(g2d);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("UI Testing");

        Main c = new Main();
        frame.add(c);
        frame.setSize(screenWidth, screenHeight);
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