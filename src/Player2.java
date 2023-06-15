import java.awt.event.KeyEvent;

public class Player2 extends Player {
    private int ticks = 0;
    public Player2() {
        super(2);
    }

    public void keyPressed(KeyEvent e, DoHack hack, int currentClassroom) {
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
            changeIsSpillingWater(true);

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


        }
    }

    public void keyReleased(Player p1, int currentRoom, KeyEvent e, DoArson arson1, ATrashCan[] trashCans, AChalkBoard cb, APressurePlate pp, DoMess mess, DoHack hack, DoVendingMachine[] vend, DoBadReaction badReaction, DoBurnTests burnTests7) {
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

            changeIsSpillingWater(false);

            for (ATrashCan tc : trashCans) {
                if (tc.isPlayerClose(this, this)) {
                    tc.setOnFire();
                }
            }
            if (cb.isPlayerClose(this, this)) {
                cb.makeScribble();
            }
            if (p1.getIsCaught() && pp.isPlayerClose(this, this) && currentRoom == 1) {
                System.out.println("PRESSURE PLATE PRESSED");
                p1.changeIsCaught(false);
                p1.spawnPlayer(getX(), getY());
            }
            if (hack.isPlayerClose(this, this)){
                hack.taskFinished();
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
            if (burnTests7.getStack() >= 1){
                burnTests7.decrementStack(this, this);
            }
        }
    }
}
