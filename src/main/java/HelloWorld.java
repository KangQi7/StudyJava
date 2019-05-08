import sun.misc.BASE64Encoder;
import trs.ids.DesEncryptUtil;
import utils.JdSendGetUtil;
import jsoup.test.TestJsoup;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class HelloWorld {
//    private static String idsUrl = "http://192.168.13.15:8899/ids/";
//    private static String serviceUrl = "service?idsServiceType=httpssoservice&serviceName=findUserBySSOID";

    public static void main(String[] args){
//        JDSendGetUtil sendGetUtil = new JDSendGetUtil();
//        String source = sendGetUtil.pc_jsonSource("https://c0.3.cn/stock?skuId=19431683299&area=1_72_2799_0&venderId=679153&cat=670,671,672&choseSuitSkuIds=&extraParam=%7b%22originid%22:%221%22%7d");
//        System.out.println(source);

//        try {
//            TestJsoup.testGetShopInfo();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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