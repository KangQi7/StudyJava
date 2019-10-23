package order.create.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Order {
    //订单总金额
    BigDecimal amount;

    //订单明细
    List<OrderDetail> details;
}


