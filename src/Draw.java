import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Draw {
    public static void drawImage(Vec3[][] pixels) {
        int width = pixels[0].length;
        int height = pixels.length;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        File f = null;
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                Vec3 color = pixels[i][j];
                int r = (int) color.getX();
                int g = (int) color.getY();
                int b = (int) color.getZ();
                int p = (256<<24) | (r<<16) | (g<<8) | b;
                img.setRGB(j, i, p);
            }
        }
        try {
            f = new File("output.jpg");
            ImageIO.write(img, "png", f);
        } catch(IOException e){
            System.out.println(e);
        }
    }
}
