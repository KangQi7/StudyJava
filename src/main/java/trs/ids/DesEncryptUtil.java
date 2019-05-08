package trs.ids;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class DesEncryptUtil {
    /**
     * DES算法名称
     */
    private final static String DES_ALGORITHM_NAME = "DES";
    /**
     * 默认的DES算法转换方式
     */
    private final static String DES_ALGORITHM_TRANSFORMATION_DEFAULT = "DES/ECB/PKCS5Padding";
    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String encryptToHex(byte[] toEncryptData, String keyData) {
        byte[] encryptedData = doEncrypt(getKeyByData(keyData), toEncryptData, DES_ALGORITHM_TRANSFORMATION_DEFAULT);
        if (null == encryptedData) {
            return "";
        }
        return bytesToHex(encryptedData, 0, encryptedData.length);
    }

    public static byte[] doEncrypt(Key key, byte[] toEncryptData, String transformation) {
        if (null == toEncryptData) {
            return null;
        }   // Get a cipher object
        Cipher cipher = getCipher(key, Cipher.ENCRYPT_MODE, transformation);
        // Encrypt
        byte[] encryptedData = null;
        try {
            encryptedData = cipher.doFinal(toEncryptData);
        } catch (IllegalBlockSizeException e) {
        } catch (BadPaddingException e) {
        }
        return encryptedData;
    }

    public static Cipher getCipher(Key key, int cipherMode, String transformation) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(transformation);
        } catch (NoSuchAlgorithmException e) {
        } catch (NoSuchPaddingException e) {
        }
        try {
            cipher.init(cipherMode, key);
        } catch (InvalidKeyException e) {
        }
        return cipher;
    }

    public static String decrypt(String encryptedDataHex, String keyData) throws Throwable {
        if (encryptedDataHex == null || encryptedDataHex.length() == 0) {
            return "";
        }
        String decryptedHexStr = DesEncryptUtil.decryptToHex(toBytes(encryptedDataHex), keyData);
        return hexToStr(decryptedHexStr);
    }

    public static String decryptToHex(byte[] toDecryptData, String keyData) {
        byte[] decryptedData = doDecrypt(getKeyByData(keyData), toDecryptData, DES_ALGORITHM_TRANSFORMATION_DEFAULT);
        if (null == decryptedData) {
            return "";
        }
        return bytesToHex(decryptedData, 0, decryptedData.length);
    }

    public static byte[] doDecrypt(Key key, byte[] toDecryptData, String transformation) {
        if (null == toDecryptData) {
            return null;
        }
        Cipher cipher = getCipher(key, Cipher.DECRYPT_MODE, transformation);
        byte[] decryptedData = null;
        try {
            decryptedData = cipher.doFinal(toDecryptData);
        } catch (IllegalBlockSizeException e) {
        } catch (BadPaddingException e) {
        }
        return decryptedData;
    }

    public static SecretKey getKey(String algorithm, KeySpec keySpec) {
        SecretKeyFactory keyFactory = null;
        try {
            keyFactory = SecretKeyFactory.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
        }
        try {
            return keyFactory.generateSecret(keySpec);
        } catch (InvalidKeySpecException e) {
        }
        return null;
    }

    public static SecretKey getKeyByData(String keyData) {
        DESKeySpec desKeySpec = null;
        try {
            desKeySpec = new DESKeySpec(keyData.getBytes());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return getKey(DES_ALGORITHM_NAME, desKeySpec);
    }

    public static String toString(byte[] data) {
        if (data == null) {
            return "null!";
        }
        int l = data.length;
        char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }
        return new String(out);
    }

    public final static String bytesToHex(byte[] buf, int off, int len) {
        char[] out = new char[len * 2];
        for (int i = 0, j = 0; i < len; i++) {
            int a = buf[off++];
            out[j++] = DIGITS[(a >>> 4) & 0X0F];
            out[j++] = DIGITS[a & 0X0F];
        }
        return (new String(out));
    }

    static int toDigit(char ch, int index) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal charcter " + ch + " at index " + index);
        }
        return digit;
    }

    public static String hexToStr(String hex) {
        return new String(toBytes(hex));
    }

    public static byte[] toBytes(String str) {
        if (str == null) {
            return null;
        }
        char[] data = str.toCharArray();
        int len = data.length;
        if ((len & 0x01) != 0) {
            throw new RuntimeException("Odd number of characters!");
        }
        byte[] out = new byte[len >> 1];
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }
        return out;
    }
}

