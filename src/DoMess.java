import java.awt.*;
public class DoMess extends Task {
    private boolean flag = false;
    protected boolean isFin;
    private boolean[][] waterStains = new boolean[(Main.SCREEN_WIDTH/2)/30][(810-270)/30];
    public DoMess(boolean isTask){
        super(isTask);
    }
    public boolean getIsFin(){
        return isFin;
    }
    public void paint(Graphics g2d) {
        checkIsFin();
        g2d.setColor(new Color(1, 184, 252));
        for (int i = 0; i < waterStains.length; i++) {
            for (int j = 0; j < waterStains[i].length; j++) {
                if (waterStains[i][j]){
                    g2d.fillRect((i*30)+15+(1920/4), j*30+270, 30, 30);
                }
            }
        }

        if (isFin){
            g2d.setColor(Color.green);
            //g2d.fillRect(0, 300,100,100);
            //System.out.println("done");
        }
    }

    public void checkIsFin(){
        isFin = true;
        //if (!flag){
            for (int i = 0; i < waterStains.length; i++) {
                for (int j = 0; j < waterStains[i].length; j++) {
                    if (!waterStains[i][j]){
                        isFin = false;
                        flag = true;
                        //System.out.println("sabotage");
                    }
                }
            }
        //}

        if (isFin) {
            //System.out.println("i work tho???");
            this.taskFinished();
            isTask = true;
        }
    }

    public void addWaterStain(Player p, Graphics g2d) {
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
                for (int a = 0; a < 10; a++){
                    for (int b = 0; b < 10; b++){
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
