package jdjson.test;

import org.junit.Test;

public class TestGetJdInfo {
    @Test
    public void test(){
        JDSendGetUtil jdSendGetUtil = new JDSendGetUtil();

//        String shopid = "1000004123";
//        String url = "https://search.jd.com/shop_head.php?ids="+ shopid;
//        String result = jdSendGetUtil.jsonSource_Referer(url);

//        String url = "https://rms.shop.jd.com/json/pop/shopInfo.action?callback=jQuery6510986&ids=10173566%2C1000000127%2C166165%2C196522%2C646824%2C784932%2C672711%2C647334%2C647331%2C1000085868&_=1581320371123";
        String url = "https://rms.shop.jd.com/json/pop/shopInfo.action?ids=1000000693,1000000706&callback=jQuery7634135&_=1581406132549";
//        String url = "https://search.jd.com/shop_head.php?ids=10173566";
        //这2个地址只能获取venderId下的数据，不能获取shopId下的，所以还是不能满足条件

//        String url = "https://search.jd.com/Search?keyword=%E7%94%B5%E8%A7%86%E6%9F%9C&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E7%94%B5%E8%A7%86%E6%9F%9C&stock=1&page=1&s=1&click=0";


        //店铺数据获取
//        String url = "https://shop.m.jd.com/detail/detailInfo?shopId=674932";
//        String url = "https://shop.m.jd.com/index/getShopTemplate.json?shopId=674932";

        String result = jdSendGetUtil.pc_jsonSource(url);

//        String result = jdSendGetUtil.jsonSource_Referer(url);
        System.out.println(result);
    }
}
