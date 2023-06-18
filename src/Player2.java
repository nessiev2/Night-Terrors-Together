import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Player2 extends Player {
    private int ticks = 0;
    public Player2() {
        super(2);
    }

    public void keyPressed(KeyEvent e, DoHack hack, int currentRoom, DoDissection dissection4) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
            changeFaceDirection(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
            changeFaceDirection(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            interact = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            if (currentRoom == 3){
                changeIsSpillingWater(true);
            }

            if (hack.isPlayerClose(this, this)){
                if (!hack.getFinished()){
                    ticks++;
                }
                if (ticks >= 20) {
                    hack.taskFinished();
                    System.out.println("HACK SUCCESS");
                    changeIsHolding(false);
                }
            }
            if (currentRoom == 4){
                if (dissection4.getIsClose(0, this)){
                    dissection4.changeIsClose(0);
                }
                if (dissection4.getIsClose(1, this)){
                    dissection4.changeIsClose(1);
                }
                if (dissection4.getIsClose(2, this)){
                    dissection4.changeIsClose(2);
                }
                if (dissection4.getIsClose(3, this)){
                    dissection4.changeIsClose(3);
                }
                if (dissection4.getIsClose(4, this)){
                    dissection4.changeIsClose(4);
                }
                if (dissection4.getIsClose(5, this)){
                    dissection4.changeIsClose(5);
                }
            }
        }
    }

    public void keyReleased(Player p1, int currentRoom, KeyEvent e, DoArson[] arson1, ATrashCan[] trashCans, AChalkBoard cb, APressurePlate pp, DoMess mess, DoHack hack, DoVendingMachine[] vend, DoBadReaction badReaction, DoStealTests stealTests, DoDissection dissection4) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            ticks = 0;

            if (currentRoom == 3){
                changeIsSpillingWater(false);
            }

            if (currentRoom > 3 && currentRoom != 5 ) {
                for (DoArson arson : arson1) {
                    if (arson.isPlayerClose(this)) {
                        arson.setOnFire();
                    }
                }
            }

            if (cb.isPlayerClose(this, this)) {
                cb.makeScribble();
            }
            if (p1.getIsCaught() && pp.isPlayerClose(this, this) && currentRoom == 1) {
                p1.changeIsCaught(false);
                p1.spawnPlayer(getX(), getY());
                p1.changePCaughtSound(true);
            }
            if (hack.isPlayerClose(this, this) && currentRoom == 8){
                hack.taskFinished();
            }

            for (DoVendingMachine VM:vend) {
                if (VM.isPlayerClose(this, this) && currentRoom == 2) {
                    VM.finishVending();
                }
            }

            if (currentRoom == 6){
                if (badReaction.getCloseGreen()) {
                    badReaction.changeGreen(true);
                }
                if (badReaction.getClosePink()) {
                    badReaction.changePink(true);
                }
                if (badReaction.getCloseCyan()) {
                    badReaction.changeCyan(true);
                }
                if (badReaction.getCloseYellow()) {
                    badReaction.changeYellow(true);
                }
            }

            if (currentRoom == 7){
                if (stealTests.getStack() >= 1){
                    stealTests.decrementStack(this, this);
                }
            }
        }
    }
}
