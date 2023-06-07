public class DoArson extends Task{

    public final int RADIUS = 200;
    public int counter = 0;
    public DoArson(boolean isTask){
        super(isTask);
    }

    public int doTask(TrashCan[] trash, Player p1, Player p2) {
        if (isTask){
            for (TrashCan t: trash){
                if (t.getIsOnFire()) {
                    counter++;
                }
            }

            if (counter >= 3){
                isComplete = true;
                return 1;
            }
        }

        return 0;
    }

}
