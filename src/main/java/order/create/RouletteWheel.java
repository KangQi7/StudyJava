package order.create;


import order.create.model.Order;

import java.util.List;
import java.util.Random;

public class RouletteWheel {

    /**
     * 待抽取订单集合
     */
    private List<Order> orders;

    private List<Integer> flags;

    /**
     * 随机抽取一个订单
     */
    public Order orderSelect(){
        return orders.get(0);
    }

}
