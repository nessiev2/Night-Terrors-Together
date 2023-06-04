import java.util.Random;

public class Sprint extends Task{
    int x, y, radius = 100;
    public Sprint(boolean isTask) {
        super(isTask);

        Random r = new Random();
        // i <3 hard-coding
        int[] randX = {r.nextInt(1431) + 270, r.nextInt(271), r.nextInt(221) + 1420};
        int[] randY = {r.nextInt(91) + 290, r.nextInt(221) + 660, r.nextInt(221) + 660};

        x = randX[r.nextInt(3)];
        y = randY[r.nextInt(3)];
    }

    public void doTask(Player p1, Player p2) {
        // if player is near point, allow player to sprint mmm yay 
    }

}
