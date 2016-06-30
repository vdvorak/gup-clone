//package ua.com.itproekt.gup.bank_api;
//
//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.ByteArrayOutputStream;
//import java.security.InvalidKeyException;
//import java.security.Key;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//
//
//public class SecurityService {
//
//    private static String algorithm = "DES";
//    private static Key key = null;
//    private static Cipher cipher = null;
//    private static HashSet<String> onlineUsers = new HashSet<String>();
//
//
//    static {
//        try {
//            byte[] encoded = new byte[]{-89, -39, 58, 84, -75, -27, -80, 46,}; //Key data
//            key = new SecretKeySpec(encoded, "DES");
//            cipher = Cipher.getInstance(algorithm);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static HashSet<String> getOnlineUsers() {
//        return onlineUsers;
//    }
//
//    public static void setOnlineUsers(HashSet<String> onlineUsers) {
//        SecurityService.onlineUsers = onlineUsers;
//    }
//
//    public static byte[] encrypt(String input) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//
//        cipher.init(Cipher.ENCRYPT_MODE, key);
//        byte[] inputBytes = input.getBytes();
//        return cipher.doFinal(inputBytes);
//
//    }
//
//    public static String decrypt(byte[] encryptionBytes) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//
//        cipher.init(Cipher.DECRYPT_MODE, key);
//        byte[] recoveredBytes = cipher.doFinal(encryptionBytes);
//        String recovered = new String(recoveredBytes);
//        return recovered;
//
//    }
//
//    public static String getRandomPassword() {
//
//
//        Map<Integer, Character> dig = new HashMap<Integer, Character>();
//        Map<Integer, Character> bigCh = new HashMap<Integer, Character>();
//        Map<Integer, Character> lowCh = new HashMap<Integer, Character>();
//        ByteArrayOutputStream br = new ByteArrayOutputStream();
//
//        int digCount = 0;
//        int lowChCount = 0;
//        int bigChCount = 0;
//
//        for (int i = 0; i < 10; i++) {
//            char data = (char) ('0' + i);
//            dig.put(i, data);
//        }
//
//        for (int i = 0; i < 26; i++) {
//
//            char low = (char) ('a' + i);
//            char big = (char) ('A' + i);
//            bigCh.put(i, big);
//            lowCh.put(i, low);
//        }
//
//        while (true) {
//
//            for (int i = 0; i < 8; i++) {
//                int random = (int) (Math.random() * 3);
//
//
//                if (i > 5) switch (random) {
//                    case 0:
//                        if (digCount == 0) random = 0;
//                        if (lowChCount == 0) random = 1;
//                        if (bigChCount == 0) random = 2;
//                        break;
//                    case 1:
//                        if (digCount == 0) random = 0;
//                        if (bigChCount == 0) random = 2;
//                        if (lowChCount == 0) random = 1;
//                        break;
//                    case 2:
//                        if (bigChCount == 0) random = 2;
//                        if (lowChCount == 0) random = 1;
//                        if (digCount == 0) random = 0;
//                        break;
//                }
//
//                switch (random) {
//
//                    case 0: {
//                        int localRandom = (int) (Math.random() * 10);
//                        br.write(dig.get(localRandom));
//                        digCount++;
//                        break;
//                    }
//                    case 1: {
//                        int localRandom = (int) (Math.random() * 26);
//                        br.write(lowCh.get(localRandom));
//                        lowChCount++;
//                        break;
//                    }
//                    case 2: {
//                        int localRandom = (int) (Math.random() * 26);
//                        br.write(bigCh.get(localRandom));
//                        bigChCount++;
//                        break;
//                    }
//                }
//
//            }
//            break;
//
//        }
//
//        return br.toString();
//
//    }
//
//    public static String sha1(String input) throws NoSuchAlgorithmException {
//        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
//        byte[] result = mDigest.digest(input.getBytes());
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < result.length; i++) {
//            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
//        }
//        return sb.toString();
//    }
//
//
//
//}