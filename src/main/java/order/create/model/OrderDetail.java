package order.create.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetail {
    //商品单价
    BigDecimal price;

    //商品数量
    Integer number;
}
