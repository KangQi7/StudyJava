package jsoup.test;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import regex.test.TestRegex;
import utils.RegexUtil;

import javax.lang.model.util.ElementScanner6;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestJsoup {
    public Document getDocument(String homePage){
        return Jsoup.parse(homePage);
    }

    public String getShopScores(String appId){

        return "";
    }

    /**
     * 测试用jsoup方式获取店铺评分
     * @throws IOException
     */
    public static void testGetDocument() throws IOException {
        File file = new File("C:\\Users\\Administrator\\Desktop\\发票.txt");
        Document document = Jsoup.parse(file,"utf-8","");
//        Elements element = document.getElementsByTag("span");
//        Elements element = document.select("span[class~=level-text-.+]");
        Elements scores = document.select("span:matches(\\d+?\\.\\d{2})");
//        Elements element = document.select(":matches((?<=level-text-[(red)|(green)]\">)\\d+.\\d{2}(?=</span))");
        for (Element score : scores) {
            System.out.println(score.text());
        }
    }

    public static void testGetShopInfo() throws IOException {
        long time1 = System.currentTimeMillis();
        File file = new File("C:\\Users\\Administrator\\Desktop\\微信端店铺信息.txt");
        String keyword = "华为mate20pro";
        Document doc = Jsoup.parse(file,"utf-8","");
        Elements elements = doc.getElementById("J_goodsList").select("li[class=gl-item]");
        int i = 0;
        for (Element element : elements) {
            //非自营
            boolean notZy = element.select("i[class=goods-icons J-picon-tips J-picon-fix]").isEmpty();
            //非广告
            boolean notAd = element.select("span[class=p-promo-flag]").isEmpty();
            if (notZy && notAd) {
                //如果商品名含有手机配件的关键字，则跳过这一商品
                String priceStr = element.select("strong:has(em) > i").first().text();

                String skuName = element.select("a > em").first().text();
                if (!checkWarePrice(priceStr) || !checkWareName(skuName, keyword))
                    continue;
                String skuId = element.attr("data-sku");
                String spuId = element.attr("data-spu");
                String shopUrl = element.select("a[class=curr-shop]").attr("href");
            }
        }


//        Document document = Jsoup.parse(file,"utf-8","");
//        Elements elements1 = document.select("div[class=Ptable-item]").select("dl[class=clearfix]");
//        int videoCount = elements1.size();
//        long time2 = System.currentTimeMillis();
//        System.out.println("jsoupTime:" + (time2 - time1));
    }

    private static boolean checkWarePrice(String priceStr){
        try {
            double price = Double.parseDouble(priceStr);
            if (price > 800.0)
                return true;
            else
                return false;
        }
        catch (Exception e){
            return false;
        }
    }

    private static boolean checkWareName(String skuName, String keyword) {
        String keywordEn = keyword.replaceAll("\\W","").toLowerCase();
        //去除商品名中的非英文、数字字符
        String skuNameEn = skuName.replaceAll("\\W","").toLowerCase();
        if(skuNameEn.contains(keywordEn))
            return true;
        else
            return false;
    }

    public static void testJsoupSelecter(){
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p>";
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("p:contains(HTML)");
        for (Element element : elements) {
            System.out.println(element.text());
        }
    }
}
