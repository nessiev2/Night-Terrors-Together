public class Task {
    protected boolean isTask, finished;
    public Task (boolean isTask) {
        this.isTask = isTask;
    }

    public void taskFinished() {
        finished = true;
        System.out.println("HELOOOOOOO??????? " + finished);
    }
    public boolean getFinished() { return finished; }
}
