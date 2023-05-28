import java.awt.*;

public class ChalkBoard extends Thing{
    private static final int BOARD_WIDTH = Main.SCREEN_WIDTH/2, BOARD_HEIGHT = (Main.SCREEN_WIDTH/4) - 230;
    public ChalkBoard(int x, int y){
        super(x, y, BOARD_WIDTH, BOARD_HEIGHT);
    }

    public void paint (Graphics2D g2d){
        g2d.setColor(Color.black);
        g2d.fillRect(getX(), getY(), BOARD_WIDTH, BOARD_HEIGHT);

        g2d.setColor(Color.white);
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 50));
        g2d.drawString("PHYSICS", 500, 200);

    }
}
