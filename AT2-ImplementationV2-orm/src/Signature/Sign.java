package Signature;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
public class Sign {

    public static byte[] signature = null; //Initializing the signature

    public static Signature sign(String privkey, HashMap<String, Object> message) throws Exception {
        //Creating KeyPair generator object
        byte[] privateKeyByteServer = Base64.getDecoder().decode(privkey);
        // generate the publicKey
        KeyFactory keyFactory = KeyFactory.getInstance("DSA");
        PrivateKey privateKeyServer = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyByteServer));
        //Initialize the signature
        Signature sign = Signature.getInstance("SHA256withDSA");
        sign.initSign(privateKeyServer);
        byte[] bytes = convertMapWithStream(message).getBytes(StandardCharsets.UTF_8);
        //Adding data to the signature
        sign.update(bytes);
        //Calculating the signature
        signature = sign.sign();
        System.out.println(signature);
        //ArrayList<Object> signature_Publickey = new ArrayList<>();
        return sign;
    }

    public static boolean verify_sign(byte[] signature, HashMap<String, Object> initial_message, Signature sign, String publicKey) throws Exception {
        byte[] bytes = convertMapWithStream(initial_message).getBytes(StandardCharsets.UTF_8);
        //Verifying the signature
        System.out.println(publicKey);
        byte[] publicKeyByteServer = Base64.getDecoder().decode(publicKey);
        // generate the publicKey
        KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
        PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyByteServer));
        
        sign.initVerify(pubKey);
        sign.update(bytes);
        boolean bool = sign.verify(signature);
        return bool;
    }
    
    public  static String convertMapWithStream(Map<String, Object> map) {
        String mapAsString = map.keySet().stream()
          .map(key -> key + "=" + map.get(key).toString())
          .collect(Collectors.joining(", ", "{", "}"));
        return mapAsString;
    }
}
