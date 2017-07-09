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

/**
 * A number tag.
 */
public abstract class NumberTag extends Tag {

  /**
   * Gets the byte value.
   *
   * @return the byte value
   */
  public abstract byte byteValue();

  /**
   * Gets the double value.
   *
   * @return the double value
   */
  public abstract double doubleValue();

  /**
   * Gets the float value.
   *
   * @return the float value
   */
  public abstract float floatValue();

  /**
   * Gets the int value.
   *
   * @return the int value
   */
  public abstract int intValue();

  /**
   * Gets the long value.
   *
   * @return the long value
   */
  public abstract long longValue();

  /**
   * Gets the short value.
   *
   * @return the short value
   */
  public abstract short shortValue();

  // rather than depending on math as a whole, we'll just copy these two methods over
  // https://github.com/KyoriPowered/math/blob/master/src/main/java/net/kyori/math/Mth.java
  static int floor(final double n) {
    final int i = (int) n;
    return n < (double) i ? i - 1 : i;
  }

  static int floor(final float n) {
    final int i = (int) n;
    return n < (float) i ? i - 1 : i;
  }
}