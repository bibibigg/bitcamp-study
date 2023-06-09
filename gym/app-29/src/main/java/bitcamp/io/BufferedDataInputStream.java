package bitcamp.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BufferedDataInputStream extends FileInputStream {

  byte[] buf = new byte[8192];
  int size;
  int cursor;

  public BufferedDataInputStream(String name) throws FileNotFoundException {
    super(name);
  }

  @Override
  public int read() throws IOException {
    if (size == -1) {
      return -1;
    }

    if (cursor == size) {
      if ((size = super.read(buf)) == -1) {
        return -1;
      }
      cursor = 0;
    }

    return buf[cursor++] & 0x000000ff;
  }

  @Override
  public int read(byte[] arr) throws IOException {
    for (int i = 0; i < arr.length; i++) {
      int b = this.read();
      if (b == -1) {
        return i;
      }
      arr[i] = (byte) b;
    }
    return arr.length;
  }

  public short readShort() throws IOException {
    return (short) (this.read() << 8 | this.read());
  }

  public int readInt() throws IOException {
    return this.read() << 24 | this.read() << 16 | this.read() << 8 | this.read();
  }

  public String readUTF() throws IOException {
    int length = this.read() << 8 | this.read();
    byte[] buf = new byte[length];
    this.read(buf);
    return new String(buf, "UTF-8");
  }

  public long readLong() throws IOException {
    return (long) this.read() << 54 | (long) this.read() << 48 | (long) this.read() << 40
        | (long) this.read() << 32 | (long) this.read() << 24 | (long) this.read() << 16
        | (long) this.read() << 8 | this.read();
  }
}
