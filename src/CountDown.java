import java.awt.*;
public class CountDown {
    long startTime;
    long currentTime;
    long elapsedTime;
    long backwardsTime;
    long elapsedSeconds;
    long secondsDisplay;
    long elapsedMinutes;

    public CountDown() {
        startTime = System.currentTimeMillis();
    }

    public void move() {
        currentTime = System.currentTimeMillis();
        elapsedTime = System.currentTimeMillis() - startTime;
        backwardsTime = 15000 - elapsedTime;
        //backwardsTime = 120000 - elapsedTime;

        elapsedSeconds = backwardsTime / 1000;
        secondsDisplay = elapsedSeconds % 60;
        elapsedMinutes = elapsedSeconds / 60;
    }

    public void paint(Graphics g2d) {
        g2d.setColor(Color.black);
        if (elapsedSeconds <= 10) {
            g2d.setColor(Color.red);
        }

        if (secondsDisplay < 10) {
            g2d.drawString(elapsedMinutes + ":0" + secondsDisplay, 0, 50);
        } else {
            g2d.drawString(elapsedMinutes + ":" + secondsDisplay, 0, 50);
        }
    }

    public long getTime() {
        return backwardsTime;
    }


}
