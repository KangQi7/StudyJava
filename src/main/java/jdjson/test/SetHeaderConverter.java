package jdjson.test;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

/**
 * @Author: yg
 * @Description:设置头部信息进行浏览器模拟行为
 * @Date: 2018-05-03 11:34
 */

public class SetHeaderConverter {


//    //测试京东关键词搜索代理：
//    public void jdSetHeaderTest(HttpGet httpGet) {
//        HttpHost proxy = new HttpHost("221.7.255.167",8080);
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setProxy(proxy)
//                .setConnectTimeout(60000).setConnectionRequestTimeout(60000)
//                .setSocketTimeout(60000).build();
//        httpGet.setConfig(requestConfig);
//        httpGet.setHeader("Accept", "*/*");
//        httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch, br");
//        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
//        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
//    }

    //setConnectTimeout：设置连接超时时间，单位毫秒。
    //setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间，单位毫秒。
    //setSocketTimeout：请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。

    //京东pc端
    public void jdSetHeader(HttpGet httpGet) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(60000).setConnectionRequestTimeout(60000)
                .setSocketTimeout(60000).build();
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("Accept", "*/*");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch, br");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
    }

    //京东移动端（店铺）
    public void jdSetHeader_move(HttpGet httpGet) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(60000).setConnectionRequestTimeout(60000)
                .setSocketTimeout(60000).build();
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
    }

    //京东移动端 商品评论
    public void jdSetHeader_move1(HttpGet httpGet,String ip,int port) {
        HttpHost proxy = new HttpHost(ip,port);
//        HttpHost proxy = new HttpHost("119.27.177.169",80);
        RequestConfig requestConfig = RequestConfig.custom()
                .setProxy(proxy)
                .setConnectTimeout(60000).setConnectionRequestTimeout(60000)
                .setSocketTimeout(60000).build();
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
    }

    //京东移动端 (商品)
    public void jdSetHeader_move1(HttpPost post) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(60000).setConnectionRequestTimeout(60000)
                .setSocketTimeout(60000).build();
        post.setConfig(requestConfig);
        post.setHeader("Accept", "application/json");
        post.setHeader("Accept-Encoding", "gzip, deflate, br");
        post.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");

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
