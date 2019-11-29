package order.create;


import order.create.model.Bill;
import order.create.model.Order;
import sun.management.GcInfoBuilder;

import java.math.BigDecimal;
import java.util.*;

public class OrderGenerationTool {

    /**
     * 订单集合
     */
    private List<Order> orders;

    /**
     * 记录随机数的集合
     */
    private Set<Integer> set;

    /**
     * 最大商值
     */
    private BigDecimal maxQuotient = new BigDecimal("100");

    public void setMaxQuotient(BigDecimal maxQuotient) {
        this.maxQuotient = maxQuotient;
    }

    public BigDecimal getMaxQuotient() {
        return this.maxQuotient;
    }

    /**
     * 初始化各集合数据
     *
     * @param orders
     */
    public OrderGenerationTool(List<Order> orders) {
        set = new HashSet<>();
        this.orders = orders;
    }


    /**
     * 根据票据生成订单
     *
     * @param bills
     * @return
     */
    public List<Bill> generationOrdersByBills(List<Bill> bills) {

        for (Bill bill :
                bills) {
            Order order = null;
            //抽取订单并判断是否合适
            for (int i = 0; i < orders.size(); i++) {
                order = orderSelect();
                //判断是否符合计算条件
                if (orderPriceJudgment(bill.getAmount(), order.getAmount(), bill.getType())) {
                    break;
                }
            }

            if (order == null) {
                bill.setOrder(null);
            } else {
                //计算订单详情
                //1.详情各项数量具有最小公约数
                //2.解n元1次方程
            }
        }

        return bills;
    }

    /**
     * 随机抽取一个订单
     */
    private Order orderSelect() {
        int randomNum = 0;
        if (set.size() == orders.size()){
            set.clear();
        }
        while (true) {
            randomNum = RandomUtil.getRandomForIntegerBounded4(0, orders.size());
            if (!set.contains(randomNum)) {
                set.add(randomNum);
                break;
            }
        }

        return orders.get(randomNum);
    }

    private boolean orderPriceJudgment(BigDecimal totalAmount, BigDecimal orderAmount, Integer billType) {
        //求商，若商大于最大限制值，返回false
        BigDecimal quotient = totalAmount.divide(orderAmount, 0, BigDecimal.ROUND_DOWN);
        if (quotient.compareTo(maxQuotient) > 0) {
            return false;
        }

        //求余，若余数/订单金额，在价格浮动允许范围内，则返回true，反之返回false
        BigDecimal remainder = totalAmount.remainder(orderAmount);
        BigDecimal _remainder = orderAmount.subtract(remainder);

        //余数必须可整除商，否则无法完整分配到每项上
//        BigDecimal _remainder = cent.remainder(quotient);
        if (remainder.multiply(new BigDecimal("100")).remainder(quotient).compareTo(new BigDecimal("0")) != 0
        && _remainder.multiply(new BigDecimal("100")).remainder(quotient).compareTo(new BigDecimal("0")) != 0) {
            return false;
        }

        BigDecimal rq = remainder.divide(quotient);

        //余数和订单金额之比，需要满足价格浮动范围条件
        BigDecimal proportion = rq.divide(orderAmount, 2, BigDecimal.ROUND_HALF_UP);
        System.out.println(proportion);
        if ((billType.equals(-1) && proportion.compareTo(new BigDecimal("0.05")) <= 0)
                //销售订单允许价格上浮
                || (billType.equals(1) && proportion.compareTo(new BigDecimal("0.95")) >= 0)
                //采购订单允许价格下调
                || (billType.equals(0) && (proportion.compareTo(new BigDecimal("0.05")) <= 0
                || proportion.compareTo(new BigDecimal("0.95")) >= 0))) {
            return true;
        } else {
            return false;
        }
    }
}
