import java.awt.*;
public class DoMess extends Task {

    public boolean[][] waterStains = new boolean[1920/30][810/30];

    public DoMess(boolean isTask){
        super(isTask);
    }

    public void paint(Graphics g2d) {
        for (int i = 0; i < waterStains.length; i++) {
            for (int j = 0; j < waterStains[i].length; j++) {
                if (waterStains[i][j]) {
                    g2d.setColor(Color.blue);
                    g2d.fillRect(i*30, j*30+60, 30, 30);
                }
            }
        }
    }

    public void addWaterStain(int x, int y) {
        int a = (x/30);
        if (a < 0) {
            a = 0;
        }
        int b = (y/30);
        if (b < 0) {
            b = 0;
        }
        for (int i = a; i < a+5; i++) {
            for (int j = b; j < b+5; j++) {
                waterStains[i][j] = true;
            }
        }
    }

}
