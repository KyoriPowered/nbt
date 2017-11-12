/*
 * This file is part of nbt, licensed under the MIT License.
 *
 * Copyright (c) 2017 KyoriPowered
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.kyori.nbt;

import net.kyori.blizzard.NonNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * A tag representing a {@code double}.
 */
public final class DoubleTag extends NumberTag {
  /**
   * The double value.
   */
  private double value;

  DoubleTag() {
  }

  public DoubleTag(final double value) {
    this.value = value;
  }

  @Override
  public byte byteValue() {
    return (byte) (floor(this.value) & 0xff);
  }

  @Override
  public double doubleValue() {
    return this.value;
  }

  @Override
  public float floatValue() {
    return (float) this.value;
  }

  @Override
  public int intValue() {
    return floor(this.value);
  }

  @Override
  public long longValue() {
    return (long) Math.floor(this.value);
  }

  @Override
  public short shortValue() {
    return (short) (floor(this.value) & 0xffff);
  }

  @Override
  protected void read(final DataInput input, final int depth) throws IOException {
    this.value = input.readDouble();
  }

  @Override
  protected void write(final DataOutput output) throws IOException {
    output.writeDouble(this.value);
  }

  @NonNull
  @Override
  public TagType type() {
    return TagType.DOUBLE;
  }

  @Override
  public DoubleTag copy() {
    return new DoubleTag(this.value);
  }

  @Override
  public int hashCode() {
    return Double.hashCode(this.value);
  }

  @Override
  public boolean equals(final Object that) {
    return this == that || (that instanceof DoubleTag && this.value == ((DoubleTag) that).value);
  }
}
