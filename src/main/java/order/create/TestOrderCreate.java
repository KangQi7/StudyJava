package order.create;

import order.create.model.Bill;
import order.create.model.Order;
import order.create.model.OrderDetail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试订单生成
 *
 * @Author kangqi
 * @Date 2019/11/29 9:33
 * @Version 1.0
 */
public class TestOrderCreate {
    public static void testOrderCreate(){
        List<Bill> bills = new ArrayList<>();
        Bill bill1 = new Bill();
        bill1.setAmount(new BigDecimal("5960.55"));
        bill1.setBillId(1L);
        bill1.setType(1);
        bills.add(bill1);

        List<Order> orders = new ArrayList<>();
        Order order1 = new Order();
        order1.setAmount(new BigDecimal("58.80"));
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setPrice(new BigDecimal("21.40"));
        orderDetail1.setNumber(2);
        orderDetails.add(orderDetail1);
        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setPrice(new BigDecimal("16.00"));
        orderDetail2.setNumber(1);
        orderDetails.add(orderDetail2);
        order1.setDetails(orderDetails);
        orders.add(order1);

        OrderGenerationTool orderGenerationTool = new OrderGenerationTool(orders);
        orderGenerationTool.setMaxQuotient(new BigDecimal("200"));

        bills = orderGenerationTool.generationOrdersByBills(bills);

        for (Bill bill:
                bills) {
            System.out.println(bill.getAmount());
        }
    }
}
