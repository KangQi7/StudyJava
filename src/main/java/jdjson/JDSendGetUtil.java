package jdjson;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @Author: yg
 * @Description:京东httpclent请求工具
 * @Date: 2018-05-03 11:34
 */
public class JDSendGetUtil {

    private SetHeaderConverter setHeaderConverter;
    public JDSendGetUtil(){
        setHeaderConverter = new SetHeaderConverter();
    }

    //==============1.京东pc页面源码(商品、店铺)=====================
    public String homePage(String url) {
        //创建请求Get实例
        HttpGet httpGet = new HttpGet(url);
        //设置头部信息进行浏览器模拟行为
        setHeaderConverter.jdSetHeader(httpGet);
        String result = getResult(httpGet);
        return result;
    }

    //==============1.京东pc端，根据关键词搜索商品的页面源码=====================
    public String homePageGoods(String url) {
        url = url.replaceAll(" ", "%20");    //....过滤......
        //创建请求Get实例
        HttpGet httpGet = new HttpGet(url);
        //设置头部信息进行浏览器模拟行为
        setHeaderConverter.jdSetHeader(httpGet);
        httpGet.setHeader("Referer", "https://search.jd.com/Search?keyword=ThinkPad&enc=utf-8&suggest=7.his.0.0&click=0");
        String result = getResult(httpGet);
        return result;
    }


    //==============2.京东pc端json页面源码==========gbk===========
    public String pc_jsonSource(String url) {
        //获取方法实例。GET
        HttpGet httpGet = new HttpGet(url);
        //设置头部信息进行浏览器模拟行为
        setHeaderConverter.jdSetHeader(httpGet);
//        httpGet.removeHeaders("Accept-Encoding");
//        httpGet.setHeader("Host", "search.jd.com");
//        httpGet.setHeader("Referer", url);
        String result = getResult(httpGet);
        return result;
    }


    //==============3.京东微信关键词排名json源码（特殊：要过滤）========= utf-8 ============
    public String keyword(String url) {
        //创建请求Get实例
        url = url.replaceAll(" ", "%20");    //....过滤......
        URL url1 = null;
        try {
            url1 = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URI uri = null;
        try {
            uri = new URI(url1.getProtocol(), url1.getHost(), url1.getPath(), url1.getQuery(), null);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpGet httpGet = new HttpGet(uri);
        //设置头部信息进行浏览器模拟行为
        setHeaderConverter.jdSetHeader(httpGet);
        String result = getResult(httpGet);
        return result;
    }


    //==============4.京东移动端店铺(top10，上新，热销，促销)的相关信息json源码 (设置的头不一样)=====================
    public String jsonSource_move(String url) {
        HttpGet httpGet = new HttpGet(url);
        //设置头部信息进行浏览器模拟行为
        setHeaderConverter.jdSetHeader_move(httpGet);
        String result = getResult(httpGet);
        return result;
    }

    //*****************************************************************************************************
//    ==============5.京东移动端店铺评论json源码=====================
    public String jsonSource_move(String url, String charset) {
        HttpGet httpGet = new HttpGet(url);
        //设置头部信息进行浏览器模拟行为
        setHeaderConverter.jdSetHeader_move(httpGet);
        //这里为什么会把编码方式设置到skuId参数上呢？就为了省一个方法？
        String result;
        if(charset.matches("^\\d+$")){
            httpGet.setHeader("Referer", "https://wqitem.jd.com/item/view?sku=" + charset);
            result = getResult(httpGet);
        } else {
            result = getResult(httpGet);
        }
        return result;
    }

    //*****************************************************************************************************
    //==============6.京东pc端List<店铺信息>=====================
    public String jsonSource_Referer(String url) {
        HttpGet httpGet = new HttpGet(url);
        //设置头部信息进行浏览器模拟行为
        setHeaderConverter.jdSetHeader(httpGet);
        //必须加“Referer”
//        httpGet.setHeader("Referer", "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E6%89%8B%E6%9C%BA&page=1&s=1&click=0");
        String result = getResult(httpGet);
        return result;
    }

    private String getResult(HttpUriRequest request) {
        String result = null;
        try {
            for (int i = 0; i < 5; i++) {
                result = getResponseStr(request);
                if (result != null && !result.equals("")) {
                    break;
                } else {
                    Thread.sleep(100);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result == null ? "" : result;
    }

    private String getResponseStr(HttpUriRequest request) {
        String responseStr = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            CloseableHttpResponse response = httpclient.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                responseStr = EntityUtils.toString(response.getEntity(), "utf-8");
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
//    //Unicode 转 utf-8工具
//    public static String decodeUnicode(String str) {
//        Charset set = Charset.forName("UTF-16");
//        Pattern p = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
//        Matcher m = p.matcher( str );
//        int start = 0 ;
//        int start2 = 0 ;
//        StringBuffer sb = new StringBuffer();
//        while( m.find( start ) ) {
//            start2 = m.start() ;
//            if( start2 > start ){
//                String seg = str.substring(start, start2) ;
//                sb.append( seg );
//            }
//            String code = m.group( 1 );
//            int i = Integer.valueOf( code , 16 );
//            byte[] bb = new byte[ 4 ] ;
//            bb[ 0 ] = (byte) ((i >> 8) & 0xFF );
//            bb[ 1 ] = (byte) ( i & 0xFF ) ;
//            ByteBuffer b = ByteBuffer.wrap(bb);
//            sb.append( String.valueOf( set.decode(b) ).trim() );
//            start = m.end() ;
//        }
//        start2 = str.length() ;
//        if( start2 > start ){
//            String seg = str.substring(start, start2) ;
//            sb.append( seg );
//        }
//        return sb.toString() ;
//    }
}
