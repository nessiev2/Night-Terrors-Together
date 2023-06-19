import java.awt.*;
public class CountDown {
    private static long startTime;
    private long currentTime;
    private long elapsedTime;
    private long backwardsTime;
    private long elapsedSeconds;
    private long secondsDisplay;
    private long elapsedMinutes;
    private static long pausedTime;

    private static long pause1 = 0;
    private static long pause2 = 0;

    public CountDown() {
        startTime = System.currentTimeMillis();
    }

    public static void CDReset()  {
        startTime = System.currentTimeMillis();
        pause1 = 0;
        pause2 = 0;
        pausedTime = 0;
    }

    public void startPause() { pause1 = System.currentTimeMillis(); }

    public void stopPause() { pause2 = System.currentTimeMillis(); }


    public void move() {
        currentTime = System.currentTimeMillis();
        elapsedTime = System.currentTimeMillis() - startTime;
        pausedTime += (pause2 - pause1);

        pause1 = 0;
        pause2 = 0;
        backwardsTime = 3*60*1000 + 1000 + pausedTime - elapsedTime;

        elapsedSeconds = backwardsTime / 1000;
        secondsDisplay = elapsedSeconds % 60;
        elapsedMinutes = elapsedSeconds / 60;
    }

    public void paint(Graphics g2d) {
        g2d.setColor(Color.white);
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 50));
        if (elapsedSeconds <= 10) {
            g2d.setColor(Color.red);
        }

        if (secondsDisplay < 10) {
            g2d.drawString(elapsedMinutes + ":0" + secondsDisplay, 10, 50);
        } else {
            g2d.drawString(elapsedMinutes + ":" + secondsDisplay, 10, 50);
        }
    }

    public long getTime() {
        return backwardsTime;
    }
}