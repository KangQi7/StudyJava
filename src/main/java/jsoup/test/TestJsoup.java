package jsoup.test;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import regex.test.TestRegex;
import utils.RegexUtil;

import java.io.File;
import java.io.IOException;
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
    public void testGetDocument() throws IOException {
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

    public void testGetShopInfo() throws IOException {
        long time1 = System.currentTimeMillis();
        File file = new File("C:\\Users\\Administrator\\Desktop\\微信端店铺信息.txt");
        Document document = Jsoup.parse(file,"utf-8","");
        Element element = document.select("div.wx_wrap").first();
        String shopHtml = element.text();
        TestRegex.testGetShopInfoTime(shopHtml);
        long time2 = System.currentTimeMillis();
        System.out.println("jsoupTime:" + (time2 - time1));
    }

    public void testJsoupSelecter(){
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p>";
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("p:contains(HTML)");
        for (Element element : elements) {
            System.out.println(element.text());
        }
    }
}
