public class Task {
    protected boolean isTask, finished = false;
    public Task (boolean isTask) {
        this.isTask = isTask;
    }

    public void taskFinished() { finished = true; }
    public boolean getFinished() { return finished; }
}
