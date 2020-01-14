package jd.getGoods;

import array.Array;
import com.sun.scenario.effect.impl.prism.PrImage;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;
import utils.URLEncodeUtil;

import javax.print.attribute.standard.PrinterURI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author KangQi
 * @Date 2020/1/10 11:15
 * @Version 1.0
 */
public class CompeteGoods {

    private List<String> userAgent = Arrays.asList(
            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50",
            "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0",
            "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; .NET CLR 3.0.30729; .NET CLR 3.5.30729; InfoPath.3; rv:11.0) like Gecko",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)",
            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)",
            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:2.0.1) Gecko/20100101 Firefox/4.0.1",
            "Mozilla/5.0 (Windows NT 6.1; rv:2.0.1) Gecko/20100101 Firefox/4.0.1",
            "Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; en) Presto/2.8.131 Version/11.11",
            "Opera/9.80 (Windows NT 6.1; U; en) Presto/2.8.131 Version/11.11",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon 2.0)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; TencentTraveler 4.0)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; The World)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SE 2.X MetaSr 1.0; SE 2.X MetaSr 1.0; .NET CLR 2.0.50727; SE 2.X MetaSr 1.0)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; 360SE)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Avant Browser)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)",
            "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5",
            "Mozilla/5.0 (iPod; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5",
            "Mozilla/5.0 (iPad; U; CPU OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5",
            "Mozilla/5.0 (Linux; U; Android 2.3.7; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1",
            "MQQBrowser/26 Mozilla/5.0 (Linux; U; Android 2.3.7; zh-cn; MB200 Build/GRJ22; CyanogenMod-7) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1",
            "Opera/9.80 (Android 2.3.4; Linux; Opera Mobi/build-1107180945; U; en-GB) Presto/2.8.149 Version/11.10",
            "Mozilla/5.0 (Linux; U; Android 3.0; en-us; Xoom Build/HRI39) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13",
            "Mozilla/5.0 (BlackBerry; U; BlackBerry 9800; en) AppleWebKit/534.1+ (KHTML, like Gecko) Version/6.0.0.337 Mobile Safari/534.1+",
            "Mozilla/5.0 (hp-tablet; Linux; hpwOS/3.0.0; U; en-US) AppleWebKit/534.6 (KHTML, like Gecko) wOSBrowser/233.70 Safari/534.6 TouchPad/1.0",
            "Mozilla/5.0 (SymbianOS/9.4; Series60/5.0 NokiaN97-1/20.0.019; Profile/MIDP-2.1 Configuration/CLDC-1.1) AppleWebKit/525 (KHTML, like Gecko) BrowserNG/7.1.18124",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0; HTC; Titan)",
            "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36",
            "UCWEB7.0.2.37/28/999",
            "NOKIA5700/ UCWEB7.0.2.37/28/999",
            "Openwave/ UCWEB7.0.2.37/28/999",
            "Mozilla/4.0 (compatible; MSIE 6.0; ) Opera/UCWEB7.0.2.37/28/999",
            "Mozilla/6.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/8.0 Mobile/10A5376e Safari/8536.25"
    );

    private String getRandomUserAgent(){
        return userAgent.get(new Random().nextInt(userAgent.size()));
    }

    @Test
    public void test(){
        String keyword = "华为 手机";
        String kwSearchGoodsUrl_1 = kwSearchGoodsUrl(keyword, "1", "");
        //urlString = "https://search.jd.com/Search?keyword=%E6%99%BA%E6%85%A7%E5%B1%8F&enc=utf-8&suggest=2.his.0.0&pvid=3b4186f307684528a5b67f200298ccfd";

        String goodsSource1 = homePageGoods(kwSearchGoodsUrl_1, kwSearchGoodsUrl_1);
        List<KeywordSearch> keywordSearches = jdKwSearchJsoup(goodsSource1); //根据关键词搜索商品前三十条
        System.out.println(keywordSearches.size());
        System.out.println(goodsSource1);
//        if (keywordSearches.size() > 20) {      //判断搜索类型，看是否拿后三十条商品
//            String kwSearchGoodsUrl_2 = kwSearchGoodsUrl(keyword, "2",
//                    String.join(",", keywordSearches.stream().map(k -> k.getSkuID()).collect(Collectors.toList())));
//            String goodsSource2 = homePageGoods(kwSearchGoodsUrl_2, kwSearchGoodsUrl_1);
//            List<KeywordSearch> keywordSearches1 = jdKwSearchJsoup(goodsSource2);//根据关键词搜索商品后三十条
//            for (int i = 0, length = keywordSearches1.size(); i < length; i++) {
//                keywordSearches.add(keywordSearches1.get(i));
//            }
//        }
    }

    public String homePageGoods(String url, String referer) {
//        url = url.replaceAll(" ", "%20");    //....过滤......
        //创建请求Get实例
        HttpGet httpGet = new HttpGet(url);
        //设置头部信息进行浏览器模拟行为

//        httpGet.setHeader("Accept", "*/*");
//        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
//        httpGet.setHeader("Connection", "keep-alive");
//        httpGet.setHeader("User-Agent", "PostmanRuntime/7.21.0");
//        httpGet.setHeader("Host","search.jd.com");
//        httpGet.setHeader("Cache-Control", "no-cache");
//        httpGet.setHeader("Postman-Token","f127b70b-6125-455e-97b6-7593a47c538b");

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(60000).setConnectionRequestTimeout(60000)
                .setSocketTimeout(60000).build();
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("accept", "*/*");
        httpGet.setHeader("accept-language", "zh-CN,zh;q=0.8");
        httpGet.setHeader("host","search.jd.com");
        httpGet.setHeader("user-agent", getRandomUserAgent());
        httpGet.setHeader("referer", referer);

        String result = getResult(httpGet);
        return result;
    }

    private String getResult(HttpUriRequest request) {
        String responseStr = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            CloseableHttpResponse response = httpclient.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                responseStr = EntityUtils.toString(response.getEntity(), "utf-8");
//                StringBuilder reMsgBuider = new StringBuilder();
//                InputStream in = response.getEntity().getContent();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
//                String msg = null;
//                // 读取返回消息体
//                while ((msg = reader.readLine()) != null) {
//                    reMsgBuider.append(msg);
//                }
//                responseStr = reMsgBuider.toString();
            }
            response.close();
        } catch (Exception e) {
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return responseStr;
        }
    }

    private String kwSearchGoodsUrl(String keyword, String page, String skuIds) {
        String result = "";
        String encodeKeyword = URLEncodeUtil.getURLEncoderString(keyword);
        if (page.equals("1")) {
            result = "https://search.jd.com/Search?keyword=" + encodeKeyword +
                    "&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=" + encodeKeyword +
                    "&stock=1&page=1&s=1&click=0";
        } else if (page.equals("2")) {
            result = "https://search.jd.com/s_new.php?keyword=" + encodeKeyword +
                    "&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=" + encodeKeyword +
                    "&stock=1&page=2&s=31&scrolling=y&log_id=" + createJdLogId() +
                    "&tpl=1_M&show_items=" + skuIds;
        }

        return result;
    }

    private String createJdLogId() {
        // 京东请求商品链接的log_id参数，前半部分是Unix时间戳（秒），后半部分是5位随机数字，例如：log_id=1578617580.65043
        return System.currentTimeMillis() / 1000L + "." + createRandomNum(10000, 99999);
    }

    /**
     * 在左闭右闭的区间内获取一个随机int值
     *
     * @param min
     * @param max
     * @return
     */
    private static Integer createRandomNum(Integer min, Integer max) {
        if (max < min) {
            Integer temp = min;
            min = max;
            max = temp;
        }
        Random random = new Random(System.currentTimeMillis());
        return random.nextInt(max - min + 1) + min;
    }

    //region 读取商品数据
    //京东关键词搜索商品
    public List<KeywordSearch> jdKwSearchJsoup(String sourceHome) {
        //采用Jsoup解析
        List<KeywordSearch> keywordSearches = new ArrayList<>();
        Document doc = Jsoup.parse(sourceHome);
        Elements links = doc.select("li[class=gl-item]");
        KeywordSearch keywordSearch;
        SearchKeywordShop searchKeywordShop;
        String skuID,shopID,productName,price,productUrl,picUrl,shop_name,shopUrl; //初始化字符串变量，提高效率
        for(Element link : links) {
            keywordSearch = new KeywordSearch();

            skuID = link.attr("data-sku");
            keywordSearch.setSkuID(skuID);//产品SKUID
            if(skuID.equals("")) {     //排除空的对象（没有）
                continue;
            }
            shopID = link.select("div[class=gl-i-wrap]").select("div[class=p-img]").select("div").attr("data-venid");
            productName = link.select("div[class=gl-i-wrap]").select("div.p-name").select("a").select("em").text();
            price = link.select("div[class=gl-i-wrap]").select("div[class=p-price]").select("strong").select("i").text();
            productUrl = link.select("div[class=gl-i-wrap]").select("div[class=p-img]").select("a").attr("href");
            picUrl = link.select("div[class=gl-i-wrap]").select("div[class=p-img]").select("a").select("img").attr("source-data-lazy-img");
            if(picUrl.equals("")) {
                picUrl = link.select("div[class=gl-i-wrap]").select("div[class=p-img]").select("a").select("img").attr("data-lazy-img");
            }

            keywordSearch.setSkuID(skuID);//产品SKUID
            keywordSearch.setProductName(productName);//产品名称
            if (price.equals("暂无报价")) {
                keywordSearch.setPrice("0");//产品价格
            }else {
                keywordSearch.setPrice(price);//产品价格
            }
            keywordSearch.setProUrl(productUrl);//产品url
            keywordSearch.setPicUrl(picUrl);//产品图片url

            shopUrl = link.select("div[class=gl-i-wrap]").select("div[class=p-shop]").select("span").select("a").attr("href");
            if(shopID.length()!=10&&!shopID.equals("")) {        //当店铺不是竞店自营的，再单独去拿shopID
                shopID = regexShopId(shopUrl);
            }
            if(shopID.equals("")) {
                shopID = link.select("div[class=gl-i-wrap]").select("div[class=p-shop]").attr("data-shopid");
            }
            keywordSearch.setShopID(shopID);//产品店铺ID


            keywordSearches.add(keywordSearch);
        }
        return keywordSearches;
    }

    //截取shopId
    public static String regexShopId(String sourceCode) {
        String regex = "x-(.+?).html";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sourceCode);
        if (matcher.find()) {
            String commentCount =  matcher.group(1);
            return commentCount;
        }
        return "";
    }
    //endregion
}
