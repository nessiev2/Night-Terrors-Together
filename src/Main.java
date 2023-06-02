import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Main extends JPanel {
    final static int SCREEN_WIDTH = 1920, SCREEN_HEIGHT = 1080;
    boolean startGame = false;
    boolean gameOver = false;
    boolean pauseGame = false;
    int currentClassroom = 5;
    CountDown cd = new CountDown();
    Transition transition1 = new Transition();


    Door bioToOffice = new Door(800, 200);
    Door officeToBio = new Door(800, 800);
    Door physToChem = new Door(SCREEN_WIDTH-150, SCREEN_HEIGHT/2-100);
    Door chemToPhys = new Door(0, SCREEN_HEIGHT/2-100);
    Door physToBio = new Door(0, SCREEN_HEIGHT/2-100);
    Door bioToPhys = new Door(SCREEN_WIDTH-150, SCREEN_HEIGHT/2-100);
    Door physToCaf = new Door(1300, 70);
    Door cafToPhys = new Door(1300, 810);
    Door gymToCaf = new Door(0, SCREEN_HEIGHT/2-100);
    Door cafToGym = new Door(SCREEN_WIDTH-150, SCREEN_HEIGHT/2-100);
    Door gymToChem = new Door(1300, 810);
    Door chemToGym = new Door(1300, 70);
    Door mathToBio = new Door(1300, 70);
    Door bioToMath = new Door(1300, 810);
    Door compSciToMath = new Door(0, SCREEN_HEIGHT/2-100);
    Door mathToCompSci = new Door(SCREEN_WIDTH-150, SCREEN_HEIGHT/2-100);
    Door physToCompSci = new Door(1300, 810);
    Door compSciToPhys = new Door(1300, 70);
    Door compSciToEng = new Door(SCREEN_WIDTH-150, SCREEN_HEIGHT/2-100);
    Door engToCompSci = new Door(0, SCREEN_HEIGHT/2-100);
    Door engToChem = new Door(1300, 70);
    Door chemToEng = new Door(1300, 810);
    Door cafToOffice = new Door(0, SCREEN_HEIGHT/2-100);
    Door officeToCaf = new Door(SCREEN_WIDTH-150, SCREEN_HEIGHT/2-100);
    Player p1 = new Player1();
    Player p2 = new Player2();
    Teacher t = new Teacher();
    Teacher[] teacher = {t};

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
                if (mainMenu.getIsMenuOpen() && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    mainMenu.changeMenu(false);
                }

                if (pauseGame == false && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    pauseGame = true;
                } else if (pauseGame == true && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    pauseGame = false;
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
        if (!pauseGame) {
            p1.checkTeacher(teacher);
            p2.checkTeacher(teacher);
        p1.checkTeacher(teacher);
        p2.checkTeacher(teacher);
        switch(currentClassroom) {
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

            phys.arson5.doTask(phys.trashCans, p1, p2);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        switch(currentClassroom) {
            case 1:
                office.paint(g, p1, p2, transition1);
                officeToBio.paint(g2d);
                if (p1.getIsCaught()) {
                    p1.spawnPlayer(170, 150);
                }
                if (p2.getIsCaught()) {
                    p2.spawnPlayer(170, 150);
                }

                if (officeToBio.containsPlayer(p1, p2)) {
                    System.out.println("bio to office");
                    transition1.paintBlack(g2d);
                    p1.spawnPlayer(bioToOffice.getX(), bioToPhys.getY() + bioToPhys.getHeight() + 50);
                    p2.spawnPlayer(bioToOffice.getX(), bioToPhys.getY() + bioToPhys.getHeight() + 50);
                    changeCurrentClassroom(4);
                }
                break;
            case 2:
                caf.paint(g, p1, p2, transition1);
                cafToPhys.paint(g2d);
                cafToGym.paint(g2d);
                cafToOffice.paint(g2d);
                if (cafToPhys.containsPlayer(p1, p2)) {
                    System.out.println("caf to physics");
                    transition1.paintBlack(g2d);
                    p1.spawnPlayer(physToCaf.getX(), physToCaf.getY() + physToCaf.getHeight() + 50);
                    p2.spawnPlayer(physToCaf.getX(), physToCaf.getY() + physToCaf.getHeight() + 50);
                    changeCurrentClassroom(5);
                }
                if (cafToGym.containsPlayer(p1, p2)) {
                    System.out.println("caf to gym");
                    transition1.paintBlack(g2d);
                    p1.spawnPlayer(gymToCaf.getX(), gymToCaf.getY() + gymToCaf.getHeight() + 50);
                    p2.spawnPlayer(gymToCaf.getX(), gymToCaf.getY() + gymToCaf.getHeight() + 50);
                    changeCurrentClassroom(3);
                }
                if (cafToOffice.containsPlayer(p1, p2)) {
                    System.out.println("caf to office");
                    transition1.paintBlack(g2d);
                    p1.spawnPlayer(officeToCaf.getX(), officeToCaf.getY() + officeToCaf.getHeight() + 50);
                    p2.spawnPlayer(officeToCaf.getX(), officeToCaf.getY() + officeToCaf.getHeight() + 50);
                    changeCurrentClassroom(1);
                }
                break;
            case 3:
                gym.paint(g, p1, p2, transition1);
                gymToCaf.paint(g2d);
                gymToChem.paint(g2d);
                if (gymToCaf.containsPlayer(p1, p2)) {
                    System.out.println("gym to caf");
                    transition1.paintBlack(g2d);
                    p1.spawnPlayer(cafToGym.getX(), cafToGym.getY() + cafToGym.getHeight() + 50);
                    p2.spawnPlayer(cafToGym.getX(), cafToGym.getY() + cafToGym.getHeight() + 50);
                    changeCurrentClassroom(2);
                }
                if (gymToChem.containsPlayer(p1, p2)) {
                    System.out.println("caf to gym");
                    transition1.paintBlack(g2d);
                    p1.spawnPlayer(chemToGym.getX(), chemToGym.getY() + chemToGym.getHeight() + 50);
                    p2.spawnPlayer(chemToGym.getX(), chemToGym.getY() + chemToGym.getHeight() + 50);
                    changeCurrentClassroom(6);
                }
                break;
            case 4:
                bio.paint(g, p1, p2, transition1);
                bioToPhys.paint(g2d);
                bioToOffice.paint(g2d);
                bioToMath.paint(g2d);
                if (bioToPhys.containsPlayer(p1, p2)) {
                    System.out.println("bio to physics");
                    transition1.paintBlack(g2d);
                    p1.spawnPlayer(physToBio.getX(), bioToPhys.getY() + bioToPhys.getHeight() + 50);
                    p2.spawnPlayer(physToBio.getX(), bioToPhys.getY() + bioToPhys.getHeight() + 50);
                    changeCurrentClassroom(5);
                }
                if (bioToOffice.containsPlayer(p1, p2)) {
                    System.out.println("bio to office");
                    transition1.paintBlack(g2d);
                    p1.spawnPlayer(bioToOffice.getX(), bioToPhys.getY() + bioToPhys.getHeight() + 50);
                    p2.spawnPlayer(bioToOffice.getX(), bioToPhys.getY() + bioToPhys.getHeight() + 50);
                    changeCurrentClassroom(1);
                }
                if (bioToMath.containsPlayer(p1, p2)) {
                    System.out.println("bio to math");
                    transition1.paintBlack(g2d);
                    p1.spawnPlayer(mathToBio.getX(), mathToBio.getY() + mathToBio.getHeight() + 50);
                    p2.spawnPlayer(mathToBio.getX(), mathToBio.getY() + mathToBio.getHeight() + 50);
                    changeCurrentClassroom(7);
                }
                break;
            case 5:
                phys.paint(g, p1, p2, transition1);
                physToChem.paint(g2d);
                physToBio.paint(g2d);
                physToCaf.paint(g2d);
                physToCompSci.paint(g2d);
                if (physToChem.containsPlayer(p1, p2)) {
                    System.out.println("physics to chem");
                    transition1.paintBlack(g2d);
                    p1.spawnPlayer(chemToPhys.getX(), bioToPhys.getY() + bioToPhys.getHeight() + 50);
                    p2.spawnPlayer(chemToPhys.getX(), bioToPhys.getY() + bioToPhys.getHeight() + 50);
                    changeCurrentClassroom(6);
                }
                if (physToBio.containsPlayer(p1, p2)) {
                    System.out.println("physics to bio");
                    transition1.paintBlack(g2d);
                    p1.spawnPlayer(bioToPhys.getX(), bioToPhys.getY() + bioToPhys.getHeight() + 50);
                    p2.spawnPlayer(bioToPhys.getX(), bioToPhys.getY() + bioToPhys.getHeight() + 50);
                    changeCurrentClassroom(4);
                }
                if (physToCaf.containsPlayer(p1, p2)) {
                    System.out.println("physics to caf");
                    transition1.paintBlack(g2d);
                    p1.spawnPlayer(cafToPhys.getX(), cafToPhys.getY() - 150);
                    p2.spawnPlayer(cafToPhys.getX(), cafToPhys.getY() - 150);
                    changeCurrentClassroom(2);
                }
                if (physToCompSci.containsPlayer(p1, p2)) {
                    System.out.println("physics to compsci");
                    transition1.paintBlack(g2d);
                    p1.spawnPlayer(compSciToPhys.getX(), compSciToPhys.getY() - 150);
                    p2.spawnPlayer(compSciToPhys.getX(), compSciToPhys.getY() - 150);
                    changeCurrentClassroom(8);
                }

                mainMenu.paintMainMenu(g2d);
                break;
            case 6:
                chem.paint(g, p1, p2, transition1);
                chemToPhys.paint(g2d);
                chemToGym.paint(g2d);
                chemToEng.paint(g2d);
                if (chemToPhys.containsPlayer(p1, p2)) {
                    System.out.println("chem to physics");
                    transition1.paintBlack(g2d);
                    p1.spawnPlayer(physToChem.getX(), bioToPhys.getY() + bioToPhys.getHeight() + 50);
                    p2.spawnPlayer(physToChem.getX(), bioToPhys.getY() + bioToPhys.getHeight() + 50);
                    changeCurrentClassroom(5);
                }
                if (chemToGym.containsPlayer(p1, p2)) {
                    System.out.println("chem to gym");
                    transition1.paintBlack(g2d);
                    p1.spawnPlayer(gymToChem.getX(), gymToChem.getY() + gymToChem.getHeight() + 50);
                    p2.spawnPlayer(gymToChem.getX(), gymToChem.getY() + gymToChem.getHeight() + 50);
                    changeCurrentClassroom(3);
                }
                if (chemToEng.containsPlayer(p1, p2)) {
                    System.out.println("chem to eng");
                    transition1.paintBlack(g2d);
                    p1.spawnPlayer(engToChem.getX(), engToChem.getY() + engToChem.getHeight() + 50);
                    p2.spawnPlayer(engToChem.getX(), engToChem.getY() + engToChem.getHeight() + 50);
                    changeCurrentClassroom(9);
                }
                break;
            case 7:
                mathematics.paint(g, p1, p2, transition1);
                mathToCompSci.paint(g2d);
                mathToBio.paint(g2d);
                if (mathToCompSci.containsPlayer(p1, p2)) {
                    System.out.println("math to compsci");
                    transition1.paintBlack(g2d);
                    p1.spawnPlayer(compSciToMath.getX(), compSciToMath.getY() + compSciToMath.getHeight() + 50);
                    p2.spawnPlayer(compSciToMath.getX(), compSciToMath.getY() + compSciToMath.getHeight() + 50);
                    changeCurrentClassroom(8);
                }
                if (mathToBio.containsPlayer(p1, p2)) {
                    System.out.println("math to bio");
                    transition1.paintBlack(g2d);
                    p1.spawnPlayer(bioToMath.getX(), bioToMath.getY() + bioToMath.getHeight() + 50);
                    p2.spawnPlayer(bioToMath.getX(), bioToMath.getY() + bioToMath.getHeight() + 50);
                    changeCurrentClassroom(4);
                }
                break;
            case 8:
                compSci.paint(g, p1, p2, transition1);
                compSciToEng.paint(g2d);
                compSciToMath.paint(g2d);
                compSciToPhys.paint(g2d);
                if (compSciToEng.containsPlayer(p1, p2)) {
                    System.out.println("compsci to eng");
                    transition1.paintBlack(g2d);
                    p1.spawnPlayer(engToCompSci.getX(), engToCompSci.getY() + engToCompSci.getHeight() + 50);
                    p2.spawnPlayer(engToCompSci.getX(), engToCompSci.getY() + engToCompSci.getHeight() + 50);
                    changeCurrentClassroom(9);
                }
                if (compSciToMath.containsPlayer(p1, p2)) {
                    System.out.println("compsci to math");
                    transition1.paintBlack(g2d);
                    p1.spawnPlayer(mathToCompSci.getX(), mathToCompSci.getY() + mathToCompSci.getHeight() + 50);
                    p2.spawnPlayer(mathToCompSci.getX(), mathToCompSci.getY() + mathToCompSci.getHeight() + 50);
                    changeCurrentClassroom(7);
                }
                if (compSciToPhys.containsPlayer(p1, p2)) {
                    System.out.println("compsci to physics");
                    transition1.paintBlack(g2d);
                    p1.spawnPlayer(physToCompSci.getX(), physToCompSci.getY() + physToCompSci.getHeight() + 50);
                    p2.spawnPlayer(physToCompSci.getX(), physToCompSci.getY() + physToCompSci.getHeight() + 50);
                    changeCurrentClassroom(5);
                }
                break;
            case 9:
                eng.paint(g, p1, p2, transition1);
                engToChem.paint(g2d);
                engToCompSci.paint(g2d);
                if (engToChem.containsPlayer(p1, p2)) {
                    System.out.println("eng to chem");
                    transition1.paintBlack(g2d);
                    p1.spawnPlayer(chemToEng.getX(), chemToEng.getY() + chemToEng.getHeight() + 50);
                    p2.spawnPlayer(chemToEng.getX(), chemToEng.getY() + chemToEng.getHeight() + 50);
                    changeCurrentClassroom(6);
                }
                if (engToCompSci.containsPlayer(p1, p2)) {
                    System.out.println("eng to compsci");
                    transition1.paintBlack(g2d);
                    p1.spawnPlayer(compSciToEng.getX(), compSciToEng.getY() + compSciToEng.getHeight() + 50);
                    p2.spawnPlayer(compSciToEng.getX(), compSciToEng.getY() + compSciToEng.getHeight() + 50);
                    changeCurrentClassroom(8);
                }
                break;
        }

        if (mainMenu.getIsMenuOpen()) {
            mainMenu.paintMainMenu(g2d);
        } else {
            if (!p1.getIsCaught() || currentClassroom == 1)
                p1.paint(g2d);
            if (!p2.getIsCaught() || currentClassroom == 1)
                p2.paint(g2d);
            t.paint(g2d);

            cd.paint(g2d);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Physics");

        Main c = new Main();
        frame.add(c);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        while (!c.gameOver)
        {
            c.move(c); //Updates the coordinates
            c.repaint(); //Calls the paint method
            Thread.sleep(10); //Pauses for a moment
        }
    }
}