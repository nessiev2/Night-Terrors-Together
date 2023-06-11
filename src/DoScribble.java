import java.awt.*;
import java.util.Random;

public class DoScribble extends Thing {
    private String[] scribbleArray;
    private int[] xs;
    private int[] ys;
    private int maxX, minX, maxY, minY;
    AChalkBoard cb;

    public void initializeScribble(){
        Random r = new Random();
        scribbleArray = new String[20];
        xs = new int[20];
        ys = new int[20];

        for (int i = 0; i < 20; i++){
            String c = randomChar();
            scribbleArray[i] = c;
            xs[i] = r.nextInt((maxX - minX) + 1) + minX;
            ys[i] = r.nextInt((maxY - minY) + 1) + minY;


        }
    }

    public DoScribble(int x, int y, AChalkBoard cb){
        super(x, y, cb.getWidth(), cb.getHeight());

        this.cb = cb;

        maxX = cb.getX() + cb.getWidth() - 40;
        minX = cb.getX() + 20;
        maxY = cb.getY() + cb.getHeight() - 30;
        minY = cb.getY() + 30;

        initializeScribble();
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

//        g2d.setColor(Color.red);
//        g2d.fillRect(minX, minY, 20, 20);
//        g2d.setColor(Color.yellow);
//        g2d.fillRect(maxX, minY, 20, 20);
//        g2d.setColor(Color.green);
//        g2d.fillRect(minX, maxY, 20, 20);
//        g2d.setColor(Color.blue);
//        g2d.fillRect(maxX, maxY, 20, 20);
    }

}
