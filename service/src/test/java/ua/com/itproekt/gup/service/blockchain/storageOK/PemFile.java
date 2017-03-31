package ua.com.itproekt.gup.service.blockchain.storageOK;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;


public class PemFile {

    private PemObject pemObject;

    public PemFile() {
    }

    public PemFile(Key key, String description) {
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

    public PemObject read1(String filename)
            throws FileNotFoundException, IOException {
        PemReader pemReader = null;
        PemObject pemObject = null;
        try {
            pemReader = new PemReader(new InputStreamReader(new FileInputStream(filename)));
            pemObject = pemReader.readPemObject();
        } finally {
            pemReader.close();
        }
        return pemObject;
    }

    public String read2(String filename)
            throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return content;
    }
}
