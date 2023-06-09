import java.awt.*;
public class DoMess extends Task {

    public boolean[][] waterStains = new boolean[(1920/2)/30][(810-270)/30];
    public DoMess(boolean isTask){
        super(isTask);
    }

    public void paint(Graphics g2d) {
        //boolean isFin = true;
        for (int i = 0; i < waterStains.length; i++) {
            for (int j = 0; j < waterStains[i].length; j++) {
                if (waterStains[i][j]) {
                    g2d.setColor(Color.blue);
                    g2d.fillRect(i*30+15+1920/4, j*30+150, 30, 30);
                } else {
                    //waterStains[i][j] = true;
                }
            }
        }

        boolean isFin = true;
        for (int i = 0; i < waterStains.length; i++) {
            for (int j = 4; j < waterStains[i].length; j++) {
                if (waterStains[i][j]) {

                } else {
                    isFin = false;
                }
            }
        }

        if (isFin) {
            updateTaskCompletion(2);
            taskFinished();
            System.out.println("task has finished");
        }
    }

    public void addWaterStain(int x, int y) {
        int a = ((x-1920/4)/30)-3;
        if (a < 0) {
            a = 0;
        } else if (a > 1920/60-3) {
            a = 28;
        }
        int b = (y/30)-3;
        if (b < 0) {
            b = 0;
        } else if (b > 15) {
            b = 14;
        }
        for (int i = a; i < a+3; i++) {
            for (int j = b; j < b+3; j++) {
                waterStains[i][j] = true;
            }
        }
    }

}
