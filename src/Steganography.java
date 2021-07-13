import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Javad Mahmodifard
 * Date: Mar 22, 2010
 * Time: 10:53:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class Steganography {

    GImage image;
    int[][] imageArray;

    public BufferedImage migrateTextImage(GImage imageI, String[] string) {
        this.image = imageI;
        this.imageArray = this.image.getPixelArray();
        int counter = 0;
        int lengh = 0;
        int i = 0;
        int j = 0;
        int endI;
        int endJ;
        for (i = 2; i < imageArray.length && (lengh < string.length); i++) {
            for (j = 0; (j < imageArray[0].length) && (lengh < string.length); j += 1) {

                int red = GImage.getRed(imageArray[i][j]);
                int green = GImage.getGreen(imageArray[i][j]);
                int blue = GImage.getBlue(imageArray[i][j]);
                //  int trans = GImage.getAlpha( imageArray [ i ][ j ] ) ;

                String sRed = ToBinary.toBinaryChar(red);
                String sGreen = ToBinary.toBinaryChar(green);
                String sBlue = ToBinary.toBinaryChar(blue);
                //   String sTrans  = ToBinary.toBinaryChar(trans) ;
                String sSub = string[counter].substring(0, 2);
                String character = string[counter];
                char ch1 = (char) ToBinary.getNumberASCII(character);


                sRed = sRed.substring(0, 6) + string[counter].substring(0, 2);

                sGreen = sGreen.substring(0, 6) + string[counter].substring(2, 4);

                sBlue = sBlue.substring(0, 5) + string[counter].substring(4, 7);

                //   sTrans = sTrans.substring(0,7) + string[counter].substring(6,7) ;

                red = ToBinary.getNumberASCII(sRed);
                green = ToBinary.getNumberASCII(sGreen);
                blue = ToBinary.getNumberASCII(sBlue);
                //       trans  = ToBinary.getNumberASCII(sTrans);

                this.image.getBufferedImage().setRGB(i, j, GImage.createRGBPixel(red, green, blue));


                lengh++;
                counter++;

            }

        }

        endI = i;
        endJ = j;

        String sEndI = toAddingZero(ToBinary.toBinaryChar(endI));
        String sEndJ = toAddingZero(ToBinary.toBinaryChar(endJ));

        addLenghOfString(sEndI, sEndJ);

        System.out.println("Finish .........");
        return image.getBufferedImage();
    }

    public void spliteTextImage(GImage image) {
        Writer output = null;
        try {
//            creatFile();
            {
                File fileName;
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Pleae Enter The Text File Name");
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = chooser.showSaveDialog(null);
                if (result == JFileChooser.CANCEL_OPTION)
                    return;
                fileName = chooser.getSelectedFile();
                if (fileName == null || fileName.equals(""))
                    JOptionPane.showMessageDialog(null, "Your Address Is Invalid .");
                else {
                    output = new BufferedWriter(new FileWriter(fileName));

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.image = image;
        imageArray = image.getPixelArray();
        int i = 0, j = 0;
        int endI, endJ;

        endI = getLenghOfIJ(0);
        endJ = getLenghOfIJ(1);
        String complete = "";
        char ch1;
        String charachter;
        int red;
        int green;
        int blue;
        String sRed;
        String sGreen;
        String sBlue;
        int endJFor = imageArray[0].length;
        for (i = 2; i < endI; i++) {
            for (j = 0; j < endJFor; j += 1) {
                if (i == endI - 1 && j == 0) {
                    endJFor = endJ;
                }
                red = GImage.getRed(imageArray[i][j]);
                green = GImage.getGreen(imageArray[i][j]);
                blue = GImage.getBlue(imageArray[i][j]);
                //         int trans = GImage.getAlpha( imageArray [ i ][ j ] ) ;

                sRed = ToBinary.toBinaryChar(red);
                sGreen = ToBinary.toBinaryChar(green);
                sBlue = ToBinary.toBinaryChar(blue);
                //      String sTrans  = ToBinary.toBinaryChar(trans) ;

                sRed = sRed.substring(6, 8);
                sGreen = sGreen.substring(6, 8);
                sBlue = sBlue.substring(5, 8);
                //       sTrans = sTrans.substring( 7 , 8 ) ;

                charachter = sRed + sGreen + sBlue /*+ sTrans*/;
                ch1 = (char) ToBinary.getNumberASCII(charachter);
                try {
                    complete = complete + ch1;
                } catch (Exception e) {
                    System.out.println("i = " + i);
                }
                {
                }

            }

        }
        try {
            output.write(complete);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void creatFile() throws FileNotFoundException {

        File fileName;
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.CANCEL_OPTION)
            return;
        fileName = chooser.getSelectedFile();
        if (fileName == null || fileName.equals(""))
            JOptionPane.showMessageDialog(null, "Your Address Is Invalid .");
        else {
            file = new RandomAccessFile(fileName, "rw");

        }
    }

    private RandomAccessFile file;

    public String toAddingZero(String str) {
        {
            int toAdd = 20 - str.length();
            if (toAdd > 0) {
                for (int k = 0; k < toAdd; k++) {
                    str = "0" + str;

                }
            }
        }

        return str;
    }

    public void addLenghOfString(String sEndI, String sEndJ) {
        addLenghtOfIJ(sEndI, 0);
        addLenghtOfIJ(sEndJ, 1);

    }

    public void addLenghtOfIJ(String sEnd, int i) {

        int p = 0;
        for (int k = 0; k < 4; k++) {
            int red = GImage.getRed(image.bufferedImage.getRGB(i, k));
            int green = GImage.getGreen(image.bufferedImage.getRGB(i, k));
            int blue = GImage.getBlue(image.bufferedImage.getRGB(i, k));
            int trans = GImage.getAlpha(image.bufferedImage.getRGB(i, k));

            String sRed = ToBinary.toBinaryChar(red);
            String sGreen = ToBinary.toBinaryChar(green);
            String sBlue = ToBinary.toBinaryChar(blue);
            String sTrans = ToBinary.toBinaryChar(trans);

            String sSub = sEnd.substring(p, p + 2);
            sSub = sRed.substring(0, 6);
            sRed = sRed.substring(0, 6) + sEnd.substring(p, p + 2);
            p += 2;
            sGreen = sGreen.substring(0, 6) + sEnd.substring(p, p + 2);
            p += 2;
            sBlue = sBlue.substring(0, 7) + sEnd.substring(p, p + 1);
            p += 1;


            red = ToBinary.getNumberASCII(sRed);
            green = ToBinary.getNumberASCII(sGreen);
            blue = ToBinary.getNumberASCII(sBlue);
            trans = ToBinary.getNumberASCII(sTrans);
            image.getBufferedImage().setRGB(i, k, GImage.createRGBPixel(red, green, blue, trans));

        }
    }

    private int getLenghOfIJ(int i) {
        int p = 6;
        String number = "";
        for (int k = 0; k < 4; k++) {
            int red = GImage.getRed(image.bufferedImage.getRGB(i, k));
            int green = GImage.getGreen(image.bufferedImage.getRGB(i, k));
            int blue = GImage.getBlue(image.bufferedImage.getRGB(i, k));

            String sRed = ToBinary.toBinaryChar(red);
            String sGreen = ToBinary.toBinaryChar(green);
            String sBlue = ToBinary.toBinaryChar(blue);


            sRed = sRed.substring(p, (p + 2));

            sGreen = sGreen.substring(p, (p + 2));

            sBlue = sBlue.substring((p + 1), (p + 2));

            number = number + sRed + sGreen + sBlue;

        }

        int result = ToBinary.getNumberASCII(number);

        return result;
    }
}

