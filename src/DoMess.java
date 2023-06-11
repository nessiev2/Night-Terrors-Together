import java.awt.*;
public class DoMess extends Task {
    private boolean isFin = false;
    private boolean[][] waterStains = new boolean[(Main.SCREEN_WIDTH/2)/30][(810-270)/30];
    public DoMess(boolean isTask){
        super(isTask);
    }

    public void paint(Graphics g2d) {
        isFin = true;
        for (int i = 0; i < waterStains.length; i++) {
            for (int j = 0; j < waterStains[i].length; j++) {
                g2d.setColor(new Color(1, 184, 252));
                if (waterStains[i][j]){
                    g2d.fillRect((i*30)+15+(1920/4), j*30+270, 30, 30);
                } else {
                    isFin = false;
                }
            }
        }

        if (isFin) {
            System.out.println("task has finished");
            //updateTaskCompletion(2);
            taskFinished();
        }
    }

    public void addWaterStain(int x, int y, Player p, Graphics g2d) {
        int px = p.getCenterX();
        int py = p.getCenterY();
        int minX = 15+(1920/4);
        int maxX = ((waterStains.length-1)*30)+15+(1920/4);
        int minY = 270;
        int maxY = ((waterStains[0].length-1)*30)+270;

        if (px > minX && px < maxX){
            if (py > minY && py < maxY + 30){
                int i = ((px-1920/4)/30);
                int j = (py-270)/30;
                for (int a = 0; a < 3; a++){
                    for (int b = 0; b < 3; b++){
                        if (i + a < waterStains.length && j + b < waterStains[0].length){
                            waterStains[i+a][j+b] = true;
                        }
                        if (i - a > 0 && i - a < waterStains.length && j - b > 0 && j - b < waterStains[0].length){
                            waterStains[i-a][j-b] = true;
                        }
                    }
                }
            }
        }
    }

}
