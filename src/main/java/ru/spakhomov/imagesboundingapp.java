package ru.spakhomov;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class imagesboundingapp {
    public static void main(String[] args){
        try{
            //создание списков частей изображения
            List<BufferedImage> bodies = new ArrayList<>();
            List<BufferedImage> heads = new ArrayList<>();
            List<BufferedImage> foots = new ArrayList<>();

            //ввод числа элементов каждой части изображения
            int countBodies = 1;
            int countHeads = 2;
            int countFoots = 2;

            //запись в соответствующие списки частей изображений
            for (int i=0; i<countBodies; i++){
                bodies.add(ImageIO.read(new File("images-details\\body\\"+(i+1)+".png")));
            }
            for (int i=0; i<countHeads; i++){
                heads.add(ImageIO.read(new File("images-details\\head\\"+(i+1)+".png")));
            }
            for (int i=0; i<countFoots; i++){
                foots.add(ImageIO.read(new File("images-details\\foot\\"+(i+1)+".png")));
            }


            int resultNumber=0; //общий счетчик числа результирующих изображений
            BufferedImage resultImage = new BufferedImage(600,800,BufferedImage.TYPE_INT_ARGB); //объект результирующего изображения
            //формирование всех возможных комбинаторных вариантов изображений
            for (int i=0; i<countBodies; i++){
                resultImage.getGraphics().drawImage(bodies.get(i),0,0,null); //рисуется первый объект на холст
                BufferedImage tempResultImageForHeads = deepCopy(resultImage); //сохранение (клонирование) промежуточного результата с целью дальнейшего использования
                for (int j = 0; j < countHeads; j++) {
                    resultImage=deepCopy(tempResultImageForHeads);
                    resultImage.getGraphics().drawImage(heads.get(j),0,0,null); //рисуется второй объект на холст
                    BufferedImage tempResultImageForFoots = deepCopy(resultImage); //сохранение (клонирование) промежуточного результата с целью дальнейшего использования
                    for (int k = 0; k < countFoots; k++) {
                        resultImage=deepCopy(tempResultImageForFoots); //сохранение (клонирование) промежуточного результата с целью дальнейшего использования
                        resultImage.getGraphics().drawImage(foots.get(k),0,0,null); //рисуется третий объект на холст
                        resultNumber++;
                        ImageIO.write(resultImage,"png",new File("images-details\\result\\"+resultNumber+".png")); //запись резуьтирующего изображения на диск
                    }
                }
            }

            //код, открывающий первое результрующее изображение после выполнения их формирования
            Desktop desktop = null;
            if (Desktop.isDesktopSupported()) {
                desktop = Desktop.getDesktop();
            }
            try {
                desktop.open(new File("images-details\\result\\1.png"));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();}
    }

    //метод для клонирования объекта BufferedImage
    public static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(bi.getRaster().createCompatibleWritableRaster());
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
}
