package webmagic.model;

import lombok.Data;

import java.util.List;

/**
 * 京东商品sku信息
 *
 * @Author KangQi
 * @Date 2020/2/20 15:45
 * @Version 1.0
 */
@Data
public class JdSkuInfo {
    //商品名
    private String productName;
    //商品ID（商品主键）
    private String skuID;
    //店铺名
    private String shopName;
    //商品所属店铺ID
    private String shopId;
    //价格
    private String minSellingPrice;
    //价格名称
    private String priceName;
    //库存（京东：有/无货，天猫：具体值）
    private String stockStatus;
    //月销量（天猫）
    private int salesVolume;
    //好评（京东）
    private int praise;
    //中评（京东）
    private int generalEvaluation;
    //差评（京东）
    private int reviews ;
    //总评论数（京东、天猫）
    private int commentCount;
    //活动对象数组（京东、天猫）
    private List<JdGoodsPromotion> data_ProductPromotions;
    //发货地
    private String supplyAddress;
    //店铺地址
    private String shopUrl;
    //商品主图（图片地址）
    private String picture;
    //嗮图数（京东、天猫）
    private int commentPictureCount;
    //日评论数（京东）
    private int commentOnTheDay;
    //日销量数（京东）
    private int salesOfDay;
    //店铺信息
    private JdShopInfo competeShop;
    //（平台标志，京东：0，天猫：1）
    private int platformFlg;
}
