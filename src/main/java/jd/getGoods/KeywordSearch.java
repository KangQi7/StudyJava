package jd.getGoods;

import lombok.Data;

/**
 * @Author KangQi
 * @Date 2020/1/14 9:39
 * @Version 1.0
 */
@Data
public class KeywordSearch {
    //产品SKUID
    private String skuID;
    // 产品店铺ID
    private String shopID;
    //产品店铺相关信息
    private SearchKeywordShop shop;
    //产品名称
    private String productName;
    //产品图片url
    private String picUrl;
    //产品url
    private String proUrl ;
    //产品价格
    private String price;
    //产品Pid
    private String pid;
}
