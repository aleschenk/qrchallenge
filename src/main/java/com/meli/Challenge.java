package com.meli;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Challenge {

  public static void main(final String[] args) throws Exception {
    new Challenge().init();
  }

  public void init() throws Exception {
    loadImage();
  }

  public void loadImage() throws IOException {
    BufferedImage img = ImageIO.read(new File("challenge.png"));

    QRImage qr = new QRImage(img);

    System.out.println("Total Tiles: " + qr.totalTiles());

    qr.countDuplicateTuples()
      .entrySet()
      .stream()
      .map(entry -> entry.getKey() + ": " + entry.getValue())
      .forEach(System.out::println);

    qr.countColors().entrySet().stream().forEach(entry -> {
      System.out.println(entry.getKey() + ": " + entry.getValue());
    });
  }

}
