import java.awt.image.BufferedImage;

/**
 * Created by IntelliJ IDEA.
 * User: Javad Mahmodifard
 * Date: Mar 22, 2010
 * Time: 11:10:19 PM
 * To change this template use File | Settings | File Templates.
 */

public class GImage {
    BufferedImage bufferedImage = null;

    public GImage(BufferedImage bufferedImage) {

        this.bufferedImage = bufferedImage;
    }


    public int[][] getPixelArray() {
        int[][] i = new int[bufferedImage.getWidth()][bufferedImage.getHeight()];
        for (int j = 0; j < bufferedImage.getWidth(); j++) {
            for (int k = 0; k < bufferedImage.getHeight(); k++) {
                i[j][k] = bufferedImage.getRGB(j, k);
            }
        }
        return i;
    }


    /**
     * Returns the alpha component from an RGB value.
     */
    public static int getAlpha(int pixel) {
        return (pixel >> 24) & 0xFF;
    }

    /**
     * Returns the red component from an RGB value.
     */
    public static int getRed(int pixel) {
        return (pixel >> 16) & 0xFF;
    }

    /**
     * Returns the green component from an RGB value.
     */
    public static int getGreen(int pixel) {
        return (pixel >> 8) & 0xFF;
    }

    /**
     * Returns the blue component from an RGB value.
     */
    public static int getBlue(int pixel) {
        return pixel & 0xFF;
    }

    /**
     * Creates an opaque pixel value from the color components
     */
    public static int createRGBPixel(int r, int g, int b) {
        return createRGBPixel(r, g, b, 0xFF);
    }

    /**
     * Creates a pixel value from the color components, including alpha
     */
    public static int createRGBPixel(int r, int g, int b, int alpha) {
        return (alpha << 24) | (r << 16) | (g << 8) | b;
    }


    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }
}
