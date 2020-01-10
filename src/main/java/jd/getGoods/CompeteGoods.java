package jd.getGoods;

import com.sun.scenario.effect.impl.prism.PrImage;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;
import utils.URLEncodeUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

/**
 * @Author KangQi
 * @Date 2020/1/10 11:15
 * @Version 1.0
 */
public class CompeteGoods {
    @Test
    public void test(){
        String urlString = "";//kwSearchGoodsUrl("华为 手机", "1", "");
        urlString = "https://search.jd.com/Search?keyword=%E6%99%BA%E6%85%A7%E5%B1%8F&enc=utf-8&pvid=6028060c2e064f9688868cf044cc9cf0";

        String result = homePageGoods(urlString, urlString);
        System.out.println(result);


//        String result = "";
//        URL url = null;
//        try {
//            url = new URL(urlString);
//            URLConnection uc = url.openConnection();
//            uc.setReadTimeout(5000);
//            uc.setConnectTimeout(5000);
//            uc.setRequestProperty("Accept", "*/*");
//            uc.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
//            uc.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
//            uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36");
////            uc.setRequestProperty("Host","search.jd.com");
//            uc.setRequestProperty("Referer", urlString);
//            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(),"utf-8"));
//            String inputLine = "";
//            while ((inputLine = in.readLine()) != null) {
//                result += inputLine;
//            }
//            in.close();
//            System.out.println(result);
//        } catch (IOException e) {
//            e.printStackTrace();
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
        httpGet.setHeader("accept-encoding", "gzip, deflate, br");
        httpGet.setHeader("accept-language", "zh-CN,zh;q=0.8");
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36");
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
                responseStr = EntityUtils.toString(response.getEntity());
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
}
