package com.meli;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.meli.Vector2.newVector;

public class QRImage {
  private static final int TILE_WIDTH_IN_PIXELS = 50;

  private static final int DISTANCE_BETWEEN_COLOR_TILES = 48;

  private static final int BOARD_TOTAL_COLUMNS = 20;

  private static final int BOARD_TOTAL_ROWS = 20;

  private final BufferedImage bufferedImage;

  private List<Tile> tiles = new ArrayList();

  public QRImage(final BufferedImage bufferedImage) {
    this.bufferedImage = bufferedImage;
    createBoard();
  }

  private void createBoard() {
    for (int column = 1; column <= BOARD_TOTAL_COLUMNS; column++) {
      for (int row = 1; row <= BOARD_TOTAL_ROWS; row++) {
        Tile tile = createTile(column, row);
        tiles.add(tile);
      }
    }
  }

  public int totalTiles() {
    return tiles.size();
  }

  public Map<Color, Integer> countColors() {
    Map<Color, Integer> colors = new HashMap();
    tiles.stream().forEach(tile -> {
      int upLeftColor = colors.getOrDefault(tile.upLeftColor(), 0);
      colors.put(tile.upLeftColor(), ++upLeftColor);

      int upRightColor = colors.getOrDefault(tile.upRightColor(), 0);
      colors.put(tile.upRightColor(), ++upRightColor);

      int downLeftColor = colors.getOrDefault(tile.downLeftColor(), 0);
      colors.put(tile.downLeftColor(), ++downLeftColor);

      int downRightColor = colors.getOrDefault(tile.downRightColor(), 0);
      colors.put(tile.downRightColor(), ++downRightColor);
    });
    return colors;
  }

  public Map<Tuple<Color>, Integer> countDuplicateTuples() {
    Map<Tuple<Color>, Integer> tuplesCount = new HashMap();
    tiles.stream().forEach(tile -> {
      int totalUpColorTuple = tuplesCount.getOrDefault(tile.upColorTuple(), 0);
      tuplesCount.put(tile.upColorTuple(), ++totalUpColorTuple);

      int totalLeftColorTuple = tuplesCount.getOrDefault(tile.leftColorTuple(), 0);
      tuplesCount.put(tile.leftColorTuple(), ++totalLeftColorTuple);

      int totalRightColorTuple = tuplesCount.getOrDefault(tile.rightColorTuple(), 0);
      tuplesCount.put(tile.rightColorTuple(), ++totalRightColorTuple);

      int totalDownColorTuple = tuplesCount.getOrDefault(tile.downColorTuple(), 0);
      tuplesCount.put(tile.downColorTuple(), ++totalDownColorTuple);
    });
    return tuplesCount;
  }

  private Tile createTile(final int column, final int row) {
    checkArgument(column > 0,
      "Column must be greater than 0. Tip: The column of the first tile is 1, not 0.");

    checkArgument(row > 0,
      "Column must be greater than 0. Tip: The row of the first tile is 1, not 0.");

    Vector2 upLeft = upLeft(newVector(column, row));
    Vector2 upRight = upRight(newVector(column, row));
    Vector2 downLeft = downLeft(newVector(column, row));
    Vector2 downRight = downRight(newVector(column, row));

    Color upLeftColor = new Color(bufferedImage.getRGB(upLeft.x(), upLeft.y()));
    Color upRightColor = new Color(bufferedImage.getRGB(upRight.x(), upRight.y()));
    Color downLeftColor = new Color(bufferedImage.getRGB(downLeft.x(), downLeft.y()));
    Color downRightColor = new Color(bufferedImage.getRGB(downRight.x(), downRight.y()));

    return Tile.fromPosition(column, row, upLeftColor, upRightColor, downLeftColor, downRightColor);
  }

  private static Vector2 upLeft(final Vector2 vector) {
    int x = ((vector.x() - 1) * TILE_WIDTH_IN_PIXELS) + 1;
    int y = ((vector.y() - 1) * TILE_WIDTH_IN_PIXELS) + 1;
    return newVector(x, y);
  }

  private static Vector2 upRight(final Vector2 vector) {
    return upLeft(vector).addVector(DISTANCE_BETWEEN_COLOR_TILES, 0);
  }

  private static Vector2 downLeft(final Vector2 vector) {
    return upLeft(vector).addVector(0, DISTANCE_BETWEEN_COLOR_TILES);
  }

  private static Vector2 downRight(final Vector2 vector) {
    return downLeft(vector).addVector(DISTANCE_BETWEEN_COLOR_TILES, 0);
  }

}
