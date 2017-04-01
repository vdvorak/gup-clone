package ua.com.itproekt.gup.service.blockchain;

import java.security.*;


/**
 * This class represents a SecureRandom which produces the same value
 */

public class FixedRand extends SecureRandom {
    MessageDigest sha;
    byte[]      state;

    FixedRand() {
        try {
            sha = MessageDigest.getInstance("SHA-256");
            state = sha.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Can't find SHA-256");
        }
    }

    @Override
    public void nextBytes(byte[] bytes) {
        int off = 0;
        sha.update(state);

        while (off < bytes.length) {
            state = sha.digest();
            if (bytes.length - off > state.length) {
                System.arraycopy(state, 0, bytes, off, state.length);
            } else {
                System.arraycopy(state, 0, bytes, off, bytes.length - off);
            }
            off += state.length;
            sha.update(state);
        }
    }
}
