package regex;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import utils.RegexUtil;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRegex {
    public void testGetShopInfo() throws IOException {
        File file = new File("C:\\Users\\Administrator\\Desktop\\微信端店铺信息.txt");
        String text = FileUtils.readFileToString(file, "utf-8");
        RegexUtil util = new RegexUtil();
        List<String> scores = util.GetMatchsString(text, "scoreRankRateGrade = \"", "\\d+\\.\\d{2}", "\";");
        for (String score : scores) {
            System.out.println(score);
        }
    }

    public void testMatchMothed() throws IOException {
        long time1 = System.currentTimeMillis();
        File file = new File("C:\\Users\\Administrator\\Desktop\\微信端店铺信息.txt");
        String text = FileUtils.readFileToString(file, "utf-8");
        testGetShopInfoTime(text);
        long time2 = System.currentTimeMillis();
        System.out.println("regexTime:" + (time2 - time1));
    }

    public static void testGetShopInfoTime(String shopHtml) {
        RegexUtil util = new RegexUtil();
        String isZyStr = RegexUtil.GetMatchString(shopHtml,"isZy = \"","\\w+","\";");
        String companyName = RegexUtil.GetMatchString(shopHtml,"companyName = \"",".+","\";").replace("null","");
        String shop = RegexUtil.GetMatchString(shopHtml,"店铺简介\\</div\\>\\s{0,50}\\<div class=\"menu_list_content\"\\>",".+","\\</div\\>");
        boolean isZy = Boolean.parseBoolean(isZyStr);
        String score1 = util.GetMatchString(shopHtml, "scoreRankRateGrade = \"", "\\d{8}", "\";");
        String score2 = util.GetMatchString(shopHtml, "userEvaluateScore = \"", "\\d+\\.\\d{2}", "\";");
        String score3 = util.GetMatchString(shopHtml, "logisticsLvyueScore = \"", "\\d+\\.\\d{2}", "\";");
        String score4 = util.GetMatchString(shopHtml, "afterServiceScore = \"", "\\d+\\.\\d{2}", "\";");
        Map<String,String> map = new HashMap<String, String>();
        map.put("score1",score1);
        System.out.println(score1 + ";" + score2 + ";" + score3 + ";" + score4 + ";");
    }

    @Test
    public void test(){
        String json = "jQuery7634135([{\"categoryId\":\"670\",\"categoryName\":\"电脑、办公\",\"errorCode\":null,\"errorMsg\":null," +
                "\"logo\":\"http:\\/\\/img30.360buyimg.com\\/popshop\\/jfs\\/t1462\\/114\\/1115719795\\/3998\\/3baf973c\\/55bb2ae7N64b6a259.png\"," +
                "\"name\":\"英特尔京东自营旗舰店\",\"score\":null,\"success\":true,\"venderId\":\"1000000693\"}," +
                "{\"categoryId\":\"670\",\"categoryName\":\"电脑、办公\",\"errorCode\":null,\"errorMsg\":null,\"logo\":" +
                "\"http:\\/\\/img30.360buyimg.com\\/popshop\\/jfs\\/t1\\/78758\\/26\\/9067\\/6072\\/5d6f277aE8c6a8c0d\\/2a8da9a5cee904b0.jpg\"," +
                "\"name\":\"AMD京东自营旗舰店\",\"score\":null,\"success\":true,\"venderId\":\"1000000706\"}])";
        RegexUtil util = new RegexUtil();
        json = util.GetMatchString(json, "^jQuery\\d{7}\\(", ".+", "\\)$");

        System.out.println(json);
    }
}
