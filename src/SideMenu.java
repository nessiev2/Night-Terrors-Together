import java.awt.*;
import java.util.Random;

public class SideMenu {
    DoMess mess;
    public static final Random gen = new Random();
    int[] array = {0, 1, 2, 3};
    String[] sArray = {"Light trash cans on fire", "Scribble on chalkboards", "Spill water in the GYM", "Run around", "Hack COMPUTER SCIENCE", "Break the vending machines in CAF"};
    boolean[] finTasks = new boolean[4];

    int x = 1835, y = 20, width = 50, height = 50, openX = Main.SCREEN_WIDTH/2 - 400, openY = 20, openWidth = 800, openHeight = 800;
    boolean isOpen = false;
    boolean hasGenerated = false;
    public SideMenu(DoMess mess){
        this.mess = mess;
    }

//    public void updateTaskCompletion(int n) {
//        System.out.println("update task completion for int " + n);
//        finTasks[2] = true;
//        System.out.println("fintasks [n]: " + finTasks[n]);
//    }

    public boolean getIsOpen(){
        return isOpen;
    }

    public void generateTasks() {
        // will randomly generate an int
        // int corresponds to a task and we have to randomly generate for a room
        int n = array.length;
        while (n > 1) {
            int k = gen.nextInt(n--); //decrements after using the value
            int temp = array[n];
            array[n] = array[k];
            array[k] = temp;
        }
    }

    public boolean getIsTaskComplete(Task t){
        if (t.getFinished()){
            return true;
        }
        return false;
    }

    public void updateIsOpen(boolean isOpen){
        this.isOpen = isOpen;
    }

    public void paint (Graphics2D g2d){
        if (!hasGenerated) {
            generateTasks();
            hasGenerated = true;
        }
        g2d.setColor(Color.white);
        //System.out.println(mess.getIsComplete());
        System.out.println(getIsTaskComplete(mess));
        if (!isOpen){
            g2d.fillRect(x, y, width, height);
        } else {
            g2d.fillRect(openX, openY, openWidth, openHeight);
            g2d.setColor(Color.black);
            g2d.drawString("TASKS:", openX + openWidth - 500, openY + 70);

            if (mess.getIsFin()){
                g2d.drawString("X", openX + 33, openY + 200 + 2*100);
                System.out.println("done!");
            }

//          for (int i = 0; i < 4; i++) {
//              System.out.print("FINTASKS: " + i + " " + finTasks[i] + " ");
//           }

            for (int i = 0; i < 4; i++)  {
                // the checkboxes
                g2d.setColor(Color.black);
                g2d.drawRect(openX + 25, openY + 155 + i*100, 50, 50);
                g2d.drawString(sArray[array[i]], openX + 100, openY + 200 + i*100);

//                if (finTasks[i]) {
//                    System.out.println("task " + i + " is fin, did draw X");
//                    g2d.setColor(Color.red);
//                    g2d.drawString("X", openX + 33, openY + 200 + i*100);
//                }
            }
        }
    }
}
