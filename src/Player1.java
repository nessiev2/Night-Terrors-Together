import java.awt.event.KeyEvent;

public class Player1 extends Player {

    public Player1() {
        super(1);
    }

    public void keyPressed(KeyEvent e) {
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
        }
    }

    public void keyReleased(Player p2, KeyEvent e, DoArson arson1, ATrashCan[] trashCans, AChalkBoard cb, APressurePlate pp, DoMess mess, DoHack hack) {
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
            changeIsSpillingWater(false);

            for (ATrashCan tc : trashCans) {
                if (tc.isPlayerClose(this, this)) {
                    tc.setOnFire();
                }
            }
            if (cb.isPlayerClose(this, this)) {
                cb.scribble();
            }
            if (pp.isPlayerClose(this, this)) {
                System.out.println("PRESSURE PLATE PRESSED");
                p2.changeIsCaught(false);
                p2.spawnPlayer(getX(), getY());
            }
            if (hack.isPlayerClose(this, this)){
                hack.taskFinished();
            }
        }
    }
}
