package com.meli;

import java.util.Objects;

public class Vector2 {
  private final int x;
  private final int y;

  private Vector2(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  public static Vector2 newVector(final int x, final int y) {
    return new Vector2(x, y);
  }

  public Vector2 addVector(final int x, final int y) {
    return new Vector2(this.x + x, this.y + y);
  }

  public int x() {
    return x;
  }

  public int y() {
    return y;
  }

  @Override
  public String toString() {
    return "(" + x + "," + y + ")";
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Vector2 vector2 = (Vector2) o;
    return x == vector2.x &&
      y == vector2.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

}
