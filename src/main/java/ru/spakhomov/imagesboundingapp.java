package ru.spakhomov;

import javax.imageio.ImageIO;
//import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
//import java.util.ArrayList;
//import java.util.List;

public class imagesboundingapp {
    public static void main(String[] args){
        try{
            BufferedImage img1 = ImageIO.read(new File("G:\\1.png"));
            BufferedImage img2 = ImageIO.read(new File("G:\\2.png"));
            BufferedImage img3 = ImageIO.read(new File("G:\\3.png"));
            BufferedImage img4 = ImageIO.read(new File("G:\\4.png"));


            BufferedImage im = new BufferedImage(1024,1024,BufferedImage.TYPE_INT_ARGB);
            im.getGraphics().drawImage(img1,100,0,null);    //2 и 3 параметр - координаты помещаемого изображения
            im.getGraphics().drawImage(img2,0,0,null); //(верхний левый угол изображения будет иметь эти
            im.getGraphics().drawImage(img3,0,0,null); //координаты).
            im.getGraphics().drawImage(img4,512,512,null);
            ImageIO.write(im,"png",new File("G:\\big.png"));

        }catch(Exception e){
            e.printStackTrace();}
    }
}
