package beanutils.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author KangQi
 * @Date 2020/3/25 14:44
 * @Version 1.0
 */
@Data
public class OrderListGetResponseOrderListItem {
    
    private Double deliveryInstallValue;
    
    private Double deliveryHomeValue;
    
    private Double homeInstallValue;
    
    private List<OrderListGetResponseOrderListItemCardInfoListItem> cardInfoList;
    
    private Integer freeSf;
    
    private Integer confirmStatus;
    
    private Integer groupStatus;
    
    private Integer returnFreightPayer;
    
    private Integer homeDeliveryType;
    
//    private PddOrderListGetResponse.OrderListGetResponseOrderListItemStepOrderInfo stepOrderInfo;
    
    private Integer tradeType;
    
    private String orderSn;
    
    private Double capitalFreeDiscount;
    
    private Double sellerDiscount;
    
    private Double platformDiscount;
    
//    private List<PddOrderListGetResponse.OrderListGetResponseOrderListItemItemListItem> itemList;
    
    private String remark;
    
    private String lastShipTime;
    
    private String updatedAt;
    
    private Integer refundStatus;
    
    private Integer isLuckyFlag;
    
    private Integer orderStatus;
    
    private String shippingTime;
    
    private String trackingNumber;
    
    private Long logisticsId;
    
    private String payType;
    
    private String payNo;
    
    private Double postage;
    
    private Double discountAmount;
    
    private Double goodsAmount;
    
    private Double payAmount;
    
    private String receiverPhone;
    
    private String address;
    
    private String town;
    
    private String city;
    
    private String province;
    
    private String country;
    
    private String createdTime;
    
    private String receiverName;
    
    private String confirmTime;
    
    private String receiveTime;
    
    private Integer townId;
    
    private Integer cityId;
    
    private Integer provinceId;
    
    private Integer countryId;
    
    private Integer isStockOut;
    
    private Integer stockOutHandleStatus;
    
    private Long catId4;
    
    private Long catId3;
    
    private Long catId2;
    
    private Long catId1;
    
    private String innerTransactionId;
    
    private String buyerMemo;
    
    private Integer invoiceStatus;
    
    private String preSaleTime;
    
    private Integer isPreSale;
    
    private Integer afterSalesStatus;
    
    private Integer onlySupportReplace;
    
    private Integer supportNationwideWarranty;
    
    private String payTime;
    
//    private PddOrderListGetResponse.OrderListGetResponseOrderListItemOrderDepotInfo orderDepotInfo;
    
    private String receiverAddress;
    
    private Integer selfContained;
    
    private Integer riskControlStatus;

    @Data
    public static class OrderListGetResponseOrderListItemCardInfoListItem{
        private String cardNo;
        private Double maskPassword;
    }
}
