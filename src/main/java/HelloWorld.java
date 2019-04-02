import jdjson.test.JDSendGetUtil;
import jsoup.test.TestJsoup;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HelloWorld {
    public static void main(String[] args){
//        JDSendGetUtil sendGetUtil = new JDSendGetUtil();
//        String source = sendGetUtil.pc_jsonSource("https://c0.3.cn/stock?skuId=19431683299&area=1_72_2799_0&venderId=679153&cat=670,671,672&choseSuitSkuIds=&extraParam=%7b%22originid%22:%221%22%7d");
//        System.out.println(source);

        try {
            TestJsoup.testGetShopInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}