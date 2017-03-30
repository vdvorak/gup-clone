package ua.com.itproekt.gup.util;

import org.bouncycastle.util.io.pem.*;

import java.io.*;
import java.nio.file.*;
import java.security.Key;


public class FileKey {

    private PemObject pemObject;

    public FileKey() {
    }

    public FileKey(java.security.Key key, String description) {
        pemObject = new PemObject(description, key.getEncoded());
    }

    public void write(String filename)
            throws FileNotFoundException, IOException {
        PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(filename)));
        try {
            pemWriter.writeObject(pemObject);
        } finally {
            pemWriter.close();
        }
    }

    public String read(String filename)
            throws FileNotFoundException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return content;
    }
}
