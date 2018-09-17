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

  private final Tuple<ColorBox> upTuple;
  private final Tuple<ColorBox> rightTuple;
  private final Tuple<ColorBox> leftTuple;
  private final Tuple<ColorBox> downTuple;

  private final ColorBox upLeft;
  private final ColorBox upRight;
  private final ColorBox downLeft;
  private final ColorBox downRight;

  Tile(final Vector2 position,
    final Color upLeftColor, final Color upRightColor,
    final Color downLeftColor, final Color downRightColor) {

    this.position = position;
    this.upLeft = new ColorBox(upLeftColor);
    this.upRight = new ColorBox(upRightColor);
    this.downLeft = new ColorBox(downLeftColor);
    this.downRight = new ColorBox(downRightColor);

    upTuple = new Tuple(upLeft, upRight);
    leftTuple = new Tuple(upLeft, downLeft);
    rightTuple = new Tuple(upRight, downRight);
    downTuple = new Tuple(downLeft, downRight);
  }

  public static Tuple<ColorBox> tupleFrom(final String colorLabelA, final String colorLabelB) {
    return new Tuple(ColorBox.from(colorLabelA), ColorBox.from(colorLabelB));
  }

  public ColorBox upLeftColor() {
    return upLeft;
  }

  public ColorBox upRightColor() {
    return upRight;
  }

  public ColorBox downLeftColor() {
    return downLeft;
  }

  public ColorBox downRightColor() {
    return downRight;
  }

  public Tuple<ColorBox> upTuple() {
    return upTuple;
  }

  public Tuple<ColorBox> leftTuple() {
    return leftTuple;
  }

  public Tuple<ColorBox> rightTuple() {
    return rightTuple;
  }

  public Tuple<ColorBox> downTuple() {
    return downTuple;
  }

  public static final Tile fromPosition(
    final int column, final int row, final String colors) {
    return fromPosition(column, row, ColorBox.from(String.valueOf(colors.charAt(0))),
      ColorBox.from(String.valueOf(colors.charAt(1))),
      ColorBox.from(String.valueOf(colors.charAt(2))),
      ColorBox.from(String.valueOf(colors.charAt(3))));
  }

  public static final Tile fromPosition(
    final int column, final int row,
    final ColorBox upLeftColor, final ColorBox upRightColor,
    final ColorBox downLeftColor, final ColorBox downRightColor) {

    return fromPosition(column, row, upLeftColor.color(), upRightColor.color(), downLeftColor.color(), downRightColor.color());
  }

  public static final Tile fromPosition(
    final int column, final int row,
    final Color upLeftColor, final Color upRightColor,
    final Color downLeftColor, final Color downRightColor) {

    return new Tile(newVector(column, row), upLeftColor, upRightColor, downLeftColor, downRightColor);
  }

  /**
   * check if
   * u(AB) = u(BA)
   * u(AB) = d(AB)
   * u(AB) = l(AB)
   * u(AB) = r(BA)
   * <p>
   * r(AB) = r(BA)
   * r(AB) = l(AB)
   * r(AB) = d(AB)
   * <p>
   * l(AB) = l(BA)
   * l(AB) = d(BA)
   * <p>
   * d(AB) = d(BA)
   *
   * @param otherTile
   * @return
   */
  public boolean isComplementary(final Tile otherTile) {
    return
      upTuple.equals(otherTile.upTuple().inverse()) ||
        upTuple.equals(otherTile.downTuple()) ||
        upTuple.equals(otherTile.leftTuple) ||
        upTuple.equals(otherTile.rightTuple.inverse()) ||
        rightTuple.equals(otherTile.rightTuple.inverse()) ||
        rightTuple.equals(otherTile.leftTuple) ||
        rightTuple.equals(otherTile.downTuple) ||
        leftTuple.equals(otherTile.leftTuple.inverse()) ||
        leftTuple.equals(otherTile.downTuple.inverse()) ||
        downTuple.equals(otherTile.downTuple.inverse());
  }

  public boolean isUpComplementary(final Tile tile) {
    return
      upTuple.equals(tile.upTuple().inverse()) ||
        upTuple.equals(tile.downTuple()) ||
        upTuple.equals(tile.leftTuple) ||
        upTuple.equals(tile.rightTuple.inverse());
  }

  public boolean isRightComplementary(final Tile tile) {
    return
      rightTuple.equals(tile.rightTuple.inverse()) ||
        rightTuple.equals(tile.leftTuple) ||
        rightTuple.equals(tile.downTuple) ||
        rightTuple.equals(tile.upTuple().inverse());
  }

  @Override
  public String toString() {
    return upLeft + "-" + upRight + " " + downLeft + "-" + downRight;
  }

  public Vector2 position() {
    return position;
  }

  public void complementaryEdge(final Tile tile) {

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