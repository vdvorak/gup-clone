package ua.com.itproekt.gup.service.blockchain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

import sun.misc.BASE64Encoder;


/**
 * http://stackoverflow.com/questions/7224626/how-to-sign-string-with-private-key
 */

public class MainClass {

    public static void main(String[] args) throws Exception {
        String      TEST = "Bla-Bla-Bla";           // Есть пользовательские данные которые нужно зашифровать, передать и проверить...

        String      HMAC = "SHA256WithRSA"; //"SHA1WithRSA";           // 1. Указываем тип шифрования (формат: SHA-1)
        KeyPair     KEYS = keysGenerator();         // 2. Создаем пользовательские ключи для шифрования...


        /////////////////////////////////////////////////// Профиль (0)
        byte[] SIGNATURE, DATA;


        System.out.println();
        /////////////////////////////////////////////////// Клиент (сторона-1)
        DATA = TEST.getBytes("UTF8");               // 3. Переводим пользовательские данные в байт-код

        Signature  signature1 = Signature.getInstance(HMAC);
        signature1.initSign( KEYS.getPrivate() );   // 4. Создаем подпись-владельца на основе клиентского приватного ключа
        signature1.update(DATA);

        SIGNATURE = signature1.sign();              // 5. Подписываем эти данные-владельца...
        System.out.println( "Data: " + new BASE64Encoder().encode( DATA ) );
        System.out.println( "PRIVATE-KEY: " + KEYS.getPrivate() );
        System.out.println( "Singature: " + new BASE64Encoder().encode( SIGNATURE ) );


        System.out.println();
        /////////////////////////////////////////////////// Клиент (сторона-2)
        Signature  signature2 = Signature.getInstance(HMAC);
        signature2.initVerify( KEYS.getPublic() );  // 6. Расшифровываем подпись-владельца на основе клиентского публичного ключа
        signature2.update(DATA);

        System.out.println( "Data: " + new BASE64Encoder().encode( DATA ) );
        System.out.println( "PUBLIC-KEY: " + KEYS.getPublic() );
        System.out.println( "Verify by Singature: " + signature2.verify( SIGNATURE ) );
    }

    protected static KeyPair keysGenerator()
            throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.genKeyPair();
    }

}
