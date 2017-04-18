package ig.labyrinth;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by User_2013 on 15.04.2017.
 *
 * https://habrahabr.ru/post/198266/
 */
public class MainForm {
    public static void main(String[] args) {

        MainForm mainForm = new MainForm();
        mainForm.run();
    }


    public void run(){
        int[][] pix = GetLabyrinthArrayFromDisk();
        PathFind pathFind = new PathFind(pix);
        int waveCount = pathFind.RunWaves(1,0);
        System.out.println("waveCount: " + waveCount);

        int[][] pix1 = pathFind.getPix();
        Point pExit = new Point(pix1.length-1,pix1[0].length-2);

        System.out.println(pix1[1][0]);
        System.out.println(pix1[pExit.X][pExit.Y]);

        BufferedImage bi = new BufferedImage(pix1.length, pix1[0].length, BufferedImage.TYPE_INT_RGB);
        for (int i=0;i< pix1.length;i++){
            for (int j=0;j< pix1[i].length;j++){
                Color color = new Color(255,255,125);
                if (pix1[i][j] ==-1) color = new Color(0,0,0);
                bi.setRGB(i,j, color.getRGB());
            }
        }

        LinkedList<Point> backpath = new LinkedList<Point>();
        pathFind.getBackPath(pExit.X,pExit.Y,pix1[pExit.X][pExit.Y]+1,backpath);
        for (int i=0;i<backpath.size();i++){
            Color color = new Color(255,0,0);
            Point p = backpath.get(i);
            bi.setRGB(p.X,p.Y, color.getRGB());
        }

        try {
            ImageIO.write(bi, "png", new File("D:\\result_path.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }


    public int[][] GetLabyrinthArrayFromDisk(){
        String s = "D:\\JavaProjects\\LabyrinthSolver\\LABYRINTH.png";

        BufferedImage image;

        try {
            image = ImageIO.read(new File(s));

            int nImageWidth = image.getWidth(null);
            int nImageHeight = image.getHeight(null);
            int[][] pix = new int[nImageWidth][nImageHeight];

            for (int i=0;i< pix.length;i++){
                for (int j=0;j< pix[i].length;j++){
                    Color color = getPixelColor(image,i,j);
                    if (color.equals(new Color(0,0,0))) {
                        pix[i][j]=-1;
                    } else {
                        pix[i][j]=0;
                    }
                }
            }
            System.out.println(getPixelColor(image,1,1));
            System.out.println(getPixelColor(image,0,0));
            return pix;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private Color getPixelColor(BufferedImage bi, int x, int y) {
        Object colorData = bi.getRaster().getDataElements(x, y, null);//данные о пикселе
        int argb = bi.getColorModel().getRGB(colorData);//преобразование данных в цветовое значение
        return new Color(argb, true);
    }


}
