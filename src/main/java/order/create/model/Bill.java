package order.create.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 票据类
 */
@Data
public class Bill {
    /**
     * 票据ID
     */
    private Long billId;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 票据类型：采购：1；销售：-1；默认：0
     */
    private Integer type;
    /**
     * 对应票据生成的订单信息
     */
    private Order order;
}
