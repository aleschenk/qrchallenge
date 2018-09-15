package com.meli;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ColorBox {

  private final Color color;

  private final String label;

  public ColorBox(final Color color) {
    this.color = color;
    this.label = labels.get(color);
  }

  public Color color() {
    return color;
  }

  public Color color(final boolean blackToWhite) {
    return color.equals(Color.BLACK) ? Color.WHITE : color;
  }

  public String label() {
    return label;
  }

  public static ColorBox from(final String label) {
    return new ColorBox(colorLabelMap.inverse().get(label));
  }

  @Override
  public String toString() {
    return label;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ColorBox colorBox = (ColorBox) o;
    return Objects.equals(color, colorBox.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(color);
  }

  private static final Map<Color, String> labels = new HashMap();

  private static final BiMap<Color, String> colorLabelMap;

  static {
    labels.put(new Color(0, 0, 0), "A");
    labels.put(new Color(255, 255, 255), "B");
    labels.put(new Color(249, 66, 58), "C");
    labels.put(new Color(154, 198, 94), "D");
    labels.put(new Color(110, 160, 106), "E");
    labels.put(new Color(244, 151, 51), "F");
    labels.put(new Color(251, 249, 124), "G");
    labels.put(new Color(86, 47, 17), "H");
    labels.put(new Color(169, 163, 161), "I");
    labels.put(new Color(155, 55, 147), "J");
    labels.put(new Color(249, 79, 49), "K");
    labels.put(new Color(247, 71, 61), "L");
    labels.put(new Color(46, 132, 224), "M");
    labels.put(new Color(54, 89, 120), "N");
    labels.put(new Color(60, 224, 46), "Ñ");
    labels.put(new Color(167, 179, 115), "O");
    labels.put(new Color(151, 33, 67), "P");
    labels.put(new Color(94, 196, 198), "Q");
    labels.put(new Color(63, 224, 228), "R");
    labels.put(new Color(221, 133, 193), "S");
    labels.put(new Color(255, 221, 33), "T");
    labels.put(new Color(245, 198, 234), "U");
    labels.put(new Color(83, 96, 52), "V");
    labels.put(new Color(247, 111, 133), "W");
    labels.put(new Color(233, 123, 80), "X");
    labels.put(new Color(0, 181, 186), "Y");
    labels.put(new Color(236, 249, 19), "Z");
    colorLabelMap = ImmutableBiMap.copyOf(labels);
  }

}
