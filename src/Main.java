import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.Random;

/*
NOTES/REMINDERS
* no tasks in physics, move sprint to some other room later

BUGS
* make sure sprint does not draw inside a desk
* make sure the office's trash cans do not generate under a desk
* player movement gets restricted when holding down interact key??
* make it easier to spill water at the edges

TO-DO/TO-FIX LIST
* teacher
    * teacher should not be able to spawn on top of players, must spawn at least certain distance away
* rooms
    * add in their proper furniture stuffs
    * add in the other computers onto the desks in comp sci
* tasks
    * finish coding rest of tasks
        * math
        * caf
        * bio
        * chem
        * eng
* side menu
    * cross off the completed tasks when done

GOLD PLATING
* instead of pressing a button to open the menu, u click with a mouse
* add music/sound
* animations???
* screen gets red and tinted the closer the teacher is
* graphics
    * load rest of graphics
* doors
    * make side doors mats
*/

public class Main extends JPanel {
    //ATTRIBUTES
    Minimap minimap = new Minimap();
    final static int SCREEN_WIDTH = 1920, SCREEN_HEIGHT = 1080;
    boolean gameOver = false, pauseGame = false;
    int currentClassroom = 5, tCurrentClassroom = 1, clickClack = 0;
    int tobX = 1300, botY = 840, topY = 70, rightX = SCREEN_WIDTH-150, leftX = 0, sideY = SCREEN_HEIGHT/2-100;
    boolean flag = false;
    CountDown cd = new CountDown();
    Transition transition1 = new Transition();

    //DOORS
    ADoor bioToOffice = new ADoor(tobX, topY);
    ADoor officeToBio = new ADoor(tobX, botY);
    ADoor physToChem = new ADoor(rightX, sideY);
    ADoor chemToPhys = new ADoor(leftX, sideY);
    ADoor physToBio = new ADoor(leftX, sideY);
    ADoor bioToPhys = new ADoor(rightX, sideY);
    ADoor physToCaf = new ADoor(tobX, topY);
    ADoor cafToPhys = new ADoor(tobX, botY);
    ADoor gymToCaf = new ADoor(leftX, sideY);
    ADoor cafToGym = new ADoor(rightX, sideY);
    ADoor gymToChem = new ADoor(tobX, botY);
    ADoor chemToGym = new ADoor(tobX, topY);
    ADoor mathToBio = new ADoor(tobX, topY);
    ADoor bioToMath = new ADoor(tobX, botY);
    ADoor compSciToMath = new ADoor(leftX, sideY);
    ADoor mathToCompSci = new ADoor(rightX, sideY);
    ADoor physToCompSci = new ADoor(tobX, botY);
    ADoor compSciToPhys = new ADoor(tobX, topY);
    ADoor compSciToEng = new ADoor(rightX, sideY);
    ADoor engToCompSci = new ADoor(leftX, sideY);
    ADoor engToChem = new ADoor(tobX, topY);
    ADoor chemToEng = new ADoor(tobX, botY);
    ADoor cafToOffice = new ADoor(leftX, sideY);
    ADoor officeToCaf = new ADoor(rightX, sideY);

    //PLAYERS AND TEACHER
    Player p1 = new Player1();
    Player p2 = new Player2();
    Teacher t = new Teacher();
    Teacher[] teacher = {t};

    //ROOMS
    ROffice office = new ROffice();
    RPhysics phys = new RPhysics();
    RChemistry chem = new RChemistry();
    RBiology bio = new RBiology();
    RCaf caf = new RCaf();
    RGym gym = new RGym();
    RMath mathematics = new RMath();
    RCompSci compSci = new RCompSci();
    REng eng = new REng();
    MainMenu mainMenu = new MainMenu();
    GameOver gameOverScreen = new GameOver();
    DoMess doMess3 = new DoMess(true);

    SideMenu sideMenu = new SideMenu(doMess3);


    public void changeCurrentClassroom(int i) {
        currentClassroom = i;
    }

    public Main() {
        p1.spawnPlayer(SCREEN_WIDTH/2-p1.getWidth(), 660);
        p2.spawnPlayer(SCREEN_WIDTH/2-2*p1.getWidth(), 660);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                // STARTING GAME - MAIN MENU
                if (mainMenu.getIsMenuOpen() && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    mainMenu.changeMenu(false);
                    CountDown.CDReset();
                } else if (gameOver && e.getKeyCode() == KeyEvent.VK_R) {
                    flag = true;

                    gameOver = false;
                    mainMenu.changeMenu(true);
                }

                // PAUSE GAME
                if (!pauseGame && e.getKeyCode() == KeyEvent.VK_ESCAPE && !sideMenu.getIsOpen()) {
                    cd.startPause();
                    pauseGame = true;
                } else if (pauseGame && e.getKeyCode() == KeyEvent.VK_ESCAPE && !sideMenu.getIsOpen()) {
                    cd.stopPause();
                    pauseGame = false;
                }

                // opening side menu
                if (!mainMenu.getIsMenuOpen() && !sideMenu.getIsOpen() && e.getKeyCode() == KeyEvent.VK_M && !pauseGame) {
                    //cd.startPause();
                    //pauseGame = true;
                    sideMenu.updateIsOpen(true);
                } else if (!mainMenu.getIsMenuOpen() && sideMenu.getIsOpen() && e.getKeyCode() == KeyEvent.VK_M && !pauseGame) {
                    //cd.startPause();
                    //pauseGame = true;
                    sideMenu.updateIsOpen(false);
                }

                if (currentClassroom == 1) {
                    p1.keyReleased(p2, currentClassroom, e, office.arson1, office.trashCans, office.cb, office.pp, doMess3, compSci.doHack8, caf.vendMachines);
                    p2.keyReleased(p1, currentClassroom, e, office.arson1, office.trashCans, office.cb, office.pp, doMess3, compSci.doHack8, caf.vendMachines);
                } else if (currentClassroom == 2){
                    p1.keyReleased(p2, currentClassroom, e, caf.arson2, caf.trashCans, caf.cb, office.pp, doMess3, compSci.doHack8, caf.vendMachines);
                    p2.keyReleased(p1, currentClassroom, e, caf.arson2, caf.trashCans, caf.cb, office.pp, doMess3, compSci.doHack8, caf.vendMachines);
                } else if (currentClassroom == 3){
                    p1.keyReleased(p2, currentClassroom, e, gym.arson3, gym.trashCans, gym.cb, office.pp, doMess3, compSci.doHack8, caf.vendMachines);
                    p2.keyReleased(p1, currentClassroom, e, gym.arson3, gym.trashCans, gym.cb, office.pp, doMess3, compSci.doHack8, caf.vendMachines);
                } else if (currentClassroom == 4) {
                    p1.keyReleased(p2, currentClassroom, e, bio.arson4, bio.trashCans, bio.cb, office.pp, doMess3, compSci.doHack8, caf.vendMachines);
                    p2.keyReleased(p1, currentClassroom, e, bio.arson4, bio.trashCans, bio.cb, office.pp, doMess3, compSci.doHack8, caf.vendMachines);
                } else if (currentClassroom == 5) {
                    p1.keyReleased(p2, currentClassroom, e, phys.arson5, phys.trashCans, phys.cb, office.pp, doMess3, compSci.doHack8, caf.vendMachines);
                    p2.keyReleased(p1, currentClassroom, e, phys.arson5, phys.trashCans, phys.cb, office.pp, doMess3, compSci.doHack8, caf.vendMachines);
                } else if (currentClassroom == 6) {
                    p1.keyReleased(p2, currentClassroom, e, chem.arson6, chem.trashCans, chem.cb, office.pp, doMess3, compSci.doHack8, caf.vendMachines);
                    p2.keyReleased(p1, currentClassroom, e, chem.arson6, chem.trashCans, chem.cb, office.pp, doMess3, compSci.doHack8, caf.vendMachines);
                } else if (currentClassroom == 7){
                    p1.keyReleased(p2, currentClassroom, e, mathematics.arson7, mathematics.trashCans, mathematics.cb, office.pp, doMess3, compSci.doHack8, caf.vendMachines);
                    p2.keyReleased(p1, currentClassroom, e, mathematics.arson7, mathematics.trashCans, mathematics.cb, office.pp, doMess3, compSci.doHack8, caf.vendMachines);
                } else if (currentClassroom == 8){
                    p1.keyReleased(p2, currentClassroom, e, compSci.arson8, compSci.trashCans, compSci.cb, office.pp, doMess3, compSci.doHack8, caf.vendMachines);
                    p2.keyReleased(p1, currentClassroom, e, compSci.arson8, compSci.trashCans, compSci.cb, office.pp, doMess3, compSci.doHack8, caf.vendMachines);
                } else if (currentClassroom == 9){
                    p1.keyReleased(p2, currentClassroom, e, eng.arson9, eng.trashCans, eng.cb, office.pp, doMess3, compSci.doHack8, caf.vendMachines);
                    p2.keyReleased(p1, currentClassroom, e, eng.arson9, eng.trashCans, eng.cb, office.pp, doMess3, compSci.doHack8, caf.vendMachines);
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                p1.keyPressed(e);
                p2.keyPressed(e);
            }
        });
        setFocusable(true);
    }

    private void move(Main c) {
        if (phys.sprint.getFlag1()){
            phys.sprint.sprintTicks(p1);
        }
        if (phys.sprint.getFlag2()){
            phys.sprint.sprintTicks(p2);
        }

        Random r = new Random();

        if (!pauseGame) {
            clickClack++;

            // TEACHER SPAWNS IN A CLASSROOM
            if (clickClack >= 50 && tCurrentClassroom != currentClassroom){
                int[] tmp = {1, 2, 3, 4, 6, 7, 8, 9};
                t.spawnTeacher(0,0);
                tCurrentClassroom = tmp[r.nextInt(8)];
                clickClack = 0;
            }

            if (currentClassroom == tCurrentClassroom){
                p1.checkPlayerCaught(teacher);
                p2.checkPlayerCaught(teacher);
            }

            if (currentClassroom != tCurrentClassroom ){
                if (p1.checkTeacher(teacher) || p2.checkTeacher(teacher)) {
                    t.spawnTeacher(100, 10);
                }
            }

            switch (currentClassroom) {
                case 1:
                    p1.move(office.desks);
                    p2.move(office.desks);
                    break;
                case 2:
                    p1.move(caf.desks);
                    p2.move(caf.desks);
                    break;
                case 3:
                    p1.move(gym.desks);
                    p2.move(gym.desks);
                    break;
                case 4:
                    p1.move(bio.desks);
                    p2.move(bio.desks);
                    break;
                case 5:
                    p1.move(phys.desks);
                    p2.move(phys.desks);
                    break;
                case 6:
                    p1.move(chem.desks);
                    p2.move(chem.desks);
                    break;
                case 7:
                    p1.move(mathematics.desks);
                    p2.move(mathematics.desks);
                    break;
                case 8:
                    p1.move(compSci.desks);
                    p2.move(compSci.desks);
                    break;
                case 9:
                    p1.move(eng.desks);
                    p2.move(eng.desks);
                    break;
            }

            if (tCurrentClassroom == currentClassroom) {
                t.move(c, p1, p2, p1.getX(), p1.getY(), p2.getX(), p2.getY());
            }

            cd.move();

            if (cd.getTime() <= 0) {
                gameOver = true;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (mainMenu.getIsMenuOpen()) {
            mainMenu.paintMainMenu(g2d);
        } else if (gameOver) {
            gameOverScreen.paintGameOver(g2d);
        }else {
            if (t.getBothCaught()){
                System.out.println("skill issue");
                gameOver = true;
            } else {
                switch(currentClassroom) {
                    case 1:
                        office.paint(g, p1, p2, transition1);
                        officeToBio.paint(g2d);
                        officeToCaf.paint(g2d);

                        if (p1.getIsCaught()) {
                            p1.spawnPlayer(170, 150);
                        }
                        if (p2.getIsCaught()) {
                            p2.spawnPlayer(170, 150);
                        }
                        changeRooms(officeToBio, bioToOffice, 4, p1, p2, transition1, g2d);
                        changeRooms(officeToCaf, cafToOffice, 2, p1, p2, transition1, g2d);
                        break;

                    case 2:
                        caf.paint(g, p1, p2, transition1);
                        cafToPhys.paint(g2d);
                        cafToGym.paint(g2d);
                        cafToOffice.paint(g2d);
                        changeRooms(cafToPhys, physToCaf, 5, p1, p2, transition1, g2d);
                        changeRooms(cafToGym, gymToCaf, 3, p1, p2, transition1, g2d);
                        changeRooms(cafToOffice, officeToCaf, 1, p1, p2, transition1, g2d);
                        break;

                    case 3:
                        gym.paint(g, p1, p2, transition1);
                        gymToCaf.paint(g2d);
                        gymToChem.paint(g2d);
                        changeRooms(gymToCaf, cafToGym, 2, p1, p2, transition1, g2d);
                        changeRooms(gymToChem, chemToGym, 6, p1, p2, transition1, g2d);
                        break;

                    case 4:
                        bio.paint(g, p1, p2, transition1);
                        bioToPhys.paint(g2d);
                        bioToOffice.paint(g2d);
                        bioToMath.paint(g2d);
                        changeRooms(bioToPhys, physToBio, 5, p1, p2, transition1, g2d);
                        changeRooms(bioToOffice, bioToOffice, 1, p1, p2, transition1, g2d);
                        changeRooms(bioToMath, mathToBio, 7, p1, p2, transition1, g2d);
                        break;

                    case 5:
                        phys.paint(g, p1, p2, transition1);
                        physToChem.paint(g2d);
                        physToBio.paint(g2d);
                        physToCaf.paint(g2d);
                        physToCompSci.paint(g2d);
                        changeRooms(physToChem, chemToPhys, 6, p1, p2, transition1, g2d);
                        changeRooms(physToBio, bioToPhys, 4, p1, p2, transition1, g2d);
                        changeRooms(physToCaf, cafToPhys, 2, p1, p2, transition1, g2d);
                        changeRooms(physToCompSci, compSciToPhys, 8, p1, p2, transition1, g2d);
                        break;

                    case 6:
                        chem.paint(g, p1, p2, transition1);
                        chemToPhys.paint(g2d);
                        chemToGym.paint(g2d);
                        chemToEng.paint(g2d);
                        changeRooms(chemToPhys, physToChem, 5, p1, p2, transition1, g2d);
                        changeRooms(chemToGym, gymToChem, 3, p1, p2, transition1, g2d);
                        changeRooms(chemToEng, engToChem, 9, p1, p2, transition1, g2d);
                        break;

                    case 7:
                        mathematics.paint(g, p1, p2, transition1);
                        mathToCompSci.paint(g2d);
                        mathToBio.paint(g2d);
                        changeRooms(mathToCompSci, compSciToMath, 8, p1, p2, transition1, g2d);
                        changeRooms(mathToBio, bioToMath, 4, p1, p2, transition1, g2d);
                        break;

                    case 8:
                        compSci.paint(g, p1, p2, transition1);
                        compSciToEng.paint(g2d);
                        compSciToMath.paint(g2d);
                        compSciToPhys.paint(g2d);
                        changeRooms(compSciToEng, engToCompSci, 9, p1, p2, transition1, g2d);
                        changeRooms(compSciToMath, mathToCompSci, 7, p1, p2, transition1, g2d);
                        changeRooms(compSciToPhys, physToCompSci, 5, p1, p2, transition1, g2d);
                        break;

                    case 9:
                        eng.paint(g, p1, p2, transition1);
                        engToChem.paint(g2d);
                        engToCompSci.paint(g2d);
                        changeRooms(engToChem, chemToEng, 6, p1, p2, transition1, g2d);
                        changeRooms(engToCompSci, compSciToEng, 8, p1, p2, transition1, g2d);
                        break;
                    default:
                        g2d.setFont(new Font("TimesRoman", Font.BOLD, 100));
                        g2d.drawString("idk how u made it here!! please stop!!!", 200, 100);
                }
                if (!p1.getIsCaught() || currentClassroom == 1){
                    p1.paint(g2d, doMess3);
                }
                if (!p2.getIsCaught() || currentClassroom == 1){
                    p2.paint(g2d, doMess3);
                }
                if ((p1.getIsCaught() || p2.getIsCaught()) && currentClassroom == 1){
                    office.paintBars(g2d);
                }
                if (currentClassroom == tCurrentClassroom){
                    t.paint(g2d);
                }
                cd.paint(g2d);
                sideMenu.paint(g2d);

                minimap.paint(g2d);
                minimap.paintYou(g2d, currentClassroom);
                minimap.paintTeacher(g2d, tCurrentClassroom);
            }
        }
    }

    public void changeRooms(ADoor door1, ADoor door2, int nextClassroom, Player p1, Player p2, Transition transition1, Graphics2D g2d){
        if (door1.containsPlayer(p1, p2)) {
            transition1.paintBlack(g2d);
            changeCurrentClassroom(nextClassroom);

            if (door2.getX() == leftX){                                                                             // door is on left
                p1.spawnPlayer(door2.getX() + door2.getWidth() + 50, door2.getY() + 15);
                p2.spawnPlayer(door2.getX() + door2.getWidth() + 50, door2.getY() + 15);
            } else if (door2.getY() == topY){                                                                       // door is on top
                p1.spawnPlayer(door2.getX() + 15, door2.getY() + door2.getHeight() + 50);
                p2.spawnPlayer(door2.getX() + 15, door2.getY() + door2.getHeight() + 50);
            } else if (door2.getY() == botY){                                                                       // door is on bottom
                p1.spawnPlayer(door2.getX() + 15, door2.getY() - 50);
                p2.spawnPlayer(door2.getX() + 15, door2.getY() - 50);
            } else if (door2.getX() == rightX){                                                                     // door is on right
                p1.spawnPlayer(door2.getX() - 50, door2.getY() + 15);
                p2.spawnPlayer(door2.getX() - 50, door2.getY() + 15);
            } else {
                System.out.println("ayo this door is kinda broken fix it asap :) ---> door1: " + door1 + "\t door2: " + door2);
                p1.spawnPlayer(0, 0);
                p2.spawnPlayer(0, 0);
            }

        }
    }

    public void reset(Main c){
        c.flag = false;
        c.gameOver = false;
        c.t.initializeTeacher();
        c.p1.initializePlayer();
        c.p2.initializePlayer();
        c.currentClassroom = 5;

        office.initializeOffice();
        caf.initializeCaf();
        gym.initializeGym();
        bio.initializeBio();
        phys.initializePhysics();
        chem.initializeChem();
        mathematics.initializeMath();
        compSci.initializeCompSci();
        eng.initializeEng();

        // reset side menu

        CountDown.CDReset();
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("NIGHT TERRORS TOGETHER!");

        Main c = new Main();
        frame.add(c);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true){
            c.reset(c);
            while (!c.flag) {
                c.move(c); //Updates the coordinates
                c.repaint(); //Calls the paint method
                Thread.sleep(10); //Pauses for a moment
            }
        }
    }
}