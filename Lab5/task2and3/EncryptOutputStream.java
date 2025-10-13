package Lab5.task2and3;

import java.io.*;

public class EncryptOutputStream extends FilterOutputStream {
    private final byte key;

    public EncryptOutputStream(OutputStream out, byte key) {
        super(out);
        this.key = key;
    }

    @Override
    public void write(int b) throws IOException {
        super.write(b + key);
    }
}