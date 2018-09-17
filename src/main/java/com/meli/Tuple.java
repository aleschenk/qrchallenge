package com.meli;

import java.util.Objects;

public class Tuple<T> {
  private final T a;
  private final T b;

  public Tuple(final T a, final T b) {
    this.a = a;
    this.b = b;
  }

  @Override
  public String toString() {
    return "(" + a + "," + b + ')';
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tuple<?> tuple = (Tuple<?>) o;
    return Objects.equals(a, tuple.a) &&
      Objects.equals(b, tuple.b);
  }

  @Override
  public int hashCode() {
    return Objects.hash(a, b);
  }

  public Tuple<T> inverse() {
    return new Tuple(b, a);
  }

}
