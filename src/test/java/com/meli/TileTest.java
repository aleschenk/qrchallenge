package com.meli;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TileTest {

  @Test
  public void test() {
    Tile abcd = Tile.fromPosition(0, 0, "ABCD");
    Tile xxab = Tile.fromPosition(0, 0, "XXAB");

    assertThat(abcd.isComplementary(xxab), is(true));
  }

}
