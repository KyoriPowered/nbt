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

import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * A tag.
 */
public abstract class Tag {
  /**
   * Reads the value of this tag from {@code input}.
   *
   * @param input the input
   * @param depth the depth
   * @throws IOException if an exception was encountered while reading
   */
  protected abstract void read(final DataInput input, final int depth) throws IOException;

  /**
   * Writes the value of this tag to {@code output}.
   *
   * @param output the output
   * @throws IOException if an exception was encountered while writing
   */
  protected abstract void write(final DataOutput output) throws IOException;

  /**
   * Gets the type of this tag.
   *
   * @return the type
   */
  public abstract @NonNull TagType type();

  /**
   * Creates a copy of this tag.
   *
   * @return a copy of this tag
   */
  public abstract @NonNull Tag copy();

  @Override
  public abstract int hashCode();

  @Override
  public abstract boolean equals(final Object that);
}
