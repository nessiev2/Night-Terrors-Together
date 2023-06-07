import java.awt.*;
public class DoMess extends Task {
    public boolean[][] waterStains = new boolean[1080-270][1920];
    public DoMess(boolean isTask){
        super(isTask);
    }

    public void paint(Graphics g2d) {
        for (int i = 0; i < waterStains.length; i++) {
            for (int j = 0; j < waterStains[i].length; j++) {
                if (waterStains[i][j]) {
                    g2d.setColor(Color.blue);
                    g2d.drawRect(i, j, 1, 1);
                }
            }
        }
    }

    public void addWaterStain(int x, int y) {
        int a = x-50;
        if (a < 0) {
            a = 0;
        }
        int b = y-50;
        if (b < 0) {
            b = 0;
        }
        for (int i = a; i < waterStains.length; i++) {
            for (int j = b; j < waterStains[i].length; j++) {
                waterStains[i][j] = true;
            }
        }
    }

}
