import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: Javad Mahmodifard
 * Date: Mar 5, 2010
 * Time: 11:22:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class Graphicizer extends JFrame implements ActionListener {


    public static void main(String[] args) {
        new Graphicizer();
    }

    // this is constracture
    public Graphicizer() throws HeadlessException {
        setSize(500, 400);

        setTitle("Steganography");

        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(
                    WindowEvent e) {
                System.exit(0);
            }
        });


        menubar = new MenuBar();
        menu = new Menu("File");

        menuItem1 = new MenuItem("Open...");
        menu.add(menuItem1);
        menuItem1.addActionListener(this);

        menuItem2 = new MenuItem("Save As...");
        menu.add(menuItem2);
        menuItem2.addActionListener(this);

        menuItem3 = new MenuItem("OpenText&Migrate");
        menu.add(menuItem3);
        menuItem3.addActionListener(this);

        menuItem4 = new MenuItem("SpliteTextFromImage");
        menu.add(menuItem4);
        menuItem4.addActionListener(this);

        menuItem5 = new MenuItem("Exit");
        menu.add(menuItem5);
        menuItem5.addActionListener(this);

        menubar.add(menu);

        setMenuBar(menubar);

        dialog = new FileDialog(this, "File Dialog");

    }

    public void actionPerformed(ActionEvent e) {


        /*                this if is for Opening Picture                        */
        if (e.getSource() == menuItem1) {

            openImage();
            repaint();

        }

        /*                 this is for Saving Picture that Edited              */

        if (e.getSource() == menuItem2) {

            saveImage();
        }
/*                 this is for Saving Picture that Edited              */

        if (e.getSource() == menuItem3) {

            File file = openText();
            ToBinary.toBinary(file);
            ToBinary.toBinary("Hello Alick");
        }
        if (e.getSource() == menuItem4) {

            saveText();
        }
        if (e.getSource() == menuItem5) {


            this.setVisible(false);
        }
    }

    @Override
    public void paint(Graphics g) {

        if (bufferedImage != null) {
            g.drawImage(bufferedImage.getBufferedImage(), getSize().width / 2
                    - bufferedImage.getBufferedImage().getWidth() / 2,
                    getInsets().top + 20, this);

        } else
            super.paint(g);
    }

    private void openImage() {

        dialog.setMode(FileDialog.LOAD);
        dialog.setTitle("BMP File Name For Opening");
        dialog.setVisible(true);

        try {
            if (!dialog.getFile().equals("")) {
                File input = new File(dialog.getDirectory()
                        + dialog.getFile());


//             bufferedImage = new GImage ( new BufferedImage ( ImageIO.read(input).getWidth () ,
//                     ImageIO.read(input).getHeight () , BufferedImage.TYPE_INT_ARGB)) ;
//
                bufferedImage = new GImage(ImageIO.read(input));

                System.out.println(bufferedImage.getBufferedImage().getType());


                int t = bufferedImage.getBufferedImage().getRGB(50, 10);
                int red = GImage.getRed(t);
                int green = GImage.getGreen(t);
                int blue = GImage.getBlue(t);

                System.out.println("red = " + red);
                System.out.println("red = " + green);
                System.out.println("red = " + blue);
                setSize(getInsets().left + getInsets().right +
                        Math.max(400, bufferedImage.getBufferedImage().getWidth() + 60),
                        getInsets().top + getInsets().bottom +
                                Math.max(340, bufferedImage.getBufferedImage().getHeight() + 60));

                button1.setBounds(30, getHeight() - 30, 60, 20);
                button2.setBounds(100, getHeight() - 30, 60, 20);
                button3.setBounds(170, getHeight() - 30, 60, 20);
                button4.setBounds(240, getHeight() - 30, 60, 20);
                button5.setBounds(310, getHeight() - 30, 60, 20);
                bufferedImageBackup = bufferedImage;

            }
        } catch (Exception e1) {
            System.out.println(e1.getMessage());

        }
    }
    private void saveImage() {


        dialog.setMode(FileDialog.SAVE);
        dialog.setTitle("BMP File Name For Saving");
        dialog.setVisible(true);

        try {
            if (!dialog.getFile().equals("")) {

                String outfile = dialog.getFile();

                File outputFile = new File(dialog.getDirectory()
                        + outfile);
                int t = bufferedImage.getBufferedImage().getRGB(2, 1);
                int red = GImage.getRed(t);
                int green = GImage.getGreen(t);
                int blue = GImage.getBlue(t);
                System.out.println("red = " + red);
                System.out.println("red = " + green);
                System.out.println("red = " + blue);
                System.out.println(bufferedImage.getBufferedImage().getType());
                ImageIO.write(bufferedImage.getBufferedImage(),
                        outfile.substring(outfile.length() - 3,
                                outfile.length()), outputFile);
                System.out.println(bufferedImage.getBufferedImage().getType());

            }

        } catch (Exception e2) {
            System.out.println(e2.getMessage());
        }

    }


    private File openText() {

        dialog.setMode(FileDialog.LOAD);
        dialog.setTitle("TXT File Name For Opening");
        dialog.setVisible(true);
        File input = null;

        try {
            if (!dialog.getFile().equals("")) {
                input = new File(dialog.getDirectory()
                        + dialog.getFile());

            }
        } catch (Exception e) {

        }
        Steganography s = new Steganography();
        bufferedImage.bufferedImage = s.migrateTextImage(bufferedImage, ToBinary.toBinary(input));
        repaint();
        return input;

    }


    public void saveText() {
        Steganography s = new Steganography();
        s.spliteTextImage(bufferedImage);
    }

    /*
       Private Variable    ------------------------------------
    */
    GImage bufferedImage = null, bufferedImageBackup;
    Image image;
    Menu menu;
    MenuBar menubar;
    MenuItem menuItem1, menuItem2, menuItem3, menuItem4, menuItem5;
    Button button1, button2, button3, button4, button5;
    FileDialog dialog;

    /*
       End Private Variable ------------------------------------
    */

}

