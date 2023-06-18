import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

public class DoMess extends Task {
    private boolean ts = true;

    private boolean isFin;
    private boolean[][] waterStains = new boolean[(NTT.SCREEN_WIDTH/2)/30][(810-270)/30];
    public DoMess(boolean isTask){
        super(isTask);
    }
    public boolean getIsFin(){
        return isFin;
    }
    public void paint(Graphics g2d, SideMenu menu) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        int k = 10;
        if (isTask){
            if (!isFin){
                g2d.setFont(new Font("TimesRoman", Font.BOLD, 25));
                g2d.setColor(Color.white);
                g2d.drawString("interact to ruin someone's day,", 15+(1920/4)+100, 270-50);
                g2d.drawString("fill the entire rectangle!", 15+(1920/4)+100, 270-25);

                g2d.drawRect(15+(1920/4), 270, NTT.SCREEN_WIDTH/2, (810-270));
            }
            g2d.setColor(Color.black);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 50));
            g2d.drawString("NO WATER IN THE GYM.", 15+(1920/4) + 200, 400);
            g2d.drawString("STAY DEHYDRATED :).", 15+(1920/4) + 200, 500);

            isFin = true;
            for (int i = 0; i < waterStains.length; i++) {
                for (int j = 0; j < waterStains[i].length; j++) {
                    if (!waterStains[i][j]){
                        isFin = false;
                    }
                }
            }

            if (isFin) {
                g2d.setFont(new Font("TimesRoman", Font.BOLD, 25));
                g2d.setColor(Color.white);
                g2d.drawString("task complete!", 15+(1920/4)+100, 270-25);

                this.taskFinished();

                if (ts) {
                    menu.updateTaskCompletion(4);
                    ts = false;
                }
                //isTask = true;
            }

            for (int i = 0; i < waterStains.length; i++) {
                for (int j = 0; j < waterStains[i].length; j++) {
                    if (waterStains[i][j]){
                        g2d.setColor(new Color(1, 184, 252));
                        g2d.fillRect((i*30)+15+(1920/4), j*30+270, 30, 30);
                    }
                }
            }
        }
    }


    public void addWaterStain(Player p, Graphics g2d) {
        //if (isTask) {
            int px = p.getCenterX();
            int py = p.getCenterY();
            int minX = 15 + (1920 / 4);
            int maxX = ((waterStains.length - 1) * 30) + 15 + (1920 / 4);
            int minY = 270;
            int maxY = ((waterStains[0].length - 1) * 30) + 270;

            if (px > minX && px < maxX && py > minY && py < maxY + 30) {
                int i = ((px - 1920 / 4) / 30);
                int j = (py - 270) / 30;
                for (int a = 0; a < 3; a++) {
                    for (int b = 0; b < 3; b++) {
                        if ((i + a < waterStains.length) && (j + b < waterStains[0].length)) {
                            waterStains[i + a][j + b] = true;
                        }
                        if ((i - a > 0) && (j - b > 0)) {
                            waterStains[i - a][j - b] = true;
                        }
                        if ((i - a > 0) && (j + b < waterStains[0].length)) {
                            waterStains[i - a][j + b] = true;
                        }
                        if ((i + a < waterStains.length) && (j - b > 0)) {
                            waterStains[i + a][j - b] = true;
                        }
                    }
                }
            }
        //}
    }

}
