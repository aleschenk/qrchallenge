package com.meli;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

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
    imageTemplate(qr.tiles());

//    qr.createGraph();

//    qr.countDuplicateTuples()
//      .entrySet()
//      .stream()
//      .map(entry -> entry.getKey() + ": " + entry.getValue())
//      .forEach(System.out::println);

//    qr.countColors().entrySet().stream().forEach(entry -> {
//      System.out.println(entry.getKey() + ": " + entry.getValue());
//    });

//    qr.tilesTuples().entrySet().stream().forEach(entry -> {
//      System.out.println(entry.getKey() + " - " + entry.getValue());
//    });

//    qr.tilesTuples().entrySet().stream()
//      .filter(tuple -> tuple.getValue().size() == 2)
//      .forEach(System.out::println);

//    qr.tilesTuples().entrySet().stream().forEach(entry -> {
//      if (entry.getValue().size() == 2) {
//        Tile t1 = entry.getValue().get(0);
//        Tile t2 = entry.getValue().get(1);
////        graph.addEdge();
////        Join join = Join.from(t1, t2);
//      }
//      System.out.println(entry.getKey() + " - " + entry.getValue());
//    });

  }

  private void imageTemplate(final List<Tile> tiles) {
    try {
      BufferedImage bi = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g = bi.createGraphics();

      Font currentFont = g.getFont();
      Font newFont = currentFont.deriveFont(currentFont.getSize() * 3f);
      g.setFont(newFont);

      tiles.stream().forEach(tile -> {
        Rectangle2D upLeft = new Rectangle2D.Double((tile.position().x() - 1) * 100, (tile.position().y() - 1) * 100, 50, 50);
        g.setPaint(tile.upLeftColor().color());
        g.fill(upLeft);
        g.setPaint(tile.upLeftColor().color(true).darker());
        g.drawString(tile.upLeftColor().label(), ((tile.position().x() - 1) * 100) + 10, ((tile.position().y() - 1) * 100) + 40);

        Rectangle2D upRight = new Rectangle2D.Double(((tile.position().x() - 1) * 100) + 50, (tile.position().y() - 1) * 100, 50, 50);
        g.setPaint(tile.upRightColor().color());
        g.fill(upRight);
        g.setPaint(tile.upRightColor().color(true).darker());
        g.drawString(tile.upRightColor().label(), ((tile.position().x() - 1) * 100) + 60, ((tile.position().y() - 1) * 100) + 40);

        Rectangle2D downLeft = new Rectangle2D.Double((tile.position().x() - 1) * 100, ((tile.position().y() - 1) * 100) + 50, 50, 50);
        g.setPaint(tile.downLeftColor().color());
        g.fill(downLeft);
        g.setPaint(tile.downLeftColor().color(true).darker());
        g.drawString(tile.downLeftColor().label(), ((tile.position().x() - 1) * 100) + 10, ((tile.position().y() - 1) * 100) + 90);

        Rectangle2D downRight = new Rectangle2D.Double(((tile.position().x() - 1) * 100) + 50, ((tile.position().y() - 1) * 100) + 50, 50, 50);
        g.setPaint(tile.downRightColor().color());
        g.fill(downRight);
        g.setPaint(tile.downRightColor().color(true).darker());
        g.drawString(tile.downRightColor().label(), ((tile.position().x() - 1) * 100) + 60, ((tile.position().y() - 1) * 100) + 90);
      });
//      g.drawString("A", 10, 40);

      ImageIO.write(bi, "PNG", new File("tiles.png"));
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }

  }

}
