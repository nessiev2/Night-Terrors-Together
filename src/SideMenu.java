import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class SideMenu {
    private NTT c;
    private Image bigOpen, minOpen, minClosed;
    private Sound sound;
    private int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    private boolean[] bArray = new boolean[9];
    private String[] sArray = {"SET FIRE TO TRASH CANS", "MIX CHEMICALS!", "DISSECT FROGS", "HACK MAIN COMPUTER", "CREATE SLIPPING HAZARD", "MESS UP CHALKBOARD", "SPRINT", "STEAL MATH TESTS" , "SMASH VENDING MACHINES"};
    private String[] roomArray = {"look out for the interactive trash cans", "in Chemistry", "in Biology", "where else", "in Gym", "look out for the interactive chalkboard", "in English", "in Math" , "in Caf"};
    private boolean[] finTasks;

    private int x = 1835, y = 20, width = 50, height = 50, openX = NTT.SCREEN_WIDTH/2 - 400, openY = 20, openWidth = 800, openHeight = 800;
    private boolean isOpen;
    private boolean hasGenerated;
    public SideMenu(NTT c, Sound sound) {
        this.c = c;
        this.sound = sound;
        initializeSideMenu();
        try {
            bigOpen = ImageIO.read(new File("res\\sidemenuOpen.png"));
            minOpen = ImageIO.read(new File("res\\sidemenuMinOpen.png"));
            minClosed = ImageIO.read(new File("res\\sidemenuMin.png"));
        } catch (IOException e) { System.out.println("arson no image"); }
    }

    public boolean[] which4Tasks() {
//        for (int i = 0; i < 9; i++)  {
//            System.out.print(bArray[i] + " ");
//        }
//        System.out.println("");
        return bArray;
    }
    public boolean isScribbleTask(){
        if (bArray[5]){
            return true;
        }
        return false;
    }
    public boolean isArsonTask(){
        if (bArray[0]){
            return true;
        }
        return false;
    }
    public void initializeSideMenu(){
        finTasks = new boolean[9];
        isOpen = false;
        hasGenerated = false;
        generateTasks();
    }

    public void updateTaskCompletion(int n) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        //System.out.println("update task completion for int " + n);
        finTasks[n] = true;
        sound.playTaskCompleteSoundEffect();
        //System.out.println("fintasks [n]: " + finTasks[n]);
    }

    public boolean checkIfWinCondition() {
        for (int i = 0; i < 4; i++)  {
            //System.out.println(finTasks[array[i]]);
            //finTasks[array[i]] = true;
            if (!finTasks[array[i]]) // if it's not ALL TRUE -> bad
                return false;
            //System.out.println("i am running");
        }
        //.out.println("ALL TASKS COMEPLTE");
        return true; // ALL TASKS COMPLETE
    }

    public boolean getIsOpen(){
        return isOpen;
    }

    public void generateTasks() {
        //Collections.shuffle(Collections.singletonList(array), new Random());
        Random rand = new Random();
        // array of 0-8 is shuffled, first four indices of array are tasks for current game
        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = rand.nextInt(array.length);
            int temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }

        //System.out.println("array[i] \n----------------------------------------\"");
        for (int i = 0; i < 9; i++) {
            bArray[i] = false;
        }

        for (int i = 0; i < 4; i++)  {
            bArray[array[i]] = true;                // change boolean array such that the chosen tasks (first four indices in array) are set as true, other values are false
            //System.out.println(array[i]);
        }
        //System.out.println("----------------------------------------\"");

//        System.out.println("bArray \n----------------------------------------");
//        for (int i = 0; i < 9; i++)  {
//            System.out.print(bArray[i] + " ");
//        }
//        System.out.println("\n----------------------------------------");
    }

    public void updateIsOpen(boolean isOpen){
        this.isOpen = isOpen;
    }

    public void paint (Graphics2D g2d, DoMess mess){
//        if (!hasGenerated) {
//            generateTasks();
//            hasGenerated = true;
//        }
        g2d.setColor(Color.white);
        if (!isOpen){
            g2d.drawImage(minClosed, x, y, null);
//            g2d.fillRect(x, y, width, height);
        } else {
            g2d.drawImage(minOpen, x, y, null);
            g2d.drawImage(bigOpen, openX, openY, null);

            //            g2d.fillRect(openX, openY, openWidth, openHeight);
            g2d.setColor(Color.black);
            g2d.drawString("TASKS:", openX + openWidth - 500, openY + 30 + 125);

            for (int i = 0; i < 4; i++)  {
                // the checkboxes
                g2d.setColor(Color.black);
                g2d.setFont(new Font("TimesRoman", Font.BOLD, 35));
                g2d.drawRect(openX + 25, openY + 155 + i*100 + 125, 50, 50);

                g2d.drawString(sArray[array[i]], openX + 100, openY + 200 + 125 + i*100);

                g2d.setFont(new Font("TimesRoman", Font.ITALIC, 25));
                g2d.drawString(roomArray[array[i]], openX + 100, openY + 230 + 125 + i*100);


                if (finTasks[array[i]]) {
                    //System.out.println("task " + i + " is fin, did draw X");
                    g2d.setFont(new Font("TimesRoman", Font.BOLD, 50));
                    g2d.setColor(Color.red);
                    g2d.drawString("X", openX + 33, openY + 200 + i*100);
                }
            }
        }
    }
}
