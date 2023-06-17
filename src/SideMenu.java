import java.awt.*;
import java.util.Random;

public class SideMenu {
    NTT c;
    int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    boolean[] bArray = new boolean[9];
    String[] sArray = {"SET FIRE TO TRASH CANS", "CHEMICALS", "DISSECTION", "HACK COMPUTER SCIENCE", "SPILL WATER", "CHALKBOARD SCRIBBLE", "SPRINT", "STEAL MATH TESTS" , "SMASH CAF VENDING MACHINES"};
    boolean[] finTasks;

    int x = 1835, y = 20, width = 50, height = 50, openX = NTT.SCREEN_WIDTH/2 - 400, openY = 20, openWidth = 800, openHeight = 800;
    boolean isOpen;
    boolean hasGenerated;
    public SideMenu(NTT c) {
        this.c = c;
        initializeSideMenu();
    }

    public boolean[] which4Tasks() {
        for (int i = 0; i < 9; i++)  {
            System.out.print(bArray[i] + " ");
        }
        System.out.println("");
        return bArray;
    }

    public void initializeSideMenu(){
        finTasks = new boolean[9];
        isOpen = false;
        hasGenerated = false;
    }

    public void updateTaskCompletion(int n) {
        //System.out.println("update task completion for int " + n);
        finTasks[n] = true;
        //System.out.println("fintasks [n]: " + finTasks[n]);
    }

    public boolean checkIfWinCondition() {
        for (int i = 0; i < 4; i++)  {
            //finTasks[array[i]] = true;
            if (!finTasks[array[i]]) // if it's not ALL TRUE -> bad
                return false;
            //System.out.println("i am running");
        }
        System.out.println("ALL TASKS COMEPLTE");
        return true; // ALL TASKS COMPLETE
    }

    public boolean getIsOpen(){
        return isOpen;
    }

    public void generateTasks() {
        //Collections.shuffle(Collections.singletonList(array), new Random());
        Random rand = new Random();

        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = rand.nextInt(array.length);
            int temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }

        for (int i = 0; i < 4; i++)  {
            bArray[array[i]] = true;
            System.out.println(array[i]);
        }

        for (int i = 0; i < 9; i++)  {
            System.out.print(bArray[i] + " ");
        }
    }

    public void updateIsOpen(boolean isOpen){
        this.isOpen = isOpen;
    }


    public void paint (Graphics2D g2d, DoMess mess){
        if (!hasGenerated) {
            //System.out.println("i have generated 4 unique tasks");
            generateTasks();
            hasGenerated = true;
        }
        g2d.setColor(Color.white);

        if (!isOpen){
            g2d.fillRect(x, y, width, height);
        } else {
            g2d.fillRect(openX, openY, openWidth, openHeight);
            g2d.setColor(Color.black);
            g2d.drawString("TASKS:", openX + openWidth - 500, openY + 70);

            for (int i = 0; i < 4; i++)  {
                // the checkboxes
                g2d.setColor(Color.black);
                g2d.drawRect(openX + 25, openY + 155 + i*100, 50, 50);
                g2d.drawString(sArray[array[i]], openX + 100, openY + 200 + i*100);

                if (finTasks[array[i]]) {
                    //System.out.println("task " + i + " is fin, did draw X");
                    g2d.setColor(Color.red);
                    g2d.drawString("X", openX + 33, openY + 200 + i*100);
                }
            }
        }
    }
}
