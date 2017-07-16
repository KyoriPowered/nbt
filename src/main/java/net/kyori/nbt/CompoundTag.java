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
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * A compound tag.
 */
public final class CompoundTag extends Tag {

  /**
   * The maximum depth.
   */
  public static final int MAX_DEPTH = 512;
  /**
   * The map of tags.
   */
  private final Map<String, Tag> tags = new HashMap<>();

  /**
   * Gets a tag by its key.
   *
   * @param key the key
   * @return the tag, or {@code null}
   */
  @Nullable
  public Tag get(@Nonnull final String key) {
    return this.tags.get(key);
  }

  /**
   * Inserts a tag.
   *
   * @param key the key
   * @param tag the tag
   */
  public void put(@Nonnull final String key, @Nonnull final Tag tag) {
    this.tags.put(key, tag);
  }

  /**
   * Removes a tag.
   *
   * @param key the key
   */
  public void remove(@Nonnull final String key) {
    this.tags.remove(key);
  }

  /**
   * Checks if this compound has a tag with the specified key.
   *
   * @param key the key
   * @return {@code true} if this compound has a tag with the specified key
   */
  public boolean contains(@Nonnull final String key) {
    return this.tags.containsKey(key);
  }

  /**
   * Checks if this compound has a tag with the specified key and type.
   *
   * @param key the key
   * @param type the type
   * @return {@code true} if this compound has a tag with the specified key and type
   */
  public boolean contains(@Nonnull final String key, @Nonnull final TagType type) {
    @Nullable final Tag tag = this.tags.get(key);
    return tag != null && (tag.type() == type || (type.number() && tag.type().number()));
  }

  /**
   * Gets a byte.
   *
   * @param key the key
   * @return the byte value, or {@code 0} if this compound does not contain a byte tag
   *     with the specified key, or has a tag with a different type
   */
  public byte getByte(@Nonnull final String key) {
    return this.getByte(key, (byte) 0);
  }

  /**
   * Gets a byte.
   *
   * @param key the key
   * @param defaultValue the default value
   * @return the byte value, or {@code defaultValue} if this compound does not contain a byte tag
   *     with the specified key, or has a tag with a different type
   */
  public byte getByte(@Nonnull final String key, final byte defaultValue) {
    if(this.contains(key, TagType.BYTE)) {
      return ((ByteTag) this.tags.get(key)).byteValue();
    }
    return defaultValue;
  }

  /**
   * Inserts a byte.
   *
   * @param key the key
   * @param value the value
   */
  public void putByte(@Nonnull final String key, final byte value) {
    this.tags.put(key, new ByteTag(value));
  }

  /**
   * Gets a short.
   *
   * @param key the key
   * @return the short value, or {@code 0} if this compound does not contain a short tag
   *     with the specified key, or has a tag with a different type
   */
  public short getShort(@Nonnull final String key) {
    return this.getShort(key, (short) 0);
  }

  /**
   * Gets a short.
   *
   * @param key the key
   * @param defaultValue the default value
   * @return the short value, or {@code defaultValue} if this compound does not contain a short tag
   *     with the specified key, or has a tag with a different type
   */
  public short getShort(@Nonnull final String key, final short defaultValue) {
    if(this.contains(key, TagType.SHORT)) {
      return ((ShortTag) this.tags.get(key)).shortValue();
    }
    return defaultValue;
  }

  /**
   * Inserts a short.
   *
   * @param key the key
   * @param value the value
   */
  public void putShort(@Nonnull final String key, final short value) {
    this.tags.put(key, new ShortTag(value));
  }

  /**
   * Gets an int.
   *
   * @param key the key
   * @return the int value, or {@code 0} if this compound does not contain an int tag
   *     with the specified key, or has a tag with a different type
   */
  public int getInt(@Nonnull final String key) {
    return this.getInt(key, 0);
  }

  /**
   * Gets an int.
   *
   * @param key the key
   * @param defaultValue the default value
   * @return the int value, or {@code defaultValue} if this compound does not contain an int tag
   *     with the specified key, or has a tag with a different type
   */
  public int getInt(@Nonnull final String key, final int defaultValue) {
    if(this.contains(key, TagType.INT)) {
      return ((IntTag) this.tags.get(key)).intValue();
    }
    return defaultValue;
  }

  /**
   * Inserts an int.
   *
   * @param key the key
   * @param value the value
   */
  public void putInt(@Nonnull final String key, final int value) {
    this.tags.put(key, new IntTag(value));
  }

  /**
   * Gets a long.
   *
   * @param key the key
   * @return the long value, or {@code 0} if this compound does not contain a long tag
   *     with the specified key, or has a tag with a different type
   */
  public long getLong(@Nonnull final String key) {
    return this.getLong(key, 0L);
  }

  /**
   * Gets a long.
   *
   * @param key the key
   * @param defaultValue the default value
   * @return the long value, or {@code defaultValue} if this compound does not contain a long tag
   *     with the specified key, or has a tag with a different type
   */
  public long getLong(@Nonnull final String key, final long defaultValue) {
    if(this.contains(key, TagType.LONG)) {
      return ((LongTag) this.tags.get(key)).longValue();
    }
    return defaultValue;
  }

  /**
   * Inserts a long.
   *
   * @param key the key
   * @param value the value
   */
  public void putLong(@Nonnull final String key, final long value) {
    this.tags.put(key, new LongTag(value));
  }

  /**
   * Gets a float.
   *
   * @param key the key
   * @return the float value, or {@code 0} if this compound does not contain a float tag
   *     with the specified key, or has a tag with a different type
   */
  public float getFloat(@Nonnull final String key) {
    return this.getFloat(key, 0f);
  }

  /**
   * Gets a float.
   *
   * @param key the key
   * @param defaultValue the default value
   * @return the float value, or {@code defaultValue} if this compound does not contain a float tag
   *     with the specified key, or has a tag with a different type
   */
  public float getFloat(@Nonnull final String key, final float defaultValue) {
    if(this.contains(key, TagType.FLOAT)) {
      return ((FloatTag) this.tags.get(key)).floatValue();
    }
    return defaultValue;
  }

  /**
   * Inserts a float.
   *
   * @param key the key
   * @param value the value
   */
  public void putFloat(@Nonnull final String key, final float value) {
    this.tags.put(key, new FloatTag(value));
  }

  /**
   * Gets a double.
   *
   * @param key the key
   * @return the double value, or {@code 0} if this compound does not contain a double tag
   *     with the specified key, or has a tag with a different type
   */
  public double getDouble(@Nonnull final String key) {
    return this.getDouble(key, 0d);
  }

  /**
   * Gets a double.
   *
   * @param key the key
   * @param defaultValue the default value
   * @return the double value, or {@code defaultValue} if this compound does not contain a double tag
   *     with the specified key, or has a tag with a different type
   */
  public double getDouble(@Nonnull final String key, final double defaultValue) {
    if(this.contains(key, TagType.DOUBLE)) {
      return ((DoubleTag) this.tags.get(key)).doubleValue();
    }
    return defaultValue;
  }

  /**
   * Inserts a double.
   *
   * @param key the key
   * @param value the value
   */
  public void putDouble(@Nonnull final String key, final double value) {
    this.tags.put(key, new DoubleTag(value));
  }

  /**
   * Gets an array of bytes.
   *
   * @param key the key
   * @return the array of bytes, or a zero-length array if this compound does not contain a byte array tag
   *     with the specified key, or has a tag with a different type
   */
  @Nonnull
  public byte[] getByteArray(@Nonnull final String key) {
    if(this.contains(key, TagType.BYTE_ARRAY)) {
      return ((ByteArrayTag) this.tags.get(key)).value();
    }
    return new byte[0];
  }

  /**
   * Gets an array of bytes.
   *
   * @param key the key
   * @param defaultValue the default value
   * @return the array of bytes, or {@code defaultValue}
   */
  @Nonnull
  public byte[] getByteArray(@Nonnull final String key, @Nonnull final byte[] defaultValue) {
    if(this.contains(key, TagType.BYTE_ARRAY)) {
      return ((ByteArrayTag) this.tags.get(key)).value();
    }
    return defaultValue;
  }

  /**
   * Inserts an array of bytes.
   *
   * @param key the key
   * @param value the value
   */
  public void putByteArray(@Nonnull final String key, @Nonnull final byte[] value) {
    this.tags.put(key, new ByteArrayTag(value));
  }

  /**
   * Gets a string.
   *
   * @param key the key
   * @return the string value, or {@code ""} if this compound does not contain a string tag
   *     with the specified key, or has a tag with a different type
   */
  @Nonnull
  public String getString(@Nonnull final String key) {
    return this.getString(key, "");
  }

  /**
   * Gets a string.
   *
   * @param key the key
   * @param defaultValue the default value
   * @return the string value, or {@code defaultValue} if this compound does not contain a string tag
   *     with the specified key, or has a tag with a different type
   */
  @Nonnull
  public String getString(@Nonnull final String key, @Nonnull final String defaultValue) {
    if(this.contains(key, TagType.STRING)) {
      return ((StringTag) this.tags.get(key)).value();
    }
    return defaultValue;
  }

  /**
   * Inserts a string.
   *
   * @param key the key
   * @param value the value
   */
  public void putString(@Nonnull final String key, @Nonnull final String value) {
    this.tags.put(key, new StringTag(value));
  }

  /**
   * Gets a list.
   *
   * @param key the key
   * @return the list, or a new list if this compound does not contain a list tag
   *     with the specified key, or has a tag with a different type
   */
  @Nonnull
  public ListTag getList(@Nonnull final String key) {
    if(this.contains(key, TagType.LIST)) {
      return (ListTag) this.tags.get(key);
    }
    return new ListTag();
  }

  /**
   * Gets a list.
   *
   * @param key the key
   * @param defaultValue the default value
   * @return the list, or {@code defaultValue} if this compound does not contain a list tag
   *     with the specified key, or has a tag with a different type
   */
  @Nonnull
  public ListTag getList(@Nonnull final String key, @Nonnull final ListTag defaultValue) {
    if(this.contains(key, TagType.LIST)) {
      return (ListTag) this.tags.get(key);
    }
    return defaultValue;
  }

  /**
   * Gets a compound.
   *
   * @param key the key
   * @return the compound, or a new compound if this compound does not contain a compound tag
   *     with the specified key, or has a tag with a different type
   */
  @Nonnull
  public CompoundTag getCompound(@Nonnull final String key) {
    if(this.contains(key, TagType.COMPOUND)) {
      return (CompoundTag) this.tags.get(key);
    }
    return new CompoundTag();
  }

  /**
   * Gets a compound.
   *
   * @param key the key
   * @param defaultValue the default value
   * @return the compound, or {@code defaultValue} if this compound does not contain a compound tag
   *     with the specified key, or has a tag with a different type
   */
  @Nonnull
  public CompoundTag getCompound(@Nonnull final String key, @Nonnull final CompoundTag defaultValue) {
    if(this.contains(key, TagType.COMPOUND)) {
      return (CompoundTag) this.tags.get(key);
    }
    return defaultValue;
  }

  /**
   * Gets an array of ints.
   *
   * @param key the key
   * @return the array of ints, or a zero-length array if this compound does not contain a int array tag
   *     with the specified key, or has a tag with a different type
   */
  @Nonnull
  public int[] getIntArray(@Nonnull final String key) {
    if(this.contains(key, TagType.BYTE_ARRAY)) {
      return ((IntArrayTag) this.tags.get(key)).value();
    }
    return new int[0];
  }

  /**
   * Gets an array of ints.
   *
   * @param key the key
   * @param defaultValue the default value
   * @return the array of ints, or {@code defaultValue}
   */
  @Nonnull
  public int[] getIntArray(@Nonnull final String key, @Nonnull final int[] defaultValue) {
    if(this.contains(key, TagType.BYTE_ARRAY)) {
      return ((IntArrayTag) this.tags.get(key)).value();
    }
    return defaultValue;
  }

  /**
   * Inserts an array of ints.
   *
   * @param key the key
   * @param value the value
   */
  public void putIntArray(@Nonnull final String key, @Nonnull final int[] value) {
    this.tags.put(key, new IntArrayTag(value));
  }

  /**
   * Gets an array of longs.
   *
   * @param key the key
   * @return the array of longs, or a zero-length array if this compound does not contain a long array tag
   *     with the specified key, or has a tag with a different type
   */
  @Nonnull
  public long[] getLongArray(@Nonnull final String key) {
    if(this.contains(key, TagType.BYTE_ARRAY)) {
      return ((LongArrayTag) this.tags.get(key)).value();
    }
    return new long[0];
  }

  /**
   * Gets an array of longs.
   *
   * @param key the key
   * @param defaultValue the default value
   * @return the array of longs, or {@code defaultValue}
   */
  @Nonnull
  public long[] getLongArray(@Nonnull final String key, @Nonnull final long[] defaultValue) {
    if(this.contains(key, TagType.BYTE_ARRAY)) {
      return ((LongArrayTag) this.tags.get(key)).value();
    }
    return defaultValue;
  }

  /**
   * Inserts an array of longs.
   *
   * @param key the key
   * @param value the value
   */
  public void putLongArray(@Nonnull final String key, @Nonnull final long[] value) {
    this.tags.put(key, new LongArrayTag(value));
  }

  @Override
  protected void read(final DataInput input, final int depth) throws IOException {
    if(depth > MAX_DEPTH) {
      throw new IllegalStateException(String.format("Depth of %d is higher than max of %d", depth, MAX_DEPTH));
    }

    TagType type;
    while((type = TagType.of(input.readByte())) != TagType.END) {
      final String key = input.readUTF();
      final Tag tag = type.create();
      tag.read(input, depth + 1);
      this.tags.put(key, tag);
    }
  }

  @Override
  protected void write(final DataOutput output) throws IOException {
    for(final String key : this.tags.keySet()) {
      final Tag tag = this.tags.get(key);
      output.writeByte(tag.type().id());
      if(tag.type() != TagType.END) {
        output.writeUTF(key);
        tag.write(output);
      }
    }
    output.writeByte(TagType.END.id());
  }

  @Nonnull
  @Override
  public TagType type() {
    return TagType.COMPOUND;
  }

  @Override
  public int hashCode() {
    return this.tags.hashCode();
  }

  @Override
  public boolean equals(final Object that) {
    return this == that || (that instanceof CompoundTag && this.tags.equals(((CompoundTag) that).tags));
  }
}
