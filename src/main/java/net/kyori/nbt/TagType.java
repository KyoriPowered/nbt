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

import java.util.function.Supplier;

import javax.annotation.Nonnull;

/**
 * An enumeration of tag types.
 */
public enum TagType {
  /**
   * @see EndTag
   */
  END((byte) 0, EndTag::new),
  /**
   * @see ByteTag
   */
  BYTE((byte) 1, ByteTag::new),
  /**
   * @see ShortTag
   */
  SHORT((byte) 2, ShortTag::new),
  /**
   * @see IntTag
   */
  INT((byte) 3, IntTag::new),
  /**
   * @see LongTag
   */
  LONG((byte) 4, LongTag::new),
  /**
   * @see FloatTag
   */
  FLOAT((byte) 5, FloatTag::new),
  /**
   * @see DoubleTag
   */
  DOUBLE((byte) 6, DoubleTag::new),
  /**
   * @see ByteArrayTag
   */
  BYTE_ARRAY((byte) 7, ByteArrayTag::new),
  /**
   * @see StringTag
   */
  STRING((byte) 8, StringTag::new),
  /**
   * @see ListTag
   */
  LIST((byte) 9, ListTag::new),
  /**
   * @see CompoundTag
   */
  COMPOUND((byte) 10, CompoundTag::new),
  /**
   * @see IntArrayTag
   */
  INT_ARRAY((byte) 11, IntArrayTag::new),
  /**
   * @see LongArrayTag
   */
  LONG_ARRAY((byte) 12, LongArrayTag::new);

  private static final TagType[] TYPES = values();
  /**
   * The byte id of this tag type.
   */
  private final byte id;
  /**
   * The tag factory.
   */
  @Nonnull private final Supplier<Tag> factory;

  TagType(final byte id, @Nonnull final Supplier<Tag> factory) {
    this.id = id;
    this.factory = factory;
  }

  /**
   * Gets the byte id of this tag type.
   *
   * @return the byte id
   */
  byte id() {
    return this.id;
  }

  /**
   * Creates a new tag.
   *
   * @return a new tag
   */
  @Nonnull
  Tag create() {
    return this.factory.get();
  }

  @Nonnull
  static TagType of(final byte id) {
    return TYPES[id];
  }
}
