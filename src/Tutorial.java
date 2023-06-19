import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tutorial {
    private int slideNum = 1;
    private boolean playTut;
    private BufferedImage img1, img2, img3, img4, img5, img6, img7;
    public void nextSlide(){
        slideNum++;
    }
    public int getSlide(){
        return slideNum;
    }
    public void changePlayTut(boolean x){
        playTut = x;
    }
    public void initializeTut(){
        slideNum = 1;
    }
    public Tutorial(){
        try {
            img1 = ImageIO.read(new File("res\\tut1.png"));
            img2 = ImageIO.read(new File("res\\tut2.png"));
            img3 = ImageIO.read(new File("res\\tut3.png"));
            img4 = ImageIO.read(new File("res\\tut4.png"));
            img5 = ImageIO.read(new File("res\\tut5.png"));
            img6 = ImageIO.read(new File("res\\tut6.png"));
            img7 = ImageIO.read(new File("res\\tut7.png"));
        } catch (IOException e) { System.out.println("tut no image"); }
    }

    public void paint(Graphics g2d){
        if (playTut){
            if (slideNum == 1){
                g2d.drawImage(img1, 0, 0, null);
            } else if (slideNum == 2){
                g2d.drawImage(img2, 0, 0, null);
            } else if (slideNum == 3){
                g2d.drawImage(img3, 0, 0, null);
            } else if (slideNum == 4){
                g2d.drawImage(img4, 0, 0, null);
            } else if (slideNum == 5){
                g2d.drawImage(img5, 0, 0, null);
            } else if (slideNum == 6){
                g2d.drawImage(img6, 0, 0, null);
            } else {
                g2d.drawImage(img7, 0, 0, null);
            }
        }
    }
}
