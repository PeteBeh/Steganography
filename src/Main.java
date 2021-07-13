import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by IntelliJ IDEA.
 * User: java
 * Date: 12/3/11
 * Time: 6:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String text = "اآبپتثجچحخدزرزشسسینوهغعقکگژلشضظط";

        char[] chString = text.toCharArray();
        int max = 0;
        int min = 1740;
        byte bMax = 0;
        byte bMin = 0;
        for (int i = 0; i < chString.length; i++) {
            if (chString[i] > max) {
                max = chString[i];
                bMax = (byte) chString[i];
            } else if (chString[i] < min) {
                min = chString[i];
                bMin = (byte) chString[i];
            }

        }

        String temp = "";
        for (int i = 0; i < chString.length; i++) {
            temp += (" " + Integer.toBinaryString(chString[i]));

        }
        File file = new File("C:\\Users\\java\\Desktop\\1.txt");
        FileInputStream input = new FileInputStream(file);

        String code1 = Integer.toBinaryString(chString[0]);
        int c = Integer.valueOf(code1, 2);
        char cc = (char) c;
        System.out.println(cc);

    }
}
