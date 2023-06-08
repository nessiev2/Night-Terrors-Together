public class Task {
    boolean isTask, isComplete = false;
    public Task (boolean isTask) {
        this.isTask = isTask;
    }

    public void taskFinished() { isComplete = true; }

}
