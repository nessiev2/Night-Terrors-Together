import java.awt.*;
public class CountDown {
    long startTime;
    long currentTime;
    long elapsedTime;
    long backwardsTime;
    long elapsedSeconds;
    long secondsDisplay;
    long elapsedMinutes;
    long pausedTime;

    long pause1 = 0;
    long pause2 = 0;

    public CountDown() {
        startTime = System.currentTimeMillis();
    }

    public void CDReset()  {
        startTime = System.currentTimeMillis();
    }

    public void startPause() { pause1 = System.currentTimeMillis(); }

    public void stopPause() { pause2 = System.currentTimeMillis(); }


    public void move() {
        currentTime = System.currentTimeMillis();
        elapsedTime = System.currentTimeMillis() - startTime;
        pausedTime += (pause2 - pause1);
        pause1 = 0;
        pause2 = 0;
        backwardsTime = 60000*10 + 1000 + pausedTime - elapsedTime;
        //backwardsTime = 120000 - elapsedTime;

        elapsedSeconds = backwardsTime / 1000;
        secondsDisplay = elapsedSeconds % 60;
        elapsedMinutes = elapsedSeconds / 60;
    }

    public void paint(Graphics g2d) {
        g2d.setColor(Color.white);
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