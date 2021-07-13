import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Created by IntelliJ IDEA.
 * User: Javad Mahmodifard
 * Date: Mar 22, 2010
 * Time: 7:25:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class ToBinary {


    public static String[] toBinary(String str) {
        char[] ch = str.toCharArray();
        String[] completeString = new String[ch.length];

        for (int i = 0; i < ch.length; i++) {
            completeString[i] = Integer.toBinaryString(ch[i]);
            if (completeString[i].length() < 7) {
                int i2 = 7 - completeString[i].length();
                for (int k = 0; k < i2; k++) {
                    completeString[i] = "0" + completeString[i];

                }
            }

        }

        return completeString;

    }

    public static String[] toBinary(File file) {

        byte[] b = null;

        int lenght = 0;
        int counter = 0;
        int i = 0;
        String[] completeString = null;
        String temp;
        try {
            FileInputStream filee = new FileInputStream(file);


            try {
                lenght = filee.available();
                b = new byte[lenght];
                completeString = new String[lenght];

                filee.read(b);

                char c = (char) b[0];
                System.out.println();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int j = 0; j < b.length; j++) {
            completeString[j] = Integer.toBinaryString(b[j]);
            if (completeString[j].length() < 7) {
                int i2 = 7 - completeString[j].length();
                for (int k = 0; k < i2; k++) {
                    completeString[j] = "0" + completeString[j];

                }
            }

        }
        return completeString;
    }

    private static void toBinaryCharacter() {

    }

    public static String toBinaryChar(int number) {


        String str = Integer.toBinaryString(number);
        if (str.length() < 8) {
            int i2 = 8 - str.length();
            for (int k = 0; k < i2; k++) {
                str = "0" + str;

            }
        }
        return str;

    }

    public static int getNumberASCII(String str) {
        return Integer.parseInt(str, 2);
    }

}

