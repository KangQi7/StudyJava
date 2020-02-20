package jdjson;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @Author: yg
 * @Description:设置头部信息进行浏览器模拟行为
 * @Date: 2018-05-03 11:34
 */

public class SetHeaderConverter {
    // 爬取常用user_agent
    private static List<String> pcUserAgent = Arrays.asList(
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
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"
    );

    // 爬取常用user_agent
    private static List<String> mobileUserAgent = Arrays.asList(
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

    /**
     * 随机获取一个user_agent
     *
     * @return
     */
    private String getRandomUserAgent(boolean isMobile) {
        if (isMobile){
            return mobileUserAgent.get(new Random().nextInt(mobileUserAgent.size()));
        } else {
            return pcUserAgent.get(new Random().nextInt(pcUserAgent.size()));
        }
    }

    /**
     * 设置京东pc/mobile页面接口请求头
     * @param httpGet
     * @param isMobile
     */
    private void setHeader(HttpGet httpGet, boolean isMobile){
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(60000).setConnectionRequestTimeout(60000)
                .setSocketTimeout(60000).build();
        httpGet.setConfig(requestConfig);

        // 设置头
        if (isMobile){
            httpGet.setHeader("Accept", "application/json");
            httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
        } else {
            httpGet.setHeader("Accept", "*/*");
            httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
        }
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpGet.setHeader("User-Agent", getRandomUserAgent(isMobile));
    }

    //京东pc端
    public void jdSetHeader(HttpGet httpGet) {
        setHeader(httpGet, false);
    }

    //京东移动端
    public void jdSetHeader_move(HttpGet httpGet) {
        setHeader(httpGet,true);
    }

    //天猫
    public void tmallSetHeader(HttpGet httpGet) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(60000).setConnectionRequestTimeout(60000)
                .setSocketTimeout(60000).build();
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("accept", "*/*");
        httpGet.setHeader("accept-encoding", "gzip, deflate, sdch, br");
        httpGet.setHeader("accept-language", "zh-CN,zh;q=0.8");
        httpGet.setHeader("Cookie", "miid=1432839444719840454; UM_distinctid=15d0795b36fb7-0db8b6a9e-4349052c-13c680-15d0795b37061b; lzstat_uv=18742840291219094852|2144678; x=e%3D1%26p%3D*%26s%3D0%26c%3D1%26f%3D0%26g%3D0%26t%3D0%26__ll%3D-1%26_ato%3D0; hng=CN%7Czh-CN%7CCNY%7C156; thw=cn; ali_ab=118.122.132.124.1498359351982.3; l=AoeH626WodvHY0-SSaRK6V1WlzFRjFtu; cna=hOXEERB2nDQCAXZ6hHz6dpqX; tracknick=tb144138_2011; t=612dec1272ecce84f0b77fb2796ef300; _cc_=WqG3DMC9EA%3D%3D; tg=0; ucn=unshyun; __guid=141928046.2115913629491890200.1512703922185.7534; isg=AlVVgGlVo7pvaIRGItQPo1bPZFHP-gRpCpQKedf6EUwbLnUgn6IZNGNujC1x; v=0; cookie2=1814d812ffbc3374c62fe8e668237a0d; _tb_token_=35399b7bb56; monitor_count=1; uc1=cookie14=UoTdeAugXL4UBg%3D%3D");
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
    }

    //天猫
    public void tmallSetHeader1(HttpGet httpGet) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(60000).setConnectionRequestTimeout(60000)
                .setSocketTimeout(60000).build();
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("accept", "*/*");
        httpGet.setHeader("accept-encoding", "gzip, deflate, sdch, br");
        httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
//        httpGet.setHeader("Cookie", "miid=1432839444719840454; UM_distinctid=15d0795b36fb7-0db8b6a9e-4349052c-13c680-15d0795b37061b; lzstat_uv=18742840291219094852|2144678; x=e%3D1%26p%3D*%26s%3D0%26c%3D1%26f%3D0%26g%3D0%26t%3D0%26__ll%3D-1%26_ato%3D0; hng=CN%7Czh-CN%7CCNY%7C156; thw=cn; ali_ab=118.122.132.124.1498359351982.3; l=AoeH626WodvHY0-SSaRK6V1WlzFRjFtu; cna=hOXEERB2nDQCAXZ6hHz6dpqX; tracknick=tb144138_2011; t=612dec1272ecce84f0b77fb2796ef300; _cc_=WqG3DMC9EA%3D%3D; tg=0; ucn=unshyun; __guid=141928046.2115913629491890200.1512703922185.7534; isg=AlVVgGlVo7pvaIRGItQPo1bPZFHP-gRpCpQKedf6EUwbLnUgn6IZNGNujC1x; v=0; cookie2=1814d812ffbc3374c62fe8e668237a0d; _tb_token_=35399b7bb56; monitor_count=1; uc1=cookie14=UoTdeAugXL4UBg%3D%3D");
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        httpGet.setHeader("Cookie","miid=1432839444719840454; lzstat_uv=18742840291219094852|2144678; ali_ab=118.122.132.124.1498359351982.3; l=AoeH626WodvHY0-SSaRK6V1WlzFRjFtu; cna=hOXEERB2nDQCAXZ6hHz6dpqX; tracknick=tb144138_2011; _cc_=WqG3DMC9EA%3D%3D; tg=0; enc=z4dQPhp7414NQkQxqR4EdByxZ2kZqQUW%2FHbl%2F2b5pRmhcCcanmRzogaV7jHlejxiw5qyVcOx8ys9ndoNK5Bnsg%3D%3D; hng=CN%7Czh-CN%7CCNY%7C156; thw=cn; isg=BHV1IKX3QpmDSaQmAjQvw3ZvhPHvWiQJXAmzW_eaLuw2zpXAv0Zz1IJPHNLdjkG8; cookie2=1f308f814e41d09138ab762a84be5587; t=1e173cf17aea12427e95dd48bba7aca8; _tb_token_=e38e338bed0bb; v=0; _umdata=6AF5B463492A874DEFC3EEBDEA4918BD9B3B6734C2CE75A102D431BC590DEE33C1F37FD652CE7020CD43AD3E795C914CB9380DF4D0C4992034C8F539D23FCCB8; x5sec=7b226d616c6c64657461696c736b69703b32223a223163623231386137646436623433656662353765383138623337623139656430434c32546c7434464549666d70395037303869393841453d227d");
    }
}
