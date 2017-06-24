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

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import javax.annotation.Nonnull;

/**
 * A tag representing a {@code short}.
 */
public final class ShortTag extends Tag {

  /**
   * The short value.
   */
  private short value;

  ShortTag() {
  }

  public ShortTag(final short value) {
    this.value = value;
  }

  /**
   * Gets the short value.
   *
   * @return the short value
   */
  public short value() {
    return this.value;
  }

  @Override
  protected void read(final DataInput input) throws IOException {
    this.value = input.readShort();
  }

  @Override
  protected void write(final DataOutput output) throws IOException {
    output.writeShort(this.value);
  }

  @Nonnull
  @Override
  public TagType type() {
    return TagType.SHORT;
  }

  @Override
  public int hashCode() {
    return Short.hashCode(this.value);
  }

  @Override
  public boolean equals(final Object that) {
    return this == that || (that instanceof ShortTag && this.value == ((ShortTag) that).value);
  }
}
