public class Task extends SideMenu {
    boolean isTask, isComplete = false;
    public Task (boolean isTask) {
        this.isTask = isTask;
    }

    public void taskFinished() { isComplete = true; }

}
