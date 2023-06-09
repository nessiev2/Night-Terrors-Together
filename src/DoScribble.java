import java.awt.*;
import java.util.Random;

public class DoScribble extends Thing {
    private String[] scribbleArray = new String[20];
    private int[] xs = new int[20];
    private int[] ys = new int[20];
    private int width, height, x, y, maxX, minX, maxY, minY;
    private boolean scribble = false;

    public DoScribble(int x, int y, int width, int height){
        super(x, y, width, height);

        maxX = x + width - 80;
        minX = x + 20;
        maxY = y + height - 30;
        minY = y + 20;

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
        //we skribl
        if (scribble) {
            g2d.setColor(Color.pink);
            for (int j = 0; j < 20; j++){
                g2d.drawString(scribbleArray[j], xs[j], ys[j]);
            }
        }
    }

}
