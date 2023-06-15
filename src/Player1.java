import java.awt.event.KeyEvent;

public class Player1 extends Player {

    Timer t = new Timer();
    private int ticks = 0;
    public Player1() {
        super(1);
    }

    public void keyPressed(KeyEvent e, DoHack hack, int currentRoom) {
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
            changeIsSpillingWater(true);
            if (hack.isPlayerClose(this, this) && currentRoom == 8){
                if (!hack.getFinished()){
                    ticks++;
                }
                if (ticks >= 20) {
                    hack.taskFinished();
                    System.out.println("HACK SUCCESS");
                    changeIsHolding(false);
                }
            }
        }
    }

    public void keyReleased(Player p2, int currentRoom, KeyEvent e, DoArson arson1, ATrashCan[] trashCans, AChalkBoard cb, APressurePlate pp, DoMess mess, DoHack hack, DoVendingMachine[] vend, DoBadReaction badReaction, DoBurnTests burnTests) {
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

            changeIsSpillingWater(false);

            for (ATrashCan tc : trashCans) {
                if (tc.isPlayerClose(this, this)) {
                    tc.setOnFire();
                }
            }
            if (cb.isPlayerClose(this, this)) {
                cb.makeScribble();
            }
            if (p2.getIsCaught() && pp.isPlayerClose(this, this) && currentRoom == 1) {
                System.out.println("PRESSURE PLATE PRESSED");
                p2.changeIsCaught(false);
                p2.spawnPlayer(getX(), getY());
            }

            for (DoVendingMachine VM:vend) {
                if (VM.isPlayerClose(this, this)) {
                    VM.finishVending();
                }
            }

            if (badReaction.getCloseGreen()){
                badReaction.changeGreen(true);
                System.out.println("true");
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
            if (burnTests.getStack() >= 1){
                burnTests.decrementStack(this, this);
            }
        }
    }
}
