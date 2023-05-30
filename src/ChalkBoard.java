import java.awt.*;
import java.util.Random;

public class ChalkBoard extends Thing{
    private static final int BOARD_WIDTH = Main.SCREEN_WIDTH/2, BOARD_HEIGHT = (Main.SCREEN_WIDTH/4) - 230;
    private final int maxX = BOARD_WIDTH, minX = 300;
    private final int maxY = 10 + BOARD_HEIGHT, minY = 10;
    private final int RADIUS = 50;
    private String[] scribbleArray = new String[20];
    private int[] xs = new int[20];
    private int[] ys = new int[20];
    private boolean scribble = false, playerIsClose = false;
    private String classroom;

    public ChalkBoard(int x, int y, String classroom){
        super(x, y, BOARD_WIDTH, BOARD_HEIGHT);
        this.classroom = classroom;

        for (int i = 0; i < 20; i++){
            String c = randomChar();
            scribbleArray[i] = c;
            xs[i] = (int)(Math.random() * maxX + minX);
            ys[i] = (int)(Math.random() * maxY + minY);
        }
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

    private static String randomChar() {
        String ans = "";
        Random r = new Random();
        ans += (char)(r.nextInt(26) + 'A');
        return ans;
    }

    public void scribble(){
        scribble = true;
    }

    public void paint (Graphics2D g2d){
        int i = 10;

        //interactive "glow"
        if (playerIsClose) {
            g2d.setColor(Color.yellow);
            g2d.fillRect(getX()-i, getY()-i, BOARD_WIDTH+2*i, BOARD_HEIGHT+2*i);
        }

        g2d.setColor(Color.black);
        g2d.fillRect(getX(), getY(), BOARD_WIDTH, BOARD_HEIGHT);

        g2d.setColor(Color.white);
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 50));
        g2d.drawString(classroom, getX()+400, getY()+100);

        //we skribl
        if (scribble) {
            g2d.setColor(Color.pink);
            for (int j = 0; j < 20; j++){
                g2d.drawString(scribbleArray[j], xs[j], ys[j]);
            }
        }
    }

}
