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
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

/**
 * A list tag.
 */
public final class ListTag extends Tag {

  /**
   * The list of tags.
   */
  private final List<Tag> tags = new ArrayList<>();
  /**
   * The type of this list.
   */
  @Nonnull private TagType type;

  public ListTag() {
    this(TagType.END);
  }

  public ListTag(@Nonnull final TagType type) {
    this.type = type;
  }

  /**
   * Gets the type of this list.
   *
   * @return the type
   */
  @Nonnull
  public TagType listType() {
    return this.type;
  }

  /**
   * Gets a tag.
   *
   * @param index the index
   * @return the tag
   * @throws IndexOutOfBoundsException if the index is out of range
   */
  @Nonnull
  public Tag get(final int index) {
    return this.tags.get(index);
  }

  /**
   * Adds a tag.
   *
   * @param tag the tag
   */
  public void add(@Nonnull final Tag tag) {
    // don't allow an end tag to be added
    if(tag.type() == TagType.END) {
      throw new IllegalArgumentException(String.format("Cannot add a '%s' to a '%s'", EndTag.class.getSimpleName(), ListTag.class.getSimpleName()));
    }
    // set the type if it has not yet been set
    if(this.type == TagType.END) {
      this.type = tag.type();
    }
    this.tags.add(tag);
  }

  /**
   * Sets the tag at the specified index.
   *
   * @param index the index
   * @param tag the tag
   * @throws IndexOutOfBoundsException if the index is out of range
   */
  public void set(final int index, @Nonnull final Tag tag) {
    // don't allow an end tag to be added
    if(tag.type() == TagType.END) {
      throw new IllegalArgumentException(String.format("Cannot add a '%s' to a '%s'", EndTag.class.getSimpleName(), ListTag.class.getSimpleName()));
    }
    // set the type if it has not yet been set
    if(this.type == TagType.END) {
      this.type = tag.type();
    }
    this.tags.set(index, tag);
  }

  /**
   * Removes a tag.
   *
   * @param index the index
   * @return the tag
   * @throws IndexOutOfBoundsException if the index is out of range
   */
  @Nonnull
  public Tag remove(final int index) {
    return this.tags.remove(index);
  }

  @Override
  protected void read(final DataInput input) throws IOException {
    this.type = TagType.of(input.readByte());

    final int length = input.readInt();
    for(int i = 0; i < length; i++) {
      final Tag tag = this.type.create();
      tag.read(input);
      this.tags.add(tag);
    }
  }

  @Override
  protected void write(final DataOutput output) throws IOException {
    output.writeByte(this.type.id());
    output.writeInt(this.tags.size());
    for(int i = 0, length = this.tags.size(); i < length; i++) {
      this.tags.get(i).write(output);
    }
  }

  @Nonnull
  @Override
  public TagType type() {
    return TagType.LIST;
  }

  @Override
  public int hashCode() {
    return this.tags.hashCode();
  }

  @Override
  public boolean equals(final Object that) {
    return this == that || (that instanceof ListTag && this.tags.equals(((ListTag) that).tags));
  }
}
