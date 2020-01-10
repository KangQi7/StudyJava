package jsoup.test;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import jsoup.test.skuQuestion.JdSkuQuestionModel;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestJSON {
    public static void jsonStrToJava(String jsonStr) {
        //1、使用JSONObject
        JdSkuQuestionModel jsonObject = JSONObject.parseObject(jsonStr, JdSkuQuestionModel.class);

        System.out.println(jsonObject.getTotalItem());
    }

    public static String regexPromotion(String sourceCode) {
        String regex = "\\((.+?)}\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sourceCode);
        if (matcher.find()) {
            String promotion = matcher.group(1);
            promotion += "}";
            return promotion;
        }
        return "";
    }

    @Test
    public void jsonStringToObject() {
        String jsonStr = "{\"apiMethod\":\"jingdong.pop.order.search\"," +
                "\"appJsonParams\":\"{\\\"dateType\\\":0,\\\"end_date\\\":\\\"2019-12-06 00:00:00\\\",\\\"optional_fields\\\":\\\"orderId,itemInfoList,consigneeInfo,invoiceInfo,invoiceCode,vatInfo,orderSellerPrice,orderPayment,orderEndTime,paymentConfirmTime,taxFee,sellerDiscount,orderTotalPrice,payType,orderState,orderRemark,venderRemark,orderStartTime,waybill,logisticsId,idSopShipmenttype,pin\\\",\\\"order_state\\\":\\\"FINISHED_L\\\",\\\"page\\\":\\\"1\\\",\\\"page_size\\\":\\\"10\\\",\\\"sortType\\\":0,\\\"start_date\\\":\\\"2019-12-05 00:00:00\\\"}\"," +
                "\"dateType\":0," +
                "\"endDate\":\"2019-12-06 00:00:00\"," +
                "\"optionalFields\":\"orderId,itemInfoList,consigneeInfo,invoiceInfo,invoiceCode,vatInfo,orderSellerPrice,orderPayment,orderEndTime,paymentConfirmTime,taxFee,sellerDiscount,orderTotalPrice,payType,orderState,orderRemark,venderRemark,orderStartTime,waybill,logisticsId,idSopShipmenttype,pin\"," +
                "\"orderState\":\"FINISHED_L\"," +
                "\"page\":\"1\"," +
                "\"pageSize\":\"10\"," +
                "\"responseClass\":\"com.jd.open.api.sdk.response.order.PopOrderSearchResponse\"," +
                "\"sortType\":0," +
                "\"startDate\":\"2019-12-05 00:00:00\"," +
                "\"sysParams\":{\"v\":\"2.0\",\"timestamp\":\"2019-12-26 09:57:32\"}," +
                "\"timestamp\":\"2019-12-26 09:57:32\"" +
                "}";

        String param = "{\"apiMethod\":\"jingdong.pop.order.get\",\"appJsonParams\":\"{\\\"optional_fields\\\":\\\"orderId,itemInfoList,consigneeInfo,invoiceEasyInfo,invoiceInfo,orderPayment,orderEndTime,paymentConfirmTime,taxFee,vatInfo,orderSellerPrice\\\",\\\"order_id\\\":106839092480,\\\"order_state\\\":null}\",\"optionalFields\":\"orderId,itemInfoList,consigneeInfo,invoiceEasyInfo,invoiceInfo,orderPayment,orderEndTime,paymentConfirmTime,taxFee,vatInfo,orderSellerPrice\",\"orderId\":108193779931,\"responseClass\":\"com.jd.open.api.sdk.response.order.PopOrderGetResponse\",\"sysParams\":{\"v\":\"2.0\",\"timestamp\":\"2019-12-27 09:35:56\"},\"timestamp\":\"2019-12-27 09:35:56\"}";

        try {
            JSONObject obj = JSONObject.parseObject(jsonStr);
            System.out.println(obj.size());
        } catch (JSONException e) {
            System.out.println("fail");
        }
    }
}
