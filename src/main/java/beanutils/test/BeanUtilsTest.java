package beanutils.test;

import beanutils.entity.OrderInfo;
import beanutils.entity.OrderListGetResponseOrderListItem;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author KangQi
 * @Date 2020/3/25 14:41
 * @Version 1.0
 */
public class BeanUtilsTest {
    @Test
    public void test(){
        List<OrderInfo> orderInfos = new ArrayList<>();
        OrderInfo orderInfo = new OrderInfo();
        List<OrderListGetResponseOrderListItem> orderListItems = new ArrayList<>();
        OrderListGetResponseOrderListItem orderListItem = new OrderListGetResponseOrderListItem();
        orderListItem.setOrderSn("askdha");
        OrderListGetResponseOrderListItem.OrderListGetResponseOrderListItemCardInfoListItem cardInfoListItem =
                new OrderListGetResponseOrderListItem.OrderListGetResponseOrderListItemCardInfoListItem();
        cardInfoListItem.setCardNo("ahjsd");
        cardInfoListItem.setMaskPassword(123.2);
        orderListItem.setCardInfoList(Arrays.asList(cardInfoListItem));
        orderListItems.add(orderListItem);

//        BeanUtils.copyProperties(orderInfo, orderListItem);

        orderInfos = copy(orderListItems, OrderInfo.class);
        orderInfos.stream().forEach(o -> o.setShopId("12"));
        System.out.println(orderInfos.get(0).getCardInfoList());
        System.out.println(orderInfos.get(0).getShopId());
    }

    /**
     * 从List<A> copy到List<B>
     * @param list List<B>
     * @param clazz B
     * @return List<B>
     */
    public static <T> List<T> copy(List<?> list,Class<T> clazz){
        String oldOb = JSON.toJSONString(list);
        return JSON.parseArray(oldOb, clazz);
    }
}
