//package org.bitcoinj.gup_favorit2.bbb;
//
//import java.io.FileInputStream;
//import java.security.Key;
//import java.security.KeyStore;
//import java.security.PrivateKey;
//import java.security.Security;
//import java.security.Signature;
//import java.security.cert.X509Certificate;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Iterator;
//import java.util.List;
//
//import org.bouncycastle.cert.X509CertificateHolder;
//import org.bouncycastle.cert.jcajce.JcaCertStore;
//import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
//import org.bouncycastle.cms.CMSProcessableByteArray;
//import org.bouncycastle.cms.CMSSignedData;
//import org.bouncycastle.cms.CMSSignedDataGenerator;
//import org.bouncycastle.cms.CMSTypedData;
//import org.bouncycastle.cms.SignerInformation;
//import org.bouncycastle.cms.SignerInformationStore;
//import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
//import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import org.bouncycastle.operator.ContentSigner;
//import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
//import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
//import org.bouncycastle.util.Store;
//
//
///**
// * @see http://stackoverflow.com/questions/24451744/verify-a-signature-with-bouncy-castle
// */
//
//public class VerifySignature {
//
//    static final String KEYSTORE_FILE = "keys/certificates.p12";
//    static final String KEYSTORE_INSTANCE = "PKCS12";
//    static final String KEYSTORE_PWD = "test";
//    static final String KEYSTORE_ALIAS = "Key1";
//
//    static final String DIGEST_SHA1 = "SHA1withRSA";
//    static final String BC_PROVIDER = "BC";
//
//    @SuppressWarnings({ "rawtypes", "unchecked" })
//    public static void main(String[] args) throws Exception {
//
//        // Content to be signed
//        String text = "My name is Oscar";
//
//        Security.addProvider(new BouncyCastleProvider());
//
//        // Get keystore
//        KeyStore ks = KeyStore.getInstance(KEYSTORE_INSTANCE);
//        ks.load(new FileInputStream(KEYSTORE_FILE), KEYSTORE_PWD.toCharArray());
//        Key key = ks.getKey(KEYSTORE_ALIAS, KEYSTORE_PWD.toCharArray());
//
//        // Get private key and sign
//        PrivateKey privKey = (PrivateKey) key;
//        Signature signature = Signature.getInstance(DIGEST_SHA1, BC_PROVIDER);
//        signature.initSign(privKey);
//        signature.update(text.getBytes());
//
//        // Build CMS
//        X509Certificate certFromKeystore = (X509Certificate) ks.getCertificate(KEYSTORE_ALIAS);
//        List certList = new ArrayList();
//        CMSTypedData data = new CMSProcessableByteArray(signature.sign());
//        certList.add(certFromKeystore);
//        Store certs = new JcaCertStore(certList);
//        CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
//        ContentSigner sha1Signer = new JcaContentSignerBuilder(DIGEST_SHA1).setProvider(BC_PROVIDER).build(privKey);
//        gen.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().setProvider(BC_PROVIDER).build()).build(sha1Signer, certFromKeystore));
//        gen.addCertificates(certs);
//        CMSSignedData signedData = gen.generate(data, true);
//
//        // Verify signature
//        Store store = signedData.getCertificates();
//        SignerInformationStore signers = signedData.getSignerInfos();
//        Collection c = signers.getSigners();
//        Iterator it = c.iterator();
//        while (it.hasNext()) {
//            SignerInformation signer = (SignerInformation) it.next();
//            Collection certCollection = store.getMatches(signer.getSID());
//            Iterator certIt = certCollection.iterator();
//            X509CertificateHolder certHolder = (X509CertificateHolder) certIt.next();
//            X509Certificate certFromSignedData = new JcaX509CertificateConverter().setProvider(BC_PROVIDER).getCertificate(certHolder);
//            if (signer.verify(new JcaSimpleSignerInfoVerifierBuilder().setProvider(BC_PROVIDER).build(certFromSignedData))) {
//                System.out.println("Signature verified");
//            } else {
//                System.out.println("Signature verification failed");
//            }
//        }
//    }
//}
