package com.meli;

import java.awt.*;

import static com.meli.Vector2.newVector;

//                                     Sample
//                            board dimension 6x4
//                            tiles dimension 50x50
// ------------------------------------------------------------------------
//                                TILES
// ------------------------------------------------------------------------|
//        |   1  |    2    |      3    |     4     |     5     |     6     |
// -------|------|---------|-----------|-----------|----------|------------|
//        1  - 49| 51 - 99 | 101 - 149 | 151 - 199 | 201 - 249 | 251 - 299 |
//    1   |      |         |           |           |           |           |
//        49 - 49| 51 - 99 | 101 - 149 | 151 - 199 | 201 - 249 | 251 - 299 |
// -------|----------------------------------------------------------------|
//        51 - 49| 51 - 99 | 101 - 149 | 151 - 199 | 201 - 249 | 251 - 299 |
//   2    |      |         |           |           |           |           |
//        99 - 49| 51 - 99 | 101 - 149 | 151 - 199 | 201 - 249 | 251 - 299 |
// -------|----------------------------------------------------------------|
//        101- 49| 51 - 99 | 101 - 149 | 151 - 199 | 201 - 249 | 251 - 299 |
//   3    |      |         |           |           |           |           |
//        149- 49| 51 - 99 | 101 - 149 | 151 - 199 | 201 - 249 | 251 - 299 |
// -------|----------------------------------------------------------------|
//        151- 49| 51 - 99 | 101 - 149 | 151 - 199 | 201 - 249 | 251 - 299 |
//   4    |      |         |           |           |           |           |
//        199- 49| 51 - 99 | 101 - 149 | 151 - 199 | 201 - 249 | 251 - 299 |
// -------|----------------------------------------------------------------|
class Tile {
  private final Vector2 position;

  private final ColorBox upLeftColor;
  private final ColorBox upRightColor;
  private final ColorBox downLeftColor;
  private final ColorBox downRightColor;

  Tile(final Vector2 position,
    final Color upLeftColor, final Color upRightColor,
    final Color downLeftColor, final Color downRightColor) {

    this.position = position;
    this.upLeftColor = new ColorBox(upLeftColor);
    this.upRightColor = new ColorBox(upRightColor);
    this.downLeftColor = new ColorBox(downLeftColor);
    this.downRightColor = new ColorBox(downRightColor);
  }

  public static Tuple<ColorBox> tupleFrom(final String colorLabelA, final String colorLabelB) {
    return new Tuple(ColorBox.from(colorLabelA), ColorBox.from(colorLabelB));
  }

  public ColorBox upLeftColor() {
    return upLeftColor;
  }

  public ColorBox upRightColor() {
    return upRightColor;
  }

  public ColorBox downLeftColor() {
    return downLeftColor;
  }

  public ColorBox downRightColor() {
    return downRightColor;
  }

  public Tuple<ColorBox> upColorTuple() {
    return new Tuple(upLeftColor, upRightColor);
  }

  public Tuple<ColorBox> leftColorTuple() {
    return new Tuple(upLeftColor, downLeftColor);
  }

  public Tuple<ColorBox> rightColorTuple() {
    return new Tuple(upRightColor, downRightColor);
  }

  public Tuple<ColorBox> downColorTuple() {
    return new Tuple(downLeftColor, downRightColor);
  }

  public static final Tile fromPosition(
    final int column, final int row,
    final Color upLeftColor, final Color upRightColor,
    final Color downLeftColor, final Color downRightColor) {

    return new Tile(newVector(column, row), upLeftColor, upRightColor, downLeftColor, downRightColor);
  }

  @Override
  public String toString() {
    return upLeftColor + "-" + upRightColor + " " + downLeftColor + "-" + downRightColor;
  }

  public Vector2 position() {
    return position;
  }

//  public String prettyPrint() {
//    return upLeft + "-" + upRight + "\n" +
//      downLeft + "-" + downRight;
//  }
//
//  public String prettyPrintColorAndPositions() {
//    return
//      upLeft + "[" + upLeftColor + "]" + "-" + upRight + "[" + upRightColor + "]" + "\n" +
//        downLeft + "[" + downLeftColor + "]" + "-" + downRight + "[" + downRightColor + "]";
//  }

}