package Lab5.task2and3;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptInputStream extends FilterInputStream {
    private final byte key;

    public DecryptInputStream(InputStream in, byte key) {
        super(in);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int data = super.read();
        return (data == -1) ? -1 : data - key;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int bytesRead = super.read(b, off, len);
        if (bytesRead == -1) return -1;
        for (int i = off; i < off + bytesRead; i++) {
            b[i] = (byte) ((b[i] - key) & 0xFF);
        }
        return bytesRead;
    }
}