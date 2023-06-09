import java.awt.*;
import java.util.Random;

public class DoScribble extends Thing {
    private String[] scribbleArray = new String[20];
    private int[] xs = new int[20];
    private int[] ys = new int[20];
    private int maxX, minX, maxY, minY;
    AChalkBoard cb;

    public DoScribble(int x, int y, AChalkBoard cb){
        super(x, y, cb.getWidth(), cb.getHeight());

        this.cb = cb;

        maxX = cb.getX() + cb.getWidth() - 80;
        minX = cb.getX() + 20;
        maxY = cb.getY() + cb.getHeight() - 30;
        minY = cb.getY() + 20;

        for (int i = 0; i < 20; i++){
            String c = randomChar();
            scribbleArray[i] = c;
            xs[i] = (int)(Math.random() * maxX + minX);
            ys[i] = (int)(Math.random() * maxY + minY);
        }
    }

    private static String randomChar() {
        String ans = "";
        Random r = new Random();
        ans += (char)(r.nextInt(26) + 'A');
        return ans;
    }

    public void paint (Graphics2D g2d){
        if (cb.getScribble()) {
            g2d.setColor(Color.pink);
            for (int j = 0; j < 20; j++){
                g2d.drawString(scribbleArray[j], xs[j], ys[j]);
            }
        }
    }

}
