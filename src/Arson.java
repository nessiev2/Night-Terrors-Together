public class Arson extends Task{

    public final int RADIUS = 50;
    public Arson(boolean isTask){
        super(isTask);
    }

    public int doTask(TrashCan[] trash, Player p1, Player p2) {
        int counter = 0;
        //while(!isComplete){
            for (TrashCan t: trash){
                double dist1 = Math.sqrt(Math.pow(t.getX()-p1.getX(), 2) + Math.pow(t.getY()-p1.getY(), 2));
                double dist2 = Math.sqrt(Math.pow(t.getX()-p2.getX(), 2) + Math.pow(t.getY()-p2.getY(), 2));

                if (dist1 <= RADIUS && p1.interact){
                    counter++;
                }
                if (dist2 <= RADIUS && p2.interact){
                    counter++;
                }
            }
            if (counter >= 3){
                isComplete = true;
                System.out.println("mmm finish");
                return 1;
            }
        //}

        return 0;
    }

}
