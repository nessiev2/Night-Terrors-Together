import java.awt.*;

public class Wall extends Thing{
    private final static int wallWidth = Main.screenWidth, wallHeight = 270;
    public Wall(int x, int y){
        super(x, y, wallWidth, wallHeight);
    }

    public int getWallHeight(){
        return wallHeight;
    }

    public void paint (Graphics2D g2d){
        g2d.setColor(Color.lightGray);
        g2d.fillRect(getX(), getY(), wallWidth, wallHeight);
    }
}
