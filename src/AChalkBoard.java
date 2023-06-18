import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AChalkBoard extends Thing {
    private BufferedImage cbcb, cbinteract;
    private boolean ts = true;
    private static final int BOARD_WIDTH = NTT.SCREEN_WIDTH/2, BOARD_HEIGHT = (NTT.SCREEN_WIDTH/4) - 230;
    // 960 x 250
    private final int RADIUS = 50;
    private boolean scribble, playerIsClose, interactive;
    private String classroom;
    private DoScribble skribl;

    public void initializeChalkboard(){
        skribl = new DoScribble(10, 10, this);
        scribble = false;
        playerIsClose = false;
    }


    public AChalkBoard(int x, int y, String classroom, boolean interactive){
        super(x, y, BOARD_WIDTH, BOARD_HEIGHT);
        this.classroom = classroom;
        this.interactive = interactive;
        initializeChalkboard();
        try {
            cbcb = ImageIO.read(new File("res\\cbcb.png"));
            cbinteract = ImageIO.read(new File("res\\cbinteract.png"));
        } catch (IOException e) { System.out.println("cb no image"); }
    }
    public boolean getScribble() {
        return scribble;
    }
    public boolean isPlayerClose(Player p1, Player p2) {
        if ((p1.getCenterX() >= getX() - RADIUS && p1.getCenterX() <= getX() + getWidth() + RADIUS && p1.getY() - getY() - getHeight() <= RADIUS) || (p2.getCenterX() >= getX() - RADIUS && p2.getCenterX() <= getX() + getWidth() + RADIUS && p2.getY() - getY() - getHeight() <= RADIUS)) {
            playerIsClose = true;
            return true;
        } else {
            playerIsClose = false;
            return false;
        }
    }

    public void makeScribble(){
        scribble = true;
    }

    public void paint (Graphics2D g2d, SideMenu menu) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        int i = 10;
        //interactive "glow"
        if (playerIsClose && interactive) {
            g2d.drawImage(cbinteract, getX()-i, getY()-i, 980, 270, null);
//            g2d.setColor(Color.yellow);
//            g2d.fillRect(getX()-i, getY()-i, BOARD_WIDTH+2*i, BOARD_HEIGHT+2*i);
        }

        g2d.drawImage(cbcb, getX(), getY(), 960, 250, null);
//        g2d.setColor(Color.black);
//        g2d.fillRect(getX(), getY(), BOARD_WIDTH, BOARD_HEIGHT);

        g2d.setColor(Color.white);
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 50));
        g2d.drawString(classroom, getX()+200, getY()+100);

        if (scribble && interactive) {
            if (ts) {
                menu.updateTaskCompletion(5);
                ts = false;
            }
            skribl.paint(g2d);
        }
    }

}
