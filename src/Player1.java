import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Player1 extends Player {
    private int ticks = 0;

    public Player1() {
        super(1);
    }

    public void keyPressed(KeyEvent e, DoHack hack, int currentRoom, DoDissection dissect) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = true;
            changeFaceDirection(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = true;
            changeFaceDirection(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (currentRoom == 3){
                changeIsSpillingWater(true);
            }

            if (hack.isPlayerClose(this, this) && currentRoom == 8){
                if (!hack.getFinished()){
                    ticks++;
                }
                if (ticks >= 20) {
                    hack.taskFinished();
                    changeIsHolding(false);
                }
            }
            if (currentRoom == 4){
                if (dissect.getIsClose(0, this)){
                    dissect.changeIsClose(0);
                }
                if (dissect.getIsClose(1, this)){
                    dissect.changeIsClose(1);
                }
                if (dissect.getIsClose(2, this)){
                    dissect.changeIsClose(2);
                }
                if (dissect.getIsClose(3, this)){
                    dissect.changeIsClose(3);
                }
                if (dissect.getIsClose(4, this)){
                    dissect.changeIsClose(4);
                }
                if (dissect.getIsClose(5, this)){
                    dissect.changeIsClose(5);
                }
            }
        }
    }

    public void keyReleased(Player p2, int currentRoom, KeyEvent e, DoArson arson1[], ATrashCan[] trashCans, AChalkBoard cb, APressurePlate pp, DoMess mess, DoHack hack, DoVendingMachine[] vend, DoBadReaction badReaction, DoStealTests stealTests, DoDissection dissect) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            ticks = 0;

            if (currentRoom == 3){
                changeIsSpillingWater(false);
            }

            if (currentRoom > 3 && currentRoom != 5 ){
                for (DoArson arson : arson1) {
                    if (arson.isPlayerClose(this)) {
                        arson.setOnFire();
                    }
                }
            }

            if (cb.isPlayerClose(this, this)) {
                cb.makeScribble();
            }

            if (p2.getIsCaught() && pp.isPlayerClose(this, this) && currentRoom == 1) {
                p2.changeIsCaught(false);
                p2.spawnPlayer(getX(), getY());
                p2.changePCaughtSound(true);
            }

            for (DoVendingMachine VM:vend) {
                if (VM.isPlayerClose(this, this) && currentRoom == 2) {
                    VM.finishVending();
                }
            }

            if (currentRoom == 6){
                if (badReaction.getCloseGreen()){
                    badReaction.changeGreen(true);
                }
                if (badReaction.getClosePink()){
                    badReaction.changePink(true);
                }
                if (badReaction.getCloseCyan()){
                    badReaction.changeCyan(true);
                }
                if (badReaction.getCloseYellow()){
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
