import java.awt.*;

public class AWall extends Thing{
    private final static int WALL_WIDTH = NTT.SCREEN_WIDTH, WALL_HEIGHT = NTT.SCREEN_HEIGHT/4;
    public AWall(int x, int y){
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
