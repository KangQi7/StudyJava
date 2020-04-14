package beanutils.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author KangQi
 * @Date 2020/3/25 14:43
 * @Version 1.0
 */
@Data
public class OrderInfo {
    /**
     * 订单编号
     */
    private String orderSn;
    /**
     * 店铺ID
     */
    private String shopId;
    /**
     * 送货入户并安装 单位：元
     */
    private BigDecimal deliveryInstallValue;
    /**
     * 送货入户费用 单位：元
     */
    private BigDecimal deliveryHomeValue;
    /**
     * 上门安装费用 单位：元
     */
    private BigDecimal homeInstallValue;
    /**
     * 成交状态：0：未成交、1：已成交、2：已取消
     */
    private Integer confirmStatus;
    /**
     * 成团状态：0：拼团中、1：已成团、2：团失败
     */
    private Integer groupStatus;
    /**
     * 定金订单信息 ，非定金订单该字段为null
     * json
     */
    private String stepOrderInfo;
    /**
     * 订单类型 0-普通订单、1-定金订单
     */
    private Integer tradeType;
    /**
     * 仓库信息
     * json
     */
    private String orderDepotInfo;
    /**
     * 支付时间
     */
    private Date payTime;
    /**
     * 全国联保，1:是，0:否
     */
    private Integer supportNationwideWarranty;
    /**
     * 退货包运费，1:是，0:否
     */
    private Integer returnFreightPayer;
    /**
     * 只换不修，1:是，0:否
     */
    private Integer onlySupportReplace;
    /**
     * 卡号信息列表
     * json
     */
    private List<OrderListGetResponseOrderListItemCardInfoListItem> cardInfoList;
    /**
     * 送货入户并安装服务 0-不支持送货，1-送货入户不安装，2-送货入户并安装
     */
    private Integer homeDeliveryType;
    /**
     * 是否顺丰包邮 1表示是 0表示否
     */
    private Integer freeSf;
    /**
     * 售后状态 0：无售后 2：买家申请退款，待商家处理 3：退货退款，待商家处理 4：商家同意退款，退款中 5：平台同意退款，退款中 6：驳回退款， 待买家处理 7：已同意退货退款,待用户发货 8：平台处理中 9：平台拒 绝退款，退款关闭 10：退款成功 11：买家撤销 12：买家逾期未处 理，退款失败 13：买家逾期，超过有效期 14 : 换货补寄待商家处理 15:换货补寄待用户处理 16:换货补寄成功 17:换货补寄失败 18:换货补寄待用户确认完成
     */
    private Integer afterSalesStatus;
    /**
     * 预售时间
     */
    private Date preSaleTime;
    /**
     * 是否为预售商品 1表示是 0表示否
     */
    private Integer isPreSale;
    /**
     * 发票申请,1代表有 0代表无
     */
    private Integer invoice_status;
    /**
     * 买家留言信息
     */
    private String buyerMemo;
    /**
     * 支付申报订单号（多多国际清关请使用此字段，单号以XP开头）
     */
    private String innerTransactionId;
    /**
     * 商品一级分类
     */
    private Long catId1;
    /**
     * 商品二级分类
     */
    private Long catId2;
    /**
     * 商品三级分类
     */
    private Long catId3;
    /**
     * 商品四级分类
     */
    private Long catId4;
    /**
     * 缺货处理状态 -1:无缺货处理 0: 缺货待处理 1缺货已处理
     */
    private Integer stockOutHandleStatus;
    /**
     * 是否缺货 0-无缺货处理 1： 有缺货处理
     */
    private Integer isStockOut;
    /**
     * 国家或地区编码
     */
    private Integer countryId;
    /**
     * 省份编码
     */
    private Integer provinceId;
    /**
     * 城市编码
     */
    private Integer cityId;
    /**
     * 区县编码
     */
    private String townId;
    /**
     * 确认收货时间
     */
    private Date receiveTime;
    /**
     * 成交时间
     */
    private Date confirmTime;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 收件地国家或地区
     */
    private String country;
    /**
     * 收件地省份
     */
    private String province;
    /**
     * 收件地城市
     */
    private String city;
    /**
     * 收件地区县
     */
    private String town;
    /**
     * 收件详细地址
     */
    private String address;
    /**
     * 收件人姓名
     */
    private String receiverName;
    /**
     * 收件人电话，仅订单状态为待发货状态下返回明文，其他状态下返回脱敏手机号，例如“1387677****”
     */
    private String receiverPhone;
    /**
     * 支付金额（元）支付金额=商品金额-折扣金额+邮费
     */
    private BigDecimal payAmount;
    /**
     * 商品金额（元）商品金额=商品销售价格*商品数量-改价金额（接口暂无该字段）
     */
    private BigDecimal goodsAmount;
    /**
     * 折扣金额（元）折扣金额=平台优惠+商家优惠+团长免单优惠金额
     */
    private BigDecimal discountAmount;
    /**
     * 支付单号
     */
    private String payNo;
    /**
     * 邮费
     */
    private BigDecimal postage;
    /**
     * 支付方式，枚举值：QQ,WEIXIN,ALIPAY,LIANLIANPAY
     */
    private String payType;
    /**
     * 快递公司编号
     */
    private Long logisticsId;
    /**
     * 快递运单号
     */
    private String trackingNumber;
    /**
     * 发货时间
     */
    private Date shippingTime;
    /**
     * 发货状态，枚举值：1：待发货，2：已发货待签收，3：已签收
     */
    private Integer orderStatus;
    /**
     * 是否抽奖订单，1-非抽奖订单，2-抽奖订单
     */
    private Integer isLuckyFlag;
    /**
     * 退款状态，枚举值：1：无售后或售后关闭，2：售后处理中，3：退款中，4： 退款成功
     */
    private Integer refundStatus;
    /**
     * 订单最近一次更新时间
     */
    private Date updatedAt;
    /**
     * 订单承诺发货时间
     */
    private Date lastShipTime;
    /**
     * 商家订单备注
     */
    private String remark;
    /**
     * 订单中商品sku列表
     * json
     */
    private String itemList;
    /**
     * 平台优惠金额
     */
    private BigDecimal platformDiscount;
    /**
     * 店铺优惠金额
     */
    private BigDecimal sellerDiscount;
    /**
     * 团长免单优惠金额，只在团长免单活动中才会返回优惠金额
     */
    private BigDecimal capitalFreeDiscount;
    /**
     * 收件人地址，不拼接省市区
     */
    private String receiverAddress;
    /**
     * 是否门店自提，1-是，0-否
     */
    private Integer selfContained;
    /**
     * 风控订单状态(0-正常订单， 1-风控中订单)
     */
    private Integer riskControlStatus;
    /**
     * 门店信息
     * json
     */
    private String storeInfo;

    @Data
    public static class OrderListGetResponseOrderListItemCardInfoListItem{
        private String cardNo;
        private Double maskPassword;
    }
}
