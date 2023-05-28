import java.awt.*;
import java.util.Random;

public class ChalkBoard extends Thing{
    private static final int BOARD_WIDTH = Main.SCREEN_WIDTH/2, BOARD_HEIGHT = (Main.SCREEN_WIDTH/4) - 230;
    private final int maxX = BOARD_WIDTH, minX = 300;
    private final int maxY = 10 + BOARD_HEIGHT, minY = 10;
    private boolean scribble = false, playerIsClose = false;

    public boolean getScribble() {
        return scribble;
    }

    public ChalkBoard(int x, int y){
        super(x, y, BOARD_WIDTH, BOARD_HEIGHT);
    }

    public boolean isPlayerClose(Player p1, Player p2) {
        double dist1 = Math.sqrt(Math.pow(getX()-p1.getX(), 2) + Math.pow(getY()-p1.getY(), 2));
        double dist2 = Math.sqrt(Math.pow(getX()-p2.getX(), 2) + Math.pow(getY()-p2.getY(), 2));

        if (dist1 <= 500 || dist2 <= 500) {
            playerIsClose = true;
            System.out.println("real");

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
        g2d.drawString("PHYSICS", 500, 200);

        //we skribl
        if (scribble) {
            g2d.setColor(Color.white);
            for (int j = 0; j < 5; j++){
                String c = randomChar();
                g2d.drawString(c, (int)(Math.random() * maxX + minX), (int)(Math.random() * maxY + minY));
            }
        }
    }

}
