import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.Random;

/*

BUGS
* if u leave physics thru door on right, then come back to physics, u cant go thru the up door bc the players are like 2 pixels too long (the boundaries got changed????? idk)

TO-DO/TO-FIX LIST
* teacher
    * dont let the teacher phase thru desks and other stuff
    * teacher should not be able to spawn on top of players, must spawn at least certain distance away
* rooms
    * ugly, why is a door on top of a table
* tasks
    * finish coding rest of tasks
    * choose 4 tasks at random
* side menu
    * write random tasks
    * write task in red after completion
* general
    * make gameover work ✖✖✖
* graphics
    * load rest of graphics
* doors
    * make side doors mats

GOLD PLATING
* instead of pressing a button to open the menu, u click with a mouse
* add music/sound
* animations???

*/

public class Main extends JPanel {
    //ATTRIBUTES
    Minimap minimap = new Minimap();
    final static int SCREEN_WIDTH = 1920, SCREEN_HEIGHT = 1080;
    boolean gameOver = false, pauseGame = false;
    int currentClassroom = 5, tCurrentClassroom = 1, clickClack = 0;
    int tobX = 1300, botY = 850, topY = 70, rightX = SCREEN_WIDTH-150, leftX = 0, sideY = SCREEN_HEIGHT/2-100;
    SideMenu sideMenu = new SideMenu();
    CountDown cd = new CountDown();
    Transition transition1 = new Transition();

    //DOORS
    Door bioToOffice = new Door(tobX, topY);
    Door officeToBio = new Door(tobX, botY);
    Door physToChem = new Door(rightX, sideY);
    Door chemToPhys = new Door(leftX, sideY);
    Door physToBio = new Door(leftX, sideY);
    Door bioToPhys = new Door(rightX, sideY);
    Door physToCaf = new Door(tobX, topY);
    Door cafToPhys = new Door(tobX, botY);
    Door gymToCaf = new Door(leftX, sideY);
    Door cafToGym = new Door(rightX, sideY);
    Door gymToChem = new Door(tobX, botY);
    Door chemToGym = new Door(tobX, topY);
    Door mathToBio = new Door(tobX, topY);
    Door bioToMath = new Door(tobX, botY);
    Door compSciToMath = new Door(leftX, sideY);
    Door mathToCompSci = new Door(rightX, sideY);
    Door physToCompSci = new Door(tobX, botY);
    Door compSciToPhys = new Door(tobX, topY);
    Door compSciToEng = new Door(rightX, sideY);
    Door engToCompSci = new Door(leftX, sideY);
    Door engToChem = new Door(tobX, topY);
    Door chemToEng = new Door(tobX, botY);
    Door cafToOffice = new Door(leftX, sideY);
    Door officeToCaf = new Door(rightX, sideY);

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

    public void changeGameOver() {
        gameOver = true;
    }

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
                    cd.CDReset();
                }

                // RESTART GAME - UISELSS
                if (gameOver && e.getKeyCode() == KeyEvent.VK_R) {
                    gameOver = false;
                    mainMenu.changeMenu(true);
                    cd.CDReset();
                }

                // PAUSE GAME
                if (!pauseGame && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    cd.startPause();
                    pauseGame = true;
                } else if (pauseGame && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    cd.stopPause();
                    pauseGame = false;
                }

                // opening side menu
                if (!mainMenu.getIsMenuOpen() && !sideMenu.getIsOpen() && e.getKeyCode() == KeyEvent.VK_M) {
                    sideMenu.updateIsOpen(true);
                    cd.startPause();
                    pauseGame = true;
                } else if (!mainMenu.getIsMenuOpen() && sideMenu.getIsOpen() && e.getKeyCode() == KeyEvent.VK_M) {
                    sideMenu.updateIsOpen(false);
                    cd.stopPause();
                    pauseGame = false;
                }

                if (gameOver && e.getKeyCode() == KeyEvent.VK_R) {
                    gameOver = false;
                    System.out.println("u pressed r");
                }

                if (currentClassroom == 1) {
                    p1.keyReleased(p2, e, office.arson1, office.trashCans, office.cb, office.pp);
                    p2.keyReleased(p1, e, office.arson1, office.trashCans, office.cb, office.pp);
                } else if (currentClassroom == 2){
                    p1.keyReleased(p2, e, caf.arson2, caf.trashCans, caf.cb, office.pp);
                    p2.keyReleased(p1, e, caf.arson2, caf.trashCans, caf.cb, office.pp);
                } else if (currentClassroom == 3){
                    p1.keyReleased(p2, e, gym.arson3, gym.trashCans, gym.cb, office.pp);
                    p2.keyReleased(p1, e, gym.arson3, gym.trashCans, gym.cb, office.pp);
                } else if (currentClassroom == 4) {
                    p1.keyReleased(p2, e, bio.arson4, bio.trashCans, bio.cb, office.pp);
                    p2.keyReleased(p1, e, bio.arson4, bio.trashCans, bio.cb, office.pp);
                } else if (currentClassroom == 5) {
                    p1.keyReleased(p2, e, phys.arson5, phys.trashCans, phys.cb, office.pp);
                    p2.keyReleased(p1, e, phys.arson5, phys.trashCans, phys.cb, office.pp);
                } else if (currentClassroom == 6) {
                    p1.keyReleased(p2, e, chem.arson6, chem.trashCans, chem.cb, office.pp);
                    p2.keyReleased(p1, e, chem.arson6, chem.trashCans, chem.cb, office.pp);
                } else if (currentClassroom == 7){
                    p1.keyReleased(p2, e, mathematics.arson7, mathematics.trashCans, mathematics.cb, office.pp);
                    p2.keyReleased(p1, e, mathematics.arson7, mathematics.trashCans, mathematics.cb, office.pp);
                } else if (currentClassroom == 8){
                    p1.keyReleased(p2, e, compSci.arson8, compSci.trashCans, compSci.cb, office.pp);
                    p2.keyReleased(p1, e, compSci.arson8, compSci.trashCans, compSci.cb, office.pp);
                } else if (currentClassroom == 9){
                    p1.keyReleased(p2, e, eng.arson9, eng.trashCans, eng.cb, office.pp);
                    p2.keyReleased(p1, e, eng.arson9, eng.trashCans, eng.cb, office.pp);
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
        Random r = new Random();

        if (!pauseGame) {
            clickClack++;

            // TEACHER SPAWNS IN A CLASSROOM
            if (clickClack >= 50 && tCurrentClassroom != currentClassroom){
                int[] tmp = {1, 2, 3, 4, 6, 7, 8, 9};
                tCurrentClassroom = tmp[r.nextInt(8)];
                System.out.println(tCurrentClassroom);
                clickClack = 0;
            }

            if (currentClassroom == tCurrentClassroom){
                p1.checkTeacher(teacher);
                p2.checkTeacher(teacher);
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

            t.move(c, p1, p2, p1.getX(), p1.getY(), p2.getX(), p2.getY());

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

        if (!gameOver){
            //g2d.setColor(Color.BLACK);
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
            }

            minimap.paint(g2d);
            minimap.paintYou(g2d, currentClassroom);
            minimap.paintTeacher(g2d, tCurrentClassroom);
            if (mainMenu.getIsMenuOpen()) {
                mainMenu.paintMainMenu(g2d);
            } else {
                if (!p1.getIsCaught() || currentClassroom == 1)
                    p1.paint(g2d);
                if (!p2.getIsCaught() || currentClassroom == 1)
                    p2.paint(g2d);

                if (currentClassroom == tCurrentClassroom){
                    t.paint(g2d);
                }
                cd.paint(g2d);
                sideMenu.paint(g2d);
            }
        } else {
               gameOverScreen.paintGameOver(g2d);
        }
    }

    public void changeRooms(Door door1, Door door2, int nextClassroom, Player p1, Player p2, Transition transition1, Graphics2D g2d){
        if (door1.containsPlayer(p1, p2)) {
            transition1.paintBlack(g2d);
            changeCurrentClassroom(nextClassroom);

            // i love hardcoding (brain doesmt function past 11pm, will fix tmr maybe)
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

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Physics");

        Main c = new Main();
        frame.add(c);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            //while (!c.gameOver) {
                c.move(c); //Updates the coordinates
                c.repaint(); //Calls the paint method
                Thread.sleep(10); //Pauses for a moment


                if (c.gameOver) {

                }
                    /*
                    Thread.sleep(3000); //Pauses for a moment

                    c = new Main();
                    c.gameOver = false;
                    frame.add(c);
                    frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
                    frame.setVisible(true);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    Thread.sleep(10); //Pauses for a moment
                } */
            }

            //Thread.sleep(10); //Pauses for a moment
        //}
    }
}