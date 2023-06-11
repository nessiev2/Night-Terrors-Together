import java.awt.*;

public class Task {
    protected boolean isTask, isComplete = false;
    public Task (boolean isTask) {
        this.isTask = isTask;
    }

    public void taskFinished() { isComplete = true; }
}
