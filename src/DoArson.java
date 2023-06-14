public class DoArson extends Task {

    public final int RADIUS = 200;
    public int counter = 0;
    public DoArson(boolean isTask){
        super(isTask);
    }

    public int doTask(ATrashCan[] trash, Player p1, Player p2, SideMenu menu) {
        if (isTask){
            for (ATrashCan t: trash){
                if (t.getIsOnFire()) {
                    counter++;
                }
            }

            if (counter >= 3){
                this.taskFinished();
                menu.updateTaskCompletion(0);
                return 1;
            }
        }

        return 0;
    }

}
