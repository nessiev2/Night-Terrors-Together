import java.awt.event.KeyEvent;

public class Player2 extends Player {
    public Player2() {
        super(2);
    }

    public void keyPressed(KeyEvent e) {
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
        }
    }

    public void keyReleased(Player p1, int currentRoom, KeyEvent e, DoArson arson1, ATrashCan[] trashCans, AChalkBoard cb, APressurePlate pp, DoMess mess, DoHack hack) {
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
            changeIsSpillingWater(false);

            for (ATrashCan tc : trashCans) {
                if (tc.isPlayerClose(this, this)) {
                    tc.setOnFire();
                }
            }
            if (cb.isPlayerClose(this, this)) {
                cb.scribble();
            }
            if (p1.getIsCaught() && pp.isPlayerClose(this, this) && currentRoom == 1) {
                System.out.println("PRESSURE PLATE PRESSED");
                p1.changeIsCaught(false);
                p1.spawnPlayer(getX(), getY());
            }
            if (hack.isPlayerClose(this, this)){
                hack.taskFinished();
            }
        }
    }
}
