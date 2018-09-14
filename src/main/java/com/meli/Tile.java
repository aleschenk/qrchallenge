package com.meli;

import java.awt.Color;

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

  private final Color upLeftColor;
  private final Color upRightColor;
  private final Color downLeftColor;
  private final Color downRightColor;

  Tile(final Vector2 position,
    final Color upLeftColor, final Color upRightColor,
    final Color downLeftColor, final Color downRightColor) {

    this.position = position;
    this.upLeftColor = upLeftColor;
    this.upRightColor = upRightColor;
    this.downLeftColor = downLeftColor;
    this.downRightColor = downRightColor;
  }

  public Color upLeftColor() {
    return upLeftColor;
  }

  public Color upRightColor() {
    return upRightColor;
  }

  public Color downLeftColor() {
    return downLeftColor;
  }

  public Color downRightColor() {
    return downRightColor;
  }

  public Tuple<Color> upColorTuple() {
    return new Tuple(upLeftColor, upRightColor);
  }

  public Tuple<Color> leftColorTuple() {
    return new Tuple(upLeftColor, downLeftColor);
  }

  public Tuple<Color> rightColorTuple() {
    return new Tuple(upRightColor, downRightColor);
  }

  public Tuple<Color> downColorTuple() {
    return new Tuple(downLeftColor, downRightColor);
  }

  public static final Tile fromPosition(
    final int column, final int row,
    final Color upLeftColor, final Color upRightColor,
    final Color downLeftColor, final Color downRightColor) {

    return new Tile(newVector(column, row), upLeftColor, upRightColor, downLeftColor, downRightColor);
  }

//  @Override
//  public String toString() {
//    return upLeft + "-" + upRight + " " + downLeft + "-" + downRight;
//  }
//
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