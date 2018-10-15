package com.dawn.zhao.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageRGBDemo {

    public static void main(String... args) throws IOException {
//        BufferedImage image = ImageIO.read(new File("C:/Users/76456/Pictures/Saved Pictures/Rem_secret.png"));
//        System.out.println(new Color(image.getRGB(0, 0)).toString());

//        writeImage();

        updateImage();
    }

    private static void putPixel(BufferedImage image, int widthStart, int height, int rgb) {
        for (int h = 0; h < height; ++h)
            for (int i = 0; i < 30; i++) image.setRGB(widthStart + i, h, rgb);
    }

    private static void writeImage() throws IOException {
        int width = 30, height = 30;
        BufferedImage img = new BufferedImage(width * 10, height, BufferedImage.TYPE_INT_RGB);
        int r = 255, g = 0, b = 0;
        for (int i = 0; i < 10; i++) {
            putPixel(img, i * 30, height, new Color(r - i, g, b).getRGB());
        }
        ImageIO.write(img, "png", new File("C:/Users/76456/Pictures/Saved Pictures/Rem_secret.png"));
    }

    private static void updateImage() throws IOException {
        BufferedImage secretImg = ImageIO.read(new File("C:/Users/76456/Pictures/Saved Pictures/Rem_secret.png"));
        for (int w = 0; w < secretImg.getWidth(); w++)
            for (int h = 0; h < secretImg.getHeight(); h++) {
                int lsb = new Color(secretImg.getRGB(w, h)).getRed() % 2; // 个人建议把 % 2 写成 & 1
                secretImg.setRGB(w, h, (lsb == 0 ? new Color(0, 0, 0) : new Color(255, 255, 255)).getRGB());
            }
        ImageIO.write(secretImg, "png", new File("C:/Users/76456/Pictures/Saved Pictures/secret.png"));
    }
}
