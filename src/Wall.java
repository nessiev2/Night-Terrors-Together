import java.awt.*;

public class Wall extends Thing{
    private final static int WALL_WIDTH = Main.SCREEN_WIDTH, WALL_HEIGHT = Main.SCREEN_HEIGHT/4;
    public Wall(int x, int y){
        super(x, y, WALL_WIDTH, WALL_HEIGHT);
    }

    public int getWallHeight(){
        return WALL_HEIGHT;
    }

    public void paint (Graphics2D g2d){
        g2d.setColor(Color.lightGray);
        g2d.fillRect(getX(), getY(), WALL_WIDTH, WALL_HEIGHT);
    }
}
