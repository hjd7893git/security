package Aes;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2019/5/5.
 */
public class AESCoder {
    private final int AES_KEY_LENGTH = 16;//密钥长度16字节，128位
    private final String AES_ALGORITHM = "AES";//算法名字
    private final String AES_TRANSFORMATION = "AES/CBC/PKCS5Padding";//算法/模式/填充
    private final String AES_IV = "0112030445060709";//使用CBC模式，需要一个向量iv，可增加加密算法的强度
    private final String AES_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIGKLOP";
    private final Charset UTF_8 = Charset.forName("UTF-8");//编码格式

    /**
     * 使用AES加密
     *
     * @param aesKey AES Key
     * @param data   被加密的数据
     * @return AES加密后的数据
     */
    public byte[] encodeAES(byte[] aesKey, String data) {
        if (aesKey == null || aesKey.length != AES_KEY_LENGTH) {
            return null;
        }
        SecretKeySpec keySpec = new SecretKeySpec(aesKey, AES_ALGORITHM);
        try {
            Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
            IvParameterSpec iv = new IvParameterSpec(AES_IV.getBytes(UTF_8));
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
            return cipher.doFinal(data.getBytes(UTF_8));
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用AES解密
     *
     * @param aesKey AES Key
     * @param data   被解密的数据
     * @return AES解密后的数据
     */
    public String decodeAES(byte[] aesKey, byte[] data) {
        if (aesKey == null || aesKey.length != AES_KEY_LENGTH) {
            return null;
        }
        SecretKeySpec keySpec = new SecretKeySpec(aesKey, AES_ALGORITHM);
        try {
            Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
            IvParameterSpec iv = new IvParameterSpec(AES_IV.getBytes(UTF_8));
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
            return new String(cipher.doFinal(data), UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getRandom(int count) {
        return (int) Math.round(Math.random() * (count));
    }

    public byte[] decodeBase64(String str) throws Base64DecodingException {
        return Base64.decode(str.getBytes(UTF_8));
    }
    /**
     * 生成AES key
     *
     * @return AES key
     */
    public String initAESKey() {
        StringBuilder sb = new StringBuilder();
        int len = AES_STRING.length();
        for (int i = 0; i < AES_KEY_LENGTH; i++) {
            sb.append(AES_STRING.charAt(getRandom(len - 1)));
        }
        return sb.toString();
    }
}
