package jdjson.test;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.annotation.Resource;
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
    public String jsonSource_move_com1(String url, String productId, String ip, int port) {
        HttpGet httpGet = new HttpGet(url);
        //设置头部信息进行浏览器模拟行为
        setHeaderConverter.jdSetHeader_move1(httpGet, ip, port);
        httpGet.setHeader("Cookie", "JAMCookie=true; abtest=20170905110659526_42; whwswswws=%2FlrRBwo1zyJg31qzs8SpCwuFBCOuk%2FGEhJP3OVjm0oldO3%2B2sp7C1Q%3D%3D; ipLoc-djd=1-72-2799-0; user-key=c48d2307-d3eb-4287-9b1f-20cf01cc003d; ipLocation=%u5317%u4EAC; __guid=178504572.3678537599949368300.1512530928236.52; webp=1; m_uuid_new=3BFAE0F6909FA669A5A5FF147ADB22CD; shshshfpa=419a6ae1-3efc-baa5-9b51-34a9fb32ea90-1513156518; cn=0; __jdv=122270672|direct|-|none|-|1513840336595; sc_width=1440; visitkey=53588244041588306; retina=0; cid=3; __wga=1513905754888.1513905746591.1513905746591.1513905746591.2.1; shshshfp=fc044e1aa1d67634d5f3375ee0bd0c97; shshshfpb=0c743ae265589457438c6c9612254d88fb92e7f2c0d62bad15a30efa6e; mt_xid=V2_52007VwMWVVhYUlsbTx5sAWJRQlcOWFRGGUAbCRliARRVQVABWxtVSV1WbgERVV5YVwkXeRpdBWEfElFBW1FLH08SXQBsAxViX2hSah9PH1sNZgEWUW1YVF4b; _jrda=5; TrackID=1wtMoQLUugCDvFyy2ZTH6VvbJcZeStDdaM6wVBj9Qbdcwx4idZ19povSeCrfyYEdLMXVOX5gwDQmBKKKzywSMFfBFnii1cFBu21w3K4DSUOg; pinId=nzmj4luAP03OFngxbbXICbV9-x-f3wj7; pin=jd_69c372950bd10; unick=jd_182812qhd; _tp=u0K3OXrdBjvrMoQbot5XifdeabmwvzEfn%2FsiMyYP8%2BA%3D; _pst=jd_69c372950bd10; PCSYCityID=1960; autoOpenApp_downCloseDate_auto=1514187564161_72000000; M_Identification=2a9f39c22a836fee_84f939403831549a7274de509c0369ee; M_Identification_abtest=20171213091508105_67450289; hmc_mt=1514192770917; mobilev=html5; USER_FLAG_CHECK=65beb613601820547cc75a51def23653; __jdu=1564110576; 3AB9D23F7A4B3C9B=7DB5RPKFAHXFJSWICDC2JPTMLNEMJRGKVLBZAKCD7KLZBAC3RFJH6IEIGM247MW5LR5CGKP3YWDBSUEJF3CXLTJVCE; warehistory=\"5674648,12947920925,5417706,12716670464,781118,10116213453,4279601,18165506260,10695987105,21561659348,22612679236,22615408452,1026682082,14223835722,10117070412,1022971679,48182,1158932,4865226,4586850,\"; monitor_count=12; __jda=122270672.1564110576.1509496641.1514187563.1514248568.82; __jdb=122270672.18.1564110576|82.1514248568; __jdc=122270672; mba_muid=1564110576; mba_sid=15142507322794503848299444759.11; sid=6fff94273e6376901679e5fb4b66e2e6");
        httpGet.setHeader("origin", "https://item.m.jd.com");
        httpGet.setHeader("referer", "https://item.m.jd.com/ware/view.action?wareId=" + productId);
        String result = getResult(httpGet);
        return result;
    }

    //==============5.京东移动端店铺评论json源码=====================
    public String jsonSource_move_com(String url, String productId) {
        HttpPost post = new HttpPost(url);
        //设置头部信息进行浏览器模拟行为
        setHeaderConverter.jdSetHeader_move1(post);
        post.setHeader("Cookie", "JAMCookie=true; abtest=20170905110659526_42; whwswswws=%2FlrRBwo1zyJg31qzs8SpCwuFBCOuk%2FGEhJP3OVjm0oldO3%2B2sp7C1Q%3D%3D; ipLoc-djd=1-72-2799-0; user-key=c48d2307-d3eb-4287-9b1f-20cf01cc003d; ipLocation=%u5317%u4EAC; __guid=178504572.3678537599949368300.1512530928236.52; webp=1; m_uuid_new=3BFAE0F6909FA669A5A5FF147ADB22CD; shshshfpa=419a6ae1-3efc-baa5-9b51-34a9fb32ea90-1513156518; cn=0; __jdv=122270672|direct|-|none|-|1513840336595; sc_width=1440; visitkey=53588244041588306; retina=0; cid=3; __wga=1513905754888.1513905746591.1513905746591.1513905746591.2.1; shshshfp=fc044e1aa1d67634d5f3375ee0bd0c97; shshshfpb=0c743ae265589457438c6c9612254d88fb92e7f2c0d62bad15a30efa6e; mt_xid=V2_52007VwMWVVhYUlsbTx5sAWJRQlcOWFRGGUAbCRliARRVQVABWxtVSV1WbgERVV5YVwkXeRpdBWEfElFBW1FLH08SXQBsAxViX2hSah9PH1sNZgEWUW1YVF4b; _jrda=5; TrackID=1wtMoQLUugCDvFyy2ZTH6VvbJcZeStDdaM6wVBj9Qbdcwx4idZ19povSeCrfyYEdLMXVOX5gwDQmBKKKzywSMFfBFnii1cFBu21w3K4DSUOg; pinId=nzmj4luAP03OFngxbbXICbV9-x-f3wj7; pin=jd_69c372950bd10; unick=jd_182812qhd; _tp=u0K3OXrdBjvrMoQbot5XifdeabmwvzEfn%2FsiMyYP8%2BA%3D; _pst=jd_69c372950bd10; PCSYCityID=1960; autoOpenApp_downCloseDate_auto=1514187564161_72000000; M_Identification=2a9f39c22a836fee_84f939403831549a7274de509c0369ee; M_Identification_abtest=20171213091508105_67450289; hmc_mt=1514192770917; mobilev=html5; USER_FLAG_CHECK=65beb613601820547cc75a51def23653; __jdu=1564110576; 3AB9D23F7A4B3C9B=7DB5RPKFAHXFJSWICDC2JPTMLNEMJRGKVLBZAKCD7KLZBAC3RFJH6IEIGM247MW5LR5CGKP3YWDBSUEJF3CXLTJVCE; warehistory=\"5674648,12947920925,5417706,12716670464,781118,10116213453,4279601,18165506260,10695987105,21561659348,22612679236,22615408452,1026682082,14223835722,10117070412,1022971679,48182,1158932,4865226,4586850,\"; monitor_count=12; __jda=122270672.1564110576.1509496641.1514187563.1514248568.82; __jdb=122270672.18.1564110576|82.1514248568; __jdc=122270672; mba_muid=1564110576; mba_sid=15142507322794503848299444759.11; sid=6fff94273e6376901679e5fb4b66e2e6");
        post.setHeader("origin", "https://item.m.jd.com");
        post.setHeader("referer", "https://item.m.jd.com/ware/view.action?wareId=" + productId);
        String result = getResult(post);
        return result;
    }

    //*****************************************************************************************************
    //==============6.京东pc端List<店铺信息>=====================
    public String jsonSource_Referer(String url) {
        HttpGet httpGet = new HttpGet(url);
        //设置头部信息进行浏览器模拟行为
        setHeaderConverter.jdSetHeader(httpGet);
        //必须加“Referer”
        httpGet.setHeader("Referer", "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E6%89%8B%E6%9C%BA&page=1&s=1&click=0");
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
                responseStr = EntityUtils.toString(response.getEntity(), "gbk");
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
