package com.meli;

public class TreeNode {

  private final Tile center;
  private TreeNode up;
  private TreeNode right;
  private TreeNode down;
  private TreeNode left;

  public TreeNode(final Tile center) {
    this.center = center;
  }

  public Tuple<ColorBox> leftTuple() {
    return center.leftTuple();
  }

  public Tuple<ColorBox> rightTuple() {
    return center.rightTuple();
  }

  public Tuple<ColorBox> upTuple() {
    return center.upTuple();
  }

  public Tuple<ColorBox> downTuple() {
    return center.downTuple();
  }

  public void add(final Tile tile) {
//    if(center.isUpComplementary(tile)) {
//      up = tile;
//    } else if(center.isRightComplementary(tile)) {
//      right = tile;
//    }
  }

  public boolean isLeftNodeEmpty() {
    return left == null;
  }

  public boolean isRightNodeEmpty() {
    return right == null;
  }

  public boolean isUpNodeEmpty() {
    return up == null;
  }

  public boolean isDownNodeEmpty() {
    return down == null;
  }

}
