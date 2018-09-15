package com.meli;

import com.google.common.collect.ImmutableList;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static com.meli.Vector2.newVector;
import static guru.nidi.graphviz.engine.Format.PNG;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

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

  public void createGraph() {
//    CycleDetector<String, DefaultEdge> cycleDetector;
//    Graph<String, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);
//    g.addEdge("b", "a");
//    g.addEdge("c", "b");

    try {

//graph("example1").
//      Graph g = graph("example1").directed()
//        .with(
//          node("A")
//            .link(node("B"))
//            .link(node("F"))
//      );

//      Map<Tuple<ColorBox>, List<Tile>> tupleListMap = tilesTuples();
//      tupleListMap.entrySet().stream().forEach(entry -> {
//            node(entry.getKey().toString())
//              .with(RED)
//              .link(entry.getValue().toString())
//          );
//      });

      //(X,U) - [X-C U-T, K-X U-U]
//      tupleListMap.get(new Tuple<ColorBox>());

//      Graphviz.fromGraph(g).width(200).render(PNG).toFile(new File("graph.png"));

    } catch (final Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int totalTiles() {
    return tiles.size();
  }

  public Map<ColorBox, Integer> countColors() {
    Map<ColorBox, Integer> colors = new HashMap();
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

  public Map<Tuple<ColorBox>, Integer> countDuplicateTuples() {
    return tilesTuples().entrySet().stream()
      .map(entry -> new SimpleEntry<>(entry.getKey(), entry.getValue().size()))
      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  public Map<Tuple<ColorBox>, List<Tile>> tilesTuples() {
    Map<Tuple<ColorBox>, List<Tile>> map = new HashMap();
    tiles.stream().forEach(tile -> {
      List<Tile> upTupleTile = map.getOrDefault(tile.upColorTuple(), new ArrayList());
      upTupleTile.add(tile);
      map.put(tile.upColorTuple(), upTupleTile);

      List<Tile> leftTupleTile = map.getOrDefault(tile.leftColorTuple(), new ArrayList());
      leftTupleTile.add(tile);
      map.put(tile.leftColorTuple(), leftTupleTile);

      List<Tile> rightTupleTile = map.getOrDefault(tile.rightColorTuple(), new ArrayList());
      rightTupleTile.add(tile);
      map.put(tile.rightColorTuple(), rightTupleTile);

      List<Tile> downTupleTile = map.getOrDefault(tile.downColorTuple(), new ArrayList());
      downTupleTile.add(tile);
      map.put(tile.downColorTuple(), downTupleTile);
    });
    return map;
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

  public List<Tile> tiles() {
    return Collections.unmodifiableList(tiles);
  }


}
