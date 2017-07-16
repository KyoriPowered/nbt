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
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.annotation.Nonnull;

public final class TagIO {

  private TagIO() {
  }

  /**
   * Reads a compound tag from {@code path}.
   *
   * @param path the path
   * @return the compound tag
   * @throws IOException if an exception was encountered while reading a compound tag
   */
  @Nonnull
  public static CompoundTag read(@Nonnull final Path path) throws IOException {
    try(final DataInputStream dis = new DataInputStream(Files.newInputStream(path))) {
      return read(dis);
    }
  }

  /**
   * Reads a compound tag from {@code path} using GZIP decompression.
   *
   * @param path the path
   * @return the compound tag
   * @throws IOException if an exception was encountered while reading a compound tag
   */
  public static CompoundTag readCompressed(@Nonnull final Path path) throws IOException {
    try(final DataInputStream dis = new DataInputStream(new GZIPInputStream(Files.newInputStream(path)))) {
      return read(dis);
    }
  }

  /**
   * Reads a compound tag from {@code input}.
   *
   * @param input the input
   * @return the compound tag
   * @throws IOException if an exception was encountered while reading a compound tag
   */
  @Nonnull
  public static CompoundTag read(@Nonnull final DataInput input) throws IOException {
    final TagType type = TagType.of(input.readByte());
    if(type != TagType.COMPOUND) {
      throw new IOException(String.format("Expected root tag to be a %s, was %s", TagType.COMPOUND, type));
    }
    input.skipBytes(input.readUnsignedShort()); // read empty name
    final Tag tag = type.create();
    tag.read(input, 0); // initial depth is zero
    return (CompoundTag) tag;
  }

  /**
   * Writes a compound tag to {@code path}.
   *
   * @param tag the compound tag
   * @param path the path
   * @throws IOException if an exception was encountered while writing the compound tag
   */
  public static void write(@Nonnull final CompoundTag tag, @Nonnull final Path path) throws IOException {
    try(final DataOutputStream dos = new DataOutputStream(Files.newOutputStream(path))) {
      write(tag, dos);
    }
  }

  /**
   * Writes a compound tag to {@code path} using GZIP compression.
   *
   * @param tag the compound tag
   * @param path the path
   * @throws IOException if an exception was encountered while writing the compound tag
   */
  public static void writeCompressed(@Nonnull final CompoundTag tag, @Nonnull final Path path) throws IOException {
    try(final DataOutputStream dos = new DataOutputStream(new GZIPOutputStream(Files.newOutputStream(path)))) {
      write(tag, dos);
    }
  }

  /**
   * Writes a compound tag to {@code output}.
   *
   * @param tag the compound tag
   * @param output the output
   * @throws IOException if an exception was encountered while writing the compound tag
   */
  public static void write(@Nonnull final CompoundTag tag, @Nonnull final DataOutput output) throws IOException {
    output.writeByte(tag.type().id());
    if(tag.type() != TagType.END) {
      output.writeUTF(""); // write empty name
      tag.write(output);
    }
  }
}
