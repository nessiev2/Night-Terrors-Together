import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;
import java.util.Random;

/*
* ENCAPSULATE CODE
* make trash cans not generate on desk
    * biology - CLEAR
    * chem
    * comp sci
    * eng
    * math
    * office
    * physics
* DESKS to PAINT
    * bio
    * math
    * compsci
    * caf
    * chem
*/

public class NTT extends JPanel {
    //ATTRIBUTES
    Minimap minimap = new Minimap();
    Tutorial tut = new Tutorial();
    final static int SCREEN_WIDTH = 1920, SCREEN_HEIGHT = 1080;
    boolean gameOver = false, pauseGame = false, tutOpen = true;
    int currentClassroom = 5, tCurrentClassroom = 1, clickClack = 0;
    int tobX = 1300, botY = 840, topY = 70, rightX = SCREEN_WIDTH-150, leftX = 0, sideY = SCREEN_HEIGHT/2-100/*440*/;
    boolean flag = false;
    CountDown cd = new CountDown();
    Transition transition1 = new Transition();

    Sound sound = new Sound();

    //DOORS
    ADoor bioToOffice = new ADoor(tobX, topY, "Office");
    ADoor officeToBio = new ADoor(tobX, botY, "Biology");
    ADoor physToChem = new ADoor(rightX, sideY, "Chemistry");
    ADoor chemToPhys = new ADoor(leftX, sideY, "Physics");
    ADoor physToBio = new ADoor(leftX, sideY, "Biology");
    ADoor bioToPhys = new ADoor(rightX, sideY, "Physics");
    ADoor physToCaf = new ADoor(tobX, topY, "Caf");
    ADoor cafToPhys = new ADoor(tobX, botY, "Physics");
    ADoor gymToCaf = new ADoor(leftX, sideY, "Caf");
    ADoor cafToGym = new ADoor(rightX, sideY, "Gym");
    ADoor gymToChem = new ADoor(tobX, botY, "Chem");
    ADoor chemToGym = new ADoor(tobX, topY, "Gym");
    ADoor mathToBio = new ADoor(tobX, topY, "Biology");
    ADoor bioToMath = new ADoor(tobX, botY, "Math");
    ADoor compSciToMath = new ADoor(leftX, sideY, "Math");
    ADoor mathToCompSci = new ADoor(rightX, sideY, "Comp. Sci.");
    ADoor physToCompSci = new ADoor(tobX, botY, "Comp. Sci.");
    ADoor compSciToPhys = new ADoor(tobX, topY, "Physics");
    ADoor compSciToEng = new ADoor(rightX, sideY, "English");
    ADoor engToCompSci = new ADoor(leftX, sideY, "Comp. Sci.");
    ADoor engToChem = new ADoor(tobX, topY, "Chemistry");
    ADoor chemToEng = new ADoor(tobX, botY, "English");
    ADoor cafToOffice = new ADoor(leftX, sideY, "Office");
    ADoor officeToCaf = new ADoor(rightX, sideY, "Caf");

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
    SideMenu sideMenu = new SideMenu(this, sound);
    boolean win = false;
    boolean first = true;

    public void changeCurrentClassroom(int i) {
        currentClassroom = i;
    }
    public NTT() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        p1.regSound(sound);
        p2.regSound(sound);

        tut.changePlayTut(true);
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
                } else if (win && e.getKeyCode() == KeyEvent.VK_R) {
                    flag = true;
                    gameOver = false;
                    mainMenu.changeMenu(true);
                }

                // TUTORIAL
                if (!mainMenu.getIsMenuOpen() && !gameOver){
                    if (!tutOpen) {
                        if (e.getKeyCode() == KeyEvent.VK_T){
                            tut.changePlayTut(true);
                            tutOpen = true;

                            //System.out.println("i paused");
                            //cd.CDReset();
                            if (!pauseGame) {
                                cd.startPause();
                                pauseGame = true;
                            }
                        }
                    } else { // tut is open
                        if (first) {
                            //System.out.println("i paused BEG");
                            first = false;
                            cd.startPause();
                            pauseGame = true;
                        }
                        if (e.getKeyCode() == KeyEvent.VK_T){
                            tutOpen = false;
                            tut.changePlayTut(false);
                            tut.initializeTut();

                            //System.out.println("i UNpaused");
                            if (pauseGame) {
                                cd.stopPause();
                                pauseGame = false;
                            }
                            //cd.CDReset();
                        }
                        if (e.getKeyCode() == KeyEvent.VK_SPACE){
                            if (tut.getSlide() < 7){
                                tut.nextSlide();
                            } else {
                                tutOpen = false;
                                tut.changePlayTut(false);
                                tut.initializeTut();

                                //System.out.println("i UNpaused");
                                if (pauseGame) {
                                    cd.stopPause();
                                    pauseGame = false;
                                }
                                //cd.CDReset();
                            }
                        }
                    }
                }

                // PAUSE GAME

                if ((!pauseGame && e.getKeyCode() == KeyEvent.VK_ESCAPE && !sideMenu.getIsOpen())) {
                    cd.startPause();
                    pauseGame = true;
                } else if ((pauseGame && e.getKeyCode() == KeyEvent.VK_ESCAPE && !sideMenu.getIsOpen())) {
                    cd.stopPause();
                    pauseGame = false;
                }


                // opening side menu
                if (!tutOpen && !mainMenu.getIsMenuOpen() && !sideMenu.getIsOpen() && e.getKeyCode() == KeyEvent.VK_M) {
                    //cd.startPause();
                    //pauseGame = true;
                    sideMenu.updateIsOpen(true);
                } else if (!mainMenu.getIsMenuOpen() && sideMenu.getIsOpen() && e.getKeyCode() == KeyEvent.VK_M) {
                    //cd.startPause();
                    //pauseGame = true;
                    sideMenu.updateIsOpen(false);
                }

                if (currentClassroom == 1) {
                    try {
                        p1.keyReleased(p2, currentClassroom, e, office.getArson(), office.getTrashCans(), office.getCb(), office.getAPressurePlate(), doMess3, compSci.getDoHack8(), caf.getVendMachines(), chem.getBadReaction6(), mathematics.getDoStealTests(), bio.getDissection4());
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        p2.keyReleased(p1, currentClassroom, e, office.getArson(), office.getTrashCans(), office.getCb(), office.getAPressurePlate(), doMess3, compSci.getDoHack8(), caf.getVendMachines(), chem.getBadReaction6(), mathematics.getDoStealTests(), bio.getDissection4());
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (currentClassroom == 2){
                    try {
                        p1.keyReleased(p2, currentClassroom, e, caf.getArson(), caf.getTrashCans(), caf.getCb(), office.getAPressurePlate(), doMess3, compSci.getDoHack8(), caf.getVendMachines(), chem.getBadReaction6(), mathematics.getDoStealTests(), bio.getDissection4());
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        p2.keyReleased(p1, currentClassroom, e, caf.getArson(), caf.getTrashCans(), caf.getCb(), office.getAPressurePlate(), doMess3, compSci.getDoHack8(), caf.getVendMachines(), chem.getBadReaction6(), mathematics.getDoStealTests(), bio.getDissection4());
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (currentClassroom == 3){
                    try {
                        p1.keyReleased(p2, currentClassroom, e, gym.getArson(), gym.getTrashCans(), gym.getCb(), office.getAPressurePlate(), doMess3, compSci.getDoHack8(), caf.getVendMachines(), chem.getBadReaction6(), mathematics.getDoStealTests(), bio.getDissection4());
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        p2.keyReleased(p1, currentClassroom, e, gym.getArson(), gym.getTrashCans(), gym.getCb(), office.getAPressurePlate(), doMess3, compSci.getDoHack8(), caf.getVendMachines(), chem.getBadReaction6(), mathematics.getDoStealTests(), bio.getDissection4());
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (currentClassroom == 4) {
                    try {
                        p1.keyReleased(p2, currentClassroom, e, bio.getArson(), bio.getTrashCans(), bio.getCb(), office.getAPressurePlate(), doMess3, compSci.getDoHack8(), caf.getVendMachines(), chem.getBadReaction6(), mathematics.getDoStealTests(), bio.getDissection4());
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        p2.keyReleased(p1, currentClassroom, e, bio.getArson(), bio.getTrashCans(), bio.getCb(), office.getAPressurePlate(), doMess3, compSci.getDoHack8(), caf.getVendMachines(), chem.getBadReaction6(), mathematics.getDoStealTests(), bio.getDissection4());
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (currentClassroom == 5) {
                    try {
                        p1.keyReleased(p2, currentClassroom, e, phys.getArson(), phys.getTrashCans(), phys.getCb(), office.getAPressurePlate(), doMess3, compSci.getDoHack8(), caf.getVendMachines(), chem.getBadReaction6(), mathematics.getDoStealTests(), bio.getDissection4());
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        p2.keyReleased(p1, currentClassroom, e, phys.getArson(), phys.getTrashCans(), phys.getCb(), office.getAPressurePlate(), doMess3, compSci.getDoHack8(), caf.getVendMachines(), chem.getBadReaction6(), mathematics.getDoStealTests(), bio.getDissection4());
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (currentClassroom == 6) {
                    try {
                        p1.keyReleased(p2, currentClassroom, e, chem.getArson(), chem.getTrashCans(), chem.getCb(), office.getAPressurePlate(), doMess3, compSci.getDoHack8(), caf.getVendMachines(), chem.getBadReaction6(), mathematics.getDoStealTests(), bio.getDissection4());
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        p2.keyReleased(p1, currentClassroom, e, chem.getArson(), chem.getTrashCans(), chem.getCb(), office.getAPressurePlate(), doMess3, compSci.getDoHack8(), caf.getVendMachines(), chem.getBadReaction6(), mathematics.getDoStealTests(), bio.getDissection4());
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (currentClassroom == 7){
                    try {
                        p1.keyReleased(p2, currentClassroom, e, mathematics.getArson(), mathematics.getTrashCans(), mathematics.getCb(), office.getAPressurePlate(), doMess3, compSci.getDoHack8(), caf.getVendMachines(), chem.getBadReaction6(), mathematics.getDoStealTests(), bio.getDissection4());
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        p2.keyReleased(p1, currentClassroom, e, mathematics.getArson(), mathematics.getTrashCans(), mathematics.getCb(), office.getAPressurePlate(), doMess3, compSci.getDoHack8(), caf.getVendMachines(), chem.getBadReaction6(), mathematics.getDoStealTests(), bio.getDissection4());
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (currentClassroom == 8){
                    try {
                        p1.keyReleased(p2, currentClassroom, e, compSci.getArson(), compSci.getTrashCans(), compSci.getCb(), office.getAPressurePlate(), doMess3, compSci.getDoHack8(), caf.getVendMachines(), chem.getBadReaction6(), mathematics.getDoStealTests(), bio.getDissection4());
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        p2.keyReleased(p1, currentClassroom, e, compSci.getArson(), compSci.getTrashCans(), compSci.getCb(), office.getAPressurePlate(), doMess3, compSci.getDoHack8(), caf.getVendMachines(), chem.getBadReaction6(), mathematics.getDoStealTests(), bio.getDissection4());
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (currentClassroom == 9){
                    try {
                        p1.keyReleased(p2, currentClassroom, e, eng.getArson(), eng.getTrashCans(), eng.getCb(), office.getAPressurePlate(), doMess3, compSci.getDoHack8(), caf.getVendMachines(), chem.getBadReaction6(), mathematics.getDoStealTests(), bio.getDissection4());
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        p2.keyReleased(p1, currentClassroom, e, eng.getArson(), eng.getTrashCans(), eng.getCb(), office.getAPressurePlate(), doMess3, compSci.getDoHack8(), caf.getVendMachines(), chem.getBadReaction6(), mathematics.getDoStealTests(), bio.getDissection4());
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                p1.keyPressed(e, compSci.getDoHack8(), currentClassroom, bio.getDissection4());
                p2.keyPressed(e, compSci.getDoHack8(), currentClassroom, bio.getDissection4());
            }
        });
        setFocusable(true);
    }

    private void move(NTT c) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        sound.playBackgroundMusic();
        sound.soundMaintenance();

        if (eng.getDoSprint().getFlag1()){
            eng.getDoSprint().sprintTicks(p1);
        }
        if (eng.getDoSprint().getFlag2()){
            eng.getDoSprint().sprintTicks(p2);
        }

        Random r = new Random();

        if (!pauseGame && !tutOpen) {
            clickClack++;

            // TEACHER SPAWNS IN A CLASSROOM
            if (clickClack >= 50 && tCurrentClassroom != currentClassroom){
                int[] tmp = {1, 2, 3, 4, 6, 7, 8, 9};

                tCurrentClassroom = tmp[r.nextInt(8)];

                //System.out.println("TRoom: " + tCurrentClassroom);

                // hehe
                if (currentClassroom == tCurrentClassroom){
                    if (tCurrentClassroom == 1){
                        if(!t.spawnTeacherAtDoor(p1, p2, "", "1", "2", "")){
                            t.spawnTeacherRandom(p1, p2);
                            sound.playTeacherJumpscareSoundEffect();
                        }
                    } else if (tCurrentClassroom == 2){
                        if (!t.spawnTeacherAtDoor(p1, p2, "", "1", "2", "3")){
                            t.spawnTeacherRandom(p1, p2);
                            sound.playTeacherJumpscareSoundEffect();
                        }
                    } else if (tCurrentClassroom == 3){
                        if (!t.spawnTeacherAtDoor(p1, p2, "", "", "2", "3")){
                            t.spawnTeacherRandom(p1, p2);
                            sound.playTeacherJumpscareSoundEffect();
                        }
                    } else if (tCurrentClassroom == 4){
                        if (!t.spawnTeacherAtDoor(p1, p2, "0", "1", "2","")){
                            t.spawnTeacherRandom(p1, p2);
                            sound.playTeacherJumpscareSoundEffect();
                        }
                    } else if (tCurrentClassroom == 6){
                        if (!t.spawnTeacherAtDoor(p1, p2, "0", "", "2", "3")){
                            t.spawnTeacherRandom(p1, p2);
                            sound.playTeacherJumpscareSoundEffect();
                        }
                    } else if (tCurrentClassroom == 7){
                        if (!t.spawnTeacherAtDoor(p1, p2, "0", "1", "", "")){
                            t.spawnTeacherRandom(p1, p2);
                            sound.playTeacherJumpscareSoundEffect();
                        }
                    } else if (tCurrentClassroom == 8){
                        if (!t.spawnTeacherAtDoor(p1, p2, "0", "1", "", "3")){
                            t.spawnTeacherRandom(p1, p2);
                            sound.playTeacherJumpscareSoundEffect();
                        }
                    } else if (tCurrentClassroom == 9){
                        if (!t.spawnTeacherAtDoor(p1, p2, "0", "", "", "3")){
                            t.spawnTeacherRandom(p1, p2);
                            sound.playTeacherJumpscareSoundEffect();
                        }
                    }
                }

                clickClack = 0;
            }

            if (currentClassroom == tCurrentClassroom) {
                //p1.checkPlayerCaught(teacher);
                //p2.checkPlayerCaught(teacher);
                if (p1.checkPlayerCaught(teacher) && p1.getPCaughtSound()) {
                    sound.playGotCaughtSoundEffect();
                    p1.changePCaughtSound(false);
                }
                if (p2.checkPlayerCaught(teacher) && p2.getPCaughtSound()) {
                    sound.playGotCaughtSoundEffect();
                    p2.changePCaughtSound(false);
                }
            }

            if (currentClassroom == 1 && (p1.getIsCaught() || p2.getIsCaught())){
                tCurrentClassroom = 1;
            } else {
                if (p1.checkTeacher(teacher) || p2.checkTeacher(teacher)) {
                    //t.spawnTeacher(p1, p2);
                }
            }

            switch (currentClassroom) {
                case 1:
                    p1.move(office.getDesks());
                    p2.move(office.getDesks());
                    break;
                case 2:
                    p1.move(caf.getDesks());
                    p2.move(caf.getDesks());
                    break;
                case 3:
                    p1.move(gym.getDesks());
                    p2.move(gym.getDesks());
                    break;
                case 4:
                    p1.move(bio.getDesks());
                    p2.move(bio.getDesks());
                    break;
                case 5:
                    p1.move(phys.getDesks());
                    p2.move(phys.getDesks());
                    break;
                case 6:
                    p1.move(chem.getDesks());
                    p2.move(chem.getDesks());
                    break;
                case 7:
                    p1.move(mathematics.getDesks());
                    p2.move(mathematics.getDesks());
                    break;
                case 8:
                    p1.move(compSci.getDesks());
                    p2.move(compSci.getDesks());
                    break;
                case 9:
                    p1.move(eng.getDesks());
                    p2.move(eng.getDesks());
                    break;
            }

            if (tCurrentClassroom == currentClassroom) {
                t.move(c, p1, p2, p1.getX(), p1.getY(), p2.getX(), p2.getY());
            }

            cd.move();

            if (sideMenu.checkIfWinCondition()) {
                gameOver = true;
                win = true;
            }
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
        } else if (win) {
            System.out.println("game over u win");
            gameOverScreen.paintGameOverYouWin(g2d);
        } else if (gameOver) {
            gameOverScreen.paintGameOver(g2d);
        } else {
            if (t.getBothCaught()){
                gameOver = true;
            } else {
                switch(currentClassroom) {
                    case 1:
                        try {
                            office.paint(g, p1, p2, transition1, sideMenu);
                        } catch (UnsupportedAudioFileException e) {
                            throw new RuntimeException(e);
                        } catch (LineUnavailableException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        officeToBio.paint(g2d);
                        officeToCaf.paint(g2d);

                        changeRooms(officeToBio, bioToOffice, 4, p1, p2, transition1, g2d);
                        changeRooms(officeToCaf, cafToOffice, 2, p1, p2, transition1, g2d);
                        break;

                    case 2:
                        try {
                            caf.paint(g, p1, p2, transition1, sideMenu);
                        } catch (UnsupportedAudioFileException e) {
                            throw new RuntimeException(e);
                        } catch (LineUnavailableException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        cafToPhys.paint(g2d);
                        cafToGym.paint(g2d);
                        cafToOffice.paint(g2d);
                        changeRooms(cafToPhys, physToCaf, 5, p1, p2, transition1, g2d);
                        changeRooms(cafToGym, gymToCaf, 3, p1, p2, transition1, g2d);
                        changeRooms(cafToOffice, officeToCaf, 1, p1, p2, transition1, g2d);
                        break;

                    case 3:
                        chem.getBadReaction6().changeLeave(true);
                        try {
                            gym.paint(g, p1, p2, transition1, sideMenu);
                        } catch (UnsupportedAudioFileException e) {
                            throw new RuntimeException(e);
                        } catch (LineUnavailableException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        gymToCaf.paint(g2d);
                        gymToChem.paint(g2d);
                        changeRooms(gymToCaf, cafToGym, 2, p1, p2, transition1, g2d);
                        changeRooms(gymToChem, chemToGym, 6, p1, p2, transition1, g2d);
                        break;

                    case 4:
                        try {
                            bio.paint(g, p1, p2, transition1, sideMenu);
                        } catch (UnsupportedAudioFileException e) {
                            throw new RuntimeException(e);
                        } catch (LineUnavailableException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        bioToPhys.paint(g2d);
                        bioToOffice.paint(g2d);
                        bioToMath.paint(g2d);
                        changeRooms(bioToPhys, physToBio, 5, p1, p2, transition1, g2d);
                        changeRooms(bioToOffice, bioToOffice, 1, p1, p2, transition1, g2d);
                        changeRooms(bioToMath, mathToBio, 7, p1, p2, transition1, g2d);
                        break;

                    case 5:
                        chem.getBadReaction6().changeLeave(true);
                        try {
                            phys.paint(g, p1, p2, transition1, sideMenu);
                        } catch (UnsupportedAudioFileException e) {
                            throw new RuntimeException(e);
                        } catch (LineUnavailableException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
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
                        if (!chem.getBadReaction6().finished){
                            chem.getBadReaction6().changeLeave(false);
                        }
                        //System.out.println(chem.badReaction6.getLeave());
                        try {
                            chem.paint(g, p1, p2, transition1, sideMenu, currentClassroom);
                        } catch (UnsupportedAudioFileException e) {
                            throw new RuntimeException(e);
                        } catch (LineUnavailableException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        chemToPhys.paint(g2d);
                        chemToGym.paint(g2d);
                        chemToEng.paint(g2d);
                        changeRooms(chemToPhys, physToChem, 5, p1, p2, transition1, g2d);
                        changeRooms(chemToGym, gymToChem, 3, p1, p2, transition1, g2d);
                        changeRooms(chemToEng, engToChem, 9, p1, p2, transition1, g2d);
                        break;

                    case 7:
                        try {
                            mathematics.paint(g, p1, p2, transition1, sideMenu);
                        } catch (UnsupportedAudioFileException e) {
                            throw new RuntimeException(e);
                        } catch (LineUnavailableException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        mathToCompSci.paint(g2d);
                        mathToBio.paint(g2d);
                        changeRooms(mathToCompSci, compSciToMath, 8, p1, p2, transition1, g2d);
                        changeRooms(mathToBio, bioToMath, 4, p1, p2, transition1, g2d);
                        break;

                    case 8:
                        try {
                            compSci.paint(g, p1, p2, transition1, sideMenu);
                        } catch (UnsupportedAudioFileException e) {
                            throw new RuntimeException(e);
                        } catch (LineUnavailableException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        compSciToEng.paint(g2d);
                        compSciToMath.paint(g2d);
                        compSciToPhys.paint(g2d);
                        changeRooms(compSciToEng, engToCompSci, 9, p1, p2, transition1, g2d);
                        changeRooms(compSciToMath, mathToCompSci, 7, p1, p2, transition1, g2d);
                        changeRooms(compSciToPhys, physToCompSci, 5, p1, p2, transition1, g2d);
                        break;

                    case 9:
                        chem.getBadReaction6().changeLeave(true);
                        try {
                            eng.paint(g, p1, p2, transition1, sideMenu);
                        } catch (UnsupportedAudioFileException e) {
                            throw new RuntimeException(e);
                        } catch (LineUnavailableException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        engToChem.paint(g2d);
                        engToCompSci.paint(g2d);
                        changeRooms(engToChem, chemToEng, 6, p1, p2, transition1, g2d);
                        changeRooms(engToCompSci, compSciToEng, 8, p1, p2, transition1, g2d);
                        break;
                    default:
                        g2d.setFont(new Font("TimesRoman", Font.BOLD, 100));
                        g2d.drawString("idk how u made it here!! please stop!!!", 200, 100);
                }
                boolean p1caught = p1.getIsCaught(), p2caught = this.p2.getIsCaught();


                if (p1caught) {
                    p1.spawnPlayer(170, 150);
                }
                if (p2caught) {
                    p2.spawnPlayer(170, 150);
                }

                if (p1caught && currentClassroom == 1) {
                    p1.paint(g2d, doMess3);
                    office.paintBars(g2d);
                } else if (p2caught && currentClassroom == 1) {
                    p2.paint(g2d, doMess3);
                    office.paintBars(g2d);
                }

                if (!p1caught) { //|| currentClassroom == 1){
                    p1.paint(g2d, doMess3);
                }
                if (!p2caught) { //|| currentClassroom == 1){
                    p2.paint(g2d, doMess3);
                }

                if (currentClassroom == tCurrentClassroom){
                    t.paint(g2d);
                }
                cd.paint(g2d);
                sideMenu.paint(g2d, doMess3);

                minimap.paint(g2d);
                minimap.paintYou(g2d, currentClassroom);
                minimap.paintTeacher(g2d, tCurrentClassroom);

                if (tutOpen){
                    tut.paint(g2d);
                }
            }
        }
    }

    public void changeRooms(ADoor door1, ADoor door2, int nextClassroom, Player p1, Player p2, Transition transition1, Graphics2D g2d){
        if (door1.containsPlayer(p1, p2)) {
            transition1.paintBlack(g2d);
            changeCurrentClassroom(nextClassroom);
            p1.updateCRoom(nextClassroom);
            p2.updateCRoom(nextClassroom);

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

    public void reset(NTT c){
        c.eng.getDoSprint().resetNormalSpeed(c.p1, c.p2);

        c.flag = false;
        c.gameOver = false;
        c.win = false;

        c.t.initializeTeacher();
        c.p1.initializePlayer();
        c.p2.initializePlayer();
        c.currentClassroom = 5;

        // 4 TASKS
        boolean[] bArray = sideMenu.which4Tasks();
        sideMenu.initializeSideMenu();

        System.out.println("BARRAY");
        for (int i = 0; i < 9; i++)  {
            System.out.print(i + ": " + bArray[i] + " ");
        }
        System.out.println("");

        // possible room that chalkboard is in
        boolean[] chalkRoom = {false, false, false, false, false, false, false, false, false};
        // possible room that trashcan is in
        boolean[] trashRoom = {false, false, false, false, false, false, false, false, false};

        // CHOOSING 1 TRASH CAN ROOM
        if (bArray[0]) {
            System.out.println("choosing trash can");
            Random rand = new Random();
            int[] tmpArr = {3, 5, 6, 7, 8};
            int randVal = tmpArr[rand.nextInt(tmpArr.length)];
            trashRoom[randVal] = true;
        }

        // CHOOSING 1 CHALKBOARD ROOM
        if (bArray[5]) {
            System.out.println("choosing chalkboard");
            Random rand = new Random();
            int[] tmpArr = {1, 2, 3, 5, 6, 7, 8};
            int randVal = tmpArr[rand.nextInt(tmpArr.length)];
            chalkRoom[randVal] = true;
        }


        /*
        RANDOM - "SET FIRE TO TRASH CANS",      0 DONE
        2 - "CHEMICALS",                        1
        0 - "DISSECTION",                       2 DONE
        3 - "HACK COMPUTER SCIENCE",            3
        5 - "SPILL WATER",                      4
        RANDOM - "CHALKBOARD SCRIBBLE",         5 DONE
        4 - "SPRINT",                           6
        6 - "STEAL MATH TESTS" ,                7
        1 - "SMASH CAF VENDING MACHINES"};      8
*/

        office.initializeOffice(false, false);
        caf.initializeCaf(chalkRoom[1], false,               bArray[8]);
        gym.initializeGym(chalkRoom[2], false,               bArray[4]);
        //System.out.println("gym - " + bArray[4]);
        bio.initializeBio(chalkRoom[3], trashRoom[3],           bArray[2]);
        phys.initializePhysics(false, false);
        chem.initializeChem(chalkRoom[5], trashRoom[5],         bArray[1]);
        mathematics.initializeMath(chalkRoom[6], trashRoom[6],  bArray[7]);
        compSci.initializeCompSci(chalkRoom[7], trashRoom[7],   bArray[3]);
        eng.initializeEng(chalkRoom[8], trashRoom[8],           bArray[6]);

        first = true;
        CountDown.CDReset();
    }

    public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        JFrame frame = new JFrame("NIGHT TERRORS TOGETHER!");

        NTT c = new NTT();
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