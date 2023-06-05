public class Timer {

    private long startTime = 0;
    private long stopTime = 0;


    public void start() {
        this.startTime = System.nanoTime();
    }


    public void stop() {
        this.stopTime = System.nanoTime();
    }

    //elaspsed time in seconds
    public long getElapsedTime() {
        long elapsed;
        elapsed = (stopTime - startTime)/(long)(Math.pow(10, 12));
        return elapsed;
    }
}