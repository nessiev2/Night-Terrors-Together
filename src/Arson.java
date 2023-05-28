public class Arson extends Task{

    public final int RADIUS = 200;
    public Arson(boolean isTask){
        super(isTask);
    }

    public int doTask(boolean isTask, TrashCan[] trash, Player1 p1, Player2 p2) {
        int counter = 0;
        if (isTask) {
            for (TrashCan t: trash){
                double dist1 = Math.sqrt(Math.pow(t.getX()-p1.getX(), 2) + Math.pow(t.getY()-p1.getY(), 2));
                double dist2 = Math.sqrt(Math.pow(t.getX()-p2.getX(), 2) + Math.pow(t.getY()-p2.getY(), 2));

                if (dist1 <= RADIUS && p1.interact){
                    counter++;
                    System.out.println("p1 interact");
                }
                if (dist2 <= RADIUS && p2.interact){
                    counter++;
                    System.out.println("p2 interact");
                }
            }
            if (counter >= 3) {
                isComplete = true;
                System.out.println("mmm finish");
                return 1;
            }

        }
        return 0;
    }

}
