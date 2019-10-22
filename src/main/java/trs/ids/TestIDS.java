package trs.ids;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestIDS {
    //    private static String idsUrl = "http://192.168.13.15:8899/ids/";
//    private static String serviceUrl = "service?idsServiceType=httpssoservice&serviceName=findUserBySSOID";
    public void test(){
        String data = "userName=1&password=2&nickName=3";

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes("UTF-8"));
            byte[] digestByte = md.digest();
            String diss = DesEncryptUtil.bytesToHex(digestByte,0,digestByte.length);
            System.out.println(diss);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            byte[] bytes = data.getBytes("utf-8");
            String base64 = new BASE64Encoder().encode(bytes);
            String desStr = DesEncryptUtil.encryptToHex(base64.getBytes("utf-8"),"12345678");
            System.out.println(desStr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
